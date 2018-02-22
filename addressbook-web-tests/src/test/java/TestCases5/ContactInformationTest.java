package TestCases5;

import TestCases1.TestBase;
import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if(!app.contact().size()) {
            ContactNameData contactName = new ContactNameData("sergey"+System.currentTimeMillis(),
                    "ivanov", "Java Corporation");
            ContactPhoneData contactPhone = new ContactPhoneData ("+7(903)1111111", "",
                    "+7905-333-33-33", "ya@yandex.ru", "Moscow, Red square");
            ContactData contact = new ContactData().withContactName(contactName).withContactPhone(contactPhone);
            app.contact().create(contact, true);
        }
    }

    @Test
    public void testContactInformation() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromForm = app.contact().infoFromContactForm(contact);

        assertThat(contact, equalTo(contactFromForm));
    }

}
