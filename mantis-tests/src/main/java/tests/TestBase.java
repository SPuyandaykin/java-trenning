package tests;

import appmanager.ApplicationManager;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;


public class TestBase {

    protected Properties properties;
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");

        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/local.properties")));
    }

    public boolean isIssueOpen (int id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String userAdmin = properties.getProperty("web.adminLogin");
        String passwordAdmin = properties.getProperty("web.adminPassword");
        System.out.println("id is:" + id);
        IssueData issueData = mc.mc_issue_get(userAdmin, passwordAdmin, BigInteger.valueOf(id));
        String status = issueData.getStatus().getName();
        if(status.equals("resolved")|| status.equals("resolved"))
            return false;

        return true;
    }
    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {

        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(properties.getProperty("web.port")));
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            System.out.println("skipped is:" + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

}
