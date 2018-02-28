package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation () {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("TestFN", "TestLN", "test1"), true);
    }
}