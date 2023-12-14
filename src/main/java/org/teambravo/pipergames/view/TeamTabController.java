package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.controller.GameController;
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
    private TableColumn<Team, String> Spel;
    @FXML
    private TableColumn<Team, String> Lag;
    @FXML
    private TableColumn<Person, String> Spelare;
    @FXML
    private TableView<Team> userTable;
    // @FXML
    // private TableView<Game> userTable1;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Team> teams = new TeamController().getAllTeams(true);
        ObservableList<Team> teamItems = FXCollections.observableList(teams);

        Spel.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getGame().getName()));
        Lag.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));
        //  Spelare.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getFirstName()));



        userTable.setItems(teamItems);
        // userTable1.setItems(gameItems);

    }
}
