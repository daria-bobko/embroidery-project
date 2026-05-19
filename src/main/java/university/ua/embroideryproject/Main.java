package university.ua.embroideryproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.MouseInfo;

import java.awt.*;
import javafx.geometry.Insets;

public class Main extends Application {
public static Group root = new Group();
public static Group root2 = new Group();

public static int ROWS = 30;
public static int COLS = 30;
public static int CELL_SIZE = 23;
public static ColorPicker colorForEmbroidery;


public static int grid[][] = new int [ROWS][COLS];


public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene menuScene = new Scene(root, Color.rgb(252, 225, 189));
        stage.setTitle("Embroidery Maker (Bobko Daria)");
        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);
        stage.setWidth(1300);
        stage.setHeight(900);
        stage.setResizable(false);
        Text programName1 = new Text("E M B R O I D E R Y");
        Text programName2 = new Text("- M A K E R -");
        programName1.setX(680);
        programName1.setY(250);
        programName1.setFont(Font.font("Verdana", FontWeight.BOLD, 55));
        programName2.setX(860);
        programName2.setY(350);
        programName2.setFont(Font.font("Liberation Sans Narrow", 55));
        root.getChildren().add(programName1);
        root.getChildren().add(programName2);

        PixelDrawName.startDrawing();

        Button create = new Button("CREATE");
        create.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        Scene mainScene = new Scene(root2, Color.rgb(252, 225, 189));


        create.setPrefSize(183, 61);
        create.setLayoutX(902);
        create.setLayoutY(500);
        root.getChildren().add(create);
        stage.setScene(menuScene);
        stage.show();


        create.setOnAction(
                (m) -> stage.setScene(mainScene));
        stage.show();

        Button returnToMenu = new Button("RETURN TO MENU");
        returnToMenu.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        returnToMenu.setPrefSize(280, 61);
        returnToMenu.setLayoutX(0);
        returnToMenu.setLayoutY(500);
        root2.getChildren().add(returnToMenu);

        Button returnToMenu2 = new Button("RETURN TO MENU");
        returnToMenu2.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        returnToMenu2.setPrefSize(280, 61);
        returnToMenu2.setLayoutX(1006 );
        returnToMenu2.setLayoutY(500);
        root2.getChildren().add(returnToMenu2);

        returnToMenu2.setOnAction(
                (m) -> stage.setScene(menuScene));
        returnToMenu.setOnAction(
                (m) -> stage.setScene(menuScene));

        EmbroideryCanvas.drawEmbroideryCanvas();

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
        colorForEmbroidery = new ColorPicker();
        colorForEmbroidery.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 24px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        colorForEmbroidery.setLayoutX(0);
        colorForEmbroidery.setLayoutY(400);
        colorForEmbroidery.setPrefSize(280, 61);
        root2.getChildren().add(colorForEmbroidery);

        VBox vbSize = new VBox();

        GridPane gpSize = new GridPane();
        gpSize.setPadding( new Insets(10, 0, 0, 0) );
        gpSize.setHgap( 4 );
        gpSize.setVgap( 10 );

        VBox.setVgrow(gpSize, Priority.ALWAYS );

        Label width = new Label("width (0-) :");
        TextField inputColumns = new TextField("4");
        Label height = new Label("height (0-) :");
        TextField inputRows = new TextField("30");

        gpSize.add(width, 0, 1);
        gpSize.add(inputColumns, 2, 1);
        gpSize.add(height, 0, 3);
        gpSize.add(inputRows, 2, 3);

        GridPane.setColumnSpan(width, 1);
        GridPane.setColumnSpan(inputColumns, 4);
        GridPane.setColumnSpan(height, 1);
        GridPane.setColumnSpan(inputRows, 4);

        vbSize.getChildren().add(gpSize);

        Stage sizeStage = new Stage();

        Scene sceneForResize = new Scene(vbSize);

        sizeStage.setTitle("Новий розмір полотна");
        sizeStage.setScene(sceneForResize);
        sizeStage.setWidth( 400 );
        sizeStage.setHeight( 200  );

        Button sizeGrid = new Button("RESIZE CANVAS");
        sizeGrid.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        sizeGrid.setPrefSize(280, 61);
        sizeGrid.setLayoutX(0);
        sizeGrid.setLayoutY(500);

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(10) );

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(
                (s) -> {
                    ROWS = Integer.parseInt(inputRows.getText());
                    COLS = Integer.parseInt(inputColumns.getText());
                    grid = new int[ROWS][COLS];
                    int sizeByWidth = 690 / COLS;
                    int sizeByHeight = 750 / ROWS;
                    CELL_SIZE = Math.min(sizeByWidth, sizeByHeight);
                    if (CELL_SIZE <= 0) {
                        CELL_SIZE = 1;}
                        sizeStage.close();
                        EmbroideryCanvas.drawEmbroideryCanvas();
                        EmbroideryCanvas.imageView.toBack();
                });
        Button cancelButton = new Button("Вийти");
        cancelButton.setOnAction(
                (c) -> sizeStage.close()
        );

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);
        gpSize.add(buttonBar, 7, 6);

        root2.getChildren().addAll(sizeGrid);


        sizeGrid.setOnAction(
                (sc) ->  sizeStage.show()
        );
        EmbroideryCanvas.imageView.setPickOnBounds(true);
        EmbroideryCanvas.imageView.toBack();
        EmbroideryCanvas.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        double x = e.getX();
        double y = e.getY();
        EmbroideryCanvas.drawOnCanvas(x, y, colorForEmbroidery.getValue());
        } );

        EmbroideryCanvas.imageView.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            EmbroideryCanvas.drawOnCanvas(x, y, colorForEmbroidery.getValue());
        } );


}
}
