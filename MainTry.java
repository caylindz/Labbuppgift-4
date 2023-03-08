package ce.kth.labb4.model;

import ce.kth.labb4.matcher.BlackAndWhite;
import ce.kth.labb4.matcher.Blur;
import ce.kth.labb4.matcher.Contrast;
import ce.kth.labb4.matcher.IimageMatcher;
import javafx.scene.image.Image;

import java.util.Arrays;

public class MainTry {

    public static void main(String[] args) {

        ImageToFilesIO readPic = new ImageToFilesIO();
        Image pic = readPic.readFromFile();


        Converter newconvert = new Converter();
        int[][] pixelated = newconvert.ConvertedToPixels(pic);

        //System.out.println(Arrays.toString(pixelated));

        Histogram picHistogram = new Histogram(pixelated);
        picHistogram.calculateIntensity();
        System.out.println(picHistogram);

        BlackAndWhite BW = new BlackAndWhite();
        pixelated = BW.change(pixelated);

        //Blur blurred = new Blur(15);
        //pixelated = blurred.change(pixelated);

        //Contrast contraster = new Contrast(100,50);
        //pixelated = contraster.change(pixelated);


        Image loadImage = newconvert.ImageFromPixels(pixelated);
        readPic.writeToFile(loadImage);

    }
}
