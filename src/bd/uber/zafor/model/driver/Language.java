package bd.uber.zafor.model.driver;

import java.io.Serializable;

public enum Language implements Serializable {
    BANGLA("Bangla"),
    ENGLISH("English");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return language;
    }
}