package university.ua.embroideryproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class PixelDrawName {

    private static WritableImage canvas = new WritableImage(1000, 900);
    private static PixelWriter writer = canvas.getPixelWriter();

    public static void startDrawing() {
        int startX = 0;
        int startY = 12;
        int s = 25;


        int[][] matrix = new int[31][31];

        matrix[0][15] = 1; matrix[1][14]=1; matrix[1][15]=1; matrix[1][16]=1; matrix[2][13]=1; matrix[2][17]=1; //верхній кут
        matrix[4][17] = 1; matrix[4][18] = 1; matrix[5][19] = 1; matrix[6][18] = 1; matrix[7][19] = 1;
        matrix[5][14] = 1; matrix[6][14] = 1; matrix[7][14] = 1;
        matrix[5][16] = 1; matrix[6][16] = 1; matrix[7][16] = 1;
        matrix[4][13] = 1; matrix[4][12] = 1; matrix[5][11] = 1; matrix[6][12] = 1; matrix[7][11]= 1;
        matrix[9][15] = 1; matrix[10][14] = 1; matrix[10][16] = 1; matrix[11][15] = 1; matrix[11][13] =1; matrix[11][17]=1; matrix[12][14]= 1; matrix[12][15] =1; matrix[12][16] = 1;


        matrix[30][15] = 1; matrix[29][14]=1; matrix[29][15]=1; matrix[29][16]=1; matrix[28][13]=1; matrix[28][17]=1; //нижній кут
        matrix[26][17] = 1; matrix[26][18] = 1; matrix[25][19] = 1; matrix[24][18] = 1; matrix[23][19] = 1;
        matrix[25][14] = 1; matrix[24][14] = 1; matrix[23][14] = 1;
        matrix[25][16] = 1; matrix[24][16] = 1; matrix[23][16] = 1;
        matrix[26][13] = 1; matrix[26][12] = 1; matrix[25][11] = 1; matrix[24][12] = 1; matrix[23][11]= 1;
        matrix[21][15] = 1; matrix[20][14] =1; matrix[20][16] =1; matrix[19][15] = 1; matrix [19][17] =1; matrix[19][13] =1; matrix[18][15]= 1; matrix[18][16] =1;matrix[18][14]=1;


        matrix[15][0] = 1; matrix[14][1]=1; matrix[15][1]=1; matrix[16][1]=1; matrix[13][2]=1; matrix[17][2]=1; //лівий кут
        matrix[13][4] = 1; matrix[12][4] = 1; matrix[11][5] = 1; matrix[12][6] = 1; matrix[11][7] = 1;
        matrix[14][5] = 1; matrix[14][6] = 1; matrix[14][7] = 1;
        matrix[16][5] = 1; matrix[16][6] = 1; matrix[16][7] = 1;
        matrix[17][4] = 1; matrix[18][4] = 1; matrix[19][5] = 1; matrix[18][6] = 1; matrix[19][7]= 1;
        matrix[15][9] = 1; matrix[14][10] = 1; matrix[16][10] = 1; matrix[15][11] = 1; matrix[13][11] =1; matrix[17][11]=1; matrix[14][12]= 1; matrix[15][12] =1; matrix[16][12] = 1;


        matrix[15][30] = 1; matrix[14][29]=1; matrix[15][29]=1; matrix[16][29]=1; matrix[13][28]=1; matrix[17][28]=1; //правий кут
        matrix[13][26] = 1; matrix[12][26] = 1; matrix[11][25] = 1; matrix[12][24] = 1; matrix[11][23] = 1;
        matrix[14][25] = 1; matrix[14][24] = 1; matrix[14][23] = 1;
        matrix[16][25] = 1; matrix[16][24] = 1; matrix[16][23] = 1;
        matrix[17][26] = 1; matrix[18][26] = 1; matrix[19][25] = 1; matrix[18][24] = 1; matrix[19][23]= 1;
        matrix[15][21] = 1; matrix[14][20] =1; matrix[16][20] =1; matrix[15][19] = 1; matrix [17][19] =1; matrix[13][19] =1; matrix[15][18]= 1; matrix[16][18] =1;matrix[14][18]=1;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3-i; j++) {
                matrix[9+i][9+j] = 1;
                matrix[9+i][21-j] = 1;
                matrix[21-i][9+j] = 1;
                matrix[21-i][21-j] = 1;
            }
        }

        matrix[7][7] = 1; matrix[6][6]=1; matrix [4][4] =1;
        matrix[7][23] = 1; matrix [6][24] = 1; matrix[4][26] =1;
        matrix[23][7] =1; matrix[24][6] = 1; matrix[26][4] =1;
        matrix[23][23] =1; matrix[24][24] =1; matrix[26][26] =1;

//        for (int row = 0; row < 31; row++) {
//            for (int col = 0; col < 31; col++) {
//                if (matrix[row][col] == 1) {
//                    drawPixel(startX + col * s, startY + row * s);
//
//                }
//            }
//        }

        List<int[]> pointsToDraw = new ArrayList<>();
        for (int r = 0; r < 31; r++) {
            for (int c = 0; c < 31; c++) {
                if (matrix[r][c] == 1) {
                    pointsToDraw.add(new int[]{r, c});
                }
            }
        }
        Timeline timeline = new Timeline();
        for (int i = 0; i < pointsToDraw.size(); i++) {
            int[] point = pointsToDraw.get(i);
            KeyFrame frame = new KeyFrame(
                    Duration.millis(i * 10), e -> drawPixel(startX + point[1] * s, startY + point[0] * s)
            );
            timeline.getKeyFrames().add(frame);
        }

        timeline.play();

        ImageView imageView = new ImageView(canvas);
        Main.rootMenu.getChildren().add(imageView);
    }

    public static void drawPixel(int x, int y) {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                writer.setColor(x + j, y + i, Color.DARKRED);
            }
        }
    }





}
