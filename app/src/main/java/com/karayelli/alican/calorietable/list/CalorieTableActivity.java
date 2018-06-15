package com.karayelli.alican.calorietable.list;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.karayelli.alican.calorietable.BaseActivity;
import com.karayelli.alican.calorietable.Injection;
import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;
import com.karayelli.alican.calorietable.util.Constant;
import com.karayelli.alican.calorietable.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import timber.log.Timber;


public class CalorieTableActivity extends BaseActivity implements CalorieTableContract.View {


    private CalorieTableContract.Presenter mPresenter;
    private List<RecycleAdapter> mAdapterList;
    private NavigationTabBar mNavigationTabBar;
    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        initUI(new ArrayList<TabUIModel>(), new ArrayList<TabItemUIModel>());

        initialzeBannerAds();

        initialzeInterstitialAds();

        //Create presenter and start
        mPresenter = new CalorieTablePresenter(Injection.provideFoodTypesDataSource(getApplicationContext()), Injection.provideFoodDataSource(getApplicationContext()), this);
        mPresenter.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //mPresenter.start();
    }


    /**
     * Initializes View pager and navigation tab bars
     * @param tabUIModels
     * @param favoriteItemList
     */
    private void initUI(final List<TabUIModel> tabUIModels, final List<TabItemUIModel> favoriteItemList) {

        // ***************  VIEW PAGER **********************
        mAdapterList = new ArrayList<>() ;
        for(int i=0; i<=tabUIModels.size(); i++){ mAdapterList.add(null); }

        final ViewPager viewPager = findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return (tabUIModels.size() == 0 ? 1 : tabUIModels.size() + 1);
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp_list, null, false);


                // ****************** RECYCLE VIEW *************************
                final RecyclerView recyclerView = view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));

                RecycleAdapter adapter = null;
                if(position == 0){
                    Timber.d("Will initialize first list (FAVORITE)...");

                    adapter = new RecycleAdapter(favoriteItemList, CalorieTableActivity.this, mItemListener);
                    recyclerView.setAdapter(adapter);
                    mAdapterList.add(position,adapter);

                }else{
                    Timber.d("Will initialize food category list...");
                    adapter = new  RecycleAdapter(tabUIModels.get(position -1 ).getTabItemUIModels(), CalorieTableActivity.this, mItemListener);
                    recyclerView.setAdapter(adapter);
                    mAdapterList.add(position,adapter);

                }
                //******************************************************************************



                //************************* FILTERABLE SEARCH **********************************
                final SearchView searchView = view.findViewById(R.id.mSearch);
                RecycleAdapter finalAdapter = adapter;
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                       // RecycleAdapter adapter = mAdapterList.get(position);
                        finalAdapter.getFilter().filter(query);
                        return false;
                    }
                });

                EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchEditText.setTextColor(getResources().getColor(R.color.white));
                searchEditText.setHintTextColor(getResources().getColor(R.color.colorAccent));

                ImageView searchButton = (ImageView) searchView.findViewById (android.support.v7.appcompat.R.id.search_button);
                searchButton.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                // ************************************************************************************

                container.addView(view);
                return view;
            }
        });


        mNavigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();


        // *************** Favorite Tab ************
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icn_favorite),
                        getResources().getColor(R.color.favoriteTabColor))
                        .title(getString(R.string.favorite_tab_title))
                        .build()
        );

        //************ Food Type Tabs creation**********
        for(TabUIModel tabUIModel : tabUIModels){
            models.add(
                    new NavigationTabBar.Model.Builder(
                            getResources().getDrawable(tabUIModel.getTabIcon()),
                            Color.parseColor(tabUIModel.getTabColor()))
                            .title(tabUIModel.getTitle())
                            .build()
            );
        }

        // Set models and start from index "0" tab
        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setViewPager(viewPager, 0);

        //****************************************************************************


        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        mNavigationTabBar.setBehaviorEnabled(true);
        mNavigationTabBar.setTitleSize(8.0f);

        mNavigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                Timber.d("Page scrolled...");

                ImageView backdropImageView = findViewById(R.id.backdrop);

                final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar);
                collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
                collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

                if(position == 0){
                    Timber.d("Will initialize first tab (FAVORITE)...");
                    //backdropImageView.setImageResource(R.drawable.sptlight_favorite);
                    collapsingToolbarLayout.setTitle(getString(R.string.favorite_tab_title));
                }else{
                    Timber.d("Will initialize food category tab...");
                    TabUIModel tabUIModel = tabUIModels.get(position -1);
                    //backdropImageView.setImageResource(tabUIModel.getCollapseImage());
                    collapsingToolbarLayout.setTitle(tabUIModel.getTitle());
                }
            }

            @Override
            public void onPageSelected(final int position) {
                Timber.d("Page selected...");
                increaseAdTrigger(AdTrigger.TAB_VISIT);
                checkToShowInterstitialAds(AdTrigger.TAB_VISIT);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                Timber.d("Page scroll state changed...");
            }
        });

    }

    FoodItemListener mItemListener = new FoodItemListener() {
        @Override
        public void onFoodClick(TabItemUIModel item) {
            Timber.d("Food Item Clicked!");
            mPresenter.openFoodDetail(item);
        }

        @Override
        public void onFoodMarkedAsFavorite(TabItemUIModel item) {
            Timber.d("Food item: " + item.getTitle() + " clicked to add to favorite!");
            mPresenter.addFoodToFavorite(item);
            increaseAdTrigger(AdTrigger.FAVORITE);
            checkToShowInterstitialAds(AdTrigger.FAVORITE);
        }

        @Override
        public void onFoodRemovedFromFavorite(TabItemUIModel item) {
            Timber.d("Food item: " + item.getTitle() + " clicked to remove from favorite!");
            mPresenter.removeFoodFromFavorite(item);
            increaseAdTrigger(AdTrigger.FAVORITE);
            checkToShowInterstitialAds(AdTrigger.FAVORITE);
        }
    };






    @Override
    public void markFoodAsFavorite(String id) {}

    @Override
    public void setLoadingIndicator(boolean active) { }

    @Override
    public void showCalorieTable(List<TabUIModel> tabUIModels, List<TabItemUIModel> favoriteTabData, Boolean initialize) {
        if(initialize) {
            this.initUI(tabUIModels, favoriteTabData);
        }else{
            mAdapterList.get(0).replaceData(favoriteTabData);
            mNavigationTabBar.setModelIndex(0,true);
        }
    }

    @Override
    public void showFoodDetail(String id) { }

    @Override
    public void showLoadingTasksError() { }

    @Override
    public void showSuccessfullySavedMessage() {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessfullyAddedToFavoriteMessage() {

        mPresenter.loadFoodTypes();
        Toast.makeText(this, getString(R.string.successfully_added_favorite), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessfullyRemovedFromFavoriteMessage() {
        mPresenter.loadFoodTypes();
        Toast.makeText(this, getString(R.string.successfully_removed_favorite), Toast.LENGTH_LONG).show();
    }

    public interface FoodItemListener {
        void onFoodClick(TabItemUIModel item);
        void onFoodMarkedAsFavorite(TabItemUIModel item);
        void onFoodRemovedFromFavorite(TabItemUIModel item);
    }


    private void checkToShowInterstitialAds(AdTrigger adTrigger) {

        if (mInterstitialAd.isLoaded()) {

            switch (adTrigger) {
                case TAB_VISIT:
                    int tabVisitCount = SharedPreferencesUtil.with(getApplicationContext()).readInt(Constant.SHARED_PREFS_KEY_TAB_VISIT_COUNT, 0);

                    if (tabVisitCount >= Constant.RequiredTabVisitCountToShowAd) {
                        SharedPreferencesUtil.with(getApplicationContext()).writeInt(Constant.SHARED_PREFS_KEY_TAB_VISIT_COUNT, 0);
                        mInterstitialAd.show();
                    }
                    break;
                case FAVORITE:
                    int favoriteActionCount = SharedPreferencesUtil.with(getApplicationContext()).readInt(Constant.SHARED_PREFS_KEY_FAVORITE_ACTION_COUNT, 0);
                    if (favoriteActionCount >= Constant.RequiredFavoriteActionToShowAd) {
                        SharedPreferencesUtil.with(getApplicationContext()).writeInt(Constant.SHARED_PREFS_KEY_FAVORITE_ACTION_COUNT, 0);
                        mInterstitialAd.show();
                    }
                    break;
            }
        } else {
            Timber.d("The interstitial wasn't loaded yet.");
        }

    }

    private int increaseAdTrigger(AdTrigger adTrigger){

        switch (adTrigger){
            case FAVORITE:
                int favoriteActionCount = SharedPreferencesUtil.with(getApplicationContext()).readInt(Constant.SHARED_PREFS_KEY_FAVORITE_ACTION_COUNT,0);
                SharedPreferencesUtil.with(getApplicationContext()).writeInt(Constant.SHARED_PREFS_KEY_FAVORITE_ACTION_COUNT, ++favoriteActionCount);
                return favoriteActionCount;
            case TAB_VISIT:
                int tabVisitCount = SharedPreferencesUtil.with(getApplicationContext()).readInt(Constant.SHARED_PREFS_KEY_TAB_VISIT_COUNT,0);
                SharedPreferencesUtil.with(getApplicationContext()).writeInt(Constant.SHARED_PREFS_KEY_TAB_VISIT_COUNT, ++tabVisitCount);
                return tabVisitCount;
        }

         return 0;
    }

    private void initialzeBannerAds(){
        final AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void initialzeInterstitialAds(){

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_unit_id_prod));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

}
