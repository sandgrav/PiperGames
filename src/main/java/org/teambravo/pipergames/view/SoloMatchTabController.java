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
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.entity.MatchSolo;
import org.teambravo.pipergames.entity.Person;
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
    private TableColumn<MatchSolo, String> player1IdTableCol;
    @FXML
    private TableColumn<MatchSolo, String> player2TableCol;
    @FXML
    private TableColumn<MatchSolo, String> player2IdTableCol;
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
    @FXML
    private TextField player1AddToMatchText;

    @FXML
    private TextField player2AddToMatchText;

    @FXML
    private TextField dateAddSoloMatchText;

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
    protected void handleAddSoloMatchButton (ActionEvent e){}

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
        MatchSolo selectedMatch = matchTable.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            try {
                // Uppdatera datum
                LocalDate newDate = LocalDate.parse(dateAddSoloMatchText.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                selectedMatch.setDate(newDate.atStartOfDay());

                // Uppdatera spelare 1
                Player player1 = selectedMatch.getPlayer1();
                player1.getPerson().setNickName(player1AddToMatchText.getText());

                // Uppdatera spelare 2
                Player player2 = selectedMatch.getPlayer2();
                player2.getPerson().setNickName(player2AddToMatchText.getText());

                // Uppdatera databasen
                matchSoloController.updateMatchSoloPlayer(selectedMatch);

                showAlert("Match Uppdaterad", "Matchen har uppdaterats!");
            } catch (DateTimeParseException e) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            } catch (Exception e) {
                showAlert("Fel", "Ett fel uppstod vid uppdatering av matchen.");
            }
        } else {
            showAlert("Inget match vald", "Vänligen välj en match att uppdatera.");
        }
    }



        /*protected void handleAddMatchButton(ActionEvent e) {
            if (!(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || nickNameText.getText().isEmpty())) {
                Dropdown-Lista: här också.


                Player player = new Player();
                player.setPerson(new Person());
                PlayerController playerController = new PlayerController();
                player.getPerson().setFirstName(firstNameText.getText());
                player.getPerson().setLastName(lastNameText.getText());
                player.getPerson().setNickName(nickNameText.getText());
                player.getPerson().setAddress(streetAdressText.getText());
                player.getPerson().setPostalCode(zipText.getText());
                player.getPerson().setCity(cityText.getText());
                player.getPerson().setCountry(countryText.getText());
                player.getPerson().setEmail(eMailText.getText());
//            player.getTeam().getName()teamText.setText();
                playerController.savePlayer(player);
            }
        }*/

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
