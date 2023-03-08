package ce.kth.labb4.controller;

import ce.kth.labb4.matcher.IimageMatcher;
import ce.kth.labb4.model.TheImage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {


    /** Allows viewer to select image from file and load it onto the screen*/
    public Image LoadImage(Stage stage, FileChooser fileChooser, ImageView imageView, BorderPane borderPane){
        File file = fileChooser.showOpenDialog(stage);

        try{
            if(file != null) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);

                // this will scale the image/image view to fit its container (pane)
                imageView.fitHeightProperty().bind(borderPane.heightProperty());
                imageView.fitWidthProperty().bind(borderPane.widthProperty());
                borderPane.setRight(imageView);
                imageView.setPreserveRatio(true);

                imageView.setSmooth(true);

                return image;

            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.show();
                return null;
            }
        }catch(RuntimeException e){
            System.out.println("Error:" +e);
            return null;
        }
    }

 /** Gets pixel data and created javafx bar chart fromn logic in TheImage class **/
    public void Histogram(Image anImage, FlowPane pane){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Histogram");
        xAxis.setLabel("Intensity");
        yAxis.setLabel("# Of Pixels");

        XYChart.Series Red = new XYChart.Series();
        Red.setName("Röd");
        XYChart.Series Green = new XYChart.Series();
        Green.setName("Grön");
        XYChart.Series Blue = new XYChart.Series();
        Blue.setName("Blå");

        TheImage image = new TheImage(anImage);
        int[][] theHisto = image.getHistogram();

        System.out.println(theHisto);

        for(int i=0; i<256; i++){
            Red.getData().add(new XYChart.Data(""+ i , theHisto[0][i]));
        }
        for(int i=0; i<256; i++){
            Green.getData().add(new XYChart.Data(""+ i, theHisto[1][i]));
        }
        for(int i=0; i<256; i++){
            Blue.getData().add(new XYChart.Data("" +i, theHisto[2][i]));
        }
        Scene scene = new Scene(bc, 800,600);
        bc.getData().add(Red);
        bc.getData().add(Blue);
        bc.getData().add(Green);
        pane.getChildren().add(bc);

    }

    /** Uses the Iimagematcher interface to manipulate the image in certain ways **/
    public Image LoadChanger(Image anImage, IimageMatcher matcher, BorderPane borderPane){
        TheImage change = new TheImage(anImage);
        int[][] pixels = change.changeImage(matcher);
        Image changedImage = change.getImage(pixels);
        ViewImage(changedImage, borderPane);

        return changedImage;
    }

    public void save(Image anImage){
        ImageToFilesIO IO = new ImageToFilesIO();
        IO.writeToFile(anImage);
    }

    /** Allows to view the image loaded without having to choose it from files again **/
    public void ViewImage(Image anImage, BorderPane borderPane){
        ImageView imageView = new ImageView();
        imageView.setImage(anImage);

        // this will scale the image/image view to fit its container (pane)
        imageView.fitHeightProperty().bind(borderPane.heightProperty());
        imageView.fitWidthProperty().bind(borderPane.widthProperty());
        borderPane.setRight(imageView);
        imageView.setPreserveRatio(true);

        imageView.setSmooth(true);


    }


}