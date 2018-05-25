package com.karayelli.alican.calorietable.list;

import android.support.annotation.NonNull;

import com.karayelli.alican.calorietable.BasePresenter;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;

import java.util.List;

public interface CalorieTableContract {


    interface View {

        void showCalorieTable(List<TabUIModel> tabUIModels, List<TabItemUIModel> favoriteTabData);

        boolean isActive();

        void showFoodDetail(String id);

        void showLoadingTasksError();

        void showSuccessfullySavedMessage();

        void setLoadingIndicator(boolean active);

    }



    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadFoodTypes();

        void addFoodToFavorite();

        void openFoodDetail(@NonNull TabItemUIModel requestedFood);

    }
}
