package Tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

public class BugifyIntegrationTest extends TestBase{

    @Test
    public void testBugifyIntegration () throws IOException {

        Set<Issue> Issues = getIssues();
        System.out.println("issues is: " + Issues);
        System.out.println("size is: " + Issues.size());
        Iterator<Issue> iterator = Issues.iterator();
        for(int i=0; i< Issues.size(); i++){
            int id = iterator.next().getId();
            System.out.println("id is: " + id);
            skipIfNotFixed(id);
        }
    }


}
