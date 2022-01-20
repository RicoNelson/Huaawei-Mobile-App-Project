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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.class_project.model.TripPlan;
import com.huawei.hms.api.bean.HwAudioPlayItem;
import com.huawei.hms.audiokit.player.callback.HwAudioConfigCallBack;
import com.huawei.hms.audiokit.player.manager.HwAudioManager;
import com.huawei.hms.audiokit.player.manager.HwAudioManagerFactory;
import com.huawei.hms.audiokit.player.manager.HwAudioPlayerConfig;
import com.huawei.hms.audiokit.player.manager.HwAudioPlayerManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView img_mall;
    ImageView img_beach;
    ImageView img_resto;
    ImageView img_museum;

    RecyclerView recommended_list;
    ArrayList<String> place_name;
    ArrayList<String> location_name;
    ArrayList<Integer> image_name;

    ArrayList<TripPlan> tripPlans = new ArrayList<>();
    AdapterForAll mallAdapter;
    AdapterForAll.RecyclerViewClickListener listener;
    private int called = 0;

    private HwAudioPlayerManager mHwAudioPlayerManager;
    List<HwAudioPlayItem> playItemList = new ArrayList<>();


    public void createHwAudioManager() {
        // Create an HwAudioPlayerConfig instance that includes various playback-related configurations. The parameter context cannot be left empty.
        HwAudioPlayerConfig hwAudioPlayerConfig = new HwAudioPlayerConfig(this);
        // Add configurations required for creating an HwAudioManager object.
//        hwAudioPlayerConfig.setDebugMode(true).setDebugPath("").setPlayCacheSize(20);
        // Create a management instance.
        HwAudioManagerFactory.createHwAudioManager(hwAudioPlayerConfig, new HwAudioConfigCallBack() {
            // Return the management instance through callback.
            @Override
            public void onSuccess(HwAudioManager hwAudioManager) {
                try {

                    // Obtain the playback management instance.
                    mHwAudioPlayerManager = hwAudioManager.getPlayerManager();
//                    // Obtain the configuration management instance.
//                    mHwAudioConfigManager = hwAudioManager.getConfigManager();
                    mHwAudioPlayerManager.playList(getLocalPlayItemList(), 0, 0);

                } catch (Exception e) {

                }
            }

            @Override
            public void onError(int errorCode) {
//                Log.w(TAG, "init err:" + errorCode);
                Toast.makeText( MainActivity.this,"init error: " + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<HwAudioPlayItem> getLocalPlayItemList() {
        // Set the local audio path.

        String path = "hms_res://lagu_untuk_huawei";

        // Create an audio object and write audio information into the object.
        HwAudioPlayItem item = new HwAudioPlayItem();
        // Set the audio title.
        item.setAudioTitle("Playing input song");
        // Set the audio ID, which is unique for each audio file. You are advised to set the ID to a hash value.
        item.setAudioId(String.valueOf(path.hashCode()));
        // Set whether an audio file is online (1) or local (0).
        item.setOnline(0);
        // Pass the local audio path.
        item.setFilePath(path);
        playItemList.add(item);
        return playItemList;
    }

    public void playLocalList() {
        if (mHwAudioPlayerManager != null) {
            // Play songs on a local playlist.
            mHwAudioPlayerManager.play();
            Toast.makeText( MainActivity.this,"play music", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause() {
        Toast.makeText( MainActivity.this,"paused at" , Toast.LENGTH_SHORT).show();
        if (mHwAudioPlayerManager == null) {
            Toast.makeText( MainActivity.this,"pause err", Toast.LENGTH_SHORT).show();
            return;
        }
        mHwAudioPlayerManager.pause();
    }

    public void stop() {
        if (mHwAudioPlayerManager == null) {
            return;
        }
        // Stop the playback and close the notification bar.
        mHwAudioPlayerManager.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createHwAudioManager();

        RelativeLayout mall_btn = (RelativeLayout) findViewById(R.id.category_btn_mall);
        RelativeLayout beach_btn = (RelativeLayout) findViewById(R.id.category_btn_beach);
        RelativeLayout resto_btn = (RelativeLayout) findViewById(R.id.category_btn_restaurant);
        RelativeLayout museum_btn = (RelativeLayout) findViewById(R.id.category_btn_museum);

        img_mall = (ImageView) findViewById(R.id.image_mall);
        img_mall.setImageResource(R.drawable.mall);

        img_beach = (ImageView) findViewById(R.id.image_beach);
        img_beach.setImageResource(R.drawable.beach);

        img_resto = (ImageView) findViewById(R.id.image_restaurant);
        img_resto.setImageResource(R.drawable.resto);

        img_museum = (ImageView) findViewById(R.id.image_museum);
        img_museum.setImageResource(R.drawable.museum);

        Intent getData = getIntent();
        called = getData.getIntExtra("calling", 0);

        if(called == 1){
            tripPlans = (ArrayList<TripPlan>) getData.getSerializableExtra("planList");
        }

        mall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, MallActivity.class);
                intent1.putExtra("planList", tripPlans);
                intent1.putExtra("calling", called);
                startActivity(intent1);
            }
        });

        beach_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, BeachActivity.class);
                intent1.putExtra("planList", tripPlans);
                intent1.putExtra("calling", called);
                startActivity(intent1);
            }
        });

        resto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RestaurantActivity.class);
                intent1.putExtra("planList", tripPlans);
                intent1.putExtra("calling", called);
                startActivity(intent1);
            }
        });

        museum_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, MuseumActivity.class);
                intent1.putExtra("planList", tripPlans);
                intent1.putExtra("calling", called);
                startActivity(intent1);
            }
        });

        recommended_list = findViewById(R.id.list_recommended_places);

        place_name = new ArrayList<>();
        place_name.add("Central Park");
        place_name.add("Pantai Ancol");
        place_name.add("Grand Indonesia");
        place_name.add("Pingoo Restaurant");
        place_name.add("La Vue at The Hermitage");
        place_name.add("Plataran Dharmawangsa");
        place_name.add("Museum Prangko Indonesia");
        place_name.add("Museum Minyak dan Gas Bumi");

        location_name = new ArrayList<>();
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Utara");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Barat");
        location_name.add("Jakarta Pusat");
        location_name.add("Jakarta Selatan");
        location_name.add("Jakarta Timur");
        location_name.add("Jakarta Timur");

        image_name = new ArrayList<>();
        image_name.add(R.drawable.mall_central_park);
        image_name.add(R.drawable.pantai_ancol);
        image_name.add(R.drawable.mall_grand_indonesia);
        image_name.add(R.drawable.resto_pingoo);
        image_name.add(R.drawable.resto_la_vue);
        image_name.add(R.drawable.resto_plataran_dharmawangsa);
        image_name.add(R.drawable.museum_prangko);
        image_name.add(R.drawable.museum_minyak_dan_gas_bumi);

        listener = new AdapterForAll.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                String name = place_name.get(position);
                String location = location_name.get(position);
                Integer img = image_name.get(position);

                Intent intent = new Intent(MainActivity.this, DetailPlaceActivity.class);
                intent.putExtra("item_name", name);
                intent.putExtra("item_location", location);
                intent.putExtra("item_image", img);

                intent.putExtra("calling", called);
                intent.putExtra("planList", tripPlans);
                startActivity(intent);
            }
        };

        mallAdapter = new AdapterForAll(this, place_name, location_name, image_name, listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recommended_list.setLayoutManager(gridLayoutManager);
        recommended_list.setAdapter(mallAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
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
            case R.id.playBtn:
                playLocalList();
                return true;
            case R.id.pauseBtn:
                pause();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}