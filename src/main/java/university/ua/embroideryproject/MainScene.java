package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public static ColorPicker colorForEmbroidery;
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
    public static Button buttonForGridChange;
    public static ColorPicker forGridChangePicker = new ColorPicker(Color.DARKGRAY);

    public static void mainSceneProperties(Stage stage, Group root2) {
        returnToMenuButton(stage, root2);
        EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue());
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
        forGridButton(root2);
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

 private static void forGridButton(Group root2) {
        buttonForGridChange = new Button("КОЛІР СІТКИ");
        Utils.createButton(buttonForGridChange, 280, 61, 1006, 395, 24);
        buttonForGridChange.setOnAction(
                (l) -> GridChangeColor.change()
        );
    }

    private static void nullCanvasButton(Group root2) {
            nullCanvas = new Button("ОЧИСТИТИ ПОЛОТНО");
            Utils.createButton(nullCanvas, 280, 40, 0, 680, 17);
            nullCanvas.setOnAction(
                    (l) -> EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue())
            );
        }

    private static void forPencilText(Group root2) {
        forPencil = new Text("розмір пензля : 1");
        Utils.textProperties(forPencil, 0, 300);
    }

    private static void textForSymetric(Group root2) {
        forSymetric = new Text("симетрія :");
        Utils.textProperties(forSymetric, 0, 400);
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
        Utils.createToggleButton(eyedropper, 85, 61, 195, 100, "/eyedropper.png" );
    }

    private static void loadPictureButton(Group root2) {
        loadPicture = new Button("ЗАВАНТАЖИТИ З ФАЙЛІВ");
        Utils.createButton(loadPicture, 280, 61, 1006, 170, 17);
        loadPicture.setOnAction(
                (l) -> WorkWithPictures.loadPNG()
        );
    }


    private static void duplicatePatternButton(Group root2) {
        duplicate = new Button("дублювати шаблон");
        Utils.createButton(duplicate, 280, 40,0, 540, 22);
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
        Utils.createToggleButton(pencil, 85, 61, 195, 180, "/pencil.png" );
    }

    private static void eraserToggleButton( Group root2) {
        eraser = new ToggleButton();
        Utils.createToggleButton(eraser, 85, 61, 97, 180, "/eraser.png");
    }

    private static void fillBackgroundToggleButton(Group root2) {
        fillBackground = new ToggleButton();
        Utils.createToggleButton(fillBackground, 84, 61, 0, 180, "/fill.png");

    }

    private static void resizeCanvasButton( Group root2) {
        resizeCanvas = new Button("РОЗМІР ПОЛОТНА");
        Utils.createButton(resizeCanvas, 280, 61, 1006, 325, 22);
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
        Utils.textProperties(textForColor, 0, 80);

        MainScene.colorForEmbroidery = new ColorPicker(Color.DARKRED);
        MainScene.colorForEmbroidery.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        MainScene.colorForEmbroidery.setLayoutX(0);
        MainScene.colorForEmbroidery.setLayoutY(100);
        MainScene.colorForEmbroidery.setPrefSize(182, 68);
        root2.getChildren().add(MainScene.colorForEmbroidery);

        MainScene.colorForEmbroidery.setOnAction(event -> {
            Color grid = MainScene.forGridChangePicker.getValue();
            Color currentColor = MainScene.colorForEmbroidery.getValue();
            if (currentColor.equals(grid)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Ви обрали колір сітки, оберіть інший колір");
                MainScene.colorForEmbroidery.setValue(Color.DARKRED);
                alert.showAndWait();
            }
        });
    }

    private static void savePictureButton(Group root2) {
        savePicture = new Button("ЗБЕРЕГТИ СХЕМУ");
        Utils.createButton(savePicture, 280, 61, 1006, 100, 20);
        savePicture.setOnAction(
                (s) -> WorkWithPictures.saveAsPNG()
        );
    }

    private static void returnToMenuButton(Stage stage, Group root2) {
        returnToMenu = new Button("ДО МЕНЮ");
        Utils.createButton(returnToMenu, 280, 61, 1006, 680, 24);
        returnToMenu.setOnAction(
                (m) -> stage.setScene(Main.menuScene));
    }

}
