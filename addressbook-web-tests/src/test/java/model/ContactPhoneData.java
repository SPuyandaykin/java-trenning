package model;

import java.util.Objects;

public class ContactPhoneData {
    private final String phoneHome;
    private final String phoneMobile;

    public ContactPhoneData(String phoneHome, String phoneMobile) {
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
    }

    public ContactPhoneData(ContactPhoneData contactPhone) {
        this.phoneHome = contactPhone.phoneHome;
        this.phoneMobile = contactPhone.phoneMobile;
    }

    @Override
    public String toString() {
        return "ContactPhoneData{" +
                "phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPhoneData that = (ContactPhoneData) o;
        return Objects.equals(phoneHome, that.phoneHome) &&
                Objects.equals(phoneMobile, that.phoneMobile);
    }

    @Override
    public int hashCode() {

        return Objects.hash(phoneHome, phoneMobile);
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }
}
