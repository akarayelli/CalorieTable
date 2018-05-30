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
import android.widget.Toast;

import com.karayelli.alican.calorietable.BaseActivity;
import com.karayelli.alican.calorietable.Injection;
import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.model.TabItemUIModel;
import com.karayelli.alican.calorietable.model.TabUIModel;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import timber.log.Timber;


public class CalorieTableActivity extends BaseActivity implements CalorieTableContract.View {


    private CalorieTableContract.Presenter mPresenter;
    private List<RecycleAdapter> mAdapterList;
    private NavigationTabBar mNavigationTabBar;

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
        mAdapterList = new ArrayList<>((tabUIModels.size() == 0 ? 0 : tabUIModels.size() + 1));

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


                if(position == 0){
                    Timber.d("Will initialize first list (FAVORITE)...");

                    RecycleAdapter adapter = new RecycleAdapter(favoriteItemList, CalorieTableActivity.this, mItemListener);
                    recyclerView.setAdapter(adapter);
                    mAdapterList.add(position,adapter);

                }else{
                    Timber.d("Will initialize food category list...");
                    RecycleAdapter adapter = new  RecycleAdapter(tabUIModels.get(position -1 ).getTabItemUIModels(), CalorieTableActivity.this, mItemListener);
                    recyclerView.setAdapter(adapter);
                    mAdapterList.add(position,adapter);

                }
                //************************************************************



                //************************* FILTERABLE SEARCH ****************
                final SearchView searchView = view.findViewById(R.id.mSearch);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        RecycleAdapter adapter = mAdapterList.get(position);
                        adapter.getFilter().filter(query);
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

        for(TabUIModel tabUIModel : tabUIModels){
            models.add(
                    new NavigationTabBar.Model.Builder(
                            getResources().getDrawable(tabUIModel.getTabIcon()),
                            Color.parseColor(tabUIModel.getTabColor()))
                            .title(tabUIModel.getTitle())
                            .build()
            );
        }

        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setViewPager(viewPager, 0);

        //****************************************************************************


        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        mNavigationTabBar.setBehaviorEnabled(true);

        /*
        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        */

        mNavigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

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
            public void onPageScrollStateChanged(final int state) {

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
        }

        @Override
        public void onFoodRemovedFromFavorite(TabItemUIModel item) {
            Timber.d("Food item: " + item.getTitle() + " clicked to remove from favorite!");
            mPresenter.removeFoodFromFavorite(item);
        }
    };


    @Override
    public void markFoodAsFavorite(String id) {

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
        Toast.makeText(this, "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessfullyAddedToFavoriteMessage() {

        mPresenter.loadFoodTypes();
        Toast.makeText(this, getString(R.string.successfully_added_favorite), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessfullyRemovedFromFavoriteMessage() {
        //mPresenter.loadFoodTypes();

        mNavigationTabBar.deselect();
        Toast.makeText(this, getString(R.string.successfully_removed_favorite), Toast.LENGTH_LONG).show();
    }

    public interface FoodItemListener {
        void onFoodClick(TabItemUIModel item);
        void onFoodMarkedAsFavorite(TabItemUIModel item);
        void onFoodRemovedFromFavorite(TabItemUIModel item);
    }
}
