package com.karayelli.alican.calorietable.list;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;

import com.karayelli.alican.calorietable.BasePresenter;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;

import java.util.List;

public interface CalorieTableContract {


    interface View {

        void showCalorieTable(List<TabUIModel> tabUIModels, List<TabItemUIModel> favoriteTabData);

        boolean isActive();

        void markFoodAsFavorite(String id);

        void showFoodDetail(String id);

        void showLoadingTasksError();

        void showSuccessfullySavedMessage();

        void showSuccessfullyAddedToFavoriteMessage();

        void showSuccessfullyRemovedFromFavoriteMessage();

        void setLoadingIndicator(boolean active);

    }



    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadFoodTypes();

        void addFoodToFavorite(@NonNull TabItemUIModel food);

        void removeFoodFromFavorite(@NonNull TabItemUIModel food);

        void openFoodDetail(@NonNull TabItemUIModel requestedFood);

    }
}
