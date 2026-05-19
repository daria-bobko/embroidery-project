module university.ua.embroideryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens university.ua.embroideryproject to javafx.fxml;
    exports university.ua.embroideryproject;
}