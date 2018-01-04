package com.wasseemb.CountryWeather.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.wasseemb.CountryWeather.Adapter.CountryAdapter;
import com.wasseemb.CountryWeather.Api.WeatherRestApi;
import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.R;
import com.wasseemb.CountryWeather.Utils.Helper;
import com.wasseemb.CountryWeather.Data.WeatherData.List;
import com.wasseemb.CountryWeather.Data.WeatherData.WeatherData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements CountryAdapter.EventCallback {
  //TextView dateView;
  TextView weatherDateText;
  TextView weatherTempText;
  TextView weatherPressureText;
  TextView weatherHumidityText;
  ImageView weatherIconView;
  public WeatherFragment() {
    // Required empty public constructor
  }

  public static WeatherFragment newInstance() {
     Bundle args = new Bundle();
     WeatherFragment fragment = new WeatherFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_weather, container, false);
    weatherDateText = v.findViewById(R.id.weather_date);
    weatherTempText = v.findViewById(R.id.weather_temp);
    weatherPressureText = v.findViewById(R.id.weather_pressure);
    weatherHumidityText = v.findViewById(R.id.weather_humidity);
    weatherIconView = v.findViewById(R.id.weather_icon);
    return v;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private void updateView(CountryResponse countryResponse) {
    WeatherRestApi weatherRestApi = WeatherRestApi.getInstance();

    String lat = countryResponse.getLatlng().get(0) + "";
    String lon = countryResponse.getLatlng().get(1) + "";

    weatherRestApi.getApiService()
        .getWeather(lat, lon, Helper.APIKEY, "metric")
        .enqueue(new Callback<WeatherData>() {
          @Override public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

            List todaysWeather = response.body().getList().get(0);
            weatherDateText.setText(todaysWeather.getDtTxt());
            weatherTempText.setText(todaysWeather.getMain().getTemp().toString());
            weatherPressureText.setText(
                todaysWeather.getMain().getPressure().toString());
            weatherHumidityText.setText(
                todaysWeather.getMain().getHumidity().toString());
            Picasso.with(getContext())
                .load(
                    Helper.getImageIconUrl(todaysWeather.getWeather().get(0).getIcon()))
                .into(weatherIconView);
          }

          @Override public void onFailure(Call<WeatherData> call, Throwable t) {

          }
        });
  }


  @Override public void countrySelected(CountryResponse response) {
    updateView(response);
  }
}
