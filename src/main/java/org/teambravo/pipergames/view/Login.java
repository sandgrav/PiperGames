package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.controller.StaffController;
import org.teambravo.pipergames.entity.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    public Button loginButton;
    @FXML
    private TableView<Staff> userTable;
    @FXML
    private TableColumn<Staff, Integer> idCol;
    @FXML
    private TableColumn<Staff, String> nickNameCol;

    @FXML
    protected void handleLoginButtonAction(ActionEvent e) throws IOException {
        Staff staff = userTable.getSelectionModel().getSelectedItem();
        if (staff != null) {
            StaffController staffController = new StaffController();
            staff.setLoggedIn(true);
            staffController.update(staff);
            SceneClass.setRoot("mainWindow");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Staff> staff = new StaffController().getAllStaff(false);
        ObservableList<Staff> items = FXCollections.observableList(staff);
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nickNameCol.setCellValueFactory(tf -> new SimpleStringProperty(tf.getValue().getPerson().getNickName()));
        userTable.setItems(items);
    }
}
