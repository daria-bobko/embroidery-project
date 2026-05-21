package university.ua.embroideryproject;

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

        MainScene.inputColumns.setMaxWidth(60);
        MainScene.inputRows.setMaxWidth(60);

        gridPaneSize.add(MainScene.width, 0, 0);
        gridPaneSize.add(MainScene.inputColumns, 1, 0);
        gridPaneSize.add(MainScene.height, 0, 1);
        gridPaneSize.add(MainScene.inputRows, 1, 1);

        GridPane.setColumnSpan(buttonBar, 2);
        gridPaneSize.add(buttonBar, 0, 3);

        GridPane.setHalignment(buttonBar, javafx.geometry.HPos.RIGHT);

        changeSizeBox.getChildren().add(gridPaneSize);


        Scene sceneForResize = new Scene(changeSizeBox);

        sizeStage.setTitle("Новий розмір полотна");
        sizeStage.setScene(sceneForResize);
        sizeStage.setWidth( 350 );
        sizeStage.setHeight( 200  );

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(
                (s) -> {
                    while(MainScene.inputRows.getText().contains(".")|| MainScene.inputRows.getText().contains(",")||
                            MainScene.inputColumns.getText().contains(".")|| MainScene.inputColumns.getText().contains(",")||
                            !(Integer.parseInt(MainScene.inputColumns.getText()) > 0) ||
                                    !(Integer.parseInt(MainScene.inputColumns.getText()) <= 100) ||
                                    !(Integer.parseInt(MainScene.inputRows.getText()) > 0) ||
                                    !(Integer.parseInt(MainScene.inputRows.getText()) <= 100) ||
                                    MainScene.inputColumns.getText().isEmpty() || MainScene.inputRows.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Помилка");
                        alert.setHeaderText(null);
                        alert.setContentText("Введіть коректні дані (цілі числа від 1 до 100)");
                        alert.showAndWait();
                        return;
                        }
                    Main.ROWS = Integer.parseInt(MainScene.inputRows.getText());
                    Main.COLS = Integer.parseInt(MainScene.inputColumns.getText());
                    Main.grid = new Color[Main.ROWS][Main.COLS];
                    int sizeByWidth = 690 / Main.COLS;
                    int sizeByHeight = 750 / Main.ROWS;
                    Main.CELL_SIZE = Math.min(sizeByWidth, sizeByHeight);
                    if (Main.CELL_SIZE <= 0) {
                        Main.CELL_SIZE = 1;}
                    ResizeCanvas.sizeStage.close();
                    EmbroideryCanvas.drawEmbroideryCanvas();
                    EmbroideryCanvas.imageView.toBack();
                });
        Button cancelButton = new Button("Вийти");
        cancelButton.setOnAction(
                (c) -> ResizeCanvas.sizeStage.close()
        );

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);
        sizeStage.show();

    }

}
