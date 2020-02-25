package com.ecommerce.shopping.store;

public enum ItemName {
    DOVE("Dove"),
    AXE_DEO("Axe Deo");

    private String itemName;

    ItemName(String itemName) {
        this.itemName = itemName;
    }

    public static ItemName getItem(final String name) {
        for (ItemName value : values()) {
            if (value.itemName.equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getItemName() {
        return itemName;
    }
}