package ru.stqa.aqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation () {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData("TestFN", "TestLN", "000000000"));
        app.getContactHelper().enterContactData();
    }
}
