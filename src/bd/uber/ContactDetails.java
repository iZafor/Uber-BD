package bd.uber;

import java.io.Serializable;

public class ContactDetails implements Serializable {
    private final int contactDetailsId;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String email;
    private int locationId;

    public ContactDetails(int contactDetailsId) {
        this.contactDetailsId = contactDetailsId;
        this.locationId = -1;
    }

    public int getContactDetailsId() {
        return contactDetailsId;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}