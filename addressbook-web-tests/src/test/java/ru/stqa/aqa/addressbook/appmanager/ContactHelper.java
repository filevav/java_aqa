package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.aqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void enterContactData() {
        click(By.name("submit"));
    }

    public void editContact() {
        click(By.cssSelector("tr:nth-child(3) img[alt='Edit']"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.cssSelector("input[type=\"submit\"]:nth-child(2)"));
    }
}