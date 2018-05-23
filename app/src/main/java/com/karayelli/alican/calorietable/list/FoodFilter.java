package com.karayelli.alican.calorietable.list;


import android.widget.Filter;

import com.karayelli.alican.calorietable.model.TabItemUIModel;

import java.util.ArrayList;
import java.util.List;

public class FoodFilter extends Filter{

    RecycleAdapter adapter;
    List<TabItemUIModel> filterList;

    public FoodFilter(List<TabItemUIModel> filterList, RecycleAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<TabItemUIModel> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setItems((ArrayList<TabItemUIModel>) results.values);

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}