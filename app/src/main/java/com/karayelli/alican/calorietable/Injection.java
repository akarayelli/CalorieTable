/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.karayelli.alican.calorietable;

import android.content.Context;
import android.support.annotation.NonNull;

import com.karayelli.alican.calorietable.data.local.food.FoodDataSource;
import com.karayelli.alican.calorietable.data.local.foodType.FoodTypesDataSource;
import com.karayelli.alican.calorietable.data.local.CalorieTableDatabase;
import com.karayelli.alican.calorietable.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {


    public static FoodDataSource provideFoodDataSource(@NonNull Context context){
        checkNotNull(context);
        CalorieTableDatabase database = CalorieTableDatabase.getInstance(context);
        return FoodDataSource.getInstance(new AppExecutors(), database.foodDao());
    }

     public static FoodTypesDataSource provideFoodTypesDataSource(@NonNull Context context){
        checkNotNull(context);
        CalorieTableDatabase database = CalorieTableDatabase.getInstance(context);
        return FoodTypesDataSource.getInstance(new AppExecutors(), database.foodTypeDao());
    }


}
