package org.teambravo.pipergames.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.teambravo.pipergames.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Tab tab = new Tab("Spelare");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("playerTab.fxml")));
            tab = new Tab("Lag");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("teamTab.fxml")));
            tab = new Tab("Solomatcher");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("soloMatchTab.fxml")));
            tab = new Tab("Lagmatcher");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("teamMatchTab.fxml")));
            tab = new Tab("Turneringar");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("tournamentTab.fxml")));
            tab = new Tab("Personal");
            tabPane.getTabs().add(tab);
            tab.setContent((Node) FXMLLoader.load(Main.class.getResource("staffTab.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
