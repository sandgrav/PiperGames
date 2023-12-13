package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.teambravo.pipergames.controller.StaffController;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StaffTabController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private TableView<Staff> staffTableView;
    @FXML
    private TableColumn<Staff, String> firstNameColumn;
    @FXML
    private TableColumn<Staff, String> lastNameColumn;
    @FXML
    private TableColumn<Staff, String> nickNameColumn;
    @FXML
    private TableColumn<Staff, String> loggedInColumn;
    private Staff currentStaff;
    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {
        if (currentStaff != null) {
            currentStaff.setLoggedIn(false);
            StaffController staffController = new StaffController();
            staffController.update(currentStaff);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/teambravo/pipergames/login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Piper Games");
            stage.setResizable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getLastName()));
        nickNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getNickName()));
        loggedInColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isLoggedIn() ? "Ja" : "Nej"));
        loadStaffData();
    }

    private void loadStaffData() {
        StaffController staffController = new StaffController();
        List<Staff> staffList = staffController.getAllStaff(false);
        ObservableList<Staff> staffData = FXCollections.observableArrayList(staffList);
        staffTableView.setItems(staffData);

    }
}

