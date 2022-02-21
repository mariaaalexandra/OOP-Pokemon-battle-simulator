package com.company.Item;

import com.company.Pokemon.Attack;
import com.company.Pokemon.Defense;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsList {
    /* Fields */
    private List<Item> my_items = null;

    /* Constructor */
    public ItemsList() {
        my_items = new ArrayList<>();

        my_items.add(new Item("Scut", null, new Defense(2, 2), new Attack(null, null)));
        my_items.add(new Item("Vestă", 10, new Defense(null, null), new Attack(null, null)));
        my_items.add(new Item("Săbiuță", null, new Defense(null, null), new Attack(3, null)));
        my_items.add(new Item("Baghetă Magică", null, new Defense(null, null), new Attack(null, 3)));
        my_items.add(new Item("Vitamine", 2, new Defense(null, null), new Attack(2, 2)));
        my_items.add(new Item("Brad de Crăciun", null, new Defense(1, null), new Attack(3, null)));
        my_items.add(new Item("Pelerină", null, new Defense(null, 3), new Attack(null, null)));
    }

    /* Methods */
    /* Extract random item */
    public Item extractItem () {
        Integer random_index = new Random().nextInt(my_items.size());
        Item keep_item = my_items.get(random_index);
        my_items.remove(random_index);
        return keep_item;
    }

    /* Check if the list exists */
    public Boolean isNull() {
        return this.my_items.size() == 0;
    }

    /* Get items */
    public List<Item> getItems() {
        return this.my_items;
    }
}
