package university.ua.embroideryproject;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseHandler {
    public static void mouseHandle(){
        EmbroideryCanvas.imageView.setPickOnBounds(true);
        EmbroideryCanvas.imageView.toBack();
        EmbroideryCanvas.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            double x = e.getX();
            double y = e.getY();
            if(e.getClickCount() == 2&&MainScene.fillBackground.isSelected()){
                EmbroideryCanvas.fillAllCanvas(MainScene.colorForEmbroidery.getValue(), MainScene.forGridChangePicker.getValue());
            }
            if(MainScene.eraser.isSelected()){
                EmbroideryCanvas.drawOnCanvas(x, y, Color.WHITE, MainScene.forGridChangePicker.getValue());
            }
            if(MainScene.pencil.isSelected()){
                EmbroideryCanvas.drawOnCanvas(x, y, MainScene.colorForEmbroidery.getValue(), MainScene.forGridChangePicker.getValue());
            }
            if(MainScene.eyedropper.isSelected()){
                if (x <= EmbroideryCanvas.screenStartX + (Main.CELL_SIZE * Main.COLS) && x>= EmbroideryCanvas.screenStartX && y <= EmbroideryCanvas.screenStartY + (Main.CELL_SIZE * Main.ROWS) && y >= EmbroideryCanvas.screenStartY) {

                    int placeInMatrixColumn = (int) (x - EmbroideryCanvas.screenStartX) / Main.CELL_SIZE;
                    int placeInMatrixRow = (int) (y - EmbroideryCanvas.screenStartY) / Main.CELL_SIZE;
                    Color choosenColor = Main.grid[placeInMatrixRow][placeInMatrixColumn];
                    if(choosenColor == null){
                        choosenColor = Color.WHITE;
                    }
                    MainScene.colorForEmbroidery.setValue(choosenColor);
                }}}
        );
        EmbroideryCanvas.imageView.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            if(MainScene.eraser.isSelected()){
                EmbroideryCanvas.drawOnCanvas(x, y, Color.WHITE, MainScene.forGridChangePicker.getValue());
            }
            if (MainScene.pencil.isSelected()){
                EmbroideryCanvas.drawOnCanvas(x, y, MainScene.colorForEmbroidery.getValue(), MainScene.forGridChangePicker.getValue());}
        });
        EmbroideryCanvas.imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            if(MainScene.eyedropper.isSelected()){
                EmbroideryCanvas.imageView.setCursor(javafx.scene.Cursor.CROSSHAIR);
            }
            if(MainScene.pencil.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor.png"));
                EmbroideryCanvas.imageView.setCursor(new ImageCursor(cursor, 0, cursor.getHeight()));
            }
            if(MainScene.eraser.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor-eraser.png"));
                EmbroideryCanvas.imageView.setCursor(new ImageCursor(cursor, 0, cursor.getHeight()));
            }
            if(MainScene.fillBackground.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor-fill.png"));
                EmbroideryCanvas.imageView.setCursor(new ImageCursor(cursor, cursor.getWidth(), cursor.getHeight()));
            }
        });
        MainScene.pencil.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.getInstructionForPencil, MainScene.getInstructionForSymetricV,
                    MainScene.getInstructionForDuplicate, MainScene.getInstructionForErasser, MainScene.getInstructionForEyedropper,
                    MainScene.instructionForFill, MainScene.getInstructionForSymetricH);
        });
        MainScene.eraser.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.getInstructionForErasser, MainScene.getInstructionForSymetricV,
                    MainScene.getInstructionForDuplicate, MainScene.getInstructionForPencil, MainScene.getInstructionForEyedropper,
                    MainScene.instructionForFill, MainScene.getInstructionForSymetricH);
        }        );

        MainScene.fillBackground.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.instructionForFill, MainScene.getInstructionForSymetricV,
                    MainScene.getInstructionForDuplicate, MainScene.getInstructionForPencil, MainScene.getInstructionForErasser,
                    MainScene.getInstructionForEyedropper,MainScene.getInstructionForSymetricH);
        }        );

        MainScene.eyedropper.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.getInstructionForEyedropper, MainScene.getInstructionForSymetricV,
                    MainScene.getInstructionForDuplicate, MainScene.getInstructionForPencil, MainScene.getInstructionForErasser,
                    MainScene.instructionForFill, MainScene.getInstructionForSymetricH);

        }        );

        MainScene.vertical.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.getInstructionForSymetricV, MainScene.getInstructionForDuplicate,
                    MainScene.getInstructionForPencil, MainScene.getInstructionForErasser, MainScene.getInstructionForEyedropper,
                    MainScene.getInstructionForSymetricH, MainScene.instructionForFill);}
        );


        MainScene.horizontal.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Utils.textInstruction(MainScene.getInstructionForSymetricH, MainScene.getInstructionForDuplicate,
                    MainScene.getInstructionForPencil, MainScene.getInstructionForErasser, MainScene.getInstructionForEyedropper,
                    MainScene.instructionForFill, MainScene.getInstructionForSymetricV);
        }        );
        MainScene.duplicate.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
            Utils.textInstruction(MainScene.getInstructionForDuplicate, MainScene.getInstructionForEyedropper,
                    MainScene.getInstructionForPencil, MainScene.getInstructionForErasser, MainScene.instructionForFill,
                    MainScene.getInstructionForSymetricH, MainScene.getInstructionForSymetricV);
            EmbroideryCanvas.duplicatePattern();}
        );



    }
}
