package org.teambravo.pipergames;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.teambravo.pipergames.controller.TeamController;
import org.teambravo.pipergames.view.SceneClass;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = SceneClass.load(stage);
        stage.setTitle("Piper Games");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}