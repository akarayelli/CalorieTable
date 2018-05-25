package com.karayelli.alican.calorietable;

import android.app.Application;

import com.karayelli.alican.calorietable.util.Constant;
import com.karayelli.alican.calorietable.util.DataUtil;
import com.karayelli.alican.calorietable.util.SharedPreferencesUtil;

import timber.log.Timber;

public class CalorieTable extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        initializeDatabase();

    }


    private void initializeDatabase(){

        Boolean isFirstOpen = SharedPreferencesUtil.with(getApplicationContext()).readBoolean(Constant.SHARED_PREFS_KEY_FIRST_OPEN, true);

        if(isFirstOpen) {
            Timber.d("Application is opened for the first time. Will initialize database");
            DataUtil dataUtil = new DataUtil(getApplicationContext());
            dataUtil.createFoodType();
            SharedPreferencesUtil.with(getApplicationContext()).writeBoolean(Constant.SHARED_PREFS_KEY_FIRST_OPEN,false);
        }

    }
}
