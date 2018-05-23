package com.karayelli.alican.calorietable.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TabUIModel {

    private String id;
    private String title;
    private String tabColor;
    private int tabIcon;
    private int collapseImage;
    private List<TabItemUIModel> tabItemUIModels;



}
