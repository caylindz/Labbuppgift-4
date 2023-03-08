package ce.kth.labb4.model;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import java.awt.*;
import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Converter{
    private int [][] ConvertedToPixels;

/** Converts any image into an int[][] with color values and vice versa **/
    public void Converter() {

    }

    public int[][] ConvertedToPixels(Image anImage) {
        int width = (int) anImage.getWidth();
        int height = (int) anImage.getHeight();

        ConvertedToPixels = new int[height][width];
        PixelReader PXLreader = anImage.getPixelReader();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //sparar information of färg som ints
                ConvertedToPixels[i][j] = PXLreader.getArgb(j, i);
            }
        }
        return ConvertedToPixels;
    }

    public WritableImage ImageFromPixels(int[][] convertedPixels) {
        int height = convertedPixels.length;
        int width = convertedPixels[0].length;

        WritableImage PixelsToImage = new WritableImage(width, height);
        PixelWriter writer = PixelsToImage.getPixelWriter();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                int aValue = ((convertedPixels[i][j] >> 24) & 0xff);
                int rValue = ((convertedPixels[i][j] >> 16) & 0xff);
                int gValue = ((convertedPixels[i][j] >> 8) & 0xff);
                int bValue = ((convertedPixels[i][j]) & 0xff);

                int color = (aValue << 24) | (rValue << 16) | (gValue << 8) | bValue;
                writer.setArgb(j, i, color);
            }
        }
            return PixelsToImage;
        }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < ConvertedToPixels.length; i++) {
            builder.append(Arrays.toString(ConvertedToPixels[i]));
            builder.append("]");
        }
        return builder.toString();
    }
}


