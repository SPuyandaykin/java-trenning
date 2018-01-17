package step2;

public class ContactNameData {
    private final String firstName;
    private final String lastName;
    private final String company;

    public ContactNameData(String firstName, String lastName, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
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
}
