package TestCases8;

import TestCases1.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactsToGroupTest extends TestBase{

    private String newGroupName = "New group";

    @BeforeTest
    public void ClearContacts(){
        // delete all UI contacts
        app.contact().DeleteAllContact();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        try(BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/contacts-groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJSON")
    public void testAddContactsToGroup(ContactData newContact) {
        app.goTo().home();
        // add (create) contacts with linked groups from .json file
        app.contact().create(newContact, false);

        Contacts before = app.db().contacts();

        // add contact to group
        Groups allGroups = app.db().groups();
        Groups contactGroups = newContact.getGroups();

        if(allGroups.equals(contactGroups)) {
            // current contact is linked to all groups from database
            System.out.println("GROUPS ARE SAME");
            app.group().page();
            // create new group
            app.group().create(new GroupData().withName(newGroupName)
                    .withHeader(newGroupName + " header").withFooter(newGroupName + " footer"));
            app.contact().ReturnToMainPage();
            // link contact to new group
            app.contact().SelectGroup(newContact.getFirstName(), newGroupName);
        } else {
            // there are one (or more) unlinked group(s)
            System.out.println("GROUPS ARE DIFFERENT");
            // find group is not linked to current contact
            String nameGroup = allGroups.FindNotAddedGroup(contactGroups);
            System.out.println("not found group is : " + nameGroup);
            // link contact to unlinked group
            app.contact().SelectGroup(newContact.getFirstName(), nameGroup);
        }

        Contacts after = app.db().contacts();
        Assert.assertFalse(after.equals(before));
    }

    @AfterTest
    public void ClearNewAddedGroup(){
        // clear groups list from new created one
//        app.group().DeleteGroupNamed(newGroupName);
    }

}

