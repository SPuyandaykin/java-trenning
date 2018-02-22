package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import unilities.StringUtilities;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name="firstname")
    private String firstName;

    @Expose
    @Column(name="lastname")
    private String lastName;

    @Expose
    @Column(name="company")
    private String company;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(company, that.company) &&
                Objects.equals(phoneHome, that.phoneHome) &&
                Objects.equals(phoneMobile, that.phoneMobile) &&
                Objects.equals(workMobile, that.workMobile) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(groups, that.groups) &&
                Objects.equals(allPhones, that.allPhones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, company, phoneHome, phoneMobile, workMobile, email, address, groups, allPhones);
    }

    @Expose
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups",
            joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @XStreamOmitField
    @Transient
    private String allPhones = "";

    public Groups getGroups() {
        return new Groups(groups);
    }


    @Column(name="photo")
    @Type(type = "text")
    private String photo;

    @XStreamOmitField
    @Transient
    private ContactNameData contactName;

    @XStreamOmitField
    @Transient
    private ContactPhoneData contactPhone;

    public int getId() { return id; }

    public ContactNameData getContactName() {
        return contactName;
    }

    public ContactPhoneData getContactPhone() {
        return contactPhone;
    }

    public void setContactName(ContactNameData contactName) {
        this.contactName = contactName;
    }

    public void setAllPhones(String allPhones) {
        this.allPhones = allPhones;
    }

    public String mergePhones (ContactPhoneData contactPhone){
        StringUtilities strUtility = new StringUtilities();
        return Arrays.asList(phoneHome, phoneMobile, workMobile)
                .stream().filter((s) -> ! s.equals(""))
                .map(strUtility::cleanPhone)
                .collect(Collectors.joining("\n"));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getWorkMobile() {
        return workMobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setContactPhone(ContactPhoneData contactPhone) {
        this.contactPhone = contactPhone;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withContactPhone(ContactPhoneData contactPhone) {
        phoneHome = contactPhone.getPhoneHome();
        phoneMobile = contactPhone.getPhoneMobile();
        workMobile = contactPhone.getWorkMobile();
        email = contactPhone.getEmail();
        address = contactPhone.getAddress();
        this.contactPhone = contactPhone;
        return this;
    }

    public ContactData withContactName(ContactNameData contactName) {
        firstName = contactName.getFirstName();
        lastName = contactName.getLastName();
        company = contactName.getCompany();
        this.contactName = contactName;
        return this;
    }

    public ContactData withFirstName(String name) {
        this.firstName = name;
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public ContactData addGroups (Groups gr) {
        groups = gr;
        return this;
    }
}
