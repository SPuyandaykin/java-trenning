package tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class BugStatusTest extends TestBase{

    @Test
    public void testBugStatus() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        String userAdmin = properties.getProperty("web.adminLogin");
        String passwordAdmin = properties.getProperty("web.adminPassword");
        ProjectData[] projects = mc.mc_projects_get_user_accessible(userAdmin, passwordAdmin);
        //get all issues from first project
        IssueData[] allIssues = mc.mc_project_get_issues(userAdmin, passwordAdmin, projects[0].getId()
                , BigInteger.valueOf(1), BigInteger.valueOf(100));
        for(int i=0; i< allIssues.length; i++){
            // check "not-closed" status for issues
            skipIfNotFixed(allIssues[i].getId().intValue());
        }
    }
}
