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

package com.karayelli.alican.calorietable.data.food;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.karayelli.alican.calorietable.data.foodType.FoodType;

import java.util.Objects;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "food", foreignKeys = @ForeignKey(entity = FoodType.class, parentColumns = "type_id", childColumns = "type_id", onDelete = CASCADE))
public final class Food {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food_id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "type_id")
    private String mTypeId;


    @NonNull
    @ColumnInfo(name = "label_tr")
    private String mLabelTR;

    @NonNull
    @ColumnInfo(name = "label_en")
    private String mLabelEN;

    @NonNull
    @ColumnInfo(name = "calorie")
    private String mCalorie;


    @NonNull
    @ColumnInfo(name = "is_favorite")
    private Boolean mFavorite;


    public Food(@NonNull String labelTR, @NonNull String labelEN, @NonNull String calorie, @NonNull String id, @NonNull Boolean favorite, @NonNull String typeId) {
        mId = id;
        mLabelTR = labelTR;
        mLabelEN = labelEN;
        mCalorie = calorie;
        mFavorite = favorite;
        mTypeId = typeId;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getTypeId() {
        return mTypeId;
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
    public String getCalorie() {
        return mCalorie;
    }

    @NonNull
    public Boolean getFavorite() {
        return mFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(mId, food.mId) &&
                Objects.equals(mTypeId, food.mTypeId) &&
                Objects.equals(mLabelTR, food.mLabelTR) &&
                Objects.equals(mLabelEN, food.mLabelEN) &&
                Objects.equals(mCalorie, food.mCalorie) &&
                Objects.equals(mFavorite, food.mFavorite);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mTypeId, mLabelTR, mLabelEN, mCalorie, mFavorite);
    }

    @Override
    public String toString() {
        return "Food{" +
                "mId='" + mId + '\'' +
                ", mTypeId='" + mTypeId + '\'' +
                ", mLabelTR='" + mLabelTR + '\'' +
                ", mLabelEN='" + mLabelEN + '\'' +
                ", mCalorie='" + mCalorie + '\'' +
                ", mFavorite=" + mFavorite +
                '}';
    }
}

