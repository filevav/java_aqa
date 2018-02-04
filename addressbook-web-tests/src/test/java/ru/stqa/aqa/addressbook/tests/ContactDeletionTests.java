package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().editContact();
        app.getContactHelper().deleteContact();
    }

}