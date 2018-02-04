package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.aqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("mobile"), contactData.getMobile());
    }

    public void enterContactData() {
        click(By.name("submit"));
    }

    public void editContact() {
        click(By.cssSelector("#maintable > tbody > tr:nth-child(2) > td:nth-child(8) > a"));
    }

    public void updateContact() {
        click(By.name("update"));
    }
}
