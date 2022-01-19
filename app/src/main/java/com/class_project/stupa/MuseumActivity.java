package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.class_project.model.Museum;
import com.class_project.model.Restaurant;
import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class MuseumActivity extends AppCompatActivity {

    RecyclerView categoryList;
    ArrayList<Museum> categoryObject;
    ArrayList<String> place_name;
    ArrayList<String> location_name;
    ArrayList<Integer> image_name;
    ArrayList<TripPlan> tripPlans;

    AdapterForAll adapterForAll;
    AdapterForAll.RecyclerViewClickListener listener;
    private int called = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);

        categoryList = findViewById(R.id.list_item_from_category);
        categoryObject = new ArrayList<>();

        place_name = new ArrayList<>();
        place_name.add("Museum Minyak dan Gas Bumi");
        place_name.add("Museum Prangko Indonesia");
        place_name.add("Museum Nasional Indonesia");
        place_name.add("Ciputra Artpreneur Museum");
        place_name.add("Museum MACAN");
        place_name.add("Museum Wayang Jakarta");
        place_name.add("Museum Sejarah Jakarta");
        place_name.add("Museum Bank Mandiri");

        location_name = new ArrayList<>();
        location_name.add("Jakarta Timur");
        location_name.add("Jakarta Timur");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Selatan");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Barat");

        image_name = new ArrayList<>();
        image_name.add(R.drawable.museum_minyak_dan_gas_bumi);
        image_name.add(R.drawable.museum_prangko);
        image_name.add(R.drawable.museum_nasional);
        image_name.add(R.drawable.museum_ciputra_artpreneur);
        image_name.add(R.drawable.museum_macan);
        image_name.add(R.drawable.museum_wayang_jakarta);
        image_name.add(R.drawable.museum_sejarah_jakarta);
        image_name.add(R.drawable.museum_bank_mandiri);

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

                Intent intent = new Intent(MuseumActivity.this, DetailPlaceActivity.class);
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