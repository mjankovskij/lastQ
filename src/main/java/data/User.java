package data;

public class User {
    private String firstname;
    private String lastname;
    private long personalCode;

    public User() {
    }

    public User(String firstname, String lastname, long personalCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalCode = personalCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", personalCode=" + personalCode +
                '}';
    }

    public long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(int personalCode) {
        this.personalCode = personalCode;
    }
}
