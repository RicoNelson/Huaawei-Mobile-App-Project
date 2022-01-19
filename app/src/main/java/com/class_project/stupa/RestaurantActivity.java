package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.class_project.model.Restaurant;
import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView categoryList;
    ArrayList<Restaurant> categoryObject;
    ArrayList<String> place_name;
    ArrayList<String> location_name;
    ArrayList<Integer> image_name;

    AdapterForAll adapterForAll;
    AdapterForAll.RecyclerViewClickListener listener;
    private int called = 0;

    ArrayList<TripPlan> tripPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        categoryList = findViewById(R.id.list_item_from_category);
        categoryObject = new ArrayList<>();

        place_name = new ArrayList<>();
        place_name.add("Pingoo Restaurant");
        place_name.add("Plataran Dharmawangsa");
        place_name.add("The Pier by Kalaha");
        place_name.add("Le Bridge");
        place_name.add("JimBARan Lounge");
        place_name.add("La Vue at The Hermitage");
        place_name.add("Soupanova Ecosky");
        place_name.add("Hause Rooftop Kitchen & Bar");

        location_name = new ArrayList<>();
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Selatan");
        location_name.add("Jakarta Utara");
        location_name.add("Jakarta Utara");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Selatan");
        location_name.add("Jakarta Selatan");

        image_name = new ArrayList<>();
        image_name.add(R.drawable.resto_pingoo);
        image_name.add(R.drawable.resto_plataran_dharmawangsa);
        image_name.add(R.drawable.resto_the_pier);
        image_name.add(R.drawable.resto_le_bridge);
        image_name.add(R.drawable.resto_jimbaran);
        image_name.add(R.drawable.resto_la_vue);
        image_name.add(R.drawable.resto_soupanova);
        image_name.add(R.drawable.resto_hause_rooftop);

        Intent intent = getIntent();
        called = intent.getIntExtra("calling", 0);
        tripPlans = new ArrayList<>();

        if(called == 1){
            tripPlans = (ArrayList<TripPlan>)intent.getSerializableExtra("planList");
        }

        listener = new AdapterForAll.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                String name = place_name.get(position);
                String location = location_name.get(position);
                Integer img = image_name.get(position);

                Intent intent = new Intent(RestaurantActivity.this, DetailPlaceActivity.class);
                intent.putExtra("item_name", name);
                intent.putExtra("item_location", location);
                intent.putExtra("item_image", img);
                intent.putExtra("calling", called);
                intent.putExtra("planList", tripPlans);
                startActivity(intent);
            }
        };

        adapterForAll = new AdapterForAll(this, place_name, location_name, image_name, listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        categoryList.setLayoutManager(gridLayoutManager);
        categoryList.setAdapter(adapterForAll);
    }
}