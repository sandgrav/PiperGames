package org.teambravo.pipergames;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.teambravo.pipergames.controller.TeamClassController;
import org.teambravo.pipergames.view.SceneClass;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Personal-scene.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene = SceneClass.load(stage);
        stage.setTitle("Piper Games");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        TeamClassController teamClassController = new TeamClassController();
//        Menu menu = new Menu();
//        menu.showMenu(teamClassController);
        launch();
    }
}