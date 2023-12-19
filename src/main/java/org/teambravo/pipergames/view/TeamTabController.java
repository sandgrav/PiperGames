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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamTabController implements Initializable {
    // Team table
    @FXML
    private TableView<Team> teamTable;
    private ObservableList<Team> teamItems;
    private ObservableList<Player> playerItems;
    @FXML
    private TableColumn<Team, String> Spel;
    @FXML
    private TableColumn<Team, String> Lag;

    // spelare table

    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;

    // Lägg till lag/spelare med knapp genom textfield
    @FXML
    private TextField teamTextField;
    @FXML
    private TextField spelTextField;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField nickName;


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
        });
        Lag.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));

        teamTable.setItems(teamItems);



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
                            teamTextField.setText(team.getName());


                            if (team.getGame() != null) {
                                spelTextField.setText(team.getGame().getName());
                            } else {
                                spelTextField.clear();
                            }



                            firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
                            lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
                            nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
                            playerTable.getItems().addAll(items);

                            playerTable.getItems().clear();
                            playerTable.getItems().addAll(items);
                        }
                        teamTable.refresh();
                    }
                }
        );

        ObservableList<Player> playerSelectedItems = playerTable.getSelectionModel().getSelectedItems();
        playerSelectedItems.addListener(
                new ListChangeListener<Player>() {
                    @Override
                    public void onChanged(Change<? extends Player> change) {
                        if (change.getList().size() == 1) {
                            Player player = change.getList().get(0);
                            firstName.setText(player.getPerson().getFirstName());
                            lastName.setText(player.getPerson().getLastName());
                            nickName.setText(player.getPerson().getNickName());

                        } else {
                            firstName.setText("");
                            lastName.setText("");
                            nickName.setText("");
                        }
                    }
                }
        );


    }

    @FXML
    protected void AddTeamButtonAction(ActionEvent e) {
        TeamController teamController = new TeamController();
        GameController gameController = new GameController();
        String gameName = spelTextField.getText();
        String teamName = teamTextField.getText();

        if (teamName.isEmpty() || gameName.isEmpty())
            return;

        for (Team team : teamItems) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Team name already exists.");
                alert.setHeaderText("Error!");
                alert.setContentText("You need to input a team name that does not exist.");

                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    return;
                } else if (alert.getResult() == ButtonType.CANCEL) {
                    return;
                }
            }
        }

        Game savedGame = gameController.saveGame1(gameName);

        if (savedGame != null) {
            Team newTeam = new Team();
            newTeam.setName(teamName);
            newTeam.setGame(savedGame);

            if (teamController.saveTeam(newTeam)) {
                teamItems.add(newTeam);
                teamTable.refresh();
            }
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


        }
        teamTable.refresh();
    }


    @FXML
    protected void AddPlayerButtonAction(ActionEvent e) {
        if (!(firstName.getText().isEmpty() || lastName.getText().isEmpty() || nickName.getText().isEmpty())) {

            Player player = new Player();
            player.setPerson(new Person());
            PlayerController playerController = new PlayerController();
            player.getPerson().setFirstName(firstName.getText());
            player.getPerson().setLastName(lastName.getText());
            player.getPerson().setNickName(nickName.getText());
            playerController.savePlayer(player);

            TeamController teamController = new TeamController();
            String teamName = teamTextField.getText();


            Team team = teamController.getTeamByOneName(teamName);

            if (team != null) {
                player.setTeam(team);
                playerController.update(player);
            }
        }
    }

    @FXML
    void DeletePlayerButton(ActionEvent e) {
        PlayerController playerController = new PlayerController();
        String playerName = firstName.getText();

        if (playerName.isEmpty())
            return;

        boolean isDeleted = playerController.deletePlayerByName(playerName);

        if (isDeleted) {
            ObservableList<Player> playerList = playerTable.getItems();
            List<Player> playersToRemove = new ArrayList<>();

            for (Player player : playerList) {
                if (player.getPerson().getFirstName().equals(playerName) ||
                        player.getPerson().getLastName().equals(playerName) ||
                        player.getPerson().getNickName().equals(playerName)) {
                    playersToRemove.add(player);
                }
            }

            playerList.removeAll(playersToRemove);
            playerTable.refresh();
        }
    }



    @FXML
    protected void updateAllTables(ActionEvent e) {
        updateTeamTable();
        updatePlayerTable();
    }

    private void updateTeamTable() {
        List<Team> teams = new TeamController().getAllTeams(true);
        teamItems.clear();
        teamItems.addAll(teams);
        teamTable.refresh();
    }

    private void updatePlayerTable() {
        List<Player> players = new PlayerController().getAllPlayer(true);
        playerTable.getItems().clear();
        playerTable.getItems();
        playerTable.refresh();
    }
}
