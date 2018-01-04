package com.wasseemb.CountryWeather.Utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Wasseem on 03/01/2018.
 */

public class Helper {
  public static String APIKEY = "1867722b6af87e1d0388e10c5a94be34";
  public static String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/";
  public static String GEO_URL = "http://www.geognos.com/api/en/countries/flag/";
  public static String OPEN_WEATHER_ICON_URL = "http://openweathermap.org/img/w/";

  public static String getImageIconUrl(String string) {
    return Helper.OPEN_WEATHER_ICON_URL + string + ".png";
  }

  public static String getImageUrl(String alphal) {
    return Helper.GEO_URL + alphal + ".png";
  }

  public static String getFlagIconUrl(String alphaCode) {
    return Helper.GEO_URL + alphaCode + ".png";
  }

  public static OkHttpClient GetClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    return httpClient.addInterceptor(logging).build();
  }
}
