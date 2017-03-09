package com.azurehorsecreations.signpost;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pattycase on 8/27/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCityList;
    private Context mContext;
    private View cityView;

    public CityAdapter(Context context, List<City> cityList) {
        mCityList = cityList;
        mContext = context;
}

    private Context getContext() {
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView flagImageView;
        public TextView cityInfoTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            flagImageView = (ImageView)itemView.findViewById(R.id.country_flag_icon);
            cityInfoTextView = (TextView)itemView.findViewById(R.id.city_info);
        }
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        cityView = inflater.inflate(R.layout.item_city_info, parent, false);
        ViewHolder viewHolder = new ViewHolder(cityView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder viewHolder, int position) {
        City city = mCityList.get(position);
        ImageView countryFlagImageView = viewHolder.flagImageView;
        TextView cityInfoTextView = viewHolder.cityInfoTextView;
        Picasso.with(mContext).load(city.getFlagResourceId()).into(countryFlagImageView);
        cityInfoTextView.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }
}
