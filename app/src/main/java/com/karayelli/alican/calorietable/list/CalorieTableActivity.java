package com.karayelli.alican.calorietable.list;

import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.karayelli.alican.calorietable.BaseActivity;
import com.karayelli.alican.calorietable.Injection;
import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.data.food.Food;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import timber.log.Timber;


public class CalorieTableActivity extends BaseActivity implements CalorieTableContract.View {


    private CalorieTableContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new CalorieTablePresenter(Injection.provideFoodTypesDataSource(getApplicationContext()), Injection.provideFoodDataSource(getApplicationContext()), this);

        initUI(new ArrayList<TabUIModel>(), new ArrayList<TabItemUIModel>());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }


    private void initUI(final List<TabUIModel> tabUIModels, final List<TabItemUIModel> favoriteItemList) {

        // ***************  VIEW PAGER **********************
        final ViewPager viewPager = findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return (tabUIModels.size() == 0 ? 0 : tabUIModels.size() + 1);
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
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp_list, null, false);


                // ****************** RECYCLE VIEW *************************
                final RecyclerView recyclerView = view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));

                RecycleAdapter adapter = null;

                if(position == 0){
                    Timber.d("Will initialize first list (FAVORITE)...");
                    adapter = new RecycleAdapter(favoriteItemList, CalorieTableActivity.this);
                    recyclerView.setAdapter(adapter);
                }else{
                    Timber.d("Will initialize food category list...");
                    adapter = new RecycleAdapter(tabUIModels.get(position -1 ).getTabItemUIModels(), CalorieTableActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                //************************************************************



                //************************* FILTERABLE SEARCH ****************
                final SearchView searchView = view.findViewById(R.id.mSearch);
                final RecycleAdapter finalAdapter = adapter;
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        finalAdapter.getFilter().filter(query);
                        return false;
                    }
                });


                EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchEditText.setTextColor(getResources().getColor(R.color.white));
                searchEditText.setHintTextColor(getResources().getColor(R.color.colorAccent));
                // ***********************************************************



                container.addView(view);
                return view;
            }
        });


        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();


        // *************** Favorite Tab ************
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icn_favorite),
                        getResources().getColor(R.color.favoriteTabColor))
                        .title(getString(R.string.favorite_tab_title))
                        .build()
        );

        for(TabUIModel tabUIModel : tabUIModels){
            models.add(
                    new NavigationTabBar.Model.Builder(
                            getResources().getDrawable(tabUIModel.getTabIcon()),
                            Color.parseColor(tabUIModel.getTabColor()))
                            .title(tabUIModel.getTitle())
                            .build()
            );
        }

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);

        //****************************************************************************


        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

                ImageView backdropImageView = findViewById(R.id.backdrop);


                tabUIModels.get(position).getTabColor()
                final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar);
                collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));

                if(position == 0){
                    Timber.d("Will initialize first tab (FAVORITE)...");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                    backdropImageView.setImageResource(R.drawable.sptlight_favorite);
                    collapsingToolbarLayout.setTitle(getString(R.string.favorite_tab_title));
                }else{
                    Timber.d("Will initialize food category tab...");
                    TabUIModel tabUIModel = tabUIModels.get(position -1);
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                    backdropImageView.setImageResource(tabUIModel.getCollapseImage());
                    collapsingToolbarLayout.setTitle(tabUIModel.getTitle());
                }
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

    }




    @Override
    public void setLoadingIndicator(boolean active) {
    }

    @Override
    public void showCalorieTable(List<TabUIModel> tabUIModels, List<TabItemUIModel> favoriteTabData) {
        this.initUI(tabUIModels, favoriteTabData);
    }

    @Override
    public boolean isActive() {
        return this.isActivityRunning(this.getClass());
    }

    @Override
    public void showFoodDetail(String id) {

    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    public interface FoodItemListener {
        void onFoodClick(Food clickedFood);
    }
}
