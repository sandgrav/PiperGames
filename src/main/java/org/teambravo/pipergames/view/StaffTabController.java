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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.teambravo.pipergames.controller.PlayerController;
import org.teambravo.pipergames.controller.StaffController;
import org.teambravo.pipergames.controller.PersonController;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.Player;
import org.teambravo.pipergames.entity.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StaffTabController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button editStaffButton;
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
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField nickName;
    @FXML
    private TextField address;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
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

    @FXML
    private void handleEditStaffDataButton(ActionEvent event) {
        Staff selectedStaff = staffTableView.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            updateStaffDetails(selectedStaff);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getLastName()));
        nickNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getNickName()));
        loggedInColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isLoggedIn() ? "Ja" : "Nej"));
        loadStaffData();

        staffTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bindStaffToFields(newSelection);
            }
        });
    }

    private void updateStaffDetails(Staff staff) {
        Person person = staff.getPerson();
        person.setFirstName(firstName.getText());
        person.setLastName(lastName.getText());
        person.setNickName(nickName.getText());
        person.setAddress(address.getText());
        person.setPostalCode(postalCode.getText());
        person.setCity(city.getText());
        person.setCountry(country.getText());
        person.setEmail(email.getText());

        StaffController staffController = new StaffController();
        PersonController personController = new PersonController();

        if (personController.updatePerson(person)) {
            loadStaffData(); // Refresh TableView
        } else {
            System.out.println("Kunde ej ändra uppgift");
        }
    }

    private void loadStaffData() {
        StaffController staffController = new StaffController();
        List<Staff> staffList = staffController.getAllStaff(false);
        ObservableList<Staff> staffData = FXCollections.observableArrayList(staffList);
        staffTableView.setItems(staffData);

    }

    private void bindStaffToFields(Staff staff) {
        Person person = staff.getPerson();
        firstName.setText(person.getFirstName()); // Bind other fields similarly
    }

    public static Staff getCurrentStaff() {
        return currentStaff;
    }

    public static void setCurrentStaff(Staff currentStaff) {
        StaffTabController.currentStaff = currentStaff;
    }
}
