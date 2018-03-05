package Tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Set;


public class TestBase {

    protected Properties properties;

    @BeforeSuite
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/local.properties")));
    }

    public boolean isIssueOpen (int id) throws IOException {
        String status = getIssueStatus(id);
        System.out.println("status is: " + status);
        if(status.equals("Open"))
            return true;

        return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            System.out.println("skipped is:" + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public Set<Issue> getIssues() throws IOException {
        // limit list to 20 issues
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?limit=3"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private String getIssueStatus (int id) throws IOException {
        String request = "http://demo.bugify.com/api/issues/"+id+".json";
        System.out.println(request);
        String json = getExecutor().execute(Request.Get(request)).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return issues.getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    }

}
