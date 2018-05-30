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

        Food food100 = new Food("Elma", "Apple","95","100",false, "1");
        Food food101 = new Food("Kayısı", "Apricot","29","101",false, "1");
        Food food102 = new Food("Avokado", "Avocado","300","102",false, "1");
        Food food103 = new Food("Amla", "Amla","60","103",false, "1");
        Food food104 = new Food("Muz", "Banana","115","104",false, "1");
        Food food105 = new Food("Üzüm", "Grape","70","105",false, "1");
        Food food106 = new Food("Limon", "Lemon","55","106",false, "1");
        Food food107 = new Food("Mango", "Mango","75","107",false, "1");
        Food food108 = new Food("Portakal", "Orange","50","108",false, "1");
        Food food109 = new Food("Karpuz", "Watermelon","15","109",false, "1");
        Food food110 = new Food("Guava", "Guava","50","110",false, "1");
        Food food111 = new Food("Böğürtlen", "Blackberry","25","111",false, "1");
        Food food112 = new Food("Yaban Mersini", "Blueberry","30","112",false, "1");
        Food food113 = new Food("Kavun", "Melon","26","113",false, "1");
        Food food114 = new Food("Şeftali", "Peach","36","114",false, "1");
        Food food115 = new Food("Armut", "Pear","64","115",false, "1");
        Food food116 = new Food("Ananas(2 Dilim)", "Pineapple(2 Rings)","50","116",false, "1");
        Food food117 = new Food("Çilek", "Strawberry","27","117",false, "1");

        foodDataSource.saveFood(food100);
        foodDataSource.saveFood(food101);
        foodDataSource.saveFood(food102);
        foodDataSource.saveFood(food103);
        foodDataSource.saveFood(food104);
        foodDataSource.saveFood(food105);
        foodDataSource.saveFood(food106);
        foodDataSource.saveFood(food107);
        foodDataSource.saveFood(food108);
        foodDataSource.saveFood(food109);
        foodDataSource.saveFood(food110);
        foodDataSource.saveFood(food111);
        foodDataSource.saveFood(food112);
        foodDataSource.saveFood(food113);
        foodDataSource.saveFood(food114);
        foodDataSource.saveFood(food115);
        foodDataSource.saveFood(food116);
        foodDataSource.saveFood(food117);


        FoodType foodType2 = new FoodType("Sebzeler", "Vegetables","Description","2", colors[2], R.drawable.tab_carrot,R.drawable.sptlight_vegetables);
        foodTypesDataSource.saveFoodType(foodType2);

        Food food200 = new Food("Enginar","Artichoke","67","200",false, "2");
        Food food201 = new Food("Kuşkonmaz","Asparagus(1 Cup)","36","201",false, "2");
        Food food202 = new Food("Brokoli","Broccoli(1 Cup)","40","202",false, "2");
        Food food203 = new Food("Brüksel Lahanası","Brussels Sprouts","56","203",false, "2");
        Food food204 = new Food("Balkabağı","Butternut Squash","139","204",false, "2");
        Food food205 = new Food("Lahana","Cabbage","31","205",false, "2");
        Food food206 = new Food("Havuç","Carrot","30","206",false, "2");
        Food food207 = new Food("Karnıbahar","Cauliflower","28","207",false, "2");
        Food food208 = new Food("Kereviz","Celery(3 Small Stalks)","9","208",false, "2");
        Food food209 = new Food("Pazı","Chard","32","209",false, "2");
        Food food210 = new Food("Mısır","Corn","96","210",false, "2");
        Food food211 = new Food("Salatalık","Cucumber","15","211",false, "2");
        Food food212 = new Food("Fasulye","Green Beans","32","212",false, "2");
        Food food213 = new Food("Biber","Green Pepper","22","213",false, "2");
        Food food214 = new Food("Pancar","Beetroot","43","214",false, "2");
        Food food215 = new Food("Turp","Radish","19","215",false, "2");
        Food food216 = new Food("Pırasa","Leek","52","216",false, "2");
        Food food217 = new Food("Marul","Lettuce","14","217",false, "2");
        Food food218 = new Food("Maydanoz","Parsley","44","218",false, "2");
        Food food219 = new Food("Patlıcan","Eggplant","25","219",false, "2");

        foodDataSource.saveFood(food200);
        foodDataSource.saveFood(food201);
        foodDataSource.saveFood(food202);
        foodDataSource.saveFood(food203);
        foodDataSource.saveFood(food204);
        foodDataSource.saveFood(food205);
        foodDataSource.saveFood(food206);
        foodDataSource.saveFood(food207);
        foodDataSource.saveFood(food208);
        foodDataSource.saveFood(food209);
        foodDataSource.saveFood(food210);
        foodDataSource.saveFood(food211);
        foodDataSource.saveFood(food212);
        foodDataSource.saveFood(food213);
        foodDataSource.saveFood(food214);
        foodDataSource.saveFood(food215);
        foodDataSource.saveFood(food216);
        foodDataSource.saveFood(food217);
        foodDataSource.saveFood(food218);
        foodDataSource.saveFood(food219);


        FoodType foodType3 = new FoodType("Süt Ürünleri","Dairy Foods","Description","3", colors[3], R.drawable.tab_milk,R.drawable.sptlight_milk);
        foodTypesDataSource.saveFoodType(foodType3);

        Food food300 = new Food("Süt(Yağlı)", "Milk, whole","68","300",false,"3");
        Food food301 = new Food("Ayran", "Buttermilk, whole","38","301",false,"3");
        Food food302 = new Food("Beyaz Peynir", "Feta Cheese","275","302",false,"3");
        Food food303 = new Food("Dil Peyniri","String Cheese","330","303",false,"3");
        Food food304 = new Food("İnek Sütü","Cow's Milk","61","304",false,"3");
        Food food305 = new Food("Kaşar Peyniri","Kashar Cheese","413","305",false,"3");
        Food food306 = new Food("Koyun Sütü","Raw Ewe Milk ","108","306",false,"3");
        Food food307 = new Food("Krem Peynir","Camambert","349","307",false,"3");
        Food food308 = new Food("Lor Peyniri","Curd Cheese","90","308",false,"3");
        Food food309 = new Food("Tulum Peyniri","Bryndza","257","309",false,"3");
        Food food310 = new Food("Yoğurt(Yağlı)","Yogurt, whole","95","310",false,"3");

        foodDataSource.saveFood(food300);
        foodDataSource.saveFood(food301);
        foodDataSource.saveFood(food302);
        foodDataSource.saveFood(food303);
        foodDataSource.saveFood(food304);
        foodDataSource.saveFood(food305);
        foodDataSource.saveFood(food306);
        foodDataSource.saveFood(food307);
        foodDataSource.saveFood(food308);
        foodDataSource.saveFood(food309);
        foodDataSource.saveFood(food310);


        FoodType foodType4 = new FoodType("Type4","","Type4 Description","4", colors[4], R.drawable.tab_fish,R.drawable.sptlight_fish);
        foodTypesDataSource.saveFoodType(foodType4);

        Food food13 = new Food("Food13","","100","13",false,"4");
        Food food14 = new Food("Food14","","200","14",false,"4");
        Food food15 = new Food("Food15","","300","15",true,"4");


        foodDataSource.saveFood(food13);
        foodDataSource.saveFood(food14);
        foodDataSource.saveFood(food15);




    }

}
