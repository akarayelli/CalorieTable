/*
 * Copyright 2017, The Android Open Source Project
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

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food WHERE is_favorite = 1")
    List<Food> getFavoriteFoods();

    @Query("SELECT * FROM food WHERE type_id = :typeId")
    List<Food> getFoodsByTypeId(String typeId);

    @Query("SELECT * FROM food WHERE food_id = :id")
    Food getFoodById(String id);

    @Query("UPDATE food SET is_favorite = :isFavorite WHERE food_id = :id")
    void updateFavoriteStatus( String id, Boolean isFavorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFood(Food food);
}
