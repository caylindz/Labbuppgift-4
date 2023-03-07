package ce.kth.labb4.model;

import ce.kth.labb4.matcher.BlackAndWhite;
import ce.kth.labb4.matcher.Blur;
import ce.kth.labb4.matcher.Contrast;

public class Image {
    private int[][] pixelated;
    private Converter converter = new Converter();

    public Image(javafx.scene.image.Image anImage){
        pixelated = converter.ConvertedToPixels(anImage);
    }

    public int[][] blurImage(int iterations){
        Blur blurrer = new Blur(iterations);
        int[][] blurredPixels = pixelated;
        blurrer.change(blurredPixels);

        return blurredPixels;
    }

    public int[][] contrastedImage(int window, int level){
        Contrast contraster = new Contrast(window, level);
        int[][] contrastedPixels = pixelated;
        contraster.change(contrastedPixels);

        return contrastedPixels;
    }

    public int[][] BlackWhite(){
        BlackAndWhite bw = new BlackAndWhite();
        int[][] bwPixels = pixelated;
        bw.change(bwPixels);

        return bwPixels;
    }

    public int[][] getHistogram(){
        Histogram histo = new Histogram(pixelated);

        return histo.calculateIntensity();
    }

    public javafx.scene.image.Image getImage(){
        javafx.scene.image.Image reconstructed = converter.ImageFromPixels(pixelated);

        return reconstructed;
    }


}
