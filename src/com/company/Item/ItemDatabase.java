package com.company.Item;

import com.company.Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/* Hold item database */
public class ItemDatabase {
    /* Fields */
    private List<Item> itemList = new ArrayList<>();

    public ItemDatabase(){}

    /* Getters & setters */
    public List<Item> getItemList() {
        return this.itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /* Methods */
    public void addItem(Item item) {
        this.itemList.add(item);
    }
}
