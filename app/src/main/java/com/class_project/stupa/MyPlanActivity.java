package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class MyPlanActivity extends AppCompatActivity {
    RecyclerView planList;

    ArrayList<String> place_name = new ArrayList<>();
    ArrayList<String> location_name = new ArrayList<>();
    ArrayList<Integer> image_name = new ArrayList<>();
    ArrayList<TripPlan> tripPlans;

    MyPlanAdapter myPlanAdapter;
    MyPlanAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);

        planList = findViewById(R.id.list_item_from_category);

        Intent intent = getIntent();
        tripPlans = new ArrayList<>();

        tripPlans = (ArrayList<TripPlan>)intent.getSerializableExtra("planList");

        for(int i = 0; i<tripPlans.size(); i++){
            place_name.add(tripPlans.get(i).getName());
            image_name.add(tripPlans.get(i).getImg());
            location_name.add(tripPlans.get(i).getLoc());
        }

        if (tripPlans.size() == 0){
            Toast.makeText(this, "Your list is empty", Toast.LENGTH_SHORT).show();
        }

        listener = new MyPlanAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                String name = tripPlans.get(position).getName();
                String location = tripPlans.get(position).getLoc();
                Integer img = tripPlans.get(position).getImg();

                Intent intent = new Intent(MyPlanActivity.this, RefreshActivity.class);
                intent.putExtra("item_name", name);
                intent.putExtra("item_location", location);
                intent.putExtra("item_image", img);

                intent.putExtra("planList", tripPlans);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };

        myPlanAdapter = new MyPlanAdapter(this, place_name, location_name, image_name, listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        planList.setLayoutManager(gridLayoutManager);
        planList.setAdapter(myPlanAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_go_back_to_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_home:

                Intent moveIntent = new Intent(MyPlanActivity.this, MainActivity.class);

                if (tripPlans.size() == 0) {
                    moveIntent.putExtra("calling", 0);
                } else {
                    moveIntent.putExtra("planList", tripPlans);
                    moveIntent.putExtra("calling", 1);
                }

                startActivity(moveIntent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}