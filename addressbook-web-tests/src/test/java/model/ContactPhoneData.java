package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import unilities.StringUtilities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name="addressbook")
public class ContactPhoneData {
    @Expose
    @Column(name="home")
    @Type(type = "text")
    private String phoneHome = "";

    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String phoneMobile = "";

    @Expose
    @Column(name="work")
    @Type(type = "text")
    private String workMobile = "";

    @Expose
    @Column(name="email")
    @Type(type = "text")
    private String email = "";

    @Expose
    @Column(name="address")
    @Type(type = "text")
    private String address = "";

    @XStreamOmitField
    @Transient
    private String allPhones = "";

    public ContactPhoneData() {
        phoneHome = "";
        phoneMobile = "";
        workMobile = "";
        email = "";
        address = "";
        allPhones = "";
    }

    public ContactPhoneData(String phoneHome, String phoneMobile) {
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.allPhones = mergePhones(this);
    }

    public ContactPhoneData(String phoneHome, String phoneMobile, String workMobile, String email, String address) {
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.workMobile = workMobile;
        this.email = email;
        this.address = address;
        this.allPhones = mergePhones(this);
    }

    @Override
    public String toString() {
        return "ContactPhoneData{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", allPhones='" + allPhones + '\'' +
                '}';
    }

    public ContactPhoneData(ContactPhoneData contactPhone) {
        this.phoneHome = contactPhone.phoneHome;
        this.phoneMobile = contactPhone.phoneMobile;
        this.workMobile = contactPhone.workMobile;
        this.email = contactPhone.email;
        this.address = contactPhone.address;
        this.allPhones = mergePhones(contactPhone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPhoneData that = (ContactPhoneData) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(allPhones, that.allPhones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, address, allPhones);
    }

    public String mergePhones (ContactPhoneData contactPhone){
        StringUtilities strUtility = new StringUtilities();
        return Arrays.asList(contactPhone.phoneHome, contactPhone.phoneMobile,contactPhone.workMobile)
                .stream().filter((s) -> ! s.equals(""))
                .map(strUtility::cleanPhone)
                .collect(Collectors.joining("\n"));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAllPhones(String allPhones){
        this.allPhones = allPhones;
    }

    public String getPhoneHome() { return phoneHome; }

    public String getPhoneMobile() { return phoneMobile; }

    public String getWorkMobile() { return workMobile; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }
}
