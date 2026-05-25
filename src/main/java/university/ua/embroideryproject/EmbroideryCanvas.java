package university.ua.embroideryproject;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// 685 width for canvas
//
public class EmbroideryCanvas {
    public static WritableImage writableImage = new WritableImage(1300, 900);
    public static PixelWriter writer = writableImage.getPixelWriter();
    public static ImageView imageView = new ImageView(writableImage);
    private static int startX;
    private static int startY;
    private static Color background;
    public static double screenStartX;
    public static double screenStartY;

    public static void drawEmbroideryCanvas() {
        Main.rootMain.getChildren().remove(imageView);
        int canvasWidth = Main.COLS * Main.CELL_SIZE;
        int canvasHeight = Main.ROWS * Main.CELL_SIZE;

        startX = 0;
        startY = 0;

        writableImage = new WritableImage(canvasWidth, canvasHeight);
        writer = writableImage.getPixelWriter();

        fillAllCanvas(Color.WHITE);
        screenStartX = (1290 - canvasWidth)/2.0;
        screenStartY = (820 - canvasHeight)/2.0;
        imageView.setX(screenStartX);
        imageView.setY(screenStartY);
        imageView.setImage(writableImage);
        Main.rootMain.getChildren().add(imageView);
}


    private static void drawCell(int x, int y, int s, Color color) {
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (i == 0 || j == 0 || j == s - 1 || i == s - 1) {
                    writer.setColor(x + j, y + i, Color.DARKGRAY);
                } else {
                    writer.setColor(x + j, y + i, color);}
            }
        }
    }

    public static void drawOnCanvas(double x, double y, Color color) {
        for(int i = 0; i < MainScene.valueForPencil; i++){
            for(int j = 0; j < MainScene.valueForPencil; j++){
        if (x + (Main.CELL_SIZE*i) < screenStartX + (Main.CELL_SIZE * Main.COLS) && x> screenStartX && y+(Main.CELL_SIZE*j) < screenStartY + (Main.CELL_SIZE * Main.ROWS) && y > screenStartY) {
            int placeInMatrixColumn = (int) (x + (Main.CELL_SIZE*i)- screenStartX) / Main.CELL_SIZE;
            int placeInMatrixRow = (int) (y+ (Main.CELL_SIZE*j) - screenStartY) / Main.CELL_SIZE;
            Color colorSaveValue = color.equals(Color.WHITE) ? null : color;
            Main.grid[placeInMatrixRow][placeInMatrixColumn] = colorSaveValue;
            drawCell(startX + placeInMatrixColumn * Main.CELL_SIZE, startY + placeInMatrixRow * Main.CELL_SIZE, Main.CELL_SIZE, color);

            if (MainScene.vertical.isSelected()) {
                int mirrorCol = Main.COLS - 1 - placeInMatrixColumn;
                Main.grid[placeInMatrixRow][mirrorCol] = colorSaveValue;
                drawCell(startX + mirrorCol * Main.CELL_SIZE, startY + placeInMatrixRow * Main.CELL_SIZE, Main.CELL_SIZE, color);            }
            if (MainScene.horizontal.isSelected()) {
                int mirrorRow = Main.ROWS - 1 - placeInMatrixRow;
                Main.grid[mirrorRow][placeInMatrixColumn] = colorSaveValue;
                drawCell(startX + placeInMatrixColumn * Main.CELL_SIZE, startY + mirrorRow * Main.CELL_SIZE, Main.CELL_SIZE, color);
            }
            if (MainScene.horizontal.isSelected() && MainScene.vertical.isSelected()) {
                int mirrorCol = Main.COLS - 1 - placeInMatrixColumn;
                int mirrorRow = Main.ROWS - 1 - placeInMatrixRow;
                Main.grid[mirrorRow][mirrorCol] = colorSaveValue;
                drawCell(startX + mirrorCol * Main.CELL_SIZE, startY + mirrorRow * Main.CELL_SIZE, Main.CELL_SIZE, color);
            }
        }}}
    }

    public static void fillAllCanvas(Color color){
        background = color;
        for (int row = 0; row < Main.ROWS; row++) {
            for (int col = 0; col < Main.COLS; col++) {
                if(color.equals(Color.WHITE)){
                    Main.grid[row][col] = null;
                }
                else {
                    Main.grid[row][col] = color;
                }
                drawCell(startX + col * Main.CELL_SIZE, startY + row * Main.CELL_SIZE, Main.CELL_SIZE, color);
            }
        }
    }



    public static void mouseHandle(){
        imageView.setPickOnBounds(true);
        imageView.toBack();
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            double x = e.getX();
            double y = e.getY();
            if(e.getClickCount() == 2&&MainScene.fillBackground.isSelected()){
                fillAllCanvas(Main.colorForEmbroidery.getValue());
            }
            if(MainScene.eraser.isSelected()){
                drawOnCanvas(x, y, Color.WHITE);
            }
            if(MainScene.pencil.isSelected()){
                drawOnCanvas(x, y, Main.colorForEmbroidery.getValue());
            }
            if(MainScene.eyedropper.isSelected()){
                if (x <= screenStartX + (Main.CELL_SIZE * Main.COLS) && x>= screenStartX && y <= screenStartY + (Main.CELL_SIZE * Main.ROWS) && y >= screenStartY) {

                    int placeInMatrixColumn = (int) (x - screenStartX) / Main.CELL_SIZE;
                    int placeInMatrixRow = (int) (y - screenStartY) / Main.CELL_SIZE;
                    Color choosenColor = Main.grid[placeInMatrixRow][placeInMatrixColumn];
                    if(choosenColor == null){
                        choosenColor = Color.WHITE;
                    }
                    Main.colorForEmbroidery.setValue(choosenColor);
                }}}
        );
        imageView.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            double x = e.getX();
            double y = e.getY();
            if(MainScene.eraser.isSelected()){
                drawOnCanvas(x, y, Color.WHITE);
            }
            if (MainScene.pencil.isSelected()){
                drawOnCanvas(x, y, Main.colorForEmbroidery.getValue());}
        });
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            if(MainScene.eyedropper.isSelected()){
                imageView.setCursor(javafx.scene.Cursor.CROSSHAIR);
            }
            if(MainScene.pencil.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor.png"));
                imageView.setCursor(new ImageCursor(cursor, 0, cursor.getHeight()));
            }
            if(MainScene.eraser.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor-eraser.png"));
                imageView.setCursor(new ImageCursor(cursor, 0, cursor.getHeight()));
            }
            if(MainScene.fillBackground.isSelected()){
                Image cursor = new Image(EmbroideryCanvas.class.getResourceAsStream("/cursor-fill.png"));
                imageView.setCursor(new ImageCursor(cursor, cursor.getWidth(), cursor.getHeight()));
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

    public static void duplicatePattern() {
        if (MainScene.horizontal.isSelected()) {
            for (int row = 0; row < Main.ROWS / 2; row++) {
                for (int col = 0; col < Main.COLS; col++) {
                    int mirrorRow = Main.ROWS - 1 - row;

                    Color topColor = Main.grid[row][col];
                    Color bottomColor = Main.grid[mirrorRow][col];
                    if(topColor == null){
                        topColor = Color.WHITE;
                    }
                    if(bottomColor == null){
                        bottomColor = Color.WHITE;
                    }

                    int topX = startX + col * Main.CELL_SIZE;
                    int topY = startY + row * Main.CELL_SIZE;
                    int bottomX = startX + col * Main.CELL_SIZE;
                    int bottomY = startY + mirrorRow * Main.CELL_SIZE;

                    if (!topColor.equals(background)&& bottomColor.equals(background)){
                        Main.grid[mirrorRow][col] = topColor;
                        drawCell(bottomX, bottomY, Main.CELL_SIZE, topColor);
                    }
                    else if (!bottomColor.equals(background)&& topColor.equals(background)){
                        Main.grid[row][col] = bottomColor;
                        drawCell(topX, topY, Main.CELL_SIZE, bottomColor);
                    }
                }
            }
        }
        if(MainScene.vertical.isSelected()){
        for (int row = 0; row < Main.ROWS; row++) {
            for (int col = 0; col < Main.COLS / 2; col++) {
                int mirrorCol = Main.COLS - 1 - col;

                Color leftColor = Main.grid[row][col];
                Color rightColor = Main.grid[row][mirrorCol];
                if(leftColor == null){
                    leftColor = Color.WHITE;
                }
                if(rightColor == null){
                    rightColor = Color.WHITE;
                }
                int leftX = startX + col * Main.CELL_SIZE;
                int leftY = startY + row * Main.CELL_SIZE;
                int rightX = startX + mirrorCol * Main.CELL_SIZE;
                int rightY = startY + row * Main.CELL_SIZE;

                if (!leftColor.equals(background)&& rightColor.equals(background)) {
                    Main.grid[row][mirrorCol] = leftColor;
                    drawCell(rightX, rightY, Main.CELL_SIZE, leftColor);
                }
                else if (!rightColor.equals(background)&& leftColor.equals(background)){
                    Main.grid[row][col] = rightColor;
                    drawCell(leftX, leftY, Main.CELL_SIZE, rightColor);
                }
            }
        }

    }}

public static void printDownLoadPicture(){
    Main.rootMain.getChildren().remove(imageView);
    writableImage = new WritableImage(1300, 900);
    writer = EmbroideryCanvas.writableImage.getPixelWriter();
    imageView.setImage(EmbroideryCanvas.writableImage);

    int canvasWidth = Main.COLS * Main.CELL_SIZE;
    int canvasHeight = Main.ROWS * Main.CELL_SIZE;

    startX = 0;
    startY = 0;

    for (int row = 0; row < Main.ROWS; row++) {
        for (int col = 0; col < Main.COLS; col++) {
            Color cellColor = Main.grid[row][col];
            if (cellColor == null) {
                cellColor = Color.WHITE;
            }
            drawCell(startX + col * Main.CELL_SIZE, startY + row * Main.CELL_SIZE, Main.CELL_SIZE, cellColor);
        }
    }
    screenStartX = (1290 - canvasWidth)/2.0;
    screenStartY = (820 - canvasHeight)/2.0;
    imageView.setX(screenStartX);
    imageView.setY(screenStartY);
    imageView.setImage(writableImage);
    Main.rootMain.getChildren().add(imageView);
    imageView.toBack();
}

}
