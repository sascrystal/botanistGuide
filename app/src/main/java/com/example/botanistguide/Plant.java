package com.example.botanistguide;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {
    private String name;
    private String plantingDate;
    private int wateringDays;
    private String description;
    private Integer pictureId;

    public void setName(String name) {
        this.name = name;
    }

    public void setWateringDays(int wateringDays) {
        this.wateringDays = wateringDays;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Plant(String name, String plantingDate, int wateringDays, String description, int pictureId) {
        this.name = name;
        this.plantingDate = plantingDate;
        this.wateringDays = wateringDays;
        this.description = description;
        this.pictureId = pictureId;
    }
    public Plant(String name, int wateringDays, String description, int pictureId) {
        this.name = name;
        this.wateringDays = wateringDays;
        this.description = description;
        this.pictureId = pictureId;
    }

    public void setPlantingDate(String plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getName() {
        return name;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    public int getWateringDays() {
        return wateringDays;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    protected Plant(Parcel in) {
        name = in.readString();
        plantingDate = in.readString();
        description = in.readString();
        wateringDays = in.readInt();
        pictureId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(plantingDate);
        dest.writeString(description);
        dest.writeInt(wateringDays);
        dest.writeInt(pictureId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };
}
