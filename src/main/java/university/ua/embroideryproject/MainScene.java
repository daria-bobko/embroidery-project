package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    public static Button duplicate;
    public static Button loadPicture;
    public static ToggleButton eyedropper;
    public static Slider pencilSize;
    public static Button nullCanvas;
    public static Text forPencil;
    public static Text forSymetric;
    public static int valueForPencil =1;
    public static Text instructionForFill = new Text("натисність двічі на полотно, щоб заповнити його кольором");
    public static Text getInstructionForErasser = new Text("клік мишкою, або затисніть і ведіть, щоб стерти");
    public static Text getInstructionForPencil = new Text("клік мишкою, або затисніть і ведіть, щоб малювати");
    public static Text getInstructionForEyedropper = new Text("клік мишкою, щоб вибрати колір з полотна");
    public static Text getInstructionForSymetricV = new Text("малюнок симетрично осі У");
    public static Text getInstructionForSymetricH = new Text("малюнок симетрично осі Х");
    public static Text getInstructionForDuplicate = new Text("скопіювати поточний патерн, оберіть симетрію");

    public static void mainSceneProperties(Stage stage, Group root2) {
        returnToMenuButton(stage, root2);
        EmbroideryCanvas.drawEmbroideryCanvas();
        savePictureButton( root2);
        colorForEmbroideryPicker( root2);
        resizeCanvasButton( root2);
        fillBackgroundToggleButton(root2);
        eraserToggleButton( root2);
        pencilToggleButton(root2);
        textForSymetric(root2);
        horizontalSymetricCheckBox(root2);
        verticalSymetricCheckBox(root2);
        duplicatePatternButton(root2);
        loadPictureButton(root2);
        eyedropperButton(root2);
        pencilSizeSlider(root2);
        nullCanvasButton(root2);
        forPencilText(root2);
        Utils.textInstruction(MainScene.getInstructionForPencil, MainScene.getInstructionForSymetricV,
                MainScene.getInstructionForDuplicate, MainScene.getInstructionForErasser, MainScene.getInstructionForEyedropper,
                MainScene.instructionForFill, MainScene.getInstructionForSymetricH);



        Utils.toggleButtonAction(pencil, eraser, fillBackground, eyedropper);
        Utils.toggleButtonAction(fillBackground, pencil, eraser, eyedropper);
        Utils.toggleButtonAction(eraser, pencil, fillBackground, eyedropper);
        Utils.toggleButtonAction(eyedropper, pencil, eraser, fillBackground);


    }

    private static void nullCanvasButton(Group root2) {
            nullCanvas = new Button("ОЧИСТИТИ ПОЛОТНО");
            nullCanvas.setPrefSize(280, 61);
            nullCanvas.setLayoutX(1006);
            nullCanvas.setLayoutY(300);
            buttonStyle(nullCanvas, root2, 17);
            nullCanvas.setOnAction(
                    (l) -> EmbroideryCanvas.drawEmbroideryCanvas()
            );
        }

    private static void forPencilText(Group root2) {
        forPencil = new Text("розмір пензля : 1");
        forPencil.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        forPencil.setX(0);
        forPencil.setY(300);
        root2.getChildren().add(forPencil);
    }

    private static void textForSymetric(Group root2) {
        forSymetric = new Text("симетрія :");
        forSymetric.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        forSymetric.setX(0);
        forSymetric.setY(400);
        root2.getChildren().add(forSymetric);
    }

    private static void pencilSizeSlider(Group root2) {
        pencilSize = new Slider(1, 4, 1);
        pencilSize.setShowTickLabels(true);
        pencilSize.setShowTickMarks(true);
        pencilSize.setMajorTickUnit(1);
        pencilSize.setMinorTickCount(0);
        pencilSize.setSnapToTicks(true);
        pencilSize.setLayoutX(0);
        pencilSize.setLayoutY(330);
        pencilSize.setPrefWidth(280);
        pencilSize.setStyle("-fx-tick-label-font-size: 50px; -fx-tick-label-font-weight: bold;");
        root2.getChildren().add(pencilSize);
        pencilSize.valueProperty().addListener((observable, oldValue, newValue) -> {
            valueForPencil = newValue.intValue();
            forPencil.setText("розмір пензля : " + valueForPencil);
        });

    }

    private static void eyedropperButton(Group root2) {
        eyedropper = new ToggleButton();
        Image iconForEyedropper = new Image(MainScene.class.getResourceAsStream("/eyedropper.png"));
        eyedropper.setGraphic(new javafx.scene.image.ImageView(iconForEyedropper));
        eyedropper.setPrefSize(85, 61);
        eyedropper.setLayoutX(195);
        eyedropper.setLayoutY(100);
        toggleButtonStyle(eyedropper, root2);

    }

    private static void loadPictureButton(Group root2) {
        loadPicture = new Button("ЗАВАНТАЖИТИ З ФАЙЛІВ");
        loadPicture.setPrefSize(280, 61);
        loadPicture.setLayoutX(1006);
        loadPicture.setLayoutY(170);
        buttonStyle(loadPicture, root2, 17);
        loadPicture.setOnAction(
                (l) -> WorkWithPictures.loadPNG()
        );
    }


    private static void duplicatePatternButton(Group root2) {
        duplicate = new Button("дублювати шаблон");
        duplicate.setLayoutX(0);
        duplicate.setLayoutY(540);
        duplicate.setPrefSize(280, 40);
        buttonStyle(duplicate, root2,22);


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
        vertical.setLayoutY(420);
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
        horizontal.setLayoutY(480);
        horizontal.setPrefSize(280, 40);
        root2.getChildren().add(horizontal);

    }

    private static void pencilToggleButton( Group root2) {
        pencil = new ToggleButton();
        pencil.setSelected(true);
        pencil.setLayoutX(195);
        pencil.setLayoutY(180);
        Image iconForPencil = new Image(MainScene.class.getResourceAsStream("/pencil.png"));
        pencil.setGraphic(new javafx.scene.image.ImageView(iconForPencil));
        pencil.setPrefSize(85, 61);
        toggleButtonStyle(pencil, root2);
    }

    private static void eraserToggleButton( Group root2) {
        eraser = new ToggleButton();
        eraser.setLayoutX(97);
        eraser.setLayoutY(180);
        Image iconForEraser = new Image(MainScene.class.getResourceAsStream("/eraser.png"));
        eraser.setGraphic(new javafx.scene.image.ImageView(iconForEraser));
        eraser.setPrefSize(85, 61);
        toggleButtonStyle(eraser, root2);
    }

    private static void fillBackgroundToggleButton(Group root2) {
        fillBackground = new ToggleButton();
        fillBackground.setLayoutX(0);
        fillBackground.setLayoutY(180);
        Image iconForFill = new Image(MainScene.class.getResourceAsStream("/fill.png"));
        fillBackground.setGraphic(new javafx.scene.image.ImageView(iconForFill));
        fillBackground.setPrefSize(84, 61);
        toggleButtonStyle(fillBackground, root2);
    }

    private static void resizeCanvasButton( Group root2) {
        resizeCanvas = new Button("РОЗМІР ПОЛОТНА");
        resizeCanvas.setPrefSize(280, 61);
        resizeCanvas.setLayoutX(0);
        resizeCanvas.setLayoutY(650);
        buttonStyle(resizeCanvas, root2, 22);
        ResizeCanvas.width = new Label("Введіть кількість стовпчиків (1-100) :");
        ResizeCanvas.inputColumns = new TextField("30");
        ResizeCanvas.height = new Label("Введіть кількість рядків (1-100) :");
        ResizeCanvas.inputRows = new TextField("30");
        resizeCanvas.setOnAction(
                (sc) ->  ResizeCanvas.resize()
        );
    }

    private static void colorForEmbroideryPicker(Group root2) {
        Text textForColor = new Text("колір :");
        textForColor.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        textForColor.setX(0);
        textForColor.setY(80);
        root2.getChildren().add(textForColor);

        Main.colorForEmbroidery = new ColorPicker(Color.DARKRED);
        Main.colorForEmbroidery.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        Main.colorForEmbroidery.setLayoutX(0);
        Main.colorForEmbroidery.setLayoutY(100);
        Main.colorForEmbroidery.setPrefSize(182, 68);
        root2.getChildren().add(Main.colorForEmbroidery);
    }

    private static void savePictureButton(Group root2) {
        savePicture = new Button("ЗБЕРЕГТИ СХЕМУ");
        savePicture.setPrefSize(280, 61);
        savePicture.setLayoutX(1006);
        savePicture.setLayoutY(100);
        buttonStyle(savePicture, root2, 17);

        savePicture.setOnAction(
                (s) -> WorkWithPictures.saveAsPNG()
        );



    }

    private static void returnToMenuButton(Stage stage, Group root2) {
        returnToMenu = new Button("ДО МЕНЮ");
        returnToMenu.setPrefSize(280, 61);
        returnToMenu.setLayoutX(1006 );
        returnToMenu.setLayoutY(500);
        buttonStyle(returnToMenu, root2, 24);

        returnToMenu.setOnAction(
                (m) -> stage.setScene(Main.menuScene));
    }


    private static void buttonStyle(Button button, Group root2, int fontSize){
        button.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-weight: bold;"+
                        "-fx-font-size: " + fontSize + "px;"+
                        "-fx-font-family: 'Georgia'; "
        );
        button.setFont(Font.font("Georgia", fontSize));
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
