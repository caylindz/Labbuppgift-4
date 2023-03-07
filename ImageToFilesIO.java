package ce.kth.labb4.model;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.io.IOException;
import java.io.*;


public class ImageToFilesIO {
    int width = 828, height = 1104;
    // ang int width = 1200, height = 630;
    // green DIM int width = 480, height = 480;
    // red DIM int width =512, height=512;
    Image newImage = null;


    public void ImageToFilesIO(int width, int height) {

        this.width = width;
        this.height = height;

        //newImage = readFromFile(width, height, newImage);
        //writeToFile(newImage);

    }

    public Image readFromFile() {
        BufferedImage nubbeFile = null;
        try {

            //File nubbeFile = new File("/Users/caylindberg/Documents/Programmering/Java Programmering/Bilder/nubbe.JPG");
            nubbeFile = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            nubbeFile = ImageIO.read(new File("/Users/caylindberg/Documents/Programmering/Java Programmering/Bilder/nubbe.JPG"));

            newImage = convertToFxImage(nubbeFile);

            System.out.println("Image read" + newImage);

        } catch (IOException i) {
            System.out.println("Error" + i);
        }
        return newImage;
    }

    /** Taken from Dan on Stack Overflow https://stackoverflow.com/questions/30970005/bufferedimage-to-javafx-image **/
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    public void writeToFile( Image image){

        try{
            File output = new File("/Users/caylindberg/Documents/Programmering/Java Programmering/Labb4/Out.jpg");

            BufferedImage SavedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(SavedImage, "png", output);

            System.out.println("Writing complete");

        } catch (IOException e) {
            System.out.println("Error" + e);
        }


    }

}
