package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.teambravo.pipergames.controller.MatchSoloController;
import org.teambravo.pipergames.controller.MatchTeamController;
import org.teambravo.pipergames.entity.MatchSolo;
import org.teambravo.pipergames.entity.Player;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    @FXML
    private void handleUpdateMatchButton(ActionEvent event) {
        matchTable.setEditable(true);
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(editEvent -> {
            MatchSolo editedItem = editEvent.getRowValue();
            try {

                LocalDate newDate = LocalDate.parse(editEvent.getNewValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                editedItem.setDate(newDate.atStartOfDay());

                // Uppdatera databasen
                matchSoloController.updateMatchSoloPlayer(editedItem);

                showAlert("Match Uppdaterad", "Datum uppdaterat!");
            } catch (DateTimeParseException e) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            }
        });
        player1TableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        player1TableColumn.setOnEditCommit(editEvent -> {
            Player editedItem = editEvent.getRowValue().getPlayer1();
            try {
                String newPlayer1Value = editEvent.getNewValue();
                // Uppdatera spelaren i databasen
                editedItem.getPerson().setNickName(newPlayer1Value);

                // Uppdatera databasen
                matchSoloController.updateMatchSoloPlayer(matchTable.getSelectionModel().getSelectedItem());

                showAlert("Match Uppdaterad", "Spelare 1 uppdaterad!");
            } catch (Exception e) {
                showAlert("Fel", "Ett fel uppstod vid uppdatering av Spelare 1.");
            }
        });
        player2TableCol.setCellFactory(TextFieldTableCell.forTableColumn());
        player2TableCol.setOnEditCommit(editEvent -> {
            Player editedItem = editEvent.getRowValue().getPlayer2();
            try {
                String newPlayer2Value = editEvent.getNewValue();
                // Uppdatera spelaren i databasen eller gör vad som behövs
                editedItem.getPerson().setNickName(newPlayer2Value);

                // Uppdatera databasen
                matchSoloController.updateMatchSoloPlayer(matchTable.getSelectionModel().getSelectedItem());

                showAlert("Match Uppdaterad", "Spelare 2 uppdaterad!");
            } catch (Exception e) {
                showAlert("Fel", "Ett fel uppstod vid uppdatering av Spelare 2.");
            }
        });
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

    }
}
