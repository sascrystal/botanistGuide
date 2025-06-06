package com.example.botanistguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DescriptionActivity extends AppCompatActivity {
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_description);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = getIntent().getIntExtra("id",0);
        setDescription();

    }
    private  void setDescription(){
        TextView name = findViewById(R.id.textView_plant_name_desciption);
        name.setText(MainActivity.plantArrayList.get(id).getName());

        TextView description = findViewById(R.id.textView_plant_description_desciption);
        description.setText("Описание: " + MainActivity.plantArrayList.get(id).getDescription());

        TextView wateringDays = findViewById(R.id.textView_plant_wateringDays_desciption);
        wateringDays.setText("Дни между поливами: " + String.valueOf(MainActivity.plantArrayList.get(id).getWateringDays()));

        ImageView image = findViewById(R.id.plant_image_description);
        image.setImageResource(MainActivity.plantArrayList.get(id).getPictureId());

        if(MainActivity.plantArrayList.get(id).getPlantingDate() != null && !MainActivity.plantArrayList.get(id).getPlantingDate().isEmpty()){
            TextView plantingDays = findViewById(R.id.textView_plant_date_desciption);
            plantingDays.setText("Дата посадки: "+MainActivity.plantArrayList.get(id).getPlantingDate());
        }else{
            TextView plantingDays = findViewById(R.id.textView_plant_date_desciption);
            plantingDays.setText("Дата посадки: не назначена");
        }
    }


    public void onClickBackButton(View v){
        Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
        startActivity(intent);

    }
    public void onClickEditButton(View v){
        Intent intent = new Intent(DescriptionActivity.this, EditActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}