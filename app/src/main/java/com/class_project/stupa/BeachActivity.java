package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.class_project.model.Beach;
import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class BeachActivity extends AppCompatActivity {

    RecyclerView beachList;
    ArrayList<Beach> beachObject;
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
        setContentView(R.layout.activity_beach);

        beachList = findViewById(R.id.list_item_from_category);
        beachObject = new ArrayList<>();

        place_name = new ArrayList<>();
        place_name.add("Pantai Ancol");
        place_name.add("Pantai Carita");
        place_name.add("Pantai Anyer");
        place_name.add("Pantai Sawarna");
        place_name.add("Pantai Pari");
        place_name.add("Pantai Bidadari");
        place_name.add("Pantai Tidung");

        location_name = new ArrayList<>();
        location_name.add("Jakarta Utara");
        location_name.add("Banten");
        location_name.add("Banten");
        location_name.add("Banten");
        location_name.add("Kepulauan Seribu");
        location_name.add("Kepulauan Seribu");
        location_name.add("Kepulauan Seribu");

        image_name = new ArrayList<>();
        image_name.add(R.drawable.pantai_ancol);
        image_name.add(R.drawable.pantai_carita);
        image_name.add(R.drawable.pantai_anyer);
        image_name.add(R.drawable.pantai_sawarna);
        image_name.add(R.drawable.pantai_pari);
        image_name.add(R.drawable.pantai_bidadari);
        image_name.add(R.drawable.pantai_tidung);

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

                Intent intent = new Intent(BeachActivity.this, DetailPlaceActivity.class);
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
        beachList.setLayoutManager(gridLayoutManager);
        beachList.setAdapter(adapterForAll);
    }

}