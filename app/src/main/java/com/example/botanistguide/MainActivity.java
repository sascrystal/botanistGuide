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
        plantArrayList.add(new Plant("Шиповник",6,"Предпочитает полное солнце (6–8 часов в день), но может расти в полутени. Лучше всего подходит рыхлая, дренированная, слабокислая или нейтральная почва (pH 5,5–7,0).\n" +
                "\n" +
                "Не переносит заболачивания – корни могут загнить. Перелив опаснее недолива! Проверяйте почву – если верхний слой (3–5 см) сухой, можно поливать.\n" +
                "\n" +
                "После полива мульчируйте (опилки, компост) для сохранения влаги. Весна (апрель): Азотные удобрения (мочевина, перегной) для роста зелени.\n" +
                "\n" +
                "Лето (июнь): Фосфорно-калийные (зола, суперфосфат) для цветения.\n" +
                "\n" +
                "Осень (сентябрь): Калий (калимагнезия) для подготовки к зиме.",R.drawable.images));
        plantArrayList.add(new Plant("Малина", 8,"Семейство: Розовые (Rosaceae)\n" +
                "\n" +
                "Тип: Листопадный полукустарник\n" +
                "\n" +
                "Высота: 1,5–2,5 м\n" +
                "\n" +
                "Побеги: Двухлетние (первый год – зелёные, второй – одревесневшие, плодоносящие)\n" +
                "\n" +
                "Листья: Овальные, зубчатые, зелёные сверху, белесые снизу\n" +
                "\n" +
                "Цветки: Белые, собраны в кисти\n" +
                "\n" +
                "Плоды: Сборные костянки (красные, жёлтые или чёрные в зависимости от сорта)\n" +
                "\n" +
                "Период плодоношения:\n" +
                "\n" +
                "Обычная малина – июнь-июль,\n"
                ,R.drawable.rasberry));
    }

    public static void addInPlantList(Plant plant){
        plantArrayList.add(plant);
    }
}