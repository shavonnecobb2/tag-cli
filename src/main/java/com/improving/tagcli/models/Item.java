package com.improving.tagcli.models;

public class Item {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item() {}
    public Item(String name) {
        this.name = name;
    }
}
