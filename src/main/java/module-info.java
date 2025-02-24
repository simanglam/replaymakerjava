module com.simanglam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.graphics;

    requires transitive com.fasterxml.jackson.core;
    requires transitive  com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    opens com.simanglam to javafx.fxml;
    opens com.simanglam.controller to javafx.fxml;
    exports com.simanglam;
    exports com.simanglam.controller;
    exports com.simanglam.model;
}
