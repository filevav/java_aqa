package ru.stqa.aqa.addressbook.model;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String mobile;

    public ContactData (String firstname, String lastname, String group) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = group;

    }
    public ContactData (int id, String firstname, String lastname, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
