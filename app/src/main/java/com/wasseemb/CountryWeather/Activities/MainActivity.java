package com.wasseemb.CountryWeather.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.wasseemb.CountryWeather.Adapter.CountryAdapter;
import com.wasseemb.CountryWeather.Api.CountryRestApi;
import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.Fragments.BlankFragment;
import com.wasseemb.CountryWeather.Fragments.DetailFragment;
import com.wasseemb.CountryWeather.Fragments.WeatherFragment;
import com.wasseemb.CountryWeather.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  ArrayList<CountryResponse> countryList = new ArrayList<>();
  private RecyclerView mRecyclerView;
  private CountryAdapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  DetailFragment detailFragment;
  WeatherFragment weatherFragment;
  BlankFragment blankFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setUpRecyclerView();
    setUpFragments();
    mAdapter.setDetailsCallback(detailFragment);
    mAdapter.setWeatherCallback(weatherFragment);
    //mAdapter.setBlankCallback(blankFragment);

    //Retrofit retrofit = builder.build();
    //ApiClient newClient = retrofit.create(ApiClient.class);
    populateNavigationDrawer();

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
  }

  private void setUpFragments() {
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    detailFragment = new DetailFragment();
    weatherFragment = new WeatherFragment();
    //blankFragment = new BlankFragment();

    fragmentManager.beginTransaction()
        .replace(R.id.topPanel, detailFragment, detailFragment.getTag())
        .commit();
    fragmentManager.beginTransaction()
        .replace(R.id.bottomPanel, weatherFragment, weatherFragment.getTag())
        .commit();
  }

  private void setUpRecyclerView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    mRecyclerView.setHasFixedSize(true);

    // use a linear layout manager
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mAdapter = new CountryAdapter(countryList, getApplicationContext());
    mRecyclerView.setAdapter(mAdapter);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void populateNavigationDrawer() {
    CountryRestApi.getInstance()
        .getApiService()
        .getCountries()
        .enqueue(new Callback<List<CountryResponse>>() {
          @Override public void onResponse(Call<List<CountryResponse>> call,
              Response<List<CountryResponse>> response) {

            mAdapter.updateData(response.body());

          }

          @Override public void onFailure(Call<List<CountryResponse>> call, Throwable t) {

          }
        });
  }
  
  // weatherCallback.countrySelected(countryList.get(position));
  // detailsCallback.countrySelected(countryList.get(position));
}
