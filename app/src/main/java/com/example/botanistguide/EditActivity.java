package com.example.botanistguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class EditActivity extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        id = getIntent().getIntExtra("id",0); // ебать я даун
        setPreviousData();
    }
    public void onClickEdit(View v){
        EditText name = findViewById(R.id.nameEdit);
        MainActivity.plantArrayList.get(id).setName(name.getText().toString());

        EditText wateringDays = findViewById(R.id.wateringDaysEdit);
        try {
            MainActivity.plantArrayList.get(id).setWateringDays(Integer.parseInt(wateringDays.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Количество дней должны быть числом", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText plantingData = findViewById(R.id.editTextDate);
        MainActivity.plantArrayList.get(id).setPlantingDate(plantingData.getText().toString());

        EditText description = findViewById(R.id.editDescription);
        MainActivity.plantArrayList.get(id).setDescription(description.getText().toString());

        Intent intent = new Intent(EditActivity.this, DescriptionActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);


    }
    @Override
    protected void onStop() {
        super.onStop();

        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.plantArrayList);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        prefs.edit().putString("plants_list", json).apply();
    }

    private void setPreviousData(){
        if(MainActivity.plantArrayList.get(id).getName() != null){
            EditText name = findViewById(R.id.nameEdit);
            name.setText(MainActivity.plantArrayList.get(id).getName());
        }
        EditText wateringDays = findViewById(R.id.wateringDaysEdit);
        wateringDays.setText(String.valueOf(MainActivity.plantArrayList.get(id).getWateringDays()));

        if(MainActivity.plantArrayList.get(id).getDescription() != null){
            EditText description = findViewById(R.id.editDescription);
            description.setText(MainActivity.plantArrayList.get(id).getDescription());
        }
        if(MainActivity.plantArrayList.get(id).getPictureId() != null){
            ImageView imageView = findViewById(R.id.imageEdit);
            imageView.setImageResource(MainActivity.plantArrayList.get(id).getPictureId());
        }

    }

    }