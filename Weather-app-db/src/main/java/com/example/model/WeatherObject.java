package com.example.model;

import java.util.Arrays;

public class WeatherObject {
    private Main main;
    private Weather[] weather;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "main=" + main +
                ", weather=" + Arrays.toString(weather) +
                '}';
    }
}
