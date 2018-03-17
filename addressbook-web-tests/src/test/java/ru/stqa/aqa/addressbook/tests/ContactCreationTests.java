package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;
import ru.stqa.aqa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation () {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/nightlights.jpeg");
        ContactData contact = new ContactData()
                .withFirstName("TestFN")
                .withLastName("TestLN")
                .withMobilePhone("222")
                .withAddress("ul. Pi")
                .withPhoto(photo);
        app.contact().create(contact, true);
        app.contact().homePage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/nightlights.jpeg");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());

    }

    @Test
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
}