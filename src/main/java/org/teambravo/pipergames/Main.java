package org.teambravo.pipergames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.teambravo.pipergames.controller.TeamClassController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("SampleLogIn.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        SampleLogIn controller = loader.getController();
        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(scene);

        controller.setStage(primaryStage);
        primaryStage.show();
        //stage.setTitle("Hello!");
        // stage.setScene(scene);
        // stage.show();
    }

    public static void main(String[] args) {
        TeamClassController teamClassController = new TeamClassController();
//        Menu menu = new Menu();
//        menu.showMenu(teamClassController);
        launch(args);
    }
}