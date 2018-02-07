package model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactNameData {
    @Expose
    private String firstName;
    @Expose
    private final String lastName;
    @Expose
    private final String company;
    private File photo;

    public ContactNameData(String firstName, String lastName, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

    public ContactNameData(ContactNameData contactName) {
        this.firstName = contactName.firstName;
        this.lastName = contactName.lastName;
        this.company = contactName.company;
    }

    @Override
    public String toString() {
        return "ContactNameData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    // comparing without "company" field
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactNameData that = (ContactNameData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public ContactNameData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
    public File getPhoto() {

        return photo;
    }
}
