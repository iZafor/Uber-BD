package bd.uber;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    protected final int id;
    protected String name;
    protected String password;
    protected LocalDate accountCreationDate;
    protected int contactDetailsId;
    protected String profileImage;
    protected String nidFrontSide;
    protected String nidBackSide;

    public User(int id) {
        this.id = id;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNidFrontSide() {
        return nidFrontSide;
    }

    public void setNidFrontSide(String nidFrontSide) {
        this.nidFrontSide = nidFrontSide;
    }

    public String getNidBackSide() {
        return nidBackSide;
    }

    public void setNidBackSide(String nidBackSide) {
        this.nidBackSide = nidBackSide;
    }
}