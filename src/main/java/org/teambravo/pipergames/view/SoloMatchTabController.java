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
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.teambravo.pipergames.controller.MatchSoloController;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.entity.MatchSolo;
import org.teambravo.pipergames.entity.MatchTeam;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.Team;

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
    private TableColumn<MatchSolo, String> winnerSoloTableCol;
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
    private ChoiceBox<Player> player1AddToMatchToCB;
    @FXML
    private ChoiceBox<Player> player2AddToMatchToCB;
    @FXML
    private ComboBox<Player> winnerCmb;


    @FXML
    private TextField textFieldDeleteMatch;
    @FXML
    private TextField player1AddToMatchText;

    @FXML
    private TextField player2AddToMatchText;

    @FXML
    private TextField dateAddSoloMatchText;

    @FXML
    private void handleDeleteMatchButton(ActionEvent event) {
        int selectedMatchId = matchTable.getSelectionModel().getSelectedIndex();
        if (selectedMatchId >= 0) {
            MatchSolo selectedMatch = matchTable.getItems().get(selectedMatchId);
            matchSoloController.deleteMatchById(selectedMatch.getId());
            matchTable.getItems().remove(selectedMatchId);
        } else showAlert("Inget match vald", "Vänligen välj en match att ta bort.");
    }

    @FXML
    protected void handleShowAllMatchesButtonAction(ActionEvent e) {
        List<MatchSolo> matches = matchSoloController.getAllMatches();
        ObservableList<MatchSolo> items = FXCollections.observableList(matches);

        matchIdColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getId())));
        dateCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getDate())));
        player1TableColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer1().getPerson().getNickName())));
        player2TableCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getPlayer2().getPerson().getNickName())));
        winnerSoloTableCol.setCellValueFactory(tf -> {
            Player winner = tf.getValue().getWinner();
            return new SimpleStringProperty(winner == null ? "" : winner.getPerson().getNickName());
        });


        matchTable.setItems(items);
    }

    @FXML
    private void handleUpdateMatchButton(ActionEvent event) {
        MatchSolo selectedMatch = matchTable.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            try {
                // Uppdatera datum
                LocalDate newDate = LocalDate.parse(dateAddSoloMatchText.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                selectedMatch.setDate(newDate);

                // Uppdatera spelare 1
                Player player1 = player1AddToMatchToCB.getValue();
                if (player1 != null) {
                    selectedMatch.setPlayer1(player1);
                }

                // Uppdatera spelare 2
                Player player2 = player2AddToMatchToCB.getValue();
                if (player2 != null) {
                    selectedMatch.setPlayer2(player2);
                }

                if (player1 != null && player1.equals(player2)) {
                    showAlert("Fel", "Samma spelare kan inte vara med i samma match.");
                    return;
                }

                // Uppdatera vinnare
                Player winner = winnerCmb.getValue();
                if (winner != null) {
                    selectedMatch.setWinner(winner);
                }

                // Uppdatera databasen
                matchSoloController.updateMatchSoloPlayer(selectedMatch);

                showAlert("Match Uppdaterat", "Matchen har uppdaterats!");
            } catch (DateTimeParseException e) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            } catch (Exception e) {
                showAlert("Fel", "Ett fel uppstod vid uppdatering av spelarna.");
            }
        } else {
            showAlert("Ingen spelare vald", "Vänligen välj en spelare att uppdatera.");
        }
    }

    @FXML
    protected void handleAddSoloMatchButton(ActionEvent e) {
        Player selectedPlayer1 = player1AddToMatchToCB.getValue();
        Player selectedPlayer2 = player2AddToMatchToCB.getValue();

        if (selectedPlayer1 != null && selectedPlayer2 != null) {
            try {

                LocalDate newDate = LocalDate.parse(dateAddSoloMatchText.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                // Kolla om spelaren är i samma lag.
                if (selectedPlayer1.equals(selectedPlayer2)) {
                    showAlert("Fel", "Samma spelare kan inte vara med i samma match.");
                    return;
                }
                // Skapar ett nytt objekt
                MatchSolo newMatch = new MatchSolo();
                newMatch.setDate(newDate);
                newMatch.setPlayer1(selectedPlayer1);
                newMatch.setPlayer2(selectedPlayer2);

                // Upp med den i databasen
                matchSoloController.createMatchSoloPlayer(newMatch);

                // lägg till den i tabellen
                matchTable.getItems().add(newMatch);

                showAlert("Lägg till Match", "Matchen har lagts till!");
            } catch (DateTimeParseException ex) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            } catch (Exception ex) {
                showAlert("Fel", "Ett fel uppstod vid läggning till matchen.");
            }
        } else {
            showAlert("Fel", "Vänligen välj olika spelare för Player 1 och Player 2.");
        }



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
        Callback<ListView<Player>, ListCell<Player>> playerCellFactory = new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> l) {
                return new ListCell<Player>() {
                    @Override
                    protected void updateItem(Player item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getPerson().getNickName());
                        }
                    }
                };
            }
        };

        matchTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedMatch) -> {
            if (selectedMatch != null) {
                // Uppdatera ComboBox och Datumfönster med matchinformation när den är markerad i tabellen
                player1AddToMatchToCB.setValue(selectedMatch.getPlayer1());
                player2AddToMatchToCB.setValue(selectedMatch.getPlayer2());
                winnerCmb.setValue(selectedMatch.getWinner());

                LocalDate matchDate = selectedMatch.getDate();
                dateAddSoloMatchText.setText(matchDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
        });

        List<Player> players = new PlayerController().getAllPlayer(false);
        ObservableList<Player> playerItems = FXCollections.observableList(players);
        player1AddToMatchToCB.setItems(playerItems);
        player1AddToMatchToCB.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player player) {return player == null ? null : player.getPerson().getNickName();}

            @Override
            public Player fromString(String string) {
                    return null;
                }
        });

        player2AddToMatchToCB.setItems(playerItems);
        player2AddToMatchToCB.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player player) {
                return player == null ? null : player.getPerson().getNickName();
            }

            @Override
            public Player fromString(String string) {

                return null;
            }
        });

        ObservableList<MatchSolo> matchSoloSelectedItems = matchTable.getSelectionModel().getSelectedItems();
        matchSoloSelectedItems.addListener(
                new ListChangeListener<MatchSolo>() {
                    @Override
                    public void onChanged(Change<? extends MatchSolo> change) {
                        if (change.getList().size() == 1) {
                            MatchSolo matchSolo = change.getList().get(0);
                            winnerCmb.getItems().clear();
                            winnerCmb.getItems().addAll(matchSolo.getPlayer1(), matchSolo.getPlayer2());
                            winnerCmb.setValue(matchSolo.getWinner());
                        } else {
                            winnerCmb.getItems().clear();
                        }
                    }
                });

        //Lägg till Check TodaysDate metod
        winnerCmb.setButtonCell(playerCellFactory.call(null));
        winnerCmb.setCellFactory(playerCellFactory);
        winnerCmb.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getPerson().getNickName();
                }
            }

            @Override
            public Player fromString(String string) {
                    return null;
                }
            });
    }
}
