package com.example.botanistguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {
    Plant plant;

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
        plant = getIntent().getParcelableExtra("plant");
        setPreviousData();
    }
    public void onClickEdit(View v){
        EditText name = findViewById(R.id.nameEdit);
        plant.setName(name.getText().toString());

        EditText wateringDays = findViewById(R.id.wateringDaysEdit);
        try {
            plant.setWateringDays(Integer.parseInt(wateringDays.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Количество дней должны быть числом", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText plantingData = findViewById(R.id.editTextDate);
        plant.setPlantingDate(plantingData.getText().toString());

        EditText description = findViewById(R.id.editDescription);
        plant.setDescription(description.getText().toString());

        Intent intent = new Intent(EditActivity.this, DescriptionActivity.class);
        intent.putExtra("plant", plant);
        startActivity(intent);


    }

    private void setPreviousData(){
        if(plant.getName() != null){
            EditText name = findViewById(R.id.nameEdit);
            name.setText(plant.getName());
        }
        EditText wateringDays = findViewById(R.id.wateringDaysEdit);
        wateringDays.setText(String.valueOf(plant.getWateringDays()));

        EditText description = findViewById(R.id.editDescription);


    }

    }