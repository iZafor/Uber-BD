package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    protected final int id;
    protected String name;
    protected String password;
    protected LocalDate accountCreationDate;
    protected int contactDetailsId;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String password, LocalDate accountCreationDate, int contactDetailsId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.contactDetailsId = contactDetailsId;
    }

    public int getId() {
        return id;
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

    public int getContactDetailsId() {
        return contactDetailsId;
    }

    public void setContactDetailsId(int contactDetailsId) {
        this.contactDetailsId = contactDetailsId;
    }
}