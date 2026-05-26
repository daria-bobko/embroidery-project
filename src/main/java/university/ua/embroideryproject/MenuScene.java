package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuScene {
    public static Button create;
    public static Button symbols;
    public static Button exit;

    public static void menuSceneProperties(Stage stage, Group root) {
        stage.setTitle("Embroidery Maker (Bobko Daria)");
        Image icon = new Image(MenuScene.class.getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);
        stage.setWidth(1300);
        stage.setHeight(900);
        stage.setResizable(false);
        Image vishyvanka = new Image(MenuScene.class.getResourceAsStream("/vishyvanka.png"));
        ImageView ornamentView = new ImageView(vishyvanka);
        ornamentView.setFitWidth(500);
        ornamentView.setPreserveRatio(true);
        ornamentView.setSmooth(true);
        ornamentView.setLayoutX(700);
        ornamentView.setLayoutY(120);
        ornamentView.setMouseTransparent(true);
        ornamentView.setOpacity(0.6);
        ornamentView.toBack();

        root.getChildren().add(ornamentView);
        Text programName1 = new Text("E M B R O I D E R Y");
        Text programName2 = new Text("- M A K E R -");
        programName1.setX(680);
        programName1.setY(280);
        programName1.setFont(Font.font("Verdana", FontWeight.BOLD, 55));
        programName2.setX(860);
        programName2.setY(350);
        programName2.setFont(Font.font("Liberation Sans Narrow", FontWeight.BOLD, 55));
        root.getChildren().add(programName1);
        root.getChildren().add(programName2);





        PixelDrawName.startDrawing();

        create = new Button("СТВОРИТИ");
        create.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        create.setPrefSize(183, 61);
        create.setLayoutX(902);
        create.setLayoutY(450);
        root.getChildren().add(create);

        create.setOnAction(
                (m) -> stage.setScene(Main.mainScene));

        symbols = new Button("ПРО СИМВОЛИ");
        symbols.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        symbols.setPrefSize(183, 61);
        symbols.setLayoutX(902);
        symbols.setLayoutY(540);
        root.getChildren().add(symbols);

        symbols.setOnAction(
                (m) -> WebsiteOpen.openHelpScene(stage));

        exit = new Button("ВИХІД");
        exit.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );

        exit.setPrefSize(183, 61);
        exit.setLayoutX(902);
        exit.setLayoutY(630);
        root.getChildren().add(exit);

        exit.setOnAction(
                (m) -> stage.close());


    }
}
