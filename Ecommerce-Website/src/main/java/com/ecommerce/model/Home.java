package com.ecommerce.model;

import java.util.List;

import lombok.Data;

@Data
public class Home {
private List<HomeCategory> electricCategories;
private List<HomeCategory> dealCategories;
private List<HomeCategory> shopCategories;
private List<HomeCategory> gridCategories;
private List<Deal>deals;
}
