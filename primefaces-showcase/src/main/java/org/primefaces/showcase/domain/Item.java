package org.primefaces.showcase.domain;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable, Comparable<Item> {

    private int id;
    private String category;
    private String name;
    private double price;
    private int quantity;

    public Item(int id, String category, String name, double price, int quantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter and Setter for all properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public int compareTo(Item o) {
        if (o == null || this.category == null) {
            return 1;
        }
        if (o.category == null) {
            return -1;
        }
        int result = this.category.compareTo(o.category);
        if (result == 0) {
            if (o.name == null || this.name == null) {
                return this.name == null ? (o.name == null ? 0 : 1) : -1;
            }
            result = this.name.compareTo(o.name);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
