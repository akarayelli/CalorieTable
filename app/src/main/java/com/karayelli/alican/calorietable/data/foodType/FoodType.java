/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karayelli.alican.calorietable.data.foodType;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.karayelli.alican.calorietable.data.food.Food;

import java.util.List;
import java.util.Objects;

/**
 * Immutable model class for a Food Type.
 */
@Entity(tableName = "food_type")
public final class FoodType {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "type_id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "label_tr")
    private String mLabelTR;

    @NonNull
    @ColumnInfo(name = "label_en")
    private String mLabelEN;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    @NonNull
    @ColumnInfo(name = "color")
    private String mColor;

    @NonNull
    @ColumnInfo(name = "icon")
    private int mIcon;

    @NonNull
    @ColumnInfo(name = "spotlight_image")
    private int mSpotlightImage;


    @Ignore
    private List<Food> foodList;

    public FoodType(@NonNull String labelTR, @NonNull String labelEN, @NonNull String description, @NonNull String id, @NonNull String color, @NonNull int icon, @NonNull int spotlightImage) {
        mId = id;
        mLabelTR = labelTR;
        mLabelEN = labelEN;
        mDescription = description;
        mColor = color;
        mIcon = icon;
        mSpotlightImage = spotlightImage;
    }


    @NonNull
    public String getId() {
        return mId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    public String getLabelTR() {
        return mLabelTR;
    }

    @NonNull
    public String getLabelEN() {
        return mLabelEN;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public String getColor() {
        return mColor;
    }

    @NonNull
    public int getIcon() {
        return mIcon;
    }

    @NonNull
    public int getSpotlightImage() {
        return mSpotlightImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodType foodType = (FoodType) o;
        return mIcon == foodType.mIcon &&
                mSpotlightImage == foodType.mSpotlightImage &&
                Objects.equals(mId, foodType.mId) &&
                Objects.equals(mLabelTR, foodType.mLabelTR) &&
                Objects.equals(mLabelEN, foodType.mLabelEN) &&
                Objects.equals(mDescription, foodType.mDescription) &&
                Objects.equals(mColor, foodType.mColor) &&
                Objects.equals(foodList, foodType.foodList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mLabelTR, mLabelEN, mDescription, mColor, mIcon, mSpotlightImage, foodList);
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "mId='" + mId + '\'' +
                ", mLabelTR='" + mLabelTR + '\'' +
                ", mLabelEN='" + mLabelEN + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mColor='" + mColor + '\'' +
                ", mIcon=" + mIcon +
                ", mSpotlightImage=" + mSpotlightImage +
                ", foodList=" + foodList +
                '}';
    }
}
