package com.example.shopvaycuoi.model;

public class Products {
    public int id;
    public String name;
    public String image_link;
    public Float price;
    public String size;
    public String color;
    public int quantity;

    public Products(int id, String name, String image_link, Float price, String size, String color, int quantity) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }


    public Products(String txtName, String txtImage, Float ftPrice, String txtSize, String txtColor, int txtQuantity) {
    }
}
