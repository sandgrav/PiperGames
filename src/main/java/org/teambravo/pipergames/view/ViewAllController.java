package org.teambravo.pipergames.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;


import java.io.IOException;

public class ViewAllController {

    @FXML
    private Button playerTabButton;
    @FXML
    private Button teamTabButton;
    @FXML
    private Button soloMatchTabButton;
    @FXML
    private Button teamMatchTabButton;
    @FXML
    private Button staffTabButton;

    @FXML
    private void handlePlayerTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/playerTab.fxml", event, "Spelare");
    }
    @FXML
    private void handleTeamTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/teamTab.fxml", event, "Lag");
    }
    @FXML
    private void handleSoloMatchTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/soloMatchTab.fxml", event, "Solomatcher");
    }
    @FXML
    private void handleTeamMatchTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/soloMatchTab.fxml", event, "Teammatcher");
    }
    @FXML
    private void handleStaffTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/soloMatchTab.fxml", event, "Personal");
    }
    private void loadFXML(String fxmlPath, ActionEvent event, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
    }


}
