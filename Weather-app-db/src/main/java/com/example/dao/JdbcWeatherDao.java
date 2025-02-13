package com.example.dao;

import com.example.model.LatLon;
import com.example.model.User;
import com.example.model.WeatherDto;
import com.example.model.WeatherObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcWeatherDao implements WeatherDao {

    private JdbcTemplate template;

    public JdbcWeatherDao(DataSource datasource) {
        this.template = new JdbcTemplate(datasource);
    }

    @Override
    public void saveWeather(WeatherObject weather, User user, LatLon latLon) {
        String sql = "INSERT INTO weather (user_id, zipcode, name, " +
        "weather_date, temp, feels_like, humidity, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

        int zip = Integer.parseInt(latLon.getZip());

        template.update(sql,
                user.getUserId(),
                zip,
                latLon.getName(),
                LocalDate.now(),
                weather.getMain().getTemp(),
                weather.getMain().getFeelsLike(),
                weather.getMain().getHumidity(),
                weather.getWeather()[0].getDescription());


    }

    @Override
    public List<WeatherDto> getWeatherByUserId(int userId) {
        String sql = "SELECT weather_id, user_id, zipcode, " +
                " name, weather_date, temp, feels_like, humidity," +
                " description FROM weather WHERE user_id=?";

        SqlRowSet results = template.queryForRowSet(sql, userId);
        List<WeatherDto> weatherDtoList = new ArrayList<>();
        while (results.next()){
            WeatherDto dto = mapRowToWeatherDto(results);
            weatherDtoList.add(dto);
        }
        return weatherDtoList;
    }


    private WeatherDto mapRowToWeatherDto(SqlRowSet result){



        int weatherId = result.getInt("weather_id");
        int userId= result.getInt("user_id");
        int zipcode = result.getInt("zipcode");
        String name = result.getString("name");
        LocalDate date = result.getDate("weather_date").toLocalDate();
        double temp = result.getDouble("temp");
        double feelsLike = result.getDouble("feels_like");
        int humidity = result.getInt("humidity");
        String description = result.getString("description");

        WeatherDto dto = new WeatherDto(weatherId, userId, zipcode, date, temp,
                feelsLike, humidity, description, name);

        return dto;

    }
}
