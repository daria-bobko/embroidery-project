package university.ua.embroideryproject;

import javafx.scene.control.ToggleButton;

public class Utils {
    public static void toggleButtonAction(ToggleButton selected, ToggleButton unselected1, ToggleButton unselected2){
        selected.setOnAction(
                (e) -> {
                    unselected1.setSelected(false);
                    unselected2.setSelected(false);
                    EmbroideryCanvas.mouseHandle();

                });
    }
}
