package bd.uber;

public class LoginInfo {
    private final int id;
    private final String password;
    private final UserType userType;

    public LoginInfo(int id, String password, UserType userType) {
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    public User verifyLoginInfo() {
        return DB.getInstance().getUser(id, password, userType);
    }
}