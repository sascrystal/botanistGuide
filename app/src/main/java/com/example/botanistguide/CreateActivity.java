package com.example.botanistguide;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClickCreate(View v){
        EditText nameEdit = findViewById(R.id.create_name_editText);
        String name = nameEdit.getText().toString();
        EditText dateEdit = findViewById(R.id.create_data_editText);
        String date = dateEdit.getText().toString();
        EditText wateringDaysEdit = findViewById(R.id.create_wateringDays_editText);
        int wateringDays = Integer.parseInt( wateringDaysEdit.getText().toString());
        EditText descriptionEdit = findViewById(R.id.create_description_editText);
        String description = descriptionEdit.getText().toString();
        Plant createPlant = new Plant(name,date,wateringDays,description, R.drawable.images);
        MainActivity.plantArrayList.add(createPlant);
        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCancel(View v){
        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
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
}