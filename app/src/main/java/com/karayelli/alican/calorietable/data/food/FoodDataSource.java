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
import android.support.annotation.VisibleForTesting;

import com.karayelli.alican.calorietable.util.AppExecutors;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class FoodDataSource implements IFoodDataSource {

    private static volatile FoodDataSource INSTANCE;

    private FoodDao mFoodDao;
    private AppExecutors mAppExecutors;


    private FoodDataSource(@NonNull AppExecutors appExecutors,
                           @NonNull FoodDao foodDao) {
        mAppExecutors = appExecutors;
        mFoodDao = foodDao;
    }


    public static FoodDataSource getInstance(@NonNull AppExecutors appExecutors,
                                             @NonNull FoodDao foodDao) {
        if (INSTANCE == null) {
            synchronized (FoodDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FoodDataSource(appExecutors, foodDao);
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public void getFavoriteFoods(@NonNull final LoadFoodsCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Food> foods = mFoodDao.getFavoriteFoods();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFoodsLoaded(foods);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getFoodsByTypeId(final String typeId, @NonNull final LoadFoodsCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Food> foods = mFoodDao.getFoodsByTypeId(typeId);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFoodsLoaded(foods);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }


    @Override
    public void saveFood(@NonNull final Food food) {
        checkNotNull(food);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mFoodDao.insertFood(food);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }



    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }
}
