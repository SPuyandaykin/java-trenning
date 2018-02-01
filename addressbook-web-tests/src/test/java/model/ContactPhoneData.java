package model;

import java.util.Objects;

public class ContactPhoneData {
    private String phoneHome = "";
    private String phoneMobile = "";
    private String workMobile = "";
    private String email = "";
    private String address = "";

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
    }

    public ContactPhoneData(ContactPhoneData contactPhone) {
        this.phoneHome = contactPhone.phoneHome;
        this.phoneMobile = contactPhone.phoneMobile;
        this.workMobile = contactPhone.workMobile;
        this.email = contactPhone.email;
        this.address = contactPhone.address;
    }

    @Override
    public String toString() {
        return "ContactPhoneData{" +
                "phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", workMobile='" + workMobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPhoneData that = (ContactPhoneData) o;
        return Objects.equals(phoneHome, that.phoneHome) &&
                Objects.equals(phoneMobile, that.phoneMobile) &&
                Objects.equals(workMobile, that.workMobile) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(phoneHome, phoneMobile, workMobile, email, address);
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() { return phoneMobile; }

    public String getWorkMobile() { return workMobile; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }
}
