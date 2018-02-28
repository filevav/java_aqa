package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

    public void submitContactData() {
        click(By.name("submit"));
    }

    public void initContactModification() {
        click(By.cssSelector("tr:nth-child(3) img[alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.cssSelector("div.left:nth-child(8) > input:nth-child(1)"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void acceptAlert() {
        try {
            wd.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
        }
    }
}