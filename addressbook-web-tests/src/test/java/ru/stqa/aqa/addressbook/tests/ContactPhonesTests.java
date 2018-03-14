package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhonesTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditFrom = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditFrom.getHomePhone()));
        assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditFrom.getMobilePhone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditFrom.getWorkPhone()));
    }
}