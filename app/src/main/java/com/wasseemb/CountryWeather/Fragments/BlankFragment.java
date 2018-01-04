package com.wasseemb.CountryWeather.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wasseemb.CountryWeather.Adapter.CountryAdapter;
import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.Adapter.PagerAdapter;
import com.wasseemb.CountryWeather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements CountryAdapter.EventCallback {
  FragmentStatePagerAdapter adapterViewPager;
  ViewPager vpPager;

  public BlankFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_blank, container, false);
    vpPager= (ViewPager) v.findViewById(R.id.vpPager);
    adapterViewPager = new PagerAdapter(getFragmentManager());
    vpPager.setAdapter(adapterViewPager);
    return v;
  }

  @Override public void countrySelected(CountryResponse response) {
    WeatherFragment page =(WeatherFragment)adapterViewPager.getItem(vpPager.getCurrentItem());
    //page.weatherDateText.setText("Weather");

    //if (page != null) page.countrySelected(response);
    // WeatherFragment fragment = (WeatherFragment) adapterViewPager.get

  }
}
