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

package com.karayelli.alican.calorietable.data.foodType;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FoodTypeDao {


    @Query("SELECT * FROM food_type")
    List<FoodType> getAllFoodTypes();


    @Query("SELECT * FROM food_type WHERE type_id = :typeId")
    FoodType getFoodTypeById(String typeId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFoodType(FoodType foodType);


    @Update
    int updateFoodType(FoodType foodType);


    @Query("DELETE FROM food_type WHERE type_id = :typeId")
    int deleteFoodTypeById(String typeId);


    @Query("DELETE FROM food_type")
    void deleteAllFoodTypes();

}
