package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.class_project.model.Mall;
import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class MallActivity extends AppCompatActivity {

    RecyclerView mallList;
    ArrayList<Mall> mallObject;
    ArrayList<String> place_name;
    ArrayList<String> location_name;
    ArrayList<Integer> image_name;
    ArrayList<TripPlan> tripPlans;

    AdapterForAll mallAdapter;
    AdapterForAll.RecyclerViewClickListener listener;
    private int called = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        mallList = findViewById(R.id.list_item_from_category);
        mallObject = new ArrayList<>();

        place_name = new ArrayList<>();
        place_name.add("Central Park");
        place_name.add("Grand Indonesia");
        place_name.add("Mall Taman Anggrek");
        place_name.add("Gajah Mada Plaza");
        place_name.add("Ciputra Mall");
        place_name.add("Plaza Indonesia");
        place_name.add("Plaza Senayan");
        place_name.add("Senayan City");
        place_name.add("Gandaria City Mall");
        place_name.add("Kota Kasablanka");

        location_name = new ArrayList<>();
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Selatan");
        location_name.add("Jakarta Selatan");

        image_name = new ArrayList<>();
        image_name.add(R.drawable.mall_central_park);
        image_name.add(R.drawable.mall_grand_indonesia);
        image_name.add(R.drawable.mall_taman_anggrek);
        image_name.add(R.drawable.mall_gajah_mada);
        image_name.add(R.drawable.mall_ciputra);
        image_name.add(R.drawable.mall_plaza_indonesia);
        image_name.add(R.drawable.mall_plaza_senayan);
        image_name.add(R.drawable.mall_senayan_city);
        image_name.add(R.drawable.mall_gandaria_city);
        image_name.add(R.drawable.mall_kota_kasablanka);

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

                Intent intent = new Intent(MallActivity.this, DetailPlaceActivity.class);
                intent.putExtra("item_name", name);
                intent.putExtra("item_location", location);
                intent.putExtra("item_image", img);

                intent.putExtra("calling", called);
                intent.putExtra("planList", tripPlans);
                startActivity(intent);
            }
        };

        mallAdapter = new AdapterForAll(this, place_name, location_name, image_name, listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mallList.setLayoutManager(gridLayoutManager);
        mallList.setAdapter(mallAdapter);
    }
}