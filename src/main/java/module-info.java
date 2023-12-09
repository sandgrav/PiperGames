module org.teambravo.pipergames {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;

    opens org.teambravo.pipergames.entity to org.hibernate.orm.core;

    opens org.teambravo.pipergames to javafx.fxml;
    exports org.teambravo.pipergames;
    exports org.teambravo.pipergames.controller;
    opens org.teambravo.pipergames.controller to javafx.fxml;
}