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
    private static Staff currentStaff;
    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {
        System.out.println("knappen är tryckt");
        System.out.println("inloggad användare " + currentStaff.getId());
        try {
            if (currentStaff != null) {
                currentStaff.setLoggedIn(null);
                StaffController staffController = new StaffController();
                staffController.update(currentStaff);
                System.out.println("loggar ut användare " + currentStaff.getId());

                SceneClass.setRoot("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static Staff getCurrentStaff() {
        return currentStaff;
    }

    public static void setCurrentStaff(Staff currentStaff) {
        StaffTabController.currentStaff = currentStaff;
    }
}

