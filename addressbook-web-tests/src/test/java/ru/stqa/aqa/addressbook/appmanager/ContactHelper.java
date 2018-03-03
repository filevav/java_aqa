package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.aqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.cssSelector("div.left:nth-child(8) > input:nth-child(1)"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void acceptAlert() {
        try {
            wd.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
        }
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("#maintable > tbody > tr:nth-child(2) > td:nth-child(7) > a > img"));
    }

    public void createContact(ContactData group, boolean b) {
        fillContactForm(group, b);
        submitContactData();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void gotoHomePage() {
        click(By.cssSelector("#nav > ul > li:nth-child(1) > a"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, null, lastName, null);
            contacts.add(contact);
        }
        return contacts;
    }
}