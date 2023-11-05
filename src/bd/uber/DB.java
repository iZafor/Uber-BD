package bd.uber;

import java.io.*;

public class DB {
    private static volatile DB db;

    public static DB getInstance() {
        synchronized (DB.class) {
            if (db == null) {
                db = new DB();
            }
            return db;
        }
    }

    public User getUser(int userId, String password, UserType userType) {
        try (FileInputStream fis = new FileInputStream(userType.getBinFilePath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User user;
            while ((user = (User) ois.readObject()) != null) {
                if (user.id == userId && user.password.equals(password)) {
                    return user;
                }
            }
        } catch (Exception ignored) {
            // log the error
        }
        return null;
    }
}