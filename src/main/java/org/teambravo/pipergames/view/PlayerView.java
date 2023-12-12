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
import javafx.scene.control.TextField;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.controller.TeamClassController;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.Team;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerView implements Initializable {
    @FXML
    private Button allPlayersButton;
    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> teamNameCol;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> firstNameCol;
    @FXML
    private TableColumn<Player, String> lastNameCol;
    @FXML
    private TableColumn<Player, String> nickNameCol;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField nickNameText;

    @FXML
    protected void handleAllPlayersButtonAction(ActionEvent e) {
        List<Player> players = new PlayerController().getAllPlayer(false);
        ObservableList<Player> items = FXCollections.observableList(players);
        firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
        lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
        playerTable.setItems(items);
    }

    @FXML
    protected void handleAllPlayersInTeamsButtonAction(ActionEvent e) {
        List<Team> teams = new TeamClassController().getAllTeams(false);
        ObservableList<Team> items = FXCollections.observableList(teams);
        teamNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));
        teamTable.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Team> teamSelectedItems = teamTable.getSelectionModel().getSelectedItems();
        teamSelectedItems.addListener(
                new ListChangeListener<Team>() {
                    @Override
                    public void onChanged(Change<? extends Team> change) {
                        playerTable.getItems().clear();
                        for (Team team:
                                teamTable.getSelectionModel().getSelectedItems()) {
                            List<Player> players = team.getPlayers();
                            ObservableList<Player> items = FXCollections.observableList(players);
                            firstNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getFirstName()));
                            lastNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getLastName()));
                            nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
                            playerTable.getItems().addAll(items);
                        }
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
                            firstNameText.setText(player.getPerson().getFirstName());
                            lastNameText.setText(player.getPerson().getLastName());
                            nickNameText.setText(player.getPerson().getNickName());
                        }
                    }
                }
        );
    }
}
