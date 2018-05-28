package com.karayelli.alican.calorietable.list;


import android.support.annotation.NonNull;

import com.karayelli.alican.calorietable.data.food.Food;
import com.karayelli.alican.calorietable.data.food.FoodDataSource;
import com.karayelli.alican.calorietable.data.food.IFoodDataSource;
import com.karayelli.alican.calorietable.data.foodType.FoodType;
import com.karayelli.alican.calorietable.data.foodType.FoodTypesDataSource;
import com.karayelli.alican.calorietable.data.foodType.IFoodTypesDataSource;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class CalorieTablePresenter implements CalorieTableContract.Presenter {


    private final FoodTypesDataSource mFoodTypesDataSource;
    private final FoodDataSource mFoodDataSource;
    private final CalorieTableContract.View mCalorieTableListView;


    public CalorieTablePresenter(@NonNull FoodTypesDataSource mFoodTypesDataSource, @NonNull FoodDataSource mFoodDataSource, @NonNull CalorieTableContract.View mCalorieTableListView) {
        this.mFoodTypesDataSource = mFoodTypesDataSource;
        this.mFoodDataSource = mFoodDataSource;
        this.mCalorieTableListView = mCalorieTableListView;
    }



    @Override
    public void start() {
        loadFoodTypes(false);
    }


    @Override
    public void loadFoodTypes() {
        loadFoodTypes(true);
    }


    @Override
    public void result(int requestCode, int resultCode) {

    }


    private void loadFoodTypes(final boolean showLoadingUI){

        if(showLoadingUI){
            mCalorieTableListView.setLoadingIndicator(true);
        }

        final List<FoodType> foodTypeListToShow = new ArrayList<>();

        mFoodTypesDataSource.getFoodTypes(new IFoodTypesDataSource.LoadFoodTypesCallback() {
            @Override
            public void onFoodTypesLoaded(final List<FoodType> foodTypes) {


                for (final FoodType foodType : foodTypes){

                    mFoodDataSource.getFoodsByTypeId(foodType.getId(), new IFoodDataSource.LoadFoodsCallback() {
                        @Override
                        public void onFoodsLoaded(List<Food> foods) {

                            foodType.setFoodList(foods);
                            foodTypeListToShow.add(foodType);

                            if(foodTypeListToShow.size() == foodTypes.size()){
                                Timber.d("All food type items fetched!");


                                mFoodDataSource.getFavoriteFoods(new IFoodDataSource.LoadFoodsCallback() {
                                    @Override
                                    public void onFoodsLoaded(List<Food> foods) {

                                        if (!mCalorieTableListView.isActive()){
                                            return;
                                        }

                                        if (showLoadingUI){
                                            mCalorieTableListView.setLoadingIndicator(false);
                                        }
                                        processLayoutData(foodTypes, foods);
                                    }

                                    @Override
                                    public void onDataNotAvailable() {
                                        Timber.e("No data found for favorite foods!");
                                    }
                                });

                            }
                        }
                        @Override
                        public void onDataNotAvailable() {}
                    });
                }
            }
            @Override
            public void onDataNotAvailable() {
                Timber.e("No data found for food types!");
            }
        });

    }

    private void processLayoutData(List<FoodType> foodTypes, List<Food> favoriteFoods){

        List<TabItemUIModel> favTabItemUIModels = new ArrayList<>();

        for (Food food: favoriteFoods) {
            favTabItemUIModels.add(TabItemUIModel.builder().titleTR(food.getLabelTR()).titleEN(food.getLabelEN()).calorieValue(food.getCalorie()).id(food.getId()).isFavorite(food.getFavorite()).build());
        }


        List<TabUIModel> tabUIModels = new ArrayList<>();

        for(FoodType foodType: foodTypes){

            List<TabItemUIModel> tabItemUIModels = new ArrayList<>();
            for (Food food: foodType.getFoodList()) {
                tabItemUIModels.add(TabItemUIModel.builder().titleTR(food.getLabelTR()).titleEN(food.getLabelEN()).calorieValue(food.getCalorie()).id(food.getId()).isFavorite(food.getFavorite()).build());
            }

            TabUIModel tabUIModel = TabUIModel.builder().id(foodType.getId())
                    .titleTR(foodType.getLabelTR())
                    .titleEN(foodType.getLabelEN())
                    .tabIcon(foodType.getIcon())
                    .tabColor(foodType.getColor())
                    .collapseImage(foodType.getSpotlightImage())
                    .tabItemUIModels(tabItemUIModels).build();

            tabUIModels.add(tabUIModel);
        }

        mCalorieTableListView.showCalorieTable(tabUIModels, favTabItemUIModels);
    }


    @Override
    public void addFoodToFavorite(@NonNull TabItemUIModel food) {

        mFoodDataSource.changeFavoriteStatus(food.getId(), true);
        mCalorieTableListView.showSuccessfullyAddedToFavoriteMessage();
    }

    @Override
    public void removeFoodFromFavorite(@NonNull TabItemUIModel food) {
        mFoodDataSource.changeFavoriteStatus(food.getId(), false);
        mCalorieTableListView.showSuccessfullyRemovedFromFavoriteMessage();
    }

    @Override
    public void openFoodDetail(@NonNull TabItemUIModel requestedFood) {

    }


}
