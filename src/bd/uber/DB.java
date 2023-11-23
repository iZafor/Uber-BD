package bd.uber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public boolean addObjects(List<Object> objectList, BinFilePath binFilePath) {
        File objectFile = new File(binFilePath.getPath());
        boolean append = objectFile.exists();
        try (FileOutputStream fos = new FileOutputStream(objectFile);
             ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos))) {
            for (Object object : objectList) {
                oos.writeObject(object);
            }
        } catch (IOException ignored) {
            // log the error
        }
        return false;
    }

    public <E> E getObject(BinFilePath binFilePath, Predicate<E> predicate) {
        try (FileInputStream fis = new FileInputStream(binFilePath.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            E e;
            while (true) {
                e = (E) ois.readObject();
                if (predicate.test(e)) {
                    return e;
                }
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return null;
    }

    public <E> List<E> getObjectList(BinFilePath binFilePath) {
        List<E> eList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(binFilePath.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                eList.add((E) ois.readObject());
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return eList;
    }

    public <E> List<E> getObjectList(BinFilePath binFilePath, Predicate<E> predicate) {
        List<E> eList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(binFilePath.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            E e;
            while (true) {
                e = (E) ois.readObject();
                if (predicate.test(e)) {
                    eList.add(e);
                }
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ignored) {
            // log the error
        }
        return eList;
    }

    public int getObjectCount(BinFilePath binFilePath) {
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
}