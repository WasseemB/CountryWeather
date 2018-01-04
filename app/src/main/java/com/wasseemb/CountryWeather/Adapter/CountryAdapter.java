package com.wasseemb.CountryWeather.Adapter;

/**
 * Created by Wasseem on 03/01/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.wasseemb.CountryWeather.Data.CountryData.CountryResponse;
import com.wasseemb.CountryWeather.R;
import com.wasseemb.CountryWeather.Utils.Helper;
import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Created by Wasseem on 15/03/2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
  private List<CountryResponse> countryList;
  private Context context;
  RecyclerView mRecyclerView;
  private EventCallback weatherCallback;
  private EventCallback detailsCallback;
  private EventCallback blankCallback;
  private Picasso picasso;



  public void setWeatherCallback(EventCallback weatherCallback) {
    this.weatherCallback = weatherCallback;
  }

  public void setDetailsCallback(EventCallback detailsCallback) {
    this.detailsCallback = detailsCallback;
  }

  public void setBlankCallback(EventCallback blankCallback) {
    this.blankCallback = blankCallback;
  }

  // Provide a reference to the views for each data item
  // Complex data items may need more than one view per item, and
  // you provide access to all the views for a data item in a view holder
  public class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case

    private TextView mTextView;
    private ImageView mImageView;

    public ViewHolder(View v) {
      super(v);
      mTextView = v.findViewById(R.id.info_text);
      mImageView = v.findViewById(R.id.info_image);
      picasso = Picasso.with(context);
    }

    public void bindTo(CountryResponse countryResponse) {
      mTextView.setText(countryResponse.getName());
      picasso.load(Helper.getFlagIconUrl(countryResponse.getAlpha2Code())).fit().centerCrop().into(mImageView);
    }
  }

  // Provide a suitable constructor (depends on the kind of dataset)
  public CountryAdapter(List<CountryResponse> countryList, Context context) {
    this.context = context;
    this.countryList = countryList;
  }

  // Create new views (invoked by the layout manager)
  @Override public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view

    final ViewHolder vh = new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));

    vh.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        int position = vh.getAdapterPosition();
        if (position != NO_POSITION) {
          weatherCallback.countrySelected(countryList.get(position));
          detailsCallback.countrySelected(countryList.get(position));
          // Log.d("Position",pos +"");
        }
      }
    });

    return vh;
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    mRecyclerView = recyclerView;
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.bindTo(countryList.get(position));
  }

  public void updateData(List<CountryResponse> countryResponse) {
    countryList.clear();
    countryList.addAll(countryResponse);
    notifyDataSetChanged();
  }

  // Return the size of your dataset (invoked by the layout manager)
  @Override public int getItemCount() {
    return countryList.size();
  }

  public interface EventCallback {
    void countrySelected(CountryResponse response);
  }



}


