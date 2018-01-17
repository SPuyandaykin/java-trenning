package model;

public class ContactPhoneData {
    private final String phoneHome;
    private final String phoneMobile;

    public ContactPhoneData(String phoneHome, String phoneMobile) {
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }
}
