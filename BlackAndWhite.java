package ce.kth.labb4.matcher;

import ce.kth.labb4.model.Histogram;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.Arrays;

public class BlackAndWhite implements IimageMatcher {


    public BlackAndWhite(){
    }

 /** Sets all pixel color values to either black or white by analyzing seperate color values**/
    @Override
    public int[][] change(int[][] pixelMatrix) {

        for (int i = 0; i < pixelMatrix.length; i++) {
            for (int j = 0; j < pixelMatrix[0].length; j++) {


                //Color c = new Color(pixelMatrix[i][j]); //Borde lösas när matrisen består av RGB värden!

                int aValue = ((pixelMatrix[i][j] >> 24) & 0xff);
                int blue = ((pixelMatrix[i][j] >> 16) & 0xff);
                int green = ((pixelMatrix[i][j] >> 8) & 0xff);
                int red = ((pixelMatrix[i][j]) & 0xff);


                int intensity = (red + green + blue) / 3;

                if (intensity < 128) {
                    blue = 0;
                    green = 0;
                    red = 0;

                    int color = (aValue << 24) | (red << 16) | (green << 8) | blue;
                    pixelMatrix[i][j] = color;

                } else {
                    blue = 255;
                    green = 255;
                    red = 255;

                    int color = (aValue << 24) | (red << 16) | (green << 8) | blue;
                    pixelMatrix[i][j] = color;
                }
            }
        }

        return pixelMatrix;
    }
}
