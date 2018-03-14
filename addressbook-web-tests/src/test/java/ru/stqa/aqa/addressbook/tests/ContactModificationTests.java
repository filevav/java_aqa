package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;
import ru.stqa.aqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstName("TestFN").withLastName("TestLN"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("EditedFN").withLastName("EditedLN");
        app.contact().modify(contact, false);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}