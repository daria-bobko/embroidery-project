package university.ua.embroideryproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;

public class Main extends Application {
public static Group root = new Group();
    public static Group root2 = new Group();
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
        returnToMenu.setPrefSize(300, 61);
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
        returnToMenu2.setPrefSize(300, 61);
        returnToMenu2.setLayoutX(985 );
        returnToMenu2.setLayoutY(500);
        root2.getChildren().add(returnToMenu2);

        returnToMenu2.setOnAction(
                (m) -> stage.setScene(menuScene));
        returnToMenu.setOnAction(
                (m) -> stage.setScene(menuScene));

}}
