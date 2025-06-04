package com.example.botanistguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantRecyclerAdapter extends  RecyclerView.Adapter<PlantRecyclerAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Plant> plantList;
    private OnItemClickListener clickListener;
    public interface OnItemClickListener {
        void onItemClick(Plant plant, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public PlantRecyclerAdapter(Context context, List<Plant> plantList) {
        this.inflater = LayoutInflater.from(context);
        this.plantList = plantList;
    }

    @NonNull
    @Override
    public PlantRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.plant_resycle_view_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public void onBindViewHolder(PlantRecyclerAdapter.ViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.nameText.setText(plant.getName());
        if(plant.getPlantingDate() != null && !plant.getPlantingDate().isEmpty()){
            holder.dateText.setText("Дата посадки: "+plant.getPlantingDate());
        }else {
            holder.dateText.setText("Дата посадки: не назначена");
        }

        holder.image.setImageResource(plant.getPictureId());

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView nameText;
        final TextView dateText;
        ViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.imageView);
            nameText = view.findViewById(R.id.nameText);
            dateText = view.findViewById(R.id.plantingDate);

            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onItemClick(plantList.get(position), position);
                }
            });

        }
    }


}
