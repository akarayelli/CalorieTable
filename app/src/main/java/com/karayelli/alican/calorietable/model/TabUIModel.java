package com.karayelli.alican.calorietable.model;

import java.util.List;
import java.util.Locale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TabUIModel {

    private String id;
    private String titleTR;
    private String titleEN;
    private String tabColor;
    private int tabIcon;
    private int collapseImage;
    private List<TabItemUIModel> tabItemUIModels;


    public String getTitle(){

        String language = Locale.getDefault().getLanguage();

        if(language.equals( "tr")){
            return this.titleTR;
        }else{
            return this.titleEN;
        }
    }


}
