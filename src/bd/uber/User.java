package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    protected int id;
    protected String name;
    protected String password;
    protected LocalDate accountCreationDate;
    protected ContactDetails contactDetails;

    public User() {
    }

    public User(int id, String name, String password, LocalDate accountCreationDate, ContactDetails contactDetails) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.contactDetails = contactDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}