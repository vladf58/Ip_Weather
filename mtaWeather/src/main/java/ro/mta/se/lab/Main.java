package ro.mta.se.lab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.weatherController;

import java.io.IOException;

/**
 * MtaWeather's main function, initializes the scene, loads the GUI.
 * @author Vlad Florea
 */

public class Main extends Application {
    private static final String windowName="MTA Weather";
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(this.getClass().getResource("/view/weatherAppView.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            loader.setController(new weatherController());




            primaryStage.setTitle(windowName);
            primaryStage.setResizable(false);
            primaryStage.show();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
