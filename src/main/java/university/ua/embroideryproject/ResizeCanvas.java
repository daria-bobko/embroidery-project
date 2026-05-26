package university.ua.embroideryproject;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ResizeCanvas {
public static Stage sizeStage;
public static GridPane gridPaneSize;
public static Label width;
public static Label height;
public static TextField inputColumns;
public static TextField inputRows;

    public static void resize(){
        sizeStage = new Stage();
        VBox changeSizeBox = new VBox();

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(10) );
        gridPaneSize = new GridPane();
        gridPaneSize.setPadding(new Insets(20, 20, 20, 20));
        gridPaneSize.setHgap(15);
        gridPaneSize.setVgap(15);

        VBox.setVgrow(gridPaneSize, Priority.ALWAYS);

        inputColumns.setMaxWidth(60);
        inputRows.setMaxWidth(60);

        gridPaneSize.add(width, 0, 0);
        gridPaneSize.add(inputColumns, 1, 0);
        gridPaneSize.add(height, 0, 1);
        gridPaneSize.add(inputRows, 1, 1);
        gridPaneSize.add(new Label("Після зміни ваш старий малюнок буде втрачено!"), 0,2);

        GridPane.setColumnSpan(buttonBar, 2);
        gridPaneSize.add(buttonBar, 0, 3);

        GridPane.setHalignment(buttonBar, HPos.RIGHT);

        changeSizeBox.getChildren().add(gridPaneSize);


        Scene sceneForResize = new Scene(changeSizeBox);

        sizeStage.setTitle("Новий розмір полотна");
        sizeStage.setScene(sceneForResize);
        sizeStage.setWidth( 400);
        sizeStage.setHeight( 220);

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(
                (s) -> {
                    while(inputRows.getText().contains(".")|| inputRows.getText().contains(",")||
                            inputColumns.getText().contains(".")|| inputColumns.getText().contains(",")||
                            !(Integer.parseInt(inputColumns.getText()) > 0) ||
                                    !(Integer.parseInt(inputColumns.getText()) <= 100) ||
                                    !(Integer.parseInt(inputRows.getText()) > 0) ||
                                    !(Integer.parseInt(inputRows.getText()) <= 100) ||
                                    inputColumns.getText().isEmpty() || inputRows.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Помилка");
                        alert.setHeaderText(null);
                        alert.setContentText("Введіть коректні дані (цілі числа від 1 до 100)");
                        alert.showAndWait();
                        return;
                        }
                    Main.ROWS = Integer.parseInt(inputRows.getText());
                    Main.COLS = Integer.parseInt(inputColumns.getText());
                    Main.grid = new Color[Main.ROWS][Main.COLS];
                    int sizeByWidth = 690 / Main.COLS;
                    int sizeByHeight = 750 / Main.ROWS;
                    Main.CELL_SIZE = Math.min(sizeByWidth, sizeByHeight);
                    if (Main.CELL_SIZE <= 0) {
                        Main.CELL_SIZE = 1;}
                    sizeStage.close();
                    EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue());
                    EmbroideryCanvas.imageView.toBack();
                });
        Button cancelButton = new Button("Вийти");
        cancelButton.setOnAction(
                (c) -> sizeStage.close()
        );

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);
        sizeStage.show();

    }

}
