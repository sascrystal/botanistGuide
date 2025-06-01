package com.example.botanistguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        if(plantArrayList.isEmpty()){
            addSavedPlants();
        }

        if(plantArrayList.isEmpty()){
            setData();
        }
        recyclerViewSet();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Gson gson = new Gson();
        String json = gson.toJson(plantArrayList);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        prefs.edit().putString("plants_list", json).apply();
    }

    private void  recyclerViewSet(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantRecyclerAdapter adapter = new PlantRecyclerAdapter(this, plantArrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((plant, position)->{
            Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
            intent.putExtra("id",position);
            startActivity(intent);
        });
    }
    public void createButtonOnClick(View v){
        Intent intent = new Intent(MainActivity.this,CreateActivity.class);
        startActivity(intent);
    }

    private void addSavedPlants(){
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("plants_list", "");
        Type type = new TypeToken<List<Plant>>() {}.getType();
        List<Plant> savedPlants = gson.fromJson(json, type);
        if(savedPlants != null&&!savedPlants.isEmpty()){
            plantArrayList.addAll(savedPlants);
        }
    }
    private void setData(){
        plantArrayList.add(new Plant("шиповник",14,"15",R.drawable.images));
        plantArrayList.add(new Plant("Шишка", 11,"14",R.drawable.images));
    }

    public static void addInPlantList(Plant plant){
        plantArrayList.add(plant);
    }
}