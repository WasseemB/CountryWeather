package com.wasseemb.CountryWeather.Api;

/**
 * Created by Wasseem on 03/01/2018.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRestApi {

  private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
  private static WeatherRestApi instance = new WeatherRestApi();
  private ApiClient apiService;

  public WeatherRestApi() {
    Retrofit retrofit = new Retrofit.Builder()
        //.client(GetClient())
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    apiService = retrofit.create(ApiClient.class);
  }

  public static WeatherRestApi getInstance() {
    return instance;
  }

  //public static OkHttpClient GetClient() {
  //  HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
  //  logging.setLevel(HttpLoggingInterceptor.Level.BODY);
  //  OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
  //  return httpClient.addInterceptor(logging).build();
  //}

  public ApiClient getApiService() {
    return apiService;
  }
}