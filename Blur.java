package ce.kth.labb4.matcher;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.lang.Math;

public class Blur implements IimageMatcher{
    private int iterations;


    public Blur(int iterations){
        this.iterations = iterations;
    }

    @Override
    public int[][] change(int[][] pixelMatrix) {


        for(int k=0; k < iterations; k++){

            for (int i = 1; i < pixelMatrix.length-1; i++) {
                for (int j = 1; j < pixelMatrix[0].length-1; j++) {

                    int aValue = ((pixelMatrix[i][j] >> 24) & 0xff);

                    int rValue0 = ((pixelMatrix[i][j] >> 16) & 0xff);
                    int rValue1 = ((pixelMatrix[i-1][j-1] >> 16) & 0xff);
                    int rValue2 = ((pixelMatrix[i-1][j] >> 16) & 0xff);
                    int rValue3 = ((pixelMatrix[i-1][j+1] >> 16) & 0xff);
                    int rValue4 = ((pixelMatrix[i][j-1] >> 16) & 0xff);
                    int rValue6 = ((pixelMatrix[i][j+1] >> 16) & 0xff);
                    int rValue7 = ((pixelMatrix[i+1][j-1] >> 16) & 0xff);
                    int rValue8 = ((pixelMatrix[i+1][j] >> 16) & 0xff);
                    int rValue9 = ((pixelMatrix[i+1][j+1] >> 16) & 0xff);

                    int finalRV = Math.round((rValue0 + rValue1 + rValue2 + rValue3 + rValue4 + rValue6 + rValue7 + rValue8 + rValue9)/9.00f);



                    int gValue0 = ((pixelMatrix[i][j] >> 8) & 0xff);
                    int gValue1 = ((pixelMatrix[i-1][j-1] >> 8) & 0xff);
                    int gValue2 = ((pixelMatrix[i-1][j] >> 8) & 0xff);
                    int gValue3 = ((pixelMatrix[i-1][j+1] >> 8) & 0xff);
                    int gValue4 = ((pixelMatrix[i][j-1] >> 8) & 0xff);
                    int gValue6 = ((pixelMatrix[i][j+1] >> 8) & 0xff);
                    int gValue7 = ((pixelMatrix[i+1][j-1] >> 8) & 0xff);
                    int gValue8 = ((pixelMatrix[i+1][j] >> 8) & 0xff);
                    int gValue9 = ((pixelMatrix[i+1][j+1] >> 8) & 0xff);

                    int finalGV = Math.round((gValue0 + gValue1 + gValue2 + gValue3 + gValue4 + gValue6 + gValue7 + gValue8 +gValue9)/9.00f);



                    int bValue0 = ((pixelMatrix[i][j]) & 0xff);
                    int bValue1 = ((pixelMatrix[i-1][j-1]) & 0xff);
                    int bValue2 = ((pixelMatrix[i-1][j]) & 0xff);
                    int bValue3 = ((pixelMatrix[i-1][j+1]) & 0xff);
                    int bValue4 = ((pixelMatrix[i][j-1]) & 0xff);
                    int bValue6 = ((pixelMatrix[i][j+1]) & 0xff);
                    int bValue7 = ((pixelMatrix[i+1][j-1]) & 0xff);
                    int bValue8 = ((pixelMatrix[i+1][j]) & 0xff);
                    int bValue9 = ((pixelMatrix[i+1][j+1]) & 0xff);

                    int finalBV = Math.round((bValue0 + bValue1 + bValue2 + bValue3 + bValue4 + bValue6 + bValue7 + bValue8 + bValue9)/9.00f);

                    int color = (aValue << 24) | (finalRV << 16) | (finalGV << 8) | finalBV;

                    pixelMatrix[i][j] = color;
                }
            }
        }

        return pixelMatrix;
    }
}
