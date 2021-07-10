package weather;

import java.util.ArrayList;

public class Town {

    private String title;
    private String location_type;
    private String woeid;
    private String latt_long;
    private ArrayList<Weather> days;

    public String getTitle() {
        return title;
    }

    public String getLocation_type() {
        return location_type;
    }

    public String getWoeid() {
        return woeid;
    }

    public String getLatt_long() {
        return latt_long;
    }

    public ArrayList<Weather> getDays() {
        return days;
    }

    @Override
    public String toString() {
        return  "Town: " + title + "\n" +
                "Weather " + days + "\n";
    }
}
