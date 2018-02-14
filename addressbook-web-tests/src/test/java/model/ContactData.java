package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;
    @Expose
    private ContactNameData contactName;
    @Expose
    private ContactPhoneData contactPhone;


    @Override
    // comparing without "company" field of "contactName" object
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(contactPhone, that.contactPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, contactName, contactPhone);
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
