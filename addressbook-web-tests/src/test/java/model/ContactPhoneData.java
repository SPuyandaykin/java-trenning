package model;

import unilities.StringUtilities;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContactPhoneData {
    private String phoneHome;
    private String phoneMobile;
    private String workMobile;
    private String email;
    private String address;
    private String allPhones;

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

    private String mergePhones (ContactPhoneData contactPhone){
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
