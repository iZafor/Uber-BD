package bd.uber.zafor.controller.operationsmanager;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import bd.uber.zafor.model.operationsmanager.PromotionalCampaign;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CampaignViewController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Text startDateText;
    @FXML
    private Text endDateText;

    private volatile PromotionalCampaign campaign;

    public void setInitData(PromotionalCampaign campaign) {
        this.campaign = campaign;
        titleLabel.setText(campaign.getCampaignName());
        descriptionLabel.setText(campaign.getDescription());
        startDateText.setText(campaign.getStartDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        endDateText.setText(campaign.getEndDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
    }

    @FXML
    private void onViewDetails(ActionEvent event) {
        Util.getInstance().getWorkers().execute(() -> {
            try {
                FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.CAMPAIGN_DETAILED_VIEW);
                AnchorPane pane = loader.load();
                Platform.runLater(() -> {
                    ((CampaignDetailedViewController) loader.getController()).setInitData(campaign);
                    Util.getInstance().showScene(
                            new Scene(pane),
                            event,
                            ""
                    );
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}