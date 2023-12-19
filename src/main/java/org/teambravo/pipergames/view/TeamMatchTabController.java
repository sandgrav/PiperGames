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
import org.teambravo.pipergames.controller.GameController;
import org.teambravo.pipergames.controller.MatchTeamController;
import org.teambravo.pipergames.controller.TeamController;
import org.teambravo.pipergames.entity.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamMatchTabController implements Initializable {

    @FXML
    private Button showAllTeamMatchesButton;
    @FXML
    private Button addTeamMatchButton;
    @FXML
    private Button deleteTeamMatchButton;
    @FXML
    private TableView<MatchTeam> teamMatchTable;
    @FXML
    private TableColumn<MatchTeam, String> teamMatchIdColumn;
    @FXML
    private TableColumn<MatchTeam, String> teamDateCol;
    @FXML
    private TableColumn<MatchTeam, String> team1TableColumn;
    @FXML
    private TableColumn<MatchTeam, String> team2TableCol;
    @FXML
    private TableColumn<MatchTeam, String> winnerTeamTableCol;
    @FXML
    private final MatchTeamController matchTeamController = new MatchTeamController();
    @FXML
    private ChoiceBox<Team> team1AddToMatchToCB;
    @FXML
    private ChoiceBox<Team> team2AddToMatchToCB;
    @FXML
    private TextField dateAddTeamMatchText;
    @FXML
    private ComboBox<Team> winnerCmb;
    @FXML
    private TableColumn<MatchTeam, String> statusCol;

    @FXML
    private void handleDeleteTeamMatchButton(ActionEvent event) {
        int selectedTeamMatchId = teamMatchTable.getSelectionModel().getSelectedIndex();
        if (selectedTeamMatchId >= 0) {
            MatchTeam selectedMatch = teamMatchTable.getItems().get(selectedTeamMatchId);
            matchTeamController.deleteTeamMatch(selectedMatch.getId());
            teamMatchTable.getItems().remove(selectedTeamMatchId);
        } else showAlert("Inget match vald", "Vänligen välj en match att ta bort.");
    }

    @FXML
    protected void handleShowAllTeamMatchesButtonAction (ActionEvent e) {
        List<MatchTeam> matches = matchTeamController.getAllMatches();
        ObservableList<MatchTeam> items = FXCollections.observableList(matches);

        teamMatchIdColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getId())));
        teamDateCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getDate())));
        team1TableColumn.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getTeam1().getName())));
        team2TableCol.setCellValueFactory(tf -> new SimpleStringProperty(String.valueOf(tf.getValue().getTeam2().getName())));
        winnerTeamTableCol.setCellValueFactory(tf -> {
            Team winner = tf.getValue().getWinner();
            return new SimpleStringProperty(winner == null ? "" : winner.getName());
        });


        teamMatchTable.setItems(items);
    }

    @FXML
    private void handleUpdateTeamMatchButton(ActionEvent event) {
        MatchTeam selectedTeamMatch = teamMatchTable.getSelectionModel().getSelectedItem();
        if (selectedTeamMatch != null) {
            try {
                // Uppdatera datum
                LocalDate newDate = LocalDate.parse(dateAddTeamMatchText.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                selectedTeamMatch.setDate(newDate.atStartOfDay());

                // Uppdatera spelare 1
                Team team1 = team1AddToMatchToCB.getValue();
                if (team1 != null) {
                    selectedTeamMatch.setTeam1(team1);
                }

                // Uppdatera spelare 2
                Team team2 = team2AddToMatchToCB.getValue();
                if (team2 != null) {
                    selectedTeamMatch.setTeam2(team2);
                }

                if (team1 != null && team1.equals(team2)) {
                    showAlert("Fel", "Samma Lag kan inte vara med i samma match.");
                    return;
                }

                // Uppdatera vinnare
                Team winner = winnerCmb.getValue();
                if (winner != null) {
                    selectedTeamMatch.setWinner(winner);
                }

                // Uppdatera databasen
                MatchTeamController.updateTeamMatch(selectedTeamMatch);

                showAlert("Match Uppdaterat", "Matchen har uppdaterats!");
            } catch (DateTimeParseException e) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            } catch (Exception e) {
                showAlert("Fel", "Ett fel uppstod vid uppdatering av lagen.");
            }
        } else {
            showAlert("Inget lag valt", "Vänligen välj ett lag att uppdatera.");
        }
    }

    @FXML
    protected void handleAddTeamMatchButton(ActionEvent e) {
        Team selectedTeam1 = team1AddToMatchToCB.getValue();
        Team selectedTeam2 = team2AddToMatchToCB.getValue();

        if (selectedTeam1 != null && selectedTeam2 != null) {
            try {

                LocalDate newDate = LocalDate.parse(dateAddTeamMatchText.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                // Kolla om lagen är med i samma match
                if (selectedTeam1.equals(selectedTeam2)) {
                    showAlert("Fel", "Samma spelare kan inte vara med i samma match.");
                    return;
                }
                // Skapar ett nytt objekt
                MatchTeam newTeamMatch = new MatchTeam();
                newTeamMatch.setDate(newDate.atStartOfDay());
                newTeamMatch.setTeam1(selectedTeam1);
                newTeamMatch.setTeam2(selectedTeam2);

                // Upp med den i databasen
                matchTeamController.createMatchTeam(newTeamMatch);

                // lägg till den i tabellen
                teamMatchTable.getItems().add(newTeamMatch);

                showAlert("Lägg till Match", "Matchen har lagts till!");
            } catch (DateTimeParseException ex) {
                showAlert("Fel", "Ogiltigt datumformat. Använd formatet YYYY-MM-DD");
            } catch (Exception ex) {
                showAlert("Fel", "Ett fel uppstod vid läggning till matchen.");
            }
        } else {
            showAlert("Fel", "Vänligen välj olika Lag för Lag 1 och Lag 2.");
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
        Callback<ListView<Team>, ListCell<Team>> teamCellFactory = new Callback<ListView<Team>, ListCell<Team>>() {
            @Override
            public ListCell<Team> call(ListView<Team> l) {
                return new ListCell<Team>() {
                    @Override
                    protected void updateItem(Team item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        teamMatchTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedMatch) -> {
            if (selectedMatch != null) {
                // Uppdatera ComboBox och Datumfönster med matchinformation när den är markerad i tabellen
                team1AddToMatchToCB.setValue(selectedMatch.getTeam1());
                team2AddToMatchToCB.setValue(selectedMatch.getTeam2());
                winnerCmb.setValue(selectedMatch.getWinner());

                LocalDate matchDate = selectedMatch.getDate().toLocalDate();
                dateAddTeamMatchText.setText(matchDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
        });

        List<Team> teams = new TeamController().getAllTeams(false);
        ObservableList<Team> teamItems = FXCollections.observableList(teams);
        team1AddToMatchToCB.setItems(teamItems);
        team1AddToMatchToCB.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team team) {
                return team == null ? null : team.getName();
            }

            @Override
            public Team fromString(String string) {
                return null;
            }
        });

        team2AddToMatchToCB.setItems(teamItems);
        team2AddToMatchToCB.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team team) {
                return team == null ? null : team.getName();
            }

            @Override
            public Team fromString(String string) {

                return null;
            }
        });
        statusCol.setCellValueFactory(tf -> {
            if (tf.getValue().getDate().isBefore(LocalDate.now().atStartOfDay())) {
                return new SimpleStringProperty("Avgjord");
            } else {
                return new SimpleStringProperty("Kommande");
            }
        });


        ObservableList<MatchTeam> matchTeamSelectedItems = teamMatchTable.getSelectionModel().getSelectedItems();
        matchTeamSelectedItems.addListener(
                new ListChangeListener<MatchTeam>() {
                    @Override
                    public void onChanged(Change<? extends MatchTeam> change) {
                        if (change.getList().size() == 1) {
                            MatchTeam matchTeam = change.getList().get(0);
                            winnerCmb.getItems().clear();
                            winnerCmb.getItems().addAll(matchTeam.getTeam1(), matchTeam.getTeam2());
                            winnerCmb.setValue(matchTeam.getWinner());
                        } else {
                            winnerCmb.getItems().clear();
                        }
                    }
                }
        );

        //
        winnerCmb.setButtonCell(teamCellFactory.call(null));
        winnerCmb.setCellFactory(teamCellFactory);
        winnerCmb.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getName();
                }
            }

            @Override
            public Team fromString(String string) {
                return null;
            }
        });
    }
}
