package com.azurehorsecreations.signpost;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CitiesActivity extends AppCompatActivity {
    private static final String TAG = "CitiesActivity";
    private ArrayList<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView cityView = (RecyclerView) findViewById(R.id.city_list);
        cityView.addItemDecoration(new SimpleDividerItemDecoration(this));
        cityView.addOnItemTouchListener(
            new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getApplicationContext(), MapFragmentActivity.class);
                    City city = cityList.get(position);
                    intent.putExtra("latitude", city.getLatitude());
                    intent.putExtra("longitude", city.getLongitude());
                    intent.putExtra("name", city.getName());
                    startActivity(intent);
                }
            })
        );
        cityList = createCityList();
        CityAdapter cityAdapter = new CityAdapter(this, cityList);
        cityView.setAdapter(cityAdapter);
        cityView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private ArrayList<City> createCityList() {
        ArrayList<City> newCityList = new ArrayList<>();
        newCityList.add(new City(R.drawable.gb, "London", getCoordinates("London")[0], getCoordinates("London Englad")[1]));
        newCityList.add(new City(R.drawable.fr, "Paris", getCoordinates("Paris")[0], getCoordinates("Paris France")[1]));
        newCityList.add(new City(R.drawable.ca, "Vancouver", getCoordinates("Vancouver")[0], getCoordinates("Vancouver Canada")[1]));
        newCityList.add(new City(R.drawable.jp, "Kyoto", getCoordinates("Kyoto")[0], getCoordinates("Kyoto Japan")[1]));
        newCityList.add(new City(R.drawable.us, "San Francisco", getCoordinates("San Francisco")[0], getCoordinates("San Francisco")[1]));
        return newCityList;
    }

    private double[] getCoordinates(String name) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        double[] coordinates = new double[2];
        try {
            List addressList = geocoder.getFromLocationName(name, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = (Address) addressList.get(0);
                coordinates[0] = address.getLatitude();
                coordinates[1] = address.getLongitude();
                StringBuilder sb = new StringBuilder();
            }
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        }
        return coordinates;
    }
}
