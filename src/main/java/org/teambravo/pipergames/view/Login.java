package org.teambravo.pipergames.view;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.controller.StaffClassController;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.StaffClass;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    public Button loginButton;
    @FXML
    private TableView<StaffClass> userTable;
    @FXML
    private TableColumn<StaffClass, Integer> idCol;
    @FXML
    private TableColumn<StaffClass, String> nickNameCol;

    @FXML
    protected void handleLoginButtonAction(ActionEvent e) {
        StaffClass staff = userTable.getSelectionModel().getSelectedItem();
        if (staff != null) {
//            staff.
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<StaffClass> staff = new StaffClassController().getAllStaff(false);
        ObservableList<StaffClass> items = FXCollections.observableList(staff);
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
        userTable.setItems(items);
    }
}
