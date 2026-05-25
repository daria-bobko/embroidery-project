package university.ua.embroideryproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;

public class Main extends Application {
public static Group rootMenu = new Group();
public static Group rootMain = new Group();
public static int ROWS = 30;
public static int COLS = 30;
public static int CELL_SIZE = 23;
public static ColorPicker colorForEmbroidery;
public static Color [][]grid = new Color [ROWS][COLS];
public static Scene menuScene;
public static Scene mainScene;


public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        menuScene = new Scene(rootMenu, Color.rgb(252, 243, 224));
        MenuScene.menuSceneProperties(stage, rootMenu);
        stage.setScene(menuScene);
        stage.show();

        mainScene = new Scene(rootMain, Color.rgb(252, 243, 224));
        MainScene.mainSceneProperties(stage, rootMain);
        stage.show();


        EmbroideryCanvas.mouseHandle();

//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//
//        // 2. Завантажуємо HTML-файл із папки ресурсів
//        URL url = getClass().getResource("/html/vyshyvanka.html");
//
//        if (url != null) {
//            webEngine.load(url.toExternalForm());
//        } else {
//            System.out.println("Помилка: Файл index.html не знайдено в ресурсах!");
//        }
//
//        // 3. Кладемо браузер у контейнер та відображаємо вікно
//        BorderPane root = new BorderPane(webView);
//        Scene scene = new Scene(root, 900, 600); // Розмір вікна під наш сайт
//
//        stage.setTitle("Довідка: Майстерня Вишивки");
//        stage.setScene(scene);
//        stage.show();
   }

}

