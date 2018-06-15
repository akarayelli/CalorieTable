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

        final String[] colors = mContext.getResources().getStringArray(R.array.tab_color);
        FoodDataSource foodDataSource = Injection.provideFoodDataSource(mContext);
        FoodTypesDataSource foodTypesDataSource = Injection.provideFoodTypesDataSource(mContext);


        // ******************** MEYVELER / SEBZELER***********************************

        FoodType foodType1 = new FoodType("Meyve/Sebze","Fruits/Vegetables","Description", "1", colors[1], R.drawable.tab_fruits_vegetables,R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType1);

        FoodType foodType2 = new FoodType("Et Ürünleri", "Meats","Description","2", colors[2], R.drawable.tab_meat,R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType2);

        FoodType foodType3 = new FoodType("Süt Ürünleri","Dairy Foods","Description","3", colors[3], R.drawable.tab_milk,R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType3);

        FoodType foodType4 = new FoodType("Balıklar","Fishes","Description","4", colors[4], R.drawable.tab_fish,R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType4);

        FoodType foodType5 = new FoodType("İçecekler","Beverages","Description","5", colors[5], R.drawable.tab_beverage,R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType5);

        FoodType foodType6 = new FoodType("Ekmek/Tahıl","Bread/Grain","Description","6", colors[6], R.drawable.tab_bread, R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType6);

        FoodType foodType7 = new FoodType("Kuruyemiş","Nuts","Description","7", colors[7], R.drawable.tab_walnut, R.drawable.sptlight_1);
        foodTypesDataSource.saveFoodType(foodType7);


        //TODO: Find a smarter solution.
        //This is not a permenant

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Food food100 = new Food("Elma", "Apple","95","100",true, "1");
        Food food101 = new Food("Kayısı", "Apricot","29","101",false, "1");
        Food food102 = new Food("Avokado", "Avocado","300","102",false, "1");
        Food food104 = new Food("Muz", "Banana","115","104",true, "1");
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
        Food food118 = new Food("Ahududu (Kırmızı)", "Raspberry (Red)","57","118",false, "1");
        Food food119 = new Food("Ahududu (Siyah)", "Raspberry (Black)","73","119",false, "1");
        Food food120 = new Food("Ayva", "Strawberry","57","120",false, "1");
        Food food121 = new Food("Dut", "Quince","93","121",false, "1");
        Food food122 = new Food("Erik (Kırmızı)", "Plum (Red)","66","122",false, "1");
        Food food123 = new Food("Erik (Mürdüm)", "Juror","79","123",false, "1");
        Food food124 = new Food("Erik (Kuru)", "Plum (Dried)","255","124",false, "1");
        Food food125 = new Food("Hurma", "Date","274","125",false, "1");
        Food food126 = new Food("İncir (Taze)", "Fig","274","126",false, "1");
        Food food127 = new Food("İncir (Kuru)", "Fig (Dried)","80","127",false, "1");
        Food food128 = new Food("Kiraz", "Cherry","5","128",false, "1");
        Food food129 = new Food("Vişne", "Sour Cherry","58","129",false, "1");


        Food food150 = new Food("Enginar","Artichoke","67","150",false, "1");
        Food food151 = new Food("Kuşkonmaz","Asparagus(1 Cup)","36","151",false, "1");
        Food food152 = new Food("Brokoli","Broccoli(1 Cup)","40","152",false, "1");
        Food food153 = new Food("Brüksel Lahanası","Brussels Sprouts","56","153",false, "1");
        Food food154 = new Food("Balkabağı","Butternut Squash","139","154",false, "1");
        Food food155 = new Food("Lahana","Cabbage","31","155",false, "1");
        Food food156 = new Food("Havuç","Carrot","30","156",false, "1");
        Food food157 = new Food("Karnıbahar","Cauliflower","28","157",false, "1");
        Food food158 = new Food("Kereviz","Celery(3 Small Stalks)","9","158",false, "1");
        Food food159 = new Food("Pazı","Chard","32","159",false, "1");
        Food food160 = new Food("Mısır","Corn","96","160",false, "1");
        Food food161 = new Food("Salatalık","Cucumber","15","161",false, "1");
        Food food162 = new Food("Fasulye","Green Beans","32","162",false, "1");
        Food food163 = new Food("Biber","Green Pepper","22","163",false, "1");
        Food food164 = new Food("Pancar","Beetroot","43","164",false, "1");
        Food food165 = new Food("Turp","Radish","19","165",false, "1");
        Food food166 = new Food("Pırasa","Leek","52","166",false, "1");
        Food food167 = new Food("Marul","Lettuce","14","167",false, "1");
        Food food168 = new Food("Maydanoz","Parsley","44","168",false, "1");
        Food food169 = new Food("Patlıcan","Eggplant","25","169",false, "1");
        Food food170 = new Food("Bezelye","Pease","84","170",false,"1");
        Food food171 = new Food("Mantar","Mushroom","14","171",false,"1");




        foodDataSource.saveFood(food100);
        foodDataSource.saveFood(food101);
        foodDataSource.saveFood(food102);
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
        foodDataSource.saveFood(food118);
        foodDataSource.saveFood(food119);
        foodDataSource.saveFood(food120);
        foodDataSource.saveFood(food121);
        foodDataSource.saveFood(food122);
        foodDataSource.saveFood(food123);
        foodDataSource.saveFood(food124);
        foodDataSource.saveFood(food125);
        foodDataSource.saveFood(food126);
        foodDataSource.saveFood(food127);
        foodDataSource.saveFood(food128);
        foodDataSource.saveFood(food129);


        foodDataSource.saveFood(food150);
        foodDataSource.saveFood(food151);
        foodDataSource.saveFood(food152);
        foodDataSource.saveFood(food153);
        foodDataSource.saveFood(food154);
        foodDataSource.saveFood(food155);
        foodDataSource.saveFood(food156);
        foodDataSource.saveFood(food157);
        foodDataSource.saveFood(food158);
        foodDataSource.saveFood(food159);
        foodDataSource.saveFood(food160);
        foodDataSource.saveFood(food161);
        foodDataSource.saveFood(food162);
        foodDataSource.saveFood(food163);
        foodDataSource.saveFood(food164);
        foodDataSource.saveFood(food165);
        foodDataSource.saveFood(food166);
        foodDataSource.saveFood(food167);
        foodDataSource.saveFood(food168);
        foodDataSource.saveFood(food169);
        foodDataSource.saveFood(food170);

        // ******************** SEBZELER ***********************************
        Food food200 = new Food("Dana Eti (Yağlı)","Veal (Fatty)","223","200",false, "2");
        Food food201 = new Food("Dana eti (Orta Yağlı)","Veal (Med-Fat)","190","201",true, "2");
        Food food202 = new Food("Dana eti (Az Yağlı)","Veal (Low-Fat)","156","202",false, "2");
        Food food203 = new Food("Koyun eti (Yağlı)","Mutton (Fatty)","310","203",false, "2");
        Food food204 = new Food("Koyun eti (Orta Yağlı)","Mutton (Med-Fat)","263","204",false, "2");
        Food food205 = new Food("Koyun eti (Az Yağlı)","Mutton (Low-Fat)","247","205",false, "2");
        Food food206 = new Food("Sığır eti (Yağlı)","Beef (Fatty)","301","206",false, "2");
        Food food207 = new Food("Sığır eti (Orta Yağlı)","Beef (Med-Fat)","263","207",false, "2");
        Food food208 = new Food("Sığır eti (Az Yağlı)","Beef (Low-Fat)","225","208",false, "2");
        Food food209 = new Food("Tavuk (Bütün-Derili)","Chicken (Whole-skinned)","215","209",false, "2");
        Food food210 = new Food("Tavuk (Derisiz)","Chicken (Whole-skinless)","114","210",false, "2");
        Food food211 = new Food("Hindi Eti (Derili)","Turkey (Whole-skinned)","15","211",false, "2");
        Food food212 = new Food("Kaz Eti","Goose Meat","371","212",false, "2");
        Food food213 = new Food("Ördek Eti","Duck Meat","404","213",false, "2");
        Food food214 = new Food("Tavşan Eti","Rabbit Meat","162","214",false, "2");
        Food food215 = new Food("Pastırma (Çemenli)","Bacon","250","215",false, "2");
        Food food216 = new Food("Jambon","Ham","182","216",false, "2");
        Food food217 = new Food("Sosis","Sausage","322","217",false, "2");
        Food food218 = new Food("Salam","Salami","450","218",false, "2");
        Food food219 = new Food("Sucuk","Fermented Sausage ","452","219",false, "2");
        Food food220 = new Food("Akciğer","Lung","106","220",false, "2");
        Food food221 = new Food("Beyin","Brain","125","221",false, "2");
        Food food222 = new Food("Böbrek (Kuzu)","Kidney (Lamb)","105","222",false, "2");
        Food food223 = new Food("Dalak (Sığır,Dana)","Spleen (Cattle)","104","223",false, "2");
        Food food224 = new Food("İşkembe (Sığır)","Tripe (Cattle)","100","224",false, "2");
        Food food225 = new Food("Yürek (Dana)","Heart (Calf)","124","225",false, "2");
        Food food226 = new Food("Karaciğer (Tavuk)","Liver (Chicken)","129","226",false, "2");
        Food food227 = new Food("Karaciğer (Dana)","Liver (Calf)","140","227",false, "2");
        Food food228 = new Food("Yumurta (Adet-Tavuk)","Egg(1 Pcs - Chicken)","70","228",true, "2");

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
        foodDataSource.saveFood(food220);
        foodDataSource.saveFood(food221);
        foodDataSource.saveFood(food222);
        foodDataSource.saveFood(food223);
        foodDataSource.saveFood(food224);
        foodDataSource.saveFood(food225);
        foodDataSource.saveFood(food226);
        foodDataSource.saveFood(food227);
        foodDataSource.saveFood(food228);


        // ******************** SÜT ÜRÜNLERİ *********************************** 

        //Food food300 = new Food("Süt(Yağlı)", "Milk, whole","68","300",true,"3");
        //Food food301 = new Food("Ayran", "Buttermilk, whole","38","301",false,"3");
        Food food302 = new Food("Beyaz Peynir", "Feta Cheese","275","302",true,"3");
        Food food303 = new Food("Dil Peyniri","String Cheese","330","303",false,"3");
        Food food304 = new Food("İnek Sütü (Yağlı)","Cow's Milk (Full Fat)","61","304",false,"3");
        Food food305 = new Food("Kaşar Peyniri","Kashar Cheese","413","305",false,"3");
        Food food306 = new Food("Koyun Sütü","Raw Ewe Milk ","108","306",false,"3");
        Food food307 = new Food("Krem Peynir","Camambert","349","307",false,"3");
        Food food308 = new Food("Lor Peyniri","Curd Cheese","90","308",false,"3");
        Food food309 = new Food("Tulum Peyniri","Bryndza","257","309",false,"3");
        Food food310 = new Food("Yoğurt(Yağlı)","Yogurt, whole","95","310",false,"3");
        Food food311 = new Food("İnek Sütü (Yağsız)","Cow's Milk (No Fat)","35","311",false,"3");
        Food food312 = new Food("İnek Sütü (1/2 Yağlı)","Cow's Milk (Half Fat)","50","312",false,"3");
        Food food313 = new Food("Keçi Sütü","Goat's Milk","69","313",false,"3");
        Food food314 = new Food("Süt Tozu (Yağlı)","Milk Powder (Full Fat)","496","314",false,"3");
        Food food315 = new Food("Süt Tozu (Yağsız)","Milk Powder (No Fat)","362","315",false,"3");

        //foodDataSource.saveFood(food300);
      // foodDataSource.saveFood(food301);
        foodDataSource.saveFood(food302);
        foodDataSource.saveFood(food303);
        foodDataSource.saveFood(food304);
        foodDataSource.saveFood(food305);
        foodDataSource.saveFood(food306);
        foodDataSource.saveFood(food307);
        foodDataSource.saveFood(food308);
        foodDataSource.saveFood(food309);
        foodDataSource.saveFood(food310);
        foodDataSource.saveFood(food311);
        foodDataSource.saveFood(food312);
        foodDataSource.saveFood(food313);
        foodDataSource.saveFood(food314);
        foodDataSource.saveFood(food315);



        // ******************** BALIKLAR ***********************************
        Food food400 = new Food("Alabalık","Trout","175","400",false,"4");
        Food food401 = new Food("Balık Unu","Fish Flour","336","401",false,"4");
        Food food402 = new Food("Kalkan","Turbot","193","402",false,"4");
        Food food403 = new Food("Kılıç","Swordfish","164","403",false,"4");
        Food food404 = new Food("Levrek","Seabass","93","404",false,"4");
        Food food405 = new Food("Mersin","Sturgeon","96","405",false,"4");
        Food food406 = new Food("Palamut","Bonito","168","406",false,"4");
        Food food407 = new Food("Sardalya","Sardine","160","407",false,"4");
        Food food408 = new Food("Somon","Salmon","155","408",false,"4");
        Food food409 = new Food("Ton","Tuna","289","409",false,"4");
        Food food410 = new Food("Tuzlu Balık","Salted Fish","305","410",false,"4");
        Food food411 = new Food("Uskumru","Mackerel","159","411",false,"4");
        Food food412 = new Food("Kalamar","Squid","79","412",false,"4");
        Food food413 = new Food("Mezgit","Mergit","80","413",false,"4");


        foodDataSource.saveFood(food400);
        foodDataSource.saveFood(food401);
        foodDataSource.saveFood(food402);
        foodDataSource.saveFood(food403);
        foodDataSource.saveFood(food404);
        foodDataSource.saveFood(food405); 
        foodDataSource.saveFood(food406);
        foodDataSource.saveFood(food407);
        foodDataSource.saveFood(food408);        
        foodDataSource.saveFood(food409);
        foodDataSource.saveFood(food410);
        foodDataSource.saveFood(food411);        
        foodDataSource.saveFood(food412);
        foodDataSource.saveFood(food413);





        // ******************** İÇECEKLER *********************************** 

        Food food500 = new Food("Buzlu Çay","Ice Tea","100","500",false,"5");
        Food food501 = new Food("Sade Kahve","Black Turkish Coffee","100","501",false,"5");
        Food food502 = new Food("Kahve (1 Fincan)","Coffee (1 Cup)","300","502",false,"5");
        Food food503 = new Food("Şekersiz Çay","Tea (Unsweetened)","100","503",false,"5");
        Food food504 = new Food("Meyveli Soda","Fruit Soda","100","504",false,"5");
        Food food505 = new Food("Adaçayı","Sage Tea","100","505",false,"5");
        Food food506 = new Food("Diyet Kola (1 Adet)","Diet Cola (1 Pcs)","300","506",false,"5");
        Food food507 = new Food("Normal Kola (1 Adet)","Cola (1 Pcs)","300","507",false,"5");
        Food food508 = new Food("Domates Suyu","Tomato Juice","300","508",false,"5");
        Food food509 = new Food("Gazoz","Soda Pop","300","509",false,"5");
        Food food510 = new Food("Ihlamur","Linden","300","510",false,"5");
        Food food511 = new Food("Limonata","Lemonade","300","511",false,"5");
        Food food512 = new Food("Rakı","Raki","300","512",false,"5");
        Food food513 = new Food("Bira (1 Adet)","Beer (1 Pcs)","300","513",false,"5");
        Food food514 = new Food("Beyaz Şarap (1 Kadeh)","White Wine (1 Glass)","200","514",false,"5");
        Food food515 = new Food("Kırmızı Şarap (1 Kadeh)","Red Wine (1 Glass)","300","515",false,"5");
        Food food516 = new Food("Portakal Suyu","Orange Juice","300","516",false,"5");
        Food food517 = new Food("Viski","Whiskey","300","517",false,"5");
        Food food518 = new Food("Votka","Vodka","300","518",false,"5");
        Food food519 = new Food("Cin","Gin","300","519",false,"5");
        Food food520 = new Food("Ayran","Buttermilk, whole","38","520",true,"5");


        foodDataSource.saveFood(food500);
        foodDataSource.saveFood(food501);
        foodDataSource.saveFood(food502);
        foodDataSource.saveFood(food503);
        foodDataSource.saveFood(food504);
        foodDataSource.saveFood(food505); 
        foodDataSource.saveFood(food506);
        foodDataSource.saveFood(food507);
        foodDataSource.saveFood(food508);        
        foodDataSource.saveFood(food509);
        foodDataSource.saveFood(food510);
        foodDataSource.saveFood(food511);        
        foodDataSource.saveFood(food512);
        foodDataSource.saveFood(food513);
        foodDataSource.saveFood(food514);
        foodDataSource.saveFood(food515);
        foodDataSource.saveFood(food516);
        foodDataSource.saveFood(food517);
        foodDataSource.saveFood(food518);
        foodDataSource.saveFood(food519);
        foodDataSource.saveFood(food520);



    // ************************** EKMEK TAHIL ******************




        Food food600 = new Food("Ekmek (Buğday)","Bread (Wheat)","276","600",true,"6");
        Food food601 = new Food("Ekmek (Kepek)","Bread (Rye)","243","601",false,"6");
        Food food602 = new Food("Ekmek (Çavdar)","Bread (Bran)","60","602",false,"6");
        Food food603 = new Food("Galeta Unu","Breadcrumbs","392","603",false,"6");
        Food food604 = new Food("Grissini","Grissini","433","604",false,"6");
        Food food605 = new Food("İrmik","Semolina","371","605",false,"6");
        Food food606 = new Food("Karaker (Sade)","Cracker (Cheese)","479","606",false,"6");
        Food food607 = new Food("Karaker (Peynirli)","Cracker (Plain)","384","607",false,"6");
        Food food608 = new Food("Karaker (Tuzlu)","Cracker (Salted)","433","608",false,"6");
        Food food609 = new Food("Makarna","Pasta","369","609",false,"6");
        Food food610 = new Food("Yufka","Poppy","271","610",false,"6");
        Food food611 = new Food("Yufka (Böreklik)","Poppy (Pastry)","152","611",false,"6");
        Food food612 = new Food("Sandviç Ekmeği","Sandwich Bread","298","612",false,"6");
        Food food613 = new Food("Mısır Gevreği","Corn Loaf","388","613",false,"6");
        Food food614 = new Food("Yulaf Ezmesi","Oatmeal","429","614",false,"6");
        Food food615 = new Food("" +
                "" +
                "" +
                "ç Pilavı","Rice Pilaf","326","615",false,"6");
        Food food616 = new Food("Pandispanya","Angel Cake","280","616",false,"6");
        Food food617 = new Food("Erişte/Şehriye","Vermicelli","111","617",false,"6");
        Food food618 = new Food("Bulgur","Bulgur","125","618",false,"6");
        Food food619 = new Food("Un","Flour","364","619",false,"6");
        Food food620 = new Food("Kuru Fasulye","Haricot","118","620",false,"6");
        Food food621 = new Food("Barbunya","Cranberry Beans","118","621",false,"6");
        Food food623 = new Food("Patates","Potato","86","623",false,"6");
        Food food624 = new Food("Patetes Püresi","Cream Potatos","77","624",false,"6");
        Food food625 = new Food("Patates Kızartması","Fried Potatos","222","625",false,"6");
        Food food626 = new Food("Susam","Sesame","589","626",false,"6");
        Food food627 = new Food("Arpa","Barley","367","627",false,"6");
        Food food628 = new Food("Kuskus","Kuskus","371","628",false,"6");


        foodDataSource.saveFood(food600);
        foodDataSource.saveFood(food601);
        foodDataSource.saveFood(food602);
        foodDataSource.saveFood(food603);
        foodDataSource.saveFood(food604);
        foodDataSource.saveFood(food605); 
        foodDataSource.saveFood(food606);
        foodDataSource.saveFood(food607);
        foodDataSource.saveFood(food608);        
        foodDataSource.saveFood(food609);
        foodDataSource.saveFood(food610);
        foodDataSource.saveFood(food611);        
        foodDataSource.saveFood(food612);
        foodDataSource.saveFood(food613);
        foodDataSource.saveFood(food614);
        foodDataSource.saveFood(food615);
        foodDataSource.saveFood(food616);
        foodDataSource.saveFood(food617);
        foodDataSource.saveFood(food618);
        foodDataSource.saveFood(food619);
        foodDataSource.saveFood(food620);
        foodDataSource.saveFood(food621);
        foodDataSource.saveFood(food623);
        foodDataSource.saveFood(food624);
        foodDataSource.saveFood(food625);
        foodDataSource.saveFood(food626);
        foodDataSource.saveFood(food627);
        foodDataSource.saveFood(food628);


        // ************************** KURUYEMİŞ ******************

        Food food700 = new Food("Badem","Almond","600","700",false,"7");
        Food food701 = new Food("Hindistan Cevizi","Coconut","701","601",false,"7");
        Food food702 = new Food("Fındık","Hazelnut","650","702",true,"7");
        Food food704 = new Food("Çam Fıstığı","Pine Nuts","600","704",false,"7");
        Food food705 = new Food("Ceviz","Walnut","549","705",true,"7");
        Food food706 = new Food("Patlamış Mısır","Popcorn","478","706",false,"7");
        Food food707 = new Food("Kabak Çekirdeği","Pumpkin seeds","571","707",false,"7");
        Food food708 = new Food("Ay Çekirdeği","Sunflower","578","708",false,"7");
        Food food709 = new Food("Yer Fıstığı","Peanut","582","709",false,"7");
        Food food710 = new Food("Antep Fıstığı","Pistachio","567","710",false,"7");
        Food food711 = new Food("Kaju","Cashew","574","711",false,"7");

        foodDataSource.saveFood(food700);
        foodDataSource.saveFood(food701);
        foodDataSource.saveFood(food702);
        foodDataSource.saveFood(food704);
        foodDataSource.saveFood(food705);
        foodDataSource.saveFood(food706);
        foodDataSource.saveFood(food707);
        foodDataSource.saveFood(food708);
        foodDataSource.saveFood(food709);
        foodDataSource.saveFood(food710);
        foodDataSource.saveFood(food711);

    }

}
