package com.android.home.util;

/**
 * Created by PengYue on 2016/6/17.
 */
public class GridItem {
    private String title; //文字
    private String description;//文字
    private int magerId; //图片

    public GridItem() {

    }

    public GridItem(String title, String description, int magerId) {
        this.title = title;
        this.description = description;
        this.magerId = magerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMagerId() {
        return magerId;
    }

    public void setMagerId(int magerId) {
        this.magerId = magerId;
    }
}
