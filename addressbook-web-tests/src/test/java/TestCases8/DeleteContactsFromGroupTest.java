package TestCases8;

import TestCases1.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
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

public class DeleteContactsFromGroupTest extends TestBase {

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
    public void testDeleteContactsFromGroup(ContactData newContact) {
        app.goTo().home();
        // add (create) contacts with linked groups from .json file
        app.contact().create(newContact, false);

        Contacts before = app.db().contacts();
        Groups contactGroups = newContact.getGroups();

        String groupForUnLink = newContact.getGroups().iterator().next().getName();
        app.contact().UnLinkGroup(newContact.getFirstName(), groupForUnLink);

        Contacts after = app.db().contacts();
        Assert.assertFalse(after.equals(before));
    }
}
