package com.example.dao;

import com.example.model.LatLon;
import com.example.model.User;
import com.example.model.WeatherDto;
import com.example.model.WeatherObject;
import java.util.List;

public interface WeatherDao {

    //CRUD
    void saveWeather(WeatherObject weather, User user, LatLon latLon);

   List<WeatherDto> getWeatherByUserId(int userId);

}
