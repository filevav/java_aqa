package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;
import ru.stqa.aqa.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/nightlights.jpeg");
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData()
                    .withFirstName(split[0])
                    .withLastName(split[1])
                    .withMobilePhone(split[2])
                    .withAddress(split[3])
                    .withPhoto(photo)});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation (ContactData contact) {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        app.contact().create(contact, true);
        app.contact().homePage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testBadContactCreation () {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withFirstName("TestFN'")
                .withLastName("TestLN")
                .withMobilePhone("222");
        app.contact().create(contact, true);
        app.contact().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/nightlights.jpeg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());

    }
}