package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("EditFN", "EditLN", "1111111111"));
        app.getContactHelper().updateContact();
    }

}
