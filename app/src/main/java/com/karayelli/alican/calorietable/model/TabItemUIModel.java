package com.karayelli.alican.calorietable.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TabItemUIModel {

    private String id;
    private String title;
    private String calorieValue;


}
