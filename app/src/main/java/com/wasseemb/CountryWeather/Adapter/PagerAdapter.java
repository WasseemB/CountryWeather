package com.wasseemb.CountryWeather.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.wasseemb.CountryWeather.Fragments.WeatherFragment;

/**
 * Created by Wasseem on 03/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
  private static int NUM_ITEMS = 2;

  public PagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  // Returns total number of pages
  @Override public int getCount() {
    return NUM_ITEMS;
  }

  // Returns the fragment to display for that page
  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0: // Fragment # 0 - This will show FirstFragment
        //return FirstFragment.newInstance(0, "Page # 1");
        return WeatherFragment.newInstance();
      case 1: // Fragment # 0 - This will show FirstFragment different title
        //return FirstFragment.newInstance(1, "Page # 2");
        return WeatherFragment.newInstance();

      default:
        return null;
    }
  }

  // Returns the page title for the top indicator
  @Override public CharSequence getPageTitle(int position) {
    return "Page " + position;
  }
}

