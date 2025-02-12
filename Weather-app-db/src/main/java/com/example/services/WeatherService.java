package com.example.services;

import org.springframework.web.client.RestTemplate;
import com.example.model.LatLon;
import com.example.model.WeatherObject;

public class WeatherService {

    private RestTemplate template = new RestTemplate();
    private final String API_URL = "http://api.openweathermap.org/";
    private final String API_KEY = "3faaa78508fe131303a47fa1f58e218b";

    public LatLon getLatLong(String zip){
        System.out.println(API_KEY);
        String url = API_URL + "geo/1.0/zip?zip=" + zip + "&appid=" + API_KEY;
        return template.getForObject(url, LatLon.class);

    }

    public WeatherObject getWeather(LatLon latLon){
        String url = "https://api.openweathermap.org" +
                "/data/2.5/weather?lat=" + latLon.getLat() +
                "&lon=" + latLon.getLon() +
                "&appid=" + API_KEY + "&units=imperial";
        WeatherObject response =
                template.getForObject(url, WeatherObject.class);
        return response;
    }
}
