package tests;

import appmanager.HttpSession;
import model.MailMessage;
import model.UserData;
import model.Users;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class ChangeUserPasswordTest extends TestBase {

    @BeforeMethod
    public void StartMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangeUserPassword () throws IOException, MessagingException {
        HttpSession session = app.newSession();

        String userAdmin = properties.getProperty("web.adminLogin");
        String passwordAdmin = properties.getProperty("web.adminPassword");
        // login and check by UI
        app.registration().logIn(userAdmin, passwordAdmin);
        Assert.assertTrue(app.registration().checkLogin(userAdmin));

        String newPass = "password1";
        String userUser = changePassword(newPass);
        // login and check by Http
        Assert.assertTrue(session.login(userUser, newPass));
        Assert.assertTrue(session.isLoggedInAs(userUser));
    }

    private String changePassword(String password_new) throws IOException, MessagingException {
        app.registration().manageUserPage();
        // get only one's user parameters
        UserData userData = getFirstUserFromDB();
        Assert.assertFalse(userData.equals(null));

        System.out.println("first user is: " + userData);
        app.registration().selectUser(userData.getName());
        app.registration().resetPassword();
        // i always got only 1 (not 2) email after reset password
        List<MailMessage> mailMessages =  app.mail().waitForMail(1, 10000);
        String comfirmationLink = findComfirmationLink(mailMessages, userData.getEmail());
        System.out.println("link is: " + comfirmationLink);
        app.registration().finish(comfirmationLink, password_new);
        return userData.getName();
    }

    private UserData getFirstUserFromDB() {
        Connection conn = null;
        Users users = new Users();
        UserData firstUser = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker_1_2?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username, email, access_level from mantis_user_table");
            while (rs.next()) {
                users.add(new UserData().withName(rs.getString("username"))
                        .withEmail(rs.getString("email"))
                        .withAccess(rs.getInt("access_level")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        Assert.assertTrue(users.size()>1);
        Iterator<UserData> iterator = users.iterator();
        while (iterator.hasNext()) {
            firstUser = iterator.next();
            // user is not Admin
            if(firstUser.getAccess() == 25)
                break;
        }

        return firstUser;
    }

    private String findComfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
