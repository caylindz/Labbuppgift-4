package ce.kth.labb4.model;

import ce.kth.labb4.matcher.BlackAndWhite;
import ce.kth.labb4.matcher.Blur;
import ce.kth.labb4.matcher.Contrast;
import ce.kth.labb4.matcher.IimageMatcher;
import javafx.scene.image.Image;

import java.util.Arrays;

public class TheImage {
    private int[][] pixelated;
    private Converter converter = new Converter();


/** Assembles all model classes and allows them to be accessed by one class **/
    public TheImage(Image anImage){
        pixelated = converter.ConvertedToPixels(anImage);
    }


    public int[][] changeImage(IimageMatcher matcher){
        int [][] changedPixels  = pixelated;

        changedPixels = matcher.change(changedPixels);
        return changedPixels;
    }


    public int[][] getHistogram(){
        Histogram histo = new Histogram(pixelated);

        return histo.calculateIntensity();
    }

    public Image getImage(int[][] pixels){
        Image reconstructed = converter.ImageFromPixels(pixels);

        return reconstructed;
    }



}
