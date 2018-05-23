package com.karayelli.alican.calorietable.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.karayelli.alican.calorietable.R;
import com.karayelli.alican.calorietable.data.local.food.Food;
import com.karayelli.alican.calorietable.model.TabItemUIModel;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements Filterable {

    private List<TabItemUIModel> mItems;
    private Context mContext;
    private CalorieTableActivity.FoodItemListener mFoodItemListener;
    private FoodFilter filter;

    public RecycleAdapter(List<TabItemUIModel> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txt.setText(mItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void replaceData(List<TabItemUIModel> items){
        setItems(items);
        notifyDataSetChanged();
    }

    public void setItems(List<TabItemUIModel> items) {
        this.mItems = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt;

        public ViewHolder(final View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
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