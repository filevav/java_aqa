package ru.stqa.aqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.aqa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.getContactHelper().isThereAContact()) {
            app.goTo().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("TestFN", "TestLN", "test1"), true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.goTo().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("TestFN", "TestLN", "test1"), true);
            app.getContactHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() -1);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}