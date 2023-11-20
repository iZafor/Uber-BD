package bd.uber;

import bd.uber.zafor.model.Ride;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
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

    public boolean addUser(User user, BinFilePath binFilePath) {
        File userFile = new File(binFilePath.getPath());
        boolean append = userFile.exists();
        try (FileOutputStream fos = new FileOutputStream(userFile, append);
             ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos))) {
            oos.writeObject(user);
            return true;
        } catch (IOException ignored) {
            // log the error
        }
        return false;
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

    public boolean addRide(Ride ride) {
        File rideFile = new File(BinFilePath.RIDE.getPath());
        boolean append = rideFile.exists();
        try (FileOutputStream fos = new FileOutputStream(rideFile, append);
             ObjectOutputStream ois = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos))) {
            ois.writeObject(ride);
        } catch (IOException ignored) {
            // log the error
        }
        return false;
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

    public boolean addLocation(Location location) {
        File locationFile = new File(BinFilePath.LOCATION.getPath());
        try (FileOutputStream fos = new FileOutputStream(BinFilePath.RIDE.getPath());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(location);
            return true;
        } catch (IOException e) {
            // log the error
        }
        return false;
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