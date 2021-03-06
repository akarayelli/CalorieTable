package com.karayelli.alican.calorietable.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.model.TabItemUIModel;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<TabItemUIModel> mItems;
    private Context mContext;
    private CalorieTableActivity.FoodItemListener mFoodItemListener;
    private FoodFilter filter;

    public RecycleAdapter(List<TabItemUIModel> mItems, Context mContext, CalorieTableActivity.FoodItemListener itemListener) {
        this.mItems = mItems;
        this.mContext = mContext;
        this.mFoodItemListener = itemListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

            final View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            ViewHolder hld = (ViewHolder) holder;

            final TabItemUIModel tabItemUIModel = mItems.get(position);

            hld.txt.setText(tabItemUIModel.getTitle());
            hld.calorieTxt.setText(tabItemUIModel.getCalorieValue() + " kCal");

            hld.imgBtn.setImageResource(tabItemUIModel.getIsFavorite() ? R.drawable.icn_favorite : R.drawable.icn_add_favorite);
            hld.imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (tabItemUIModel.getIsFavorite()) {
                        hld.imgBtn.setImageResource(R.drawable.icn_add_favorite);
                        mFoodItemListener.onFoodRemovedFromFavorite(tabItemUIModel);
                    } else {
                        hld.imgBtn.setImageResource(R.drawable.icn_favorite);
                        mFoodItemListener.onFoodMarkedAsFavorite(tabItemUIModel);
                    }
                }
            });
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }



    public void replaceData(List<TabItemUIModel> items){
        setItems(items);
        notifyDataSetChanged();
    }

    public void setItems(List<TabItemUIModel> items) {
        this.mItems = items;
    }


    private class AdRecyclerHolder extends RecyclerView.ViewHolder {

        public AdView adView;

        public AdRecyclerHolder(View inflate) {
            super(inflate);
            adView = inflate.findViewById(R.id.adView);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt;
        public TextView calorieTxt;
        public ImageButton imgBtn;

        public ViewHolder(final View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
            imgBtn = itemView.findViewById(R.id.favorite_btn);
            calorieTxt = itemView.findViewById(R.id.calorie_value);
        }
    }


    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new FoodFilter(mItems, this);
        }
        return filter;
    }

}