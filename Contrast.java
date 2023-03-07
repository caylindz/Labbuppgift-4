package ce.kth.labb4.matcher;

import ce.kth.labb4.model.Converter;
import javafx.scene.image.Image;

public class Contrast implements IimageMatcher{
    private int window=0, level=0;
    private int a ;
    private int b ;


    public Contrast(int window, int level){
        this.window = window;
        this.level = level;

        a = level - (window/2);
        b = level + (window/2);
    }


    @Override
    public int[][] change(int[][] pixelMatrix) {


        for(int i=0; i<pixelMatrix.length; i++){
            for(int j=0; j<pixelMatrix[0].length; j++){

                int aValue = ((pixelMatrix[i][j] >> 24) & 0xff);
                int rValue = ((pixelMatrix[i][j] >> 16) & 0xff);
                int gValue = ((pixelMatrix[i][j]>> 8) & 0xff);
                int bValue = ((pixelMatrix[i][j]) & 0xff);

                int newRed = helpContrast(rValue);
                int newGreen = helpContrast(gValue);
                int newBlue = helpContrast(bValue);

                int newColor = (aValue << 24) | (newRed << 16) | (newGreen << 8) | newBlue;

                pixelMatrix[i][j] = newColor;

            }
        }

        return pixelMatrix;
    }


    private int helpContrast(int color){
        int colorOut = 0;

        if(color<a){
            colorOut = 0;
        }else{
            if(color>b){
                colorOut = 255;
            }else{
                colorOut=Math.round((255/window)*(color-a));
            }
        }
        return colorOut;
    }


}
