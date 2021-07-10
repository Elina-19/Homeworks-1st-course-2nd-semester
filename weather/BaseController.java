package weather;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class BaseController {

    private final static String imageUrl = "https://www.metaweather.com/static/img/weather/png/";
    private int count = 0;
    private ArrayList<Weather> weathers = new ArrayList<>();
    private ArrayList<Town> towns = new ArrayList<>();

    @FXML
    private TextField city;

    @FXML
    private TextField curCity;

    @FXML
    private TextField the_temp;

    @FXML
    private TextField min_temp;

    @FXML
    private TextField weather_state_name;

    @FXML
    private TextField max_temp;

    @FXML
    private ImageView weather;

    @FXML
    private static Button getData;

    @FXML
    private Button getNext;

    @FXML
    private Button previous;

    @FXML
    private void search() {

        String str = city.getText();
        count = 0;

        try {
            towns = RequestHandler.getTowns(str);
            getNext.setVisible(false);
            previous.setVisible(false);
            if (towns.size() > 1){
                getNext.setVisible(true);
            }
            for (Town town: towns) {
                weathers.add(RequestHandler.getTodayWeather(town.getWoeid()));
            }
            print(0);
        }
        catch (IOException e) {
            System.out.println("There is no internet");
            alertInternetProblem();
        }
        catch (IllegalArgumentException e) {
            System.out.println("There is no such town");
            alertNoSuchTown();
        }
    }

    private void alertInternetProblem(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Проблемы с интернетом. Проверьте подключение");
        alert.showAndWait();
    }

    private void alertNoSuchTown(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Такого города нет");
        alert.showAndWait();
    }

    @FXML
    private void onKeyRight(KeyEvent event){
        if (event.getCode() == KeyCode.RIGHT) {
            next();
        }
    }

    @FXML
    private void onKeyLeft(KeyEvent event){
        if (event.getCode() == KeyCode.LEFT) {
            previous();
        }
    }

    @FXML
    private void next() {

        previous.setVisible(true);
        if (count < towns.size()-1) {
            count++;
            print(count);
        }
        if (count == towns.size()-1) {
            getNext.setVisible(false);
        }
    }

    @FXML
    private void previous() {

        getNext.setVisible(true);
        if (0 < count) {
            count--;
            print(count);
        }
        if (count == 0) {
            previous.setVisible(false);
        }
    }

    private void print(int index) {

        curCity.setText(towns.get(index).getTitle());
        weather.setImage(new Image(imageUrl + weathers.get(index).getWeather_state_abbr() +".png"));
        the_temp.setText(String.valueOf(weathers.get(index).getThe_temp()));
        min_temp.setText(String.valueOf(weathers.get(index).getMin_temp()));
        max_temp.setText(String.valueOf(weathers.get(index).getMax_temp()));
        weather_state_name.setText(weathers.get(index).getWeather_state_name());
    }
}
