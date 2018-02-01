package TestCases5;

import TestCases1.TestBase;
import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.Contacts;
import org.testng.annotations.Test;

public class ContactInformationTest extends TestBase {

    @Test
    public void testContactInformation() {
        Contacts before = app.contact().all();
        ContactNameData contactName = new ContactNameData("sergey"+System.currentTimeMillis(),
                "ivanov", "Java Corporation");
        ContactPhoneData contactPhone = new ContactPhoneData ("+79031111111", "+79042222222",
                "+79053333333", "ya@yandex.ru", "Moscow, Red square");
        ContactData contact = new ContactData().withContactName(contactName).withContactPhone(contactPhone);
        app.contact().create(contact);
    }
}
