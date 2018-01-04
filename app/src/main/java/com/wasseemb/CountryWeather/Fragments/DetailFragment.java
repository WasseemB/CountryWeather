package com.wasseemb.CountryWeather.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.wasseemb.CountryWeather.Adapter.CountryAdapter;
import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.R;
import com.wasseemb.CountryWeather.Utils.Helper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements CountryAdapter.EventCallback {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  // TODO: Rename and change types of parameters
  private String mParam1;
  TextView flagNameText;
  TextView flagRegionText;
  TextView flagCapitalText;
  TextView flagPopulationText;
  ImageView flagImageView;

  public DetailFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @return A new instance of fragment DetailFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static DetailFragment newInstance(String param1) {
    DetailFragment fragment = new DetailFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_detail, container, false);
    flagNameText = v.findViewById(R.id.flag_name);
    flagRegionText = v.findViewById(R.id.flag_region);
    flagPopulationText = v.findViewById(R.id.flag_population);
    flagCapitalText = v.findViewById(R.id.flag_capital);
    flagImageView = v.findViewById(R.id.flag_image);

    return v;
  }

  public void updateDetails(CountryResponse countryResponse) {
    flagNameText.setText(countryResponse.getName());
    flagRegionText.setText(countryResponse.getRegion());
    flagPopulationText.setText(countryResponse.getPopulation().toString());
    flagCapitalText.setText(countryResponse.getCapital());
    Picasso.with(getContext()).load(Helper.getImageUrl(countryResponse.getAlpha2Code())).into(flagImageView);
  }

  @Override public void countrySelected(CountryResponse response) {
    updateDetails(response);
  }
}
