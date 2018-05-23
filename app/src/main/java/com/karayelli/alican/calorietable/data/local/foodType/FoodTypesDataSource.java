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

package com.karayelli.alican.calorietable.data.local.foodType;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.karayelli.alican.calorietable.data.local.food.Food;
import com.karayelli.alican.calorietable.util.AppExecutors;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class FoodTypesDataSource implements IFoodTypesDataSource {

    private static volatile FoodTypesDataSource INSTANCE;

    private FoodTypeDao mFoodTypeDao;
    private AppExecutors mAppExecutors;


    private FoodTypesDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull FoodTypeDao foodTypeDao) {
        mAppExecutors = appExecutors;
        mFoodTypeDao = foodTypeDao;
    }


    public static FoodTypesDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull FoodTypeDao foodTypeDao) {
        if (INSTANCE == null) {
            synchronized (FoodTypesDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FoodTypesDataSource(appExecutors, foodTypeDao);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getFoodTypes(@NonNull final LoadFoodTypesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<FoodType> foodTypes = mFoodTypeDao.getAllFoodTypes();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFoodTypesLoaded(foodTypes);
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }



    @Override
    public void getFoodType(@NonNull final String foodTypeId, @NonNull final GetFoodTypeCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final FoodType foodType = mFoodTypeDao.getFoodTypeById(foodTypeId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (foodType != null) {
                            callback.onFoodTypeLoaded(foodType);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }


    @Override
    public void saveFoodType(@NonNull final FoodType foodType) {
        checkNotNull(foodType);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mFoodTypeDao.insertFoodType(foodType);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }



    @Override
    public void deleteAllFoodTypes() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mFoodTypeDao.deleteAllFoodTypes();
            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    @Override
    public void deleteFoodType(@NonNull final String taskId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mFoodTypeDao.deleteFoodTypeById(taskId);
            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }
}
