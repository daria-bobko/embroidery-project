package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScene {
    public static  Button returnToMenu2;
    public static Button returnToMenu;
    public static Button resizeCanvas;
    public static void mainSceneProperties(Stage stage, Group root2) {
        returnToMenu = new Button("RETURN TO MENU");
        returnToMenu.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        returnToMenu.setPrefSize(280, 61);
        returnToMenu.setLayoutX(1006 );
        returnToMenu.setLayoutY(500);
        root2.getChildren().add(returnToMenu);

        returnToMenu.setOnAction(
                (m) -> stage.setScene(Main.menuScene));

        EmbroideryCanvas.drawEmbroideryCanvas();

        returnToMenu2 = new Button("RETURN TO MENU");
        returnToMenu2.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        returnToMenu2.setPrefSize(280, 61);
        returnToMenu2.setLayoutX(0);
        returnToMenu2.setLayoutY(100);
        root2.getChildren().add(returnToMenu2);
        returnToMenu2.setOnAction(
                (m) -> stage.setScene(Main.menuScene));

        Text textForColor = new Text("choose color :");
        textForColor.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        textForColor.setX(0);
        textForColor.setY(380);
        root2.getChildren().add(textForColor);

        Main.colorForEmbroidery = new ColorPicker();
        Main.colorForEmbroidery.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        Main.colorForEmbroidery.setLayoutX(0);
        Main.colorForEmbroidery.setLayoutY(400);
        Main.colorForEmbroidery.setPrefSize(280, 61);
        root2.getChildren().add(Main.colorForEmbroidery);

        resizeCanvas = new Button("RESIZE CANVAS");
        resizeCanvas.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        resizeCanvas.setPrefSize(280, 61);
        resizeCanvas.setLayoutX(0);
        resizeCanvas.setLayoutY(500);

        root2.getChildren().addAll(resizeCanvas);
        resizeCanvas.setOnAction(
                (sc) ->  ResizeCanvas.sizeStage.show()
        );


    }
}
