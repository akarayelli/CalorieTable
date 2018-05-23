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
import com.karayelli.alican.calorietable.data.local.food.Food;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;
import com.karayelli.alican.calorietable.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;


public class CalorieTableActivity extends BaseActivity implements CalorieTableContract.View {


    private CalorieTableContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataUtil dataUtil = new DataUtil(getApplicationContext());
        dataUtil.createFoodType();

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
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
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
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp_list, null, false);


                // ****************** RECYCLEVIEW *************************
                final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(
                                getBaseContext(), LinearLayoutManager.VERTICAL, false
                        )
                );

                RecycleAdapter adapter = null;

                if(position == 0){
                    adapter = new RecycleAdapter(favoriteItemList, CalorieTableActivity.this);
                    recyclerView.setAdapter(adapter);
                }else{
                    adapter = new RecycleAdapter(tabUIModels.get(position -1 ).getTabItemUIModels(), CalorieTableActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                //************************************************************

                //************************* FILTERABLE SEARCH ****************
                final SearchView searchView = (SearchView) view.findViewById(R.id.mSearch);
                final RecycleAdapter finalAdapter = adapter;
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        //FILTER AS YOU TYPE
                        finalAdapter.getFilter().filter(query);
                        return false;
                    }
                });


                EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchEditText.setTextColor(getResources().getColor(R.color.white));
                searchEditText.setHintTextColor(getResources().getColor(R.color.colorAccent));

                // ***********************************************************

                container.addView(view);
                return view;
            }
        });


        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();


        // *************** Favorite Tab ************
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("Favoriler")
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



                ImageView backdropImageView = (ImageView)findViewById(R.id.backdrop);
                backdropImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


                final CollapsingToolbarLayout collapsingToolbarLayout =
                        (CollapsingToolbarLayout) findViewById(R.id.toolbar);
                collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#009F90AF"));
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#9f90af"));

                if(position == 0){
                    backdropImageView.setImageResource(R.drawable.favorite);
                    collapsingToolbarLayout.setTitle("Favoriler");

                }else{
                    TabUIModel tabUIModel = tabUIModels.get(position -1);
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
