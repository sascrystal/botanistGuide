package com.example.botanistguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static   ArrayList<Plant> plantArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        plantArrayList.add(new Plant("шиповник",14,"15",R.drawable.images));
        plantArrayList.add(new Plant("Шишка", 11,"14",R.drawable.images));

        PlantRecyclerAdapter adapter = new PlantRecyclerAdapter(this, plantArrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((plant, position)->{
            Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
            intent.putExtra("plant",plant);
            startActivity(intent);

        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        Gson gson = new Gson();
        String json = gson.toJson(plantArrayList);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        prefs.edit().putString("plants_list", json).apply();
    }

    public void addInPlantList(Plant plant){
        plantArrayList.add(plant);
    }
}