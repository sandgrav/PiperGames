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
    /*@FXML
    private Tab playerTab;
    @FXML
    private Tab teamTab;
*/

    /*@FXML
    private void handlePlayerTabButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = loadPlayerTab(event);
        PlayerTabController playerTabController = fxmlLoader.getController();
    }

    private FXMLLoader loadPlayerTab(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewAllController.class.getResource("/org/teambravo/pipergames/playerTab.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        return fxmlLoader;
    }*/

    @FXML
    private void handlePlayerTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/playerTab.fxml", event, "Spelare");
    }
    @FXML
    private void handleTeamTabButton(ActionEvent event) throws IOException {
        loadFXML("/org/teambravo/pipergames/teamTab.fxml", event, "Lag");
    }
    private void loadFXML(String fxmlPath, ActionEvent event, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
    }
    /*@FXML
    private void handlePlayerTab(Event event) {
        loadTabContent(playerTab, "playerTab.fxml");
    }

    @FXML
    private void handleTeamTab(Event event) {
        loadTabContent(teamTab, "teamTab.fxml");
    }*/

    /*private void loadTabContent(Tab tab, String fxmlFile) {
        if (tab.getContent() == null) {
            try {
                Node content = FXMLLoader.load(getClass().getResource(fxmlFile));
                tab.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


}
