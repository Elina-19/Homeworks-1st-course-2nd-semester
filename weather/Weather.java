package weather;

public class Weather {

    private String id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String created;
    private String applicable_date;
    private Double min_temp;
    private Double max_temp;
    private Double the_temp;
    private Double wind_speed;
    private Double wind_direction;
    private Double air_pressure;
    private Double humidity;
    private Double visibility;
    private Double predictability;

    public String getId() {
        return id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public String getCreated() {
        return created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public Double getMin_temp() {
        return (double)Math.round(min_temp*100)/100;
    }

    public Double getMax_temp() {
        return (double)Math.round(max_temp*100)/100;
    }

    public Double getThe_temp() {
        return (double)Math.round(the_temp*100)/100;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public Double getWind_direction() {
        return wind_direction;
    }

    public Double getAir_pressure() {
        return air_pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getVisibility() {
        return visibility;
    }

    public Double getPredictability() {
        return predictability;
    }

    @Override
    public String toString() {
        return  "temperature " + the_temp + "\n" +
                "minimum " + min_temp + "\n" +
                "maximum " + max_temp + "\n" +
                "weather " + weather_state_name + "\n";
    }
}
