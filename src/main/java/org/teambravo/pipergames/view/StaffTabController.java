package org.teambravo.pipergames.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.teambravo.pipergames.controller.StaffController;
import org.teambravo.pipergames.controller.PersonController;
import org.teambravo.pipergames.entity.Person;
import org.teambravo.pipergames.entity.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffTabController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button editStaffButton;
    @FXML
    private Button deleteStaffButton;
    @FXML
    private Button addStaffButton;
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
    protected void handleAddStaffButton(ActionEvent e) {
        if (!(firstName.getText().isEmpty() || lastName.getText().isEmpty() || nickName.getText().isEmpty())) {
            Staff staff = new Staff();
            staff.setPerson(new Person());
            StaffController staffController = new StaffController();
            staff.getPerson().setFirstName(firstName.getText());
            staff.getPerson().setLastName(lastName.getText());
            staff.getPerson().setNickName(nickName.getText());
            staff.getPerson().setAddress(address.getText());
            staff.getPerson().setPostalCode(postalCode.getText());
            staff.getPerson().setCity(city.getText());
            staff.getPerson().setCountry(country.getText());
            staff.getPerson().setEmail(email.getText());
            staffController.save(staff);
            loadStaffData();
        }
    }

    @FXML
    private void handleEditStaffButton(ActionEvent event) {
        Staff selectedStaff = staffTableView.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            updateStaffDetails(selectedStaff);
        }
    }

    @FXML
    private void handleDeleteStaffButton(ActionEvent event) {
        Staff selectedStaff = staffTableView.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean confirmed = showConfirmationDialog("Delete Staff", "Are you sure you want to delete this staff member?");
            if (confirmed) {
                deleteStaff(selectedStaff);
            }
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
        } );
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

    private boolean showConfirmationDialog(String title, String message) {
        // Create and display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void deleteStaff(Staff staff) {
        StaffController staffController = new StaffController();
        if (staffController.deleteStaff(staff.getId())) {
            loadStaffData(); // Refresh TableView
        } else {
            // Handle delete failure
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

