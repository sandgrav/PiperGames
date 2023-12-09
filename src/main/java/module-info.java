module org.teambravo.pipergames {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.teambravo.pipergames to javafx.fxml;
    exports org.teambravo.pipergames;
}