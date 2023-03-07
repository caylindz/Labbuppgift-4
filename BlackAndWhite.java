package ce.kth.labb4.matcher;

import ce.kth.labb4.model.Histogram;
import javafx.scene.image.Image;

import java.awt.*;

public class BlackAndWhite implements IimageMatcher {


    public BlackAndWhite(){
    }


    @Override
    public int[][] change(int[][] pixelMatrix) {

        for (int i = 0; i < pixelMatrix.length; i++) {
            for (int j = 0; j < pixelMatrix[0].length; j++) {

                int red = 0;
                int green = 0;
                int blue = 0;

                Color c = new Color(pixelMatrix[i][j]); //Borde lösas när matrisen består av RGB värden!

                red = (int) c.getRed(); //intensitet av röd mellan 0-255
                green = (int) c.getGreen(); //intensitet av grön mellan 0-255
                blue = (int) c.getBlue(); //intensitet av blå mellan 0-255

                int intensity = (red + green + blue) / 3;

                if (intensity < 128) {
                    pixelMatrix[i][j] = 0;
                } else {
                    pixelMatrix[i][j] = 255;
                }
            }
        }
        return pixelMatrix;
    }
}
