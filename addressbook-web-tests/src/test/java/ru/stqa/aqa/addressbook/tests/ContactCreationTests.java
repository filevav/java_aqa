package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;
import ru.stqa.aqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation () {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withFirstName("TestFN")
                .withLastName("TestLN")
                .withMobilePhone("222")
                .withAddress("ul. Pi");
        app.contact().create(contact, true);
        app.contact().homePage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt()))));
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