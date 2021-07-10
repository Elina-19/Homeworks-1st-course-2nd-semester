package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WeatherApp extends Application {

    public static void main(String[] args) throws IOException {
        launch();
    }

    public void start(Stage primaryStage) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Weather");
        primaryStage.setScene(new Scene(root, 476, 634));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
