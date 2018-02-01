package model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private ContactNameData contactName;
    private ContactPhoneData contactPhone;

/*    public ContactData (int id, ContactNameData contactName, ContactPhoneData contactPhone) {
        this.id = id;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(contactName, that.contactName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, contactName);
    }

/*    public ContactData (ContactNameData contactName, ContactPhoneData contactPhone) {
        this.id = 0;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }*/

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", contactName=" + contactName +
                ", contactPhone=" + contactPhone +
                '}';
    }
    public int getId() { return id; }

    public ContactNameData getContactName() {
        return contactName;
    }

    public ContactPhoneData getContactPhone() {
        return contactPhone;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withContactPhone(ContactPhoneData contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public ContactData withContactName(ContactNameData contactName) {
        this.contactName = contactName;
        return this;
    }
}
