package org.teambravo.pipergames.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.Node;

import java.io.IOException;

public class ViewAllController {

    @FXML
    private Tab playerTab;
    @FXML
    private Tab teamTab;

    @FXML
    private void handlePlayerTab(Event event) {
        loadTabContent(playerTab, "playerTab.fxml");
    }

    @FXML
    private void handleTeamTab(Event event) {
        loadTabContent(teamTab, "teamTab.fxml");
    }

    private void loadTabContent(Tab tab, String fxmlFile) {
        if (tab.getContent() == null) {
            try {
                Node content = FXMLLoader.load(getClass().getResource(fxmlFile));
                tab.setContent(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
