package org.teambravo.pipergames.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.SampleLogIn;
import org.teambravo.pipergames.entity.Game;
import org.teambravo.pipergames.entity.TeamClass;

public class MainScreen {

    @FXML
    private Label welcomeText;

    @FXML
    private TabPane tabPane;

    private TableView<TeamClass> teamTable;
    private SampleLogIn sampleLogIn;

    public void initialize() {
        // Add tabs to the TabPane
        Tab tab1 = new Tab("Team Table");
        Tab tab2 = new Tab("Other Content");

        // Create a VBox to hold content for tab1 (Team Table)
        VBox content1 = new VBox();

        // TextField to set team name
        TextField teamNameField = new TextField();
        teamNameField.setPromptText("Enter Team Name");
        teamNameField.setMaxWidth(121);

        // Button to populate the table with the entered name
        Button populateButton = new Button("Populate Table");
        populateButton.setPrefHeight(100);
        populateButton.setLayoutX(50);
        Button populateButton1 = new Button("Populate Table");
        Button populateButton2 = new Button("Populate Table");
        populateButton.setOnAction(e -> populateTable(teamNameField.getText()));

        // Create the TableView to display team details
        teamTable = new TableView<>();
        TableColumn<TeamClass, String> nameColumn = new TableColumn<>("Team Name");
        TableColumn<TeamClass, Integer> game_id = new TableColumn<>("Game ID");
        TableColumn<TeamClass, Integer> team_id = new TableColumn<>("Team ID");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // teamTable.getColumns().add(nameColumn);
        // teamTable.getColumns().add(game_id);
        teamTable.getColumns().addAll(nameColumn,game_id,team_id);

        // Add components to the content VBox
        content1.getChildren().addAll(teamNameField, populateButton, teamTable);
        tab1.setContent(content1);

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(tab1, tab2);
    }


    private void populateTable(String teamName) {
        TeamClass team = new TeamClass(teamName);
        TeamClass game_id = new TeamClass();
        teamTable.getItems().add(team);
        // teamTable.getItems().add()
        team.setName("1");
        team.setTeam_id(1);
        team.setGame(new Game());
    }
    @FXML
    public void closeProgram(ActionEvent actionEvent) {
    }
}
