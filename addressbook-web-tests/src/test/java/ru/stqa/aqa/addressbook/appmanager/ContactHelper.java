package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.aqa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());

        if (creation) {
            if (contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
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

    public void selectContactById(int id) {
        WebElement checkBox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
        WebElement row = checkBox.findElement(By.xpath("../.."));
        row.findElements(By.tagName("td")).get(7).click();
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

    public void create(ContactData group, boolean b) {
        fillContactForm(group, b);
        submitContactData();
    }

    public void modify(ContactData contact, boolean creation) {
        selectContactById(contact.getId());
        fillContactForm(contact, creation);
        click(By.name("update"));
        homePage();
        }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        acceptAlert();
        homePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void homePage() {
        click(By.cssSelector("#nav > ul > li:nth-child(1) > a"));
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withLastName(lastName));
        }
        return contacts;
    }


}