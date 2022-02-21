package com.company.CostlyObjects;

import com.company.Item.ItemsList;

public class CostlyObjectsPool extends ObjectPool<ItemsList> {
    /* Constructor */
    public CostlyObjectsPool() {}

    /* Methods */
    @Override
    public ItemsList create() {
        return new ItemsList();
    }

    @Override
    boolean validate(ItemsList o) {
        return o.isNull();
    }

    @Override
    void dead(ItemsList o) {
        /*  Don`t need to free due to Java Garbage Collector  */
    }
}
