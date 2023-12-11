package org.teambravo.pipergames.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.teambravo.pipergames.entity.Game;
import org.teambravo.pipergames.entity.TeamClass;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.List;


public class LogInScreen extends Application  {


        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Log in screen");

            Label textLabel = new Label("Log in screen");

            Button button1 = new Button("Log in as - person 1");
            Button button2 = new Button("Log in as - person 2");
            Button button3 = new Button("Log in as - person 3");
            Button button4 = new Button("Log in as - person 4");
            Button button5 = new Button("Log in as - person 5");
            Button button6 = new Button("Log in as - person 6");

            TilePane tilePane = new TilePane();

            tilePane.getChildren().add(button1);
            tilePane.getChildren().add(button2);
            tilePane.getChildren().add(button3);
            tilePane.getChildren().add(button4);
            tilePane.getChildren().add(button5);
            tilePane.getChildren().add(button6);



            ChoiceBox choiceBox = new ChoiceBox();
            tilePane.getChildren().add(choiceBox);

            tilePane.getChildren().addAll(textLabel);

            tilePane.setTileAlignment(Pos.TOP_LEFT);

            Scene scene = new Scene(tilePane, 300, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            Application.launch(args);
        }

        public void ShowPersonal(MouseEvent mouseEvent) {
            // List all options of whom to log in as

        }
    }

