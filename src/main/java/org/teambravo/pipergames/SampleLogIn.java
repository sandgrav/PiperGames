package org.teambravo.pipergames;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SampleLogIn implements Initializable {

    private double x = 0,y = 0;

    @FXML
    private AnchorPane sideBar;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sideBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        sideBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void closeProgram(ActionEvent event) {
        stage.close();
    }

    public void openMainView(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main-Screen.fxml"));
            Parent root = loader.load();

            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);

            secondaryStage.setScene(scene);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);


            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}