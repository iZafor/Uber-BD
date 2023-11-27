package bd.uber.zafor.model.driver;

import java.io.Serializable;

public class RideFeedback implements Serializable {
    private final int rating;
    private final String feedbackMessage;

    public RideFeedback(int rating, String feedbackMessage) {
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }
}