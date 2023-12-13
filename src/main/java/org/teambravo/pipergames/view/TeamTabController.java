package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.controller.TeamController;
import org.teambravo.pipergames.entity.Team;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamTabController implements Initializable {

    @FXML
    private TableColumn<Team, Integer> idCol;
    @FXML
    private TableColumn<Team, String> nickNameCol;
    @FXML
    private TableView<Team> userTable;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Team> matches = new TeamController().getAllTeams(true);
        ObservableList<Team> items = FXCollections.observableList(matches);
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getName()));
        userTable.setItems(items);
    }
}
