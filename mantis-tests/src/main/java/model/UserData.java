package model;

import java.util.Objects;

public class UserData {

    private String name;
    private String email;
    private int access;

    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", access=" + access +
                '}';
    }

    public UserData withAccess(int access) {
        this.access = access;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return access == userData.access &&
                Objects.equals(name, userData.name) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email, access);
    }

    public String getEmail() {
        return email;
    }

    public int getAccess() {
        return access;
    }


}
