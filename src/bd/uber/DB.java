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

    public <E> boolean addObjects(List<E> objectList, BinFilePath binFilePath, boolean overwrite) {
        File objectFile = new File(binFilePath.getPath());
        boolean append = !overwrite && objectFile.exists();
        try (FileOutputStream fos = new FileOutputStream(objectFile, append);
             ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos))) {
            for (E e : objectList) {
                oos.writeObject(e);
            }
            return true;
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

    public <E> boolean updateObjectFile(E e, BinFilePath binFilePath, Predicate<E> predicate, boolean delete) {
        List<E> eList = getObjectList(binFilePath);
        int idxToRemove = -1;
        for (int i = 0; i < eList.size(); i++) {
            if (predicate.test(eList.get(i))) {
                idxToRemove = i;
                break;
            }
        }
        eList.remove(idxToRemove);
        if (!delete) {
            eList.add(e);
        }
        return addObjects(eList, binFilePath, true);
    }
}