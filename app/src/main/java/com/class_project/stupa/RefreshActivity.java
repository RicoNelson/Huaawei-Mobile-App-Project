package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.class_project.model.TripPlan;

import java.util.ArrayList;

public class RefreshActivity extends AppCompatActivity {

    ArrayList<TripPlan> planList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

//        intent.putExtra("item_name", name);
//        intent.putExtra("item_location", location);
//        intent.putExtra("item_image", img);

//        intent.putExtra("calling", called);
//        intent.putExtra("planList", tripPlans);

        planList = new ArrayList<>();
        Intent intent = getIntent();

        planList = (ArrayList<TripPlan>) intent.getSerializableExtra("planList");

        int position = intent.getIntExtra("position", -1);
        planList.remove(position);

        Intent intent2 = new Intent(RefreshActivity.this, MyPlanActivity.class);
        intent2.putExtra("planList", planList);
        startActivity(intent2);
    }




}