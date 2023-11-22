package bd.uber;

import bd.uber.zafor.model.Ride;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public boolean addObject(Object object, BinFilePath binFilePath) {
        File objectFile = new File(binFilePath.getPath());
        boolean append = objectFile.exists();
        try (FileOutputStream fos = new FileOutputStream(objectFile, append);
             ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos))) {
            oos.writeObject(object);
            return true;
        } catch (IOException ignored) {
            // log the error
        }
        return false;
    }

    public int getUserCount(BinFilePath binFilePath) {
        int i = 0;
        try (FileInputStream fis = new FileInputStream(binFilePath.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                ois.readObject();
                i++;
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return i;
    }

    public User getUser(int userId, String password, BinFilePath binFilePath) {
        try (FileInputStream fis = new FileInputStream(binFilePath.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User user;
            while ((user = (User) ois.readObject()) != null) {
                if (user.id == userId && user.password.equals(password)) {
                    return user;
                }
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return null;
    }

    public List<Ride> getRides(int userId, UserType userType) {
        List<Ride> rideList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(BinFilePath.RIDE.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Ride ride;
            while ((ride = (Ride) ois.readObject()) != null) {
                if (userType.equals(UserType.DRIVER) && ride.getDriverId() == userId) {
                    rideList.add(ride);
                    continue;
                }

                if (userType.equals(UserType.PASSENGER) && ride.getPassengerId() == userId) {
                    rideList.add(ride);
                }
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return rideList;
    }

    public List<Location> getLocations() {
        List<Location> locationList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(BinFilePath.LOCATION.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                locationList.add((Location) ois.readObject());
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            // log the error
        }
        return locationList;
    }
}