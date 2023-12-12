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
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.StaffClass;
import org.teambravo.pipergames.entity.TeamClass;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerView implements Initializable {
    @FXML
    private TableView<TeamClass> teamTable;
    @FXML
    private TableColumn<Player, String> teamNameCol;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TeamClass> teamSelectedItems = teamTable.getSelectionModel().getSelectedItems();
        ObservableList<Player> playerSelectedItems = playerTable.getSelectionModel().getSelectedItems();
        teamSelectedItems.addListener(
                new ListChangeListener<TeamClass>() {
                    @Override
                    public void onChanged(Change<? extends TeamClass> change) {
                        if (change.getList().size() == 1) {
                            TeamClass team = change.getList().get(0);

                        }
                    }
                }
        );
        playerSelectedItems.addListener(
                new ListChangeListener<Player>() {
                    @Override
                    public void onChanged(Change<? extends Player> change) {
                        playerTable.getItems().clear();
                        for (TeamClass team:
                            teamTable.getSelectionModel().getSelectedItems()) {
                            //team.g
                        }
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
