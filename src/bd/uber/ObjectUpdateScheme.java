package bd.uber;

import java.util.function.Predicate;

public class ObjectUpdateScheme<E> {
    private final E e;
    private final BinFilePath binFilePath;
    private final Predicate<E> ePredicate;
    private final boolean delete;

    public ObjectUpdateScheme(E e, BinFilePath binFilePath, Predicate<E> ePredicate, boolean delete) {
        this.e = e;
        this.binFilePath = binFilePath;
        this.ePredicate = ePredicate;
        this.delete = delete;
    }

    public E getE() {
        return e;
    }

    public BinFilePath getBinFilePath() {
        return binFilePath;
    }

    public Predicate<E> getePredicate() {
        return ePredicate;
    }

    public boolean isDelete() {
        return delete;
    }
}