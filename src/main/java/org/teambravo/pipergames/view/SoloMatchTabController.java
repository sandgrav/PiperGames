package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.teambravo.pipergames.controller.MatchSoloController;
import org.teambravo.pipergames.controller.MatchTeamController;
import org.teambravo.pipergames.entity.MatchSolo;
import org.teambravo.pipergames.entity.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SoloMatchTabController implements Initializable {

    @FXML
    private Button showAllMatchesButton;
    @FXML
    private Button addMatchButton;
    @FXML
    private Button deleteMatchButton;
    @FXML
    private TableView<MatchSolo> matchTable;
    @FXML
    private TableColumn<MatchSolo, String> matchIdColumn;
    @FXML
    private TableColumn<MatchSolo, String> dateCol;
    @FXML
    private TableColumn<MatchSolo, String> player1TableColumn;
    @FXML
    private TableColumn<MatchSolo, String> player2TableCol;
    @FXML
    private final MatchSoloController matchSoloController = new MatchSoloController();
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;


    @FXML
    protected void handleShowAllMatchesButtonAction(ActionEvent e) {
        List<MatchSolo> matches = matchSoloController.getAllMatches();
        ObservableList<MatchSolo> items = FXCollections.observableList(matches);

        matchIdColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getId())));
        dateCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getDate())));
        player1TableColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer1().getPerson().getNickName())));
        player2TableCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer2().getPerson().getNickName())));

        matchTable.setItems(items);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
