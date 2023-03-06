package ce.kth.labb4.model;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class Histogram {

    int[][] image;

    public Histogram(int[][] image) {

        this.image = image;
        calculateIntensity();

    }


    public int [][] calculateIntensity(){

        int [] intensityRed = new int[256];
        int [] intensityGreen = new int[256];
        int [] intensityBlue = new int[256];

        int [][] intensity = new int [256][3];


        for (int i=0; i< image.length; i++){
            for (int j=0; j< image[0].length; j++){

                int red = 0;
                int green = 0;
                int blue = 0;

                Color c = new Color(image[i][j]); //Borde lösas när matrisen består av RGB värden!

                red = (int) c.getRed(); //intensitet av röd mellan 0-255
                green = (int) c.getGreen(); //intensitet av grön mellan 0-255
                blue = (int) c.getBlue(); //intensitet av blå mellan 0-255

                //Nu har vi tre värden på pixeln vi tittar på, hur röd, grön och blå den är



                intensityRed[red] ++;
                intensityGreen[green] = +1;
                intensityBlue[blue] = +1;

                //För varje intensitet som vi fick fram har vi nu sagt åt arrayen att det finns
                // en till pixel med just den intensiteten!
            }
        }

        for (int k=0; k<265; k++){
            intensity [k][1] = intensityRed[k];
            intensity [k][2] = intensityGreen[k];
            intensity [k][3] = intensityBlue[k];
        }

        System.out.println(Arrays.toString(intensityRed));
        System.out.println(Arrays.toString(intensityGreen));
        System.out.println(Arrays.toString(intensityBlue));

        //För att kolla om det fungerar sen! Borde skriva ut tre arrays med värden som histogrammet
        // kan använda sig av

        return intensity;
    }



}
