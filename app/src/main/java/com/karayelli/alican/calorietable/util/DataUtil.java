package com.karayelli.alican.calorietable.util;

import android.content.Context;

import com.karayelli.alican.calorietable.Injection;
import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.data.food.FoodDataSource;
import com.karayelli.alican.calorietable.data.foodType.FoodTypesDataSource;
import com.karayelli.alican.calorietable.data.food.Food;
import com.karayelli.alican.calorietable.data.foodType.FoodType;

import java.util.ArrayList;
import java.util.List;


public class DataUtil {

    private final Context mContext;

    public DataUtil(Context context) {
        mContext = context;
    }



    public void createFoodType(){

        final String[] colors = mContext.getResources().getStringArray(R.array.default_preview);
        FoodDataSource foodDataSource = Injection.provideFoodDataSource(mContext);
        FoodTypesDataSource foodTypesDataSource = Injection.provideFoodTypesDataSource(mContext);


        FoodType foodType1 = new FoodType("Meyveler","Fruits","Description", "1", colors[1], R.drawable.tab_apple,R.drawable.sptlight_fruit);
        foodTypesDataSource.saveFoodType(foodType1);

        Food food1 = new Food("Elma", "Apple","95","1",false, "1");
        Food food2 = new Food("Kayısı", "Apricot","29","2",false, "1");
        Food food3 = new Food("Avokado", "Avocado","300","3",false, "1");

        foodDataSource.saveFood(food1);
        foodDataSource.saveFood(food2);
        foodDataSource.saveFood(food3);

        List<Food> foodList1 = new ArrayList<>();
        foodList1.add(food1);
        foodList1.add(food2);
        foodList1.add(food3);

        foodType1.setFoodList(foodList1);



        FoodType foodType2 = new FoodType("Type2", "","Type2 Description","2", colors[2], R.drawable.tab_carrot,R.drawable.sptlight_vegetables);
        foodTypesDataSource.saveFoodType(foodType2);

        Food food4 = new Food("Food4","","100","4",false, "2");
        Food food5 = new Food("Food5","","200","5",false, "2");
        Food food6 = new Food("Food6","","300","6",false, "2");

        foodDataSource.saveFood(food4);
        foodDataSource.saveFood(food5);
        foodDataSource.saveFood(food6);


        List<Food> foodList2 = new ArrayList<>();
        foodList2.add(food4);
        foodList2.add(food5);
        foodList2.add(food6);

        foodType2.setFoodList(foodList2);



        FoodType foodType3 = new FoodType("Type3","","Type3 Description","3", colors[3], R.drawable.tab_milk,R.drawable.sptlight_milk);
        foodTypesDataSource.saveFoodType(foodType3);

        Food food7 = new Food("Food7", "","100","7",false,"3");
        Food food8 = new Food("Food8", "","200","8",false,"3");
        Food food9 = new Food("Food9","","300","9",true,"3");
        Food food10 = new Food("Food10","","100","10",false,"3");
        Food food11 = new Food("Food11","","200","11",true,"3");
        Food food12 = new Food("Food12","","300","12",false,"3");

        foodDataSource.saveFood(food7);
        foodDataSource.saveFood(food8);
        foodDataSource.saveFood(food9);
        foodDataSource.saveFood(food10);
        foodDataSource.saveFood(food11);
        foodDataSource.saveFood(food12);


        FoodType foodType4 = new FoodType("Type4","","Type4 Description","4", colors[4], R.drawable.tab_fish,R.drawable.sptlight_fish);
        foodTypesDataSource.saveFoodType(foodType4);

        Food food13 = new Food("Food13","","100","13",false,"4");
        Food food14 = new Food("Food14","","200","14",false,"4");
        Food food15 = new Food("Food15","","300","15",true,"4");


        foodDataSource.saveFood(food13);
        foodDataSource.saveFood(food14);
        foodDataSource.saveFood(food15);


        List<Food> foodList3 = new ArrayList<>();
        foodList3.add(food7);
        foodList3.add(food8);
        foodList3.add(food9);
        foodList3.add(food10);
        foodList3.add(food11);
        foodList3.add(food12);
        foodType3.setFoodList(foodList3);




    }

}
