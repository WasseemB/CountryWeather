package com.wasseemb.CountryWeather.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wasseem on 03/01/2018.
 */

public class CountryRestApi {

  private static final String BASE_URL = "https://restcountries.eu/rest/v1/";
  private static CountryRestApi instance = new CountryRestApi();
  private ApiClient apiService;

  public CountryRestApi() {
    Retrofit retrofit = new Retrofit.Builder()
        //.client(GetClient())
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    apiService = retrofit.create(ApiClient.class);
  }

  public static CountryRestApi getInstance() {
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