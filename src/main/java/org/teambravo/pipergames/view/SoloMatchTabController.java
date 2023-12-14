package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Label labelDeleteMatch;
    @FXML
    private ChoiceBox<Integer> choiceBoxDeleteMatch;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;
    @FXML
    private TextField textFieldDeleteMatch;

    int enteredMatchId;


    @FXML
    private void handleDeleteMatchButton(ActionEvent event) {
    int selectedMatchId = matchTable.getSelectionModel().getSelectedIndex();
    if (selectedMatchId >= 0){
        MatchSolo selectedMatch = matchTable.getItems().get(selectedMatchId);
        matchSoloController.deleteMatchById(selectedMatch.getId());
        matchTable.getItems().remove(selectedMatchId);
    } else showAlert("Inget match vald", "Vänligen välj en match att ta bort.");
    }


    @FXML
    protected void handleShowAllMatchesButtonAction (ActionEvent e){
        List<MatchSolo> matches = matchSoloController.getAllMatches();
        ObservableList<MatchSolo> items = FXCollections.observableList(matches);

        matchIdColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getId())));
        dateCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getDate())));
        player1TableColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer1().getPerson().getNickName())));
        player2TableCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer2().getPerson().getNickName())));

        matchTable.setItems(items);
        }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //deleteMatchButton.setOnAction(event -> handleDeleteMatchButton());

    }
}
/*
    enteredMatchId = Integer.parseInt(textFieldDeleteMatch.getText());
            System.out.println(enteredMatchId);


        try {
        MatchSolo matchToDelete = matchSoloController.getMatchById(enteredMatchId).orElse(null);

        if (matchToDelete != null) {

            matchSoloController.deleteMatchById(enteredMatchId);
            matchTable.getItems().remove(enteredMatchId);

            showAlert("Match borttagen", "Match med ID " + enteredMatchId + " har tagits bort.");
        } else {
            showAlert("Ingen match med det Match-ID", "Match med ID " + enteredMatchId + " hittades ej.");
        }
    } catch (NumberFormatException e) {
        showAlert("Felaktigt värde", "Vänligen ange ett Match ID.");
    }
}

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }*/