package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.teambravo.pipergames.controller.GameController;
import org.teambravo.pipergames.controller.PersonController;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.controller.TeamController;
import org.teambravo.pipergames.entity.Game;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.Team;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamTabController implements Initializable {
    @FXML
    private TableView<Team> teamTable;
    private ObservableList<Team> teamItems;
    @FXML
    private TableColumn<Team, String> Spel;
    @FXML
    private TableColumn<Team, String> Lag;

    // Second table

    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;

    // Lägg till lag med knapp genom textfield
    @FXML
    private TextField teamTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Team> teams = new TeamController().getAllTeams(true);
        teamItems = FXCollections.observableList(teams);

        Spel.setCellValueFactory(tf -> {
            Team team = tf.getValue();
            if (team != null && team.getGame() != null) {
                return new SimpleStringProperty(team.getGame().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });        Lag.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));

        teamTable.setItems(teamItems);

        teamTable.getSelectionModel().setCellSelectionEnabled(true);

        teamTable.setEditable(true);

        Lag.setCellFactory(TextFieldTableCell.forTableColumn());

        Lag.setOnEditCommit(event -> {
            if (event.getNewValue() != null && !event.getNewValue().isEmpty()) {
                Team newTeam = new Team();
                newTeam.setName(event.getNewValue());
                teamTable.getItems().add(newTeam);
            }
        });


        ObservableList<Team> teamSelectedItems = teamTable.getSelectionModel().getSelectedItems();
        teamSelectedItems.addListener(

                new ListChangeListener<Team>() {
                    @Override
                    public void onChanged(Change<? extends Team> change) {
                        playerTable.getItems().clear();
                        for (Team team :
                                teamTable.getSelectionModel().getSelectedItems()) {
                            List<Player> players = team.getPlayers();
                            ObservableList<Player> items = FXCollections.observableList(players);
                            firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
                            lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
                            nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
                            playerTable.getItems().addAll(items);
                        }
                        teamTable.refresh();
                    }
                }
        );
    }
    @FXML
    protected void AddTeamButtonAction(ActionEvent e) {
        TeamController teamController = new TeamController();
        String teamName = teamTextField.getText();

        if (teamName.isEmpty())
            return;

        Team newTeam = new Team();
        newTeam.setName(teamName);

        if (teamController.saveTeam(newTeam)) {
            teamItems.addAll(teamController.getTeamByName(teamName));
            teamTable.refresh();
        }
    }

    @FXML
    void DeleteTeamButton(ActionEvent e) {
        TeamController teamController = new TeamController();
        String teamName = teamTextField.getText();

        if (teamName.isEmpty())
            return;

        boolean isDeleted = teamController.deleteTeamByName(teamName);

        if (isDeleted) {

            teamItems.removeIf(team -> team.getName().equals(teamName));

            teamTable.refresh();
        }
    }


}








// List<Player> players = new PlayerController().getAllPlayer(true);
// ObservableList<Player> playerItems = FXCollections.observableList(players);

// TableColumn<Player, String> firstNameColumn = new TableColumn<>("First Name");
// firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));


// userTable1.setItems(playerItems);