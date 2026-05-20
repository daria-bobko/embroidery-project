package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScene {
    public static Button create;

    public static void menuSceneProperties(Stage stage, Group root) {
        stage.setTitle("Embroidery Maker (Bobko Daria)");
        Image icon = new Image(MenuScene.class.getResourceAsStream("/icon.png"));
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

        create = new Button("CREATE");
        create.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        create.setPrefSize(183, 61);
        create.setLayoutX(902);
        create.setLayoutY(500);
        root.getChildren().add(create);

        create.setOnAction(
                (m) -> stage.setScene(Main.mainScene));

    }
}
