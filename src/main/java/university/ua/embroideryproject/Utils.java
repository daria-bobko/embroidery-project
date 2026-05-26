package university.ua.embroideryproject;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Utils {

    public static void toggleButtonAction(ToggleButton selected, ToggleButton unselected1, ToggleButton unselected2, ToggleButton unselected3){
        selected.setOnAction(
                (e) -> {
                    unselected1.setSelected(false);
                    unselected2.setSelected(false);
                    unselected3.setSelected(false);
                    MouseHandler.mouseHandle();

                });
    }

    public static void textInstruction(Text text1current, Text text2, Text text3, Text text4, Text text5, Text text6, Text text7){
        Main.rootMain.getChildren().removeAll(text1current, text2, text3, text4, text5, text6, text7);
        text1current.setTextAlignment(TextAlignment.LEFT);
        text1current.setStyle(
                "-fx-fill: #b52302; " +
                        "-fx-font-family: 'Georgia', serif;" +
                        "-fx-font-style: italic;");
        text1current.setFont(Font.font("Century", 25));
        text1current.setX((1245 - text1current.getBoundsInLocal().getWidth())/2);
        text1current.setY(((EmbroideryCanvas.screenStartY-25)/2)+20);
        Main.rootMain.getChildren().add(text1current);
    }

    public static void createButton(Button button, int sizeX, int sizeY, int x, int y, int fontSize ){
        button.setPrefSize(sizeX, sizeY);
        button.setLayoutX(x );
        button.setLayoutY(y);
        Utils.buttonStyle(button, Main.rootMain, fontSize);
    }

    public static void createToggleButton(ToggleButton toggleButton, int sizeX, int sizeY, int x, int y, String pass){
        toggleButton.setPrefSize(sizeX, sizeY);
        toggleButton.setLayoutX(x);
        toggleButton.setLayoutY(y);
        Image icon = new Image(MainScene.class.getResourceAsStream(pass));
        toggleButton.setGraphic(new ImageView(icon));
        Utils.toggleButtonStyle(toggleButton, Main.rootMain);

    }

    public static void buttonStyle(Button button, Group root2, int fontSize){
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

    public static void toggleButtonStyle(ToggleButton button, Group root2){
        button.setStyle(
                "-fx-border-color:  #b52302;" +
                        "-fx-text-fill:  #b52302;" +
                        "-fx-font-size: 24px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Georgia';"
        );
        root2.getChildren().add(button);
    }

    public static void textProperties(Text text, int x, int y){
        text.setStyle(
                "-fx-font-family: 'Verdana'; " +
                        "-fx-font-size: 24px; " +
                        "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;"
        );
        text.setX(x);
        text.setY(y);
        Main.rootMain.getChildren().add(text);

    }



}
