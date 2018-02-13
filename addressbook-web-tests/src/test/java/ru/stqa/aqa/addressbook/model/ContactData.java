package ru.stqa.aqa.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String mobile;

    public ContactData (String firstname, String lastname, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = group;
    }

    public String getFirstName() {return firstname;}
    public String getLastName() {return lastname;}
    public String getGroup() {
        return mobile;
    }
}
