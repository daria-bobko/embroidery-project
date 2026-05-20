package university.ua.embroideryproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
public static Group rootMenu = new Group();
public static Group rootMain = new Group();
public static int ROWS = 30;
public static int COLS = 30;
public static int CELL_SIZE = 23;
public static ColorPicker colorForEmbroidery;
public static int grid[][] = new int [ROWS][COLS];
public static Scene menuScene;
public static Scene mainScene;


public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        menuScene = new Scene(rootMenu, Color.rgb(252, 225, 189));
        MenuScene.menuSceneProperties(stage, rootMenu);
        stage.setScene(menuScene);
        stage.show();

        mainScene = new Scene(rootMain, Color.rgb(252, 225, 189));
        MainScene.mainSceneProperties(stage, rootMain);
        stage.show();

        EmbroideryCanvas.mouseHandle();

}
}
