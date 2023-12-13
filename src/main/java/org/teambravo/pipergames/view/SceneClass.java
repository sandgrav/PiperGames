package org.teambravo.pipergames.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.teambravo.pipergames.Main;

import java.io.IOException;

public class SceneClass {
    private static Scene scene;

    public static Scene load(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("soloMatchTab.fxml"));
        scene = new Scene(fxmlLoader.load());
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
