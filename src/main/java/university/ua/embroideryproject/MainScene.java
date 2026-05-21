package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScene {
    public static  Button savePicture;
    public static Button returnToMenu;
    public static Button resizeCanvas;
    public static ToggleButton fillBackground;
    public static ToggleButton eraser;
    public static ToggleButton pencil;
    public static CheckBox horizontal;
    public static CheckBox vertical;
    public static Label width;
    public static Label height;
    public static TextField inputColumns;
    public static  TextField inputRows;
    public static Button duplicate;
    public static void mainSceneProperties(Stage stage, Group root2) {

        returnToMenuButton(stage, root2);
        EmbroideryCanvas.drawEmbroideryCanvas();
        savePictureButton( root2);
        colorForEmbroideryPicker( root2);
        resizeCanvasButton( root2);
        fillBackgroundToggleButton(root2);
        eraserToggleButton( root2);
        pencilToggleButton(root2);
        horizontalSymetricCheckBox(root2);
        verticalSymetricCheckBox(root2);
        duplicatePatternButton(root2);


        Utils.toggleButtonAction(pencil, eraser, fillBackground);
        Utils.toggleButtonAction(fillBackground, pencil, eraser);
        Utils.toggleButtonAction(eraser, pencil, fillBackground);


    }

    private static void duplicatePatternButton(Group root2) {
        duplicate = new Button("дублювати шаблон");
        duplicate.setLayoutX(0);
        duplicate.setLayoutY(430);
        duplicate.setPrefSize(280, 40);
        buttonStyle(duplicate, root2);
        duplicate.setOnAction(
                (d) -> EmbroideryCanvas.duplicatePattern()
        );

    }

    private static void verticalSymetricCheckBox(Group root2) {
        vertical = new CheckBox("вертикальна");
        vertical.setStyle("-fx-border-color:  #b52302;" +
                "-fx-text-fill:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        vertical.setLayoutX(0);
        vertical.setLayoutY(300);
        vertical.setPrefSize(280, 40);
        root2.getChildren().add(vertical);


    }

    private static void horizontalSymetricCheckBox(Group root2) {
        horizontal = new CheckBox("горизонтальна");
        horizontal.setStyle("-fx-border-color:  #b52302;" +
                "-fx-text-fill:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        horizontal.setLayoutX(0);
        horizontal.setLayoutY(360);
        horizontal.setPrefSize(280, 40);
        root2.getChildren().add(horizontal);

    }

    private static void pencilToggleButton( Group root2) {
        pencil = new ToggleButton();
        pencil.setSelected(true);
        pencil.setLayoutX(195);
        pencil.setLayoutY(200);
        Image iconForPencil = new Image(MainScene.class.getResourceAsStream("/pencil.png"));
        pencil.setGraphic(new javafx.scene.image.ImageView(iconForPencil));
        pencil.setPrefSize(85, 61);
        toggleButtonStyle(pencil, root2);
    }

    private static void eraserToggleButton( Group root2) {
        eraser = new ToggleButton();
        eraser.setLayoutX(97);
        eraser.setLayoutY(200);
        Image iconForEraser = new Image(MainScene.class.getResourceAsStream("/eraser.png"));
        eraser.setGraphic(new javafx.scene.image.ImageView(iconForEraser));
        eraser.setPrefSize(85, 61);
        toggleButtonStyle(eraser, root2);
    }

    private static void fillBackgroundToggleButton(Group root2) {
        fillBackground = new ToggleButton();
        fillBackground.setLayoutX(0);
        fillBackground.setLayoutY(200);
        Image iconForFill = new Image(MainScene.class.getResourceAsStream("/fill.png"));
        fillBackground.setGraphic(new javafx.scene.image.ImageView(iconForFill));
        fillBackground.setPrefSize(84, 61);
        toggleButtonStyle(fillBackground, root2);
    }

    private static void resizeCanvasButton( Group root2) {
        resizeCanvas = new Button("RESIZE CANVAS");
        resizeCanvas.setPrefSize(280, 61);
        resizeCanvas.setLayoutX(0);
        resizeCanvas.setLayoutY(500);
        buttonStyle(resizeCanvas, root2);
        width = new Label("Введіть кількість стовпчиків (1-100) :");
        inputColumns = new TextField("30");
        height = new Label("Введіть кількість рядків (1-100) :");
        inputRows = new TextField("30");
        resizeCanvas.setOnAction(
                (sc) ->  ResizeCanvas.resize()
        );
    }

    private static void colorForEmbroideryPicker(Group root2) {
        Text textForColor = new Text("choose color :");
        textForColor.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        textForColor.setX(0);
        textForColor.setY(70);
        root2.getChildren().add(textForColor);

        Main.colorForEmbroidery = new ColorPicker();
        Main.colorForEmbroidery.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        Main.colorForEmbroidery.setLayoutX(0);
        Main.colorForEmbroidery.setLayoutY(100);
        Main.colorForEmbroidery.setPrefSize(280, 61);
        root2.getChildren().add(Main.colorForEmbroidery);
    }

    private static void savePictureButton(Group root2) {
        savePicture = new Button("SAVE");
        savePicture.setPrefSize(280, 61);
        savePicture.setLayoutX(1006);
        savePicture.setLayoutY(100);
        buttonStyle(savePicture, root2);

    }

    private static void returnToMenuButton(Stage stage, Group root2) {
        returnToMenu = new Button("RETURN TO MENU");
        returnToMenu.setPrefSize(280, 61);
        returnToMenu.setLayoutX(1006 );
        returnToMenu.setLayoutY(500);
        buttonStyle(returnToMenu, root2);

        returnToMenu.setOnAction(
                (m) -> stage.setScene(Main.menuScene));
    }


    private static void buttonStyle(Button button, Group root2){
        button.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        root2.getChildren().add(button);
    }

    private static void toggleButtonStyle(ToggleButton button, Group root2){
        button.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        root2.getChildren().add(button);
    }


}
