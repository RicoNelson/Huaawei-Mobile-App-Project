package com.class_project.stupa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.class_project.model.TripPlan;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DetailPlaceActivity extends AppCompatActivity {

    Button add_to_plan_btn;
    ArrayList<TripPlan> tripPlans;
    int called;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        HwAds.init(this);
        BannerView bannerView = findViewById(R.id.hw_banner_view);
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Set the refresh interval to 60 seconds.
        bannerView.setBannerRefresh(60);
        // Create an ad request to load an ad.
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);

        Intent intent = getIntent();

        String name = intent.getStringExtra("item_name");
        String location = intent.getStringExtra("item_location");
        int image = intent.getIntExtra("item_image", -1);
        called = intent.getIntExtra("calling", 0);

        tripPlans = new ArrayList<>();

        if(called == 1){
            tripPlans = (ArrayList<TripPlan>)intent.getSerializableExtra("planList");
        }

        ImageView place_image = (ImageView)findViewById(R.id.item_image);
        TextView item_name = (TextView)findViewById(R.id.item_name);
        TextView item_location = (TextView)findViewById(R.id.item_location);

        place_image.setImageResource(image);
        item_name.setText(name);
        item_location.setText(location);

        add_to_plan_btn = findViewById(R.id.add_to_plan_btn);

        add_to_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripPlans.add(new TripPlan(name, location, image));
                called = 1;
                Toast.makeText(DetailPlaceActivity.this, name + " added to plan list", Toast.LENGTH_SHORT).show();

                Intent intent_to_main = new Intent(DetailPlaceActivity.this, MainActivity.class);

                intent_to_main.putExtra("calling", called);
                intent_to_main.putExtra("planList", tripPlans);

                startActivity(intent_to_main);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_my_plan, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.myPlan:
                Intent intent = getIntent();
                called = intent.getIntExtra("calling", 0);
                if(called == 1) {
                    tripPlans = (ArrayList<TripPlan>) intent.getSerializableExtra("planList");
                    Intent moveIntent = new Intent(this, MyPlanActivity.class);
                    moveIntent.putExtra("calling", called);
                    moveIntent.putExtra("planList", tripPlans);

                    startActivity(moveIntent);
                } else {
                    Toast.makeText(this, "Your Trip Plan is Empty", Toast.LENGTH_SHORT).show();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}