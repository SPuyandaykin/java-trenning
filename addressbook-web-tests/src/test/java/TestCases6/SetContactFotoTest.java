package TestCases6;

import TestCases1.TestBase;
import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SetContactFotoTest extends TestBase  {
    @Test
    public void testSetContactFoto() {
        File photo = new File("src/test/resources/face.jpg");

        app.goTo().home();
        Contacts before = app.contact().all();
        ContactNameData contactName = new ContactNameData("sergey"+System.currentTimeMillis(),
                "ivanov", "Java Corporation").withPhoto(photo);
        ContactPhoneData contactPhone = new ContactPhoneData ("+79031111111", "+79042222222");
        ContactData contact = new ContactData().withContactName(contactName).withContactPhone(contactPhone);
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
/*
    @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println("path is: "+currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/face.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }*/
}
