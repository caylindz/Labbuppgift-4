package ce.kth.labb4;

import ce.kth.labb4.controller.Controller;
import ce.kth.labb4.matcher.BlackAndWhite;
import ce.kth.labb4.matcher.Blur;
import ce.kth.labb4.matcher.Contrast;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;



// inspirerat av övningskod
public class App extends Application {
    private ImageView imageView;
    private Image theImage;
    private Image changedImage;
    private FileChooser fileChooser;
    private Controller controller= new Controller();

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();

        StackPane constrainingPane = new StackPane();
        StackPane centerPane = new StackPane();
        FlowPane bottomPane = new FlowPane();
        FlowPane sidePane = new FlowPane();
        sidePane.setAlignment(Pos.CENTER);
        FlowPane histoPane = new FlowPane();
        VBox Sliders = new VBox();
        Sliders.setAlignment(Pos.CENTER);

        Sliders.setPadding(new Insets(20));
        Sliders.setSpacing(10);


        Button loadButton = new Button("Load new image");
        Button Choice1Button = new Button("Get Histogram");
        Button Choice2Button = new Button("Manipulate Image");
        Button Change1Button = new Button("Change Contrast");
        Button Change2Button = new Button("Convert to Black and White");
        Button Change3Button = new Button("Blurr the Image");
        Button BWb= new Button("Convert");
        Button saveButton = new Button("Save");

        Button BackHisto = new Button("Back");
        Button BackBW = new Button("Back");
        Button BackContrast = new Button("Back");
        Button BackBlur = new Button("Back");
        Button BackMain = new Button("Back");


        Label sliderLabel1 = new Label("Window");
        Label sliderLabel2 = new Label("Level");
        Label sliderLabel3 = new Label("Blurr Level");

        Slider window = new Slider();
        window.setMax(255);
        window.setMin(0);
        window.setShowTickLabels(true);
        window.setShowTickMarks(true);
        window.setBlockIncrement(10);

        Slider level = new Slider();
        level.setMax(255);
        level.setMin(0);
        level.setShowTickLabels(true);
        level.setShowTickMarks(true);
        level.setBlockIncrement(10);

        Slider blur = new Slider();
        blur.setMax(16);
        blur.setMin(0);
        blur.setShowTickLabels(true);
        blur.setShowTickMarks(true);
        blur.setBlockIncrement(2);



        imageView = new ImageView();
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter(
                        "png files", "*.png","*.JPG","*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open Image File");


        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                theImage = controller.LoadImage(primaryStage, fileChooser, imageView, root);
                bottomPane.getChildren().remove(loadButton);
                sidePane.getChildren().add(Choice1Button);
                sidePane.getChildren().add(Choice2Button);
                root.setLeft(sidePane);
            }
        });

        Choice1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().remove(Choice1Button);
                sidePane.getChildren().remove(Choice2Button);
                controller.ViewImage(theImage,root);
                controller.Histogram(theImage, histoPane);
                histoPane.getChildren().add(BackHisto);
                root.setLeft(histoPane);
            }
        });

        BackHisto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                histoPane.getChildren().clear();
                sidePane.getChildren().add(Choice1Button);
                sidePane.getChildren().add(Choice2Button);
                root.setLeft(sidePane);
            }
        });

        Choice2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().remove(Choice1Button);
                sidePane.getChildren().remove(Choice2Button);
                sidePane.getChildren().add(Change1Button);
                sidePane.getChildren().add(Change2Button);
                sidePane.getChildren().add(Change3Button);
                sidePane.getChildren().add(BackMain);
                root.setLeft(sidePane);
            }
        });

        BackMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().add(Choice1Button);
                sidePane.getChildren().add(Choice2Button);
                sidePane.getChildren().remove(Change1Button);
                sidePane.getChildren().remove(Change2Button);
                sidePane.getChildren().remove(Change3Button);
                sidePane.getChildren().remove(BackMain);
                root.setLeft(sidePane);
            }
        });

        Change1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().remove(Change1Button);
                sidePane.getChildren().remove(Change2Button);
                sidePane.getChildren().remove(Change3Button);
                sidePane.getChildren().remove(BackMain);

                Sliders.getChildren().addAll(sliderLabel1, window);
                Sliders.getChildren().addAll(sliderLabel2, level);
                Sliders.getChildren().add(BackContrast);
                Sliders.getChildren().add(saveButton);
                root.setLeft(Sliders);
            }
        });

        BackContrast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sliders.getChildren().clear();

                sidePane.getChildren().add(Change1Button);
                sidePane.getChildren().add(Change2Button);
                sidePane.getChildren().add(Change3Button);
                sidePane.getChildren().add(BackMain);
                root.setLeft(sidePane);

            }
        });

        window.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int W = (int) Math.round(window.getValue());
                int L = (int) Math.round(level.getValue());
                changedImage = controller.LoadChanger(theImage, new Contrast(W,L), root );

            }
        });

        level.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int W = (int) Math.round(window.getValue());
                int L = (int) Math.round(level.getValue());
                changedImage = controller.LoadChanger(theImage, new Contrast(W,L), root);

            }
        });

        Change2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().clear();

                sidePane.getChildren().add(BWb);
                sidePane.getChildren().add(BackBW);
                sidePane.getChildren().add(saveButton);
                root.setLeft(sidePane);
            }
        });

        BWb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               changedImage = controller.LoadChanger(theImage, new BlackAndWhite(), root);
            }
        });

        BackBW.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().remove(BackBW);
                sidePane.getChildren().remove(BWb);
                sidePane.getChildren().remove(saveButton);

                sidePane.getChildren().add(Change1Button);
                sidePane.getChildren().add(Change2Button);
                sidePane.getChildren().add(Change3Button);
                sidePane.getChildren().add(BackMain);
                root.setLeft(sidePane);
            }
        });

        Change3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().clear();

                Sliders.getChildren().removeAll(window,sliderLabel1,level,sliderLabel2);
                Sliders.getChildren().addAll(sliderLabel3, blur);
                Sliders.getChildren().add(BackBlur);
                Sliders.getChildren().add(saveButton);

                root.setLeft(Sliders);

            }
        });

        BackBlur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sliders.getChildren().removeAll(sliderLabel3, blur);
                Sliders.getChildren().remove(BackBlur);

                sidePane.getChildren().add(Change1Button);
                sidePane.getChildren().add(Change2Button);
                sidePane.getChildren().add(Change3Button);
                sidePane.getChildren().add(BackMain);
                root.setLeft(sidePane);
            }
        });


        blur.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int B = (int) Math.round(blur.getValue());
                changedImage = controller.LoadChanger(theImage, new Blur(B), root );

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sidePane.getChildren().clear();
                controller.save(changedImage);
                root.setRight(null);
                Sliders.getChildren().clear();
                bottomPane.getChildren().add(loadButton);
                root.setBottom(bottomPane);
            }
        });






        constrainingPane.getChildren().add(imageView);
        constrainingPane.minWidthProperty().bind(centerPane.widthProperty().divide(2));
        constrainingPane.maxHeightProperty().bind(centerPane.heightProperty().divide(2));
        constrainingPane.minHeightProperty().bind(centerPane.heightProperty().divide(2));
        centerPane.getChildren().add(constrainingPane);
        root.setCenter(centerPane);

        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(10));
        bottomPane.getChildren().add(loadButton);
        root.setBottom(bottomPane);

        root.setPadding(new Insets(5));
        Scene scene = new Scene(root, 1000, 550);
        scene.setFill(Color.WHITE);

        primaryStage.setTitle("Image Handling");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch();
    }
}