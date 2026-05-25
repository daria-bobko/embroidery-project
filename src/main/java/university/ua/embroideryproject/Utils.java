package university.ua.embroideryproject;

import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Utils {
    public static void toggleButtonAction(ToggleButton selected, ToggleButton unselected1, ToggleButton unselected2, ToggleButton unselected3){
        selected.setOnAction(
                (e) -> {
                    unselected1.setSelected(false);
                    unselected2.setSelected(false);
                    unselected3.setSelected(false);
                    EmbroideryCanvas.mouseHandle();

                });
    }

    public static void textInstruction(Text text1current, Text text2, Text text3, Text text4, Text text5, Text text6, Text text7){
        Main.rootMain.getChildren().removeAll(text1current, text2, text3, text4, text5, text6, text7);
        text1current.setStyle(
                "-fx-fill: #b52302; " +
                        "-fx-font-weight: bold;");
        text1current.setFont(Font.font("Verdana", 25));
        text1current.setX((1200 - text1current.getBoundsInLocal().getWidth())/2);
        text1current.setY((EmbroideryCanvas.screenStartY-25)/2);
        Main.rootMain.getChildren().add(text1current);
    }





}
