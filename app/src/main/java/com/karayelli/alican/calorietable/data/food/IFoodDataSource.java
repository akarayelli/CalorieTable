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

import android.support.annotation.NonNull;

import java.util.List;


public interface IFoodDataSource {


    interface LoadFoodsCallback {

        void onFoodsLoaded(List<Food> foods);

        void onDataNotAvailable();
    }

    interface GetFoodCallback {

        void onFoodLoaded(Food food);

        void onDataNotAvailable();
    }

    void getFavoriteFoods(@NonNull LoadFoodsCallback callback);

    void getFoodsByTypeId(String typeId, @NonNull LoadFoodsCallback callback);

    void getFoodById(String foodId, @NonNull GetFoodCallback callback);

    void changeFavoriteStatus(String foodId, Boolean favorite);

    void saveFood(@NonNull final Food food);

 }
