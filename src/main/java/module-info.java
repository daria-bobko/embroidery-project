module university.ua.embroideryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires jdk.compiler;
    requires javafx.web;


    opens university.ua.embroideryproject to javafx.fxml;
    exports university.ua.embroideryproject;
}