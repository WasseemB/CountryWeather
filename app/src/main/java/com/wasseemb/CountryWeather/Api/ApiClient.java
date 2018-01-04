package com.wasseemb.CountryWeather.Api;

import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.Data.WeatherData.WeatherData;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wasseem on 03/01/2018.
 */

public interface ApiClient {
  @GET("all") Call<List<CountryResponse>> getCountries();

  @GET("forecast") Call<WeatherData> getWeather(
      @Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid,@Query("units") String units);


}