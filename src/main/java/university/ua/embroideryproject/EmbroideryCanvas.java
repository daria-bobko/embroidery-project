package university.ua.embroideryproject;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

// 685 width for canvas
//
public class EmbroideryCanvas {
    public static WritableImage writableImage = new WritableImage(1300, 900);
    public static PixelWriter writer = writableImage.getPixelWriter();
    public static ImageView imageView = new ImageView(writableImage);
    private static int startX;
    private static int startY;

    public static void drawEmbroideryCanvas() {
        Main.root2.getChildren().remove(imageView);
        startX = (1290 - Main.COLS * Main.CELL_SIZE) / 2;
        startY = (820 - Main.ROWS * Main.CELL_SIZE) / 2;

        writableImage = new WritableImage(1300, 900);
        writer = writableImage.getPixelWriter();

        for (int row = 0; row < Main.ROWS; row++) {
            for (int col = 0; col < Main.COLS; col++) {
                drawGrid(startX + col * Main.CELL_SIZE, startY + row * Main.CELL_SIZE, Main.CELL_SIZE, Color.WHITE);
            }
        }
        imageView.setImage(writableImage);
        Main.root2.getChildren().add(imageView);
    }

    private static void drawGrid(int x, int y, int s, Color color) {
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (i == 0 || j == 0 || j == s - 1 || i == s - 1) {
                    writer.setColor(x + j, y + i, Color.DARKGRAY);
                } else {
                    writer.setColor(x + j, y + i, color);
                }
            }
        }
    }

    public static void drawOnCanvas(double x, double y, Color color) {
        if (x <= startX + (Main.CELL_SIZE * Main.COLS) && x>= startX && y <= startY + (Main.CELL_SIZE * Main.ROWS) && y >= startY) {
            int placeInMatrixColumn = (int) (x - startX) / Main.CELL_SIZE;
            int placeInMatrixRow = (int) (y - startY) / Main.CELL_SIZE;
            Main.grid[placeInMatrixRow][placeInMatrixColumn] = 1;
            drawGrid(startX+placeInMatrixColumn*Main.CELL_SIZE, startY+placeInMatrixRow*Main.CELL_SIZE, Main.CELL_SIZE, color);

        }


    }
}