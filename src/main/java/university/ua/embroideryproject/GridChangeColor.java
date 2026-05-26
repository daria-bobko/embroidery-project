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

public class GridChangeColor {
    public static Stage changeGridStage;
    public static GridPane gridPaneChangeGrid;
    public static void change() {
        changeGridStage = new Stage();
        VBox changeGridBox = new VBox();

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(10) );
        gridPaneChangeGrid = new GridPane();
        gridPaneChangeGrid.setPadding(new Insets(20, 20, 20, 20));
        gridPaneChangeGrid.setHgap(15);
        gridPaneChangeGrid.setVgap(15);

        VBox.setVgrow(gridPaneChangeGrid, Priority.ALWAYS);

        MainScene.forGridChangePicker.setStyle("-fx-border-color:  #b52302;" +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Georgia';"
        );
        MainScene.forGridChangePicker.setLayoutX(1006);
        MainScene.forGridChangePicker.setLayoutY(400);
        MainScene.forGridChangePicker.setPrefSize(280, 40);

        gridPaneChangeGrid.add(MainScene.forGridChangePicker, 0, 0);
        gridPaneChangeGrid.add(new Label("Після зміни ваш старий малюнок буде втрачено!"), 0,2);
        GridPane.setColumnSpan(buttonBar, 2);
        gridPaneChangeGrid.add(buttonBar, 0, 3);
        GridPane.setHalignment(buttonBar, HPos.RIGHT);

        changeGridBox.getChildren().add(gridPaneChangeGrid);


        Scene sceneForGrid = new Scene(changeGridBox);

        changeGridStage.setTitle("Новий колір сітки");
        changeGridStage.setScene(sceneForGrid);
        changeGridStage.setWidth( 400);
        changeGridStage.setHeight( 220);

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(
                (s) -> {
                    changeGridStage.close();
                    if(MainScene.forGridChangePicker.getValue().equals(Color.WHITE)){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Помилка!");
                            alert.setHeaderText(null);
                            alert.setContentText("Колір сітки не може бути білим");
                            MainScene.forGridChangePicker.setValue(Color.DARKGRAY);
                            alert.showAndWait();
                            return;
                        }
                    EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue());

                    EmbroideryCanvas.imageView.toBack();
                });
        Button cancelButton = new Button("Вийти");
        cancelButton.setOnAction(
                (c) -> changeGridStage.close()
        );

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);
        changeGridStage.show();
    }
}