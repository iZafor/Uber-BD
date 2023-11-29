package bd.uber.zafor.model.driver;

import java.io.Serializable;

public class RideFeedback implements Serializable {
    private final int rideFeedBackId;
    private final int userId;
    private final int rating;
    private final String feedbackMessage;

    public RideFeedback(int rideFeedBackId, int userId, int rating, String feedbackMessage) {
        this.rideFeedBackId = rideFeedBackId;
        this.userId = userId;
        this.rating = rating;
        this.feedbackMessage = feedbackMessage;
    }

    public int getRideFeedBackId() {
        return rideFeedBackId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }
}