package university.ua.embroideryproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class WebsiteOpen {
    public static void openHelpScene(Stage stage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        URL url = WebsiteOpen.class.getResource("/html/vyshyvanka.html");
        if (url != null) {
            webEngine.load(url.toExternalForm());
        } else {
            System.err.println("файл не знайдено");
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(webView);

        Button buttonMenu = new Button("ДО МЕНЮ");
        buttonMenu.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        buttonMenu.setPrefSize(180 , 61);
        buttonMenu.setLayoutX(10);

        buttonMenu.setOnAction(event -> {
            stage.setScene(Main.menuScene);
        });

        Button buttonMain = new Button("СТВОРИТИ");
        buttonMain.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 22px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        buttonMain.setPrefSize(180 , 61);
        buttonMain.setLayoutX(200);
        buttonMain.setOnAction(event -> {
            stage.setScene(Main.mainScene);
        });
        HBox buttonBox = new HBox(800);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(buttonMenu, buttonMain);


        borderPane.setTop(buttonBox);


        Scene sceneSymbols = new Scene(borderPane, 900, 600);
        stage.setScene(sceneSymbols);
        stage.setTitle("Про символи української вишивки");
    }
}
