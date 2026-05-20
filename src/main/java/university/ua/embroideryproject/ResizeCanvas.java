package university.ua.embroideryproject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResizeCanvas {
public static Stage sizeStage;
public static GridPane gridPaneSize;
    public static void resize(){
        VBox changeSizeBox = new VBox();

        gridPaneSize = new GridPane();
        gridPaneSize.setPadding( new Insets(10, 0, 0, 0) );
        gridPaneSize.setHgap( 4 );
        gridPaneSize.setVgap( 10 );

        VBox.setVgrow(gridPaneSize, Priority.ALWAYS );

        Label width = new Label("width (1-200) :");
        TextField inputColumns = new TextField("30");
        Label height = new Label("height (1-200) :");
        TextField inputRows = new TextField("30");

        gridPaneSize.add(width, 0, 1);
        gridPaneSize.add(inputColumns, 2, 1);
        gridPaneSize.add(height, 0, 3);
        gridPaneSize.add(inputRows, 2, 3);

        GridPane.setColumnSpan(width, 1);
        GridPane.setColumnSpan(inputColumns, 4);
        GridPane.setColumnSpan(height, 1);
        GridPane.setColumnSpan(inputRows, 4);

        changeSizeBox.getChildren().add(gridPaneSize);

        sizeStage = new Stage();

        Scene sceneForResize = new Scene(changeSizeBox);

        sizeStage.setTitle("Новий розмір полотна");
        sizeStage.setScene(sceneForResize);
        sizeStage.setWidth( 400 );
        sizeStage.setHeight( 200  );

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(10) );

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(
                (s) -> {
                    Main.ROWS = Integer.parseInt(inputRows.getText());
                    Main.COLS = Integer.parseInt(inputColumns.getText());
                    Main.grid = new int[Main.ROWS][Main.COLS];
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
        gridPaneSize.add(buttonBar, 7, 6);

    }

}
