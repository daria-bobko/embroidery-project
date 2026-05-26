package university.ua.embroideryproject;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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

    public static void drawEmbroideryCanvas(Color forGrid) {
        Main.rootMain.getChildren().remove(imageView);
        int canvasWidth = Main.COLS * Main.CELL_SIZE;
        int canvasHeight = Main.ROWS * Main.CELL_SIZE;

        startX = 0;
        startY = 0;

        writableImage = new WritableImage(canvasWidth, canvasHeight);
        writer = writableImage.getPixelWriter();

        fillAllCanvas(Color.WHITE, forGrid);
        screenStartX = (1290 - canvasWidth)/2.0;
        screenStartY = (820 - canvasHeight)/2.0;
        imageView.setX(screenStartX);
        imageView.setY(screenStartY);
        imageView.setImage(writableImage);
        Main.rootMain.getChildren().add(imageView);
}


    private static void drawCell(int x, int y, int s, Color mainColor, Color forGrid) {
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (i == 0 || j == 0 || j == s - 1 || i == s - 1) {
                    writer.setColor(x + j, y + i, forGrid);
                } else {
                    writer.setColor(x + j, y + i, mainColor);}
            }
        }
    }

    public static void drawOnCanvas(double x, double y, Color mainColor, Color forGrid) {
        for(int i = 0; i < MainScene.valueForPencil; i++){
            for(int j = 0; j < MainScene.valueForPencil; j++){
        if (x + (Main.CELL_SIZE*i) < screenStartX + (Main.CELL_SIZE * Main.COLS) && x> screenStartX && y+(Main.CELL_SIZE*j) < screenStartY + (Main.CELL_SIZE * Main.ROWS) && y > screenStartY) {
            int placeInMatrixColumn = (int) (x + (Main.CELL_SIZE*i)- screenStartX) / Main.CELL_SIZE;
            int placeInMatrixRow = (int) (y+ (Main.CELL_SIZE*j) - screenStartY) / Main.CELL_SIZE;
            Color colorSaveValue = mainColor.equals(Color.WHITE) ? null : mainColor;
            Main.grid[placeInMatrixRow][placeInMatrixColumn] = colorSaveValue;
            drawCell(startX + placeInMatrixColumn * Main.CELL_SIZE, startY + placeInMatrixRow * Main.CELL_SIZE, Main.CELL_SIZE, mainColor, forGrid);

            if (MainScene.vertical.isSelected()) {
                int mirrorCol = Main.COLS - 1 - placeInMatrixColumn;
                Main.grid[placeInMatrixRow][mirrorCol] = colorSaveValue;
                drawCell(startX + mirrorCol * Main.CELL_SIZE, startY + placeInMatrixRow * Main.CELL_SIZE, Main.CELL_SIZE, mainColor, forGrid);            }
            if (MainScene.horizontal.isSelected()) {
                int mirrorRow = Main.ROWS - 1 - placeInMatrixRow;
                Main.grid[mirrorRow][placeInMatrixColumn] = colorSaveValue;
                drawCell(startX + placeInMatrixColumn * Main.CELL_SIZE, startY + mirrorRow * Main.CELL_SIZE, Main.CELL_SIZE, mainColor, forGrid);
            }
            if (MainScene.horizontal.isSelected() && MainScene.vertical.isSelected()) {
                int mirrorCol = Main.COLS - 1 - placeInMatrixColumn;
                int mirrorRow = Main.ROWS - 1 - placeInMatrixRow;
                Main.grid[mirrorRow][mirrorCol] = colorSaveValue;
                drawCell(startX + mirrorCol * Main.CELL_SIZE, startY + mirrorRow * Main.CELL_SIZE, Main.CELL_SIZE, mainColor, forGrid);
            }
        }}}
    }

    public static void fillAllCanvas(Color mainColor, Color forGrid){
        background = mainColor;
        for (int row = 0; row < Main.ROWS; row++) {
            for (int col = 0; col < Main.COLS; col++) {
                if(mainColor.equals(Color.WHITE)){
                    Main.grid[row][col] = null;
                }
                else {
                    Main.grid[row][col] = mainColor;
                }
                drawCell(startX + col * Main.CELL_SIZE, startY + row * Main.CELL_SIZE, Main.CELL_SIZE, mainColor, forGrid);
            }
        }
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
                        drawCell(bottomX, bottomY, Main.CELL_SIZE, topColor, MainScene.forGridChangePicker.getValue());
                    }
                    else if (!bottomColor.equals(background)&& topColor.equals(background)){
                        Main.grid[row][col] = bottomColor;
                        drawCell(topX, topY, Main.CELL_SIZE, bottomColor, MainScene.forGridChangePicker.getValue());
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
                    drawCell(rightX, rightY, Main.CELL_SIZE, leftColor, MainScene.forGridChangePicker.getValue());
                }
                else if (!rightColor.equals(background)&& leftColor.equals(background)){
                    Main.grid[row][col] = rightColor;
                    drawCell(leftX, leftY, Main.CELL_SIZE, rightColor, MainScene.forGridChangePicker.getValue());
                }
            }
        }

    }}

public static void printDownLoadPicture(Color grid){
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
            drawCell(startX + col * Main.CELL_SIZE, startY + row * Main.CELL_SIZE, Main.CELL_SIZE, cellColor, grid);
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
