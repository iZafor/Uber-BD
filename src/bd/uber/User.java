package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    protected final int id;
    protected String password;
    protected String name;
    protected final LocalDate accountCreationDate;
    protected final ContactDetails contactDetails;

    public User(int id, String password, String name, LocalDate accountCreationDate, ContactDetails contactDetails) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.accountCreationDate = accountCreationDate;
        this.contactDetails = contactDetails;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }
}