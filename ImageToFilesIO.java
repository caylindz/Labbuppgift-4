package ce.kth.labb4.controller;

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

    public ImageToFilesIO() {
    }

    public void writeToFile(Image image){

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
