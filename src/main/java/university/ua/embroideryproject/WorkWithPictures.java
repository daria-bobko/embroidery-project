package university.ua.embroideryproject;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;

public class WorkWithPictures {
    private static int startCanvasX =-1;
    private static int startCanvasY =-1;
    private static int pixelWidth;
    private static int pixelHeight;
    public static void saveAsPNG() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Зберегти вишивку як малюнок");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("(*.png)", "*.png")
        );

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(EmbroideryCanvas.writableImage, null), "png", file);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово!");
                alert.setHeaderText(null);
                alert.setContentText("Вишивку збережено у ваших файлах.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка!");
                alert.setHeaderText(null);
                alert.setContentText("Не вдалося зберегти");
                alert.showAndWait();
                Main.ROWS = 30;
                Main.COLS = 30;
                Main.CELL_SIZE = 23;
                EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue());
            }
        }
    }
    public static void loadPNG() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрити патерн вишивки");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("(*.png, *.jpg)", "*.png", "*.jpg")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Image loadedImage = new Image(file.toURI().toString());
                PixelReader reader = loadedImage.getPixelReader();

                pixelWidth = 0;
                pixelHeight = 0;
                for( int x = 0; x < loadedImage.getWidth(); x ++){
                    Color pixel = reader.getColor(x, (int)(loadedImage.getHeight() / 2));
                    if(pixel.getOpacity() > 0.0){
                        pixelWidth++;
                } }
                for (int y = 0; y < loadedImage.getHeight(); y++) {
                    Color pixel = reader.getColor((int)(loadedImage.getWidth() / 2), y);
                    if (pixel.getOpacity() > 0.0) {
                        pixelHeight++;
                    }
                }


                Main.CELL_SIZE = 0;
                for (int x = 0; x < loadedImage.getWidth(); x++) {
                    Color pixel = reader.getColor(x, (int)(loadedImage.getHeight() / 2));
                    if (pixel.getOpacity() > 0.0) {
                        startCanvasX = x;
                        break;
                    }
                }
                for (int y = 0;  y< loadedImage.getHeight(); y++) {
                    Color pixel = reader.getColor((int)(loadedImage.getWidth()/2) , y);
                    if (pixel.getOpacity() > 0.0) {
                        startCanvasY = y;
                        break;
                    }
                }

                if (startCanvasX == -1) startCanvasX = 0;

                Color borderColor = reader.getColor(0,0);
                for (int x = startCanvasX + 1; x < loadedImage.getWidth(); x++) {
                    Color current = reader.getColor(x, 1);
                    if(current.equals(borderColor)){
                        Main.CELL_SIZE = x - startCanvasX+1;
                        break;
                    }
                }
                if (startCanvasY == -1) startCanvasY = 0;

                if(Main.CELL_SIZE == 0){ Main.CELL_SIZE = 20;}

                Main.COLS = (int) (pixelWidth / Main.CELL_SIZE);
                Main.ROWS = (int) (pixelHeight / Main.CELL_SIZE);
                Main.grid = new Color[Main.ROWS][Main.COLS];


                for (int row = 0; row < Main.ROWS; row++) {
                    for (int col = 0; col < Main.COLS; col++) {
                        double pixelX = (startCanvasX + (col * Main.CELL_SIZE) + (Main.CELL_SIZE/2));
                        double pixelY = (startCanvasY + (row * Main.CELL_SIZE) + (Main.CELL_SIZE/2));

                        if (pixelX < pixelWidth && pixelY < pixelHeight) {
                            Color colorFromFile = reader.getColor((int)pixelX, (int)pixelY);
                            if (colorFromFile.equals(Color.WHITE)) {
                                Main.grid[row][col] = null;
                            } else {
                                Main.grid[row][col] = colorFromFile;
                            }
                        }
                    }
                }
                EmbroideryCanvas.printDownLoadPicture();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово!");
                alert.setHeaderText(null);
                alert.setContentText("Вишивку завантажено на полотно.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка!");
                alert.setHeaderText(null);
                alert.setContentText("Виберіть інше зображення.");
                alert.showAndWait();
                Main.ROWS = 30;
                Main.COLS = 30;
                Main.CELL_SIZE = 23;
                EmbroideryCanvas.drawEmbroideryCanvas(MainScene.forGridChangePicker.getValue());
            }
        }
    }

}
