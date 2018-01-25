package model;

import java.util.Objects;

public class ContactData {
    private int id;
    private ContactNameData contactName;
    private ContactPhoneData contactPhone;

    public ContactData (int id, ContactNameData contactName, ContactPhoneData contactPhone) {
        this.id = id;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

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

    public ContactData (ContactNameData contactName, ContactPhoneData contactPhone) {
        this.id = 0;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", contactName=" + contactName +
                ", contactPhone=" + contactPhone +
                '}';
    }

    public ContactNameData getContactName() {
        return contactName;
    }

    public ContactPhoneData getContactPhone() {
        return contactPhone;
    }


}
