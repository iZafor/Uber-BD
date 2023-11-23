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
        return Util.getInstance().getDb().getObject(BinFilePath.valueOf(userType.name()), user -> user.getId() == id && user.password.equals(password));
    }
}