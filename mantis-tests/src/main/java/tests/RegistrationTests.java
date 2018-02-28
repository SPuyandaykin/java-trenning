package tests;

import model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void StartMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration () throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = "user" + now;
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);
        String comfirmationLink = findComfirmationLink(mailMessages, email);
        app.registration().finish(comfirmationLink, password);
        Assert.assertTrue(app.newSession().login(user,password));
    }

    private String findComfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
