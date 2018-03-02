package ru.stqa.aqa.addressbook.model;

public class ContactData {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String mobile;

    public ContactData (String firstname, String lastname, String group) {
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = group;
    }
    public ContactData (String id, String firstname, String lastname, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = group;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getGroup() {
        return mobile;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
