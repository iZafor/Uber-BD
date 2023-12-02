package bd.uber.zafor.controller.operationsmanager;

import bd.uber.zafor.model.operationsmanager.Discount;
import bd.uber.zafor.model.operationsmanager.PassengerRatingDiscountCondition;
import bd.uber.zafor.model.operationsmanager.RideDistanceCondition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class DiscountViewController {
    @FXML
    private Text discountCodeText;
    @FXML
    private Text discountAmountText;
    @FXML
    private ImageView conditionIcon;
    @FXML
    private Text conditionValue;
    @FXML
    private Text kmText;

    private Discount discount;
    private List<Integer> discountIdList;

    public void setInitData(Discount discount, List<Integer> discountIdList) {
        this.discount = discount;
        this.discountIdList = discountIdList;

        discountCodeText.setText(discount.getDiscountCode());
        discountAmountText.setText(String.valueOf(discount.getDiscountAmount()));

        if (discount.getDiscountCondition() instanceof PassengerRatingDiscountCondition) {
            conditionValue.setText(String.valueOf(((PassengerRatingDiscountCondition) discount.getDiscountCondition()).getMinPassengerRating()));
            kmText.setVisible(false);
        } else {
            conditionValue.setText(String.valueOf(((RideDistanceCondition) discount.getDiscountCondition()).getMinDistance()));
            conditionIcon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/distance.png"))));
        }
    }

    @FXML
    private void onDelete() {
        discountIdList.removeIf(id -> id == discount.getDiscountId());
    }
}