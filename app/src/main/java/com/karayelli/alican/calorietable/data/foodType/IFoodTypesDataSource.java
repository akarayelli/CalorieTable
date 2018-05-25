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

import android.support.annotation.NonNull;

import java.util.List;



public interface IFoodTypesDataSource {

    interface LoadFoodTypesCallback {

        void onFoodTypesLoaded(List<FoodType> foodTypes);

        void onDataNotAvailable();
    }

    interface GetFoodTypeCallback {

        void onFoodTypeLoaded(FoodType foodType);

        void onDataNotAvailable();
    }


    void getFoodTypes(@NonNull LoadFoodTypesCallback callback);

    void getFoodType(@NonNull String taskId, @NonNull GetFoodTypeCallback callback);

    void saveFoodType(@NonNull FoodType foodType);

    void deleteAllFoodTypes();

    void deleteFoodType(@NonNull String foodTypeId);
}
