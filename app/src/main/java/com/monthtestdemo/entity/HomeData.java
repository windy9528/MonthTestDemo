package com.monthtestdemo.entity;

import java.util.List;

/**
 * date:2019/7/27
 * name:windy
 * function:
 */
public class HomeData {

    private int id;
    private String name;
    private List<HomeGoods> commodityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HomeGoods> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<HomeGoods> commodityList) {
        this.commodityList = commodityList;
    }
}
