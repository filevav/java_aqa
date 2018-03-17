package ru.stqa.aqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.aqa.addressbook.model.ContactData;
import ru.stqa.aqa.addressbook.model.Contacts;

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
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

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
        click(By.cssSelector("input[value='Delete']"));
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
        contactCache = null;
    }

    public void modify(ContactData contact, boolean creation) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, creation);
        click(By.name("update"));
        contactCache = null;
        homePage();
        }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
        acceptAlert();
        homePage();
    }

    public void homePage() {
        click(By.cssSelector("#nav > ul > li:nth-child(1) > a"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("[name=entry]")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.cssSelector("td")).get(1).getText();
            String firstName = element.findElements(By.cssSelector("td")).get(2).getText();
            String mobile = element.findElements(By.cssSelector("td")).get(5).getText();
            String address = element.findElements(By.cssSelector("td")).get(3).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withMobilePhone(mobile)
                    .withAddress(address)
                    .withGroup(null));
        }
        return new Contacts(contactCache);
    }


    public Set<ContactData> all1 () {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        //WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        //WebElement row = checkbox.findElement(By.xpath("./../.."));
        //List<WebElement> cells = row.findElements(By.tagName("td"));
        //cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}