package com.example.shopvaycuoi.model;

public class Products {
    public int id;
    public String name;
    public String image_link;
    public float price;
    public String size;
    public String color;
    public int quantity;
    public int catego_id;
    public Products(int id, String name, String image_link, float price, String size, String color, int quantity, int catego_id) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.catego_id = catego_id;
    }
    public Products(String name, String image_link, float price, String size, String color, Integer quantity) {
        this.name = name;
       this.image_link = image_link;
       this.price = price;
       this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public Products(Integer id, String name, String image_link, float price, String size, String color, int quantity) {
        this.id=id;
        this.name = name;
        this.image_link = image_link;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public Products(String name, String image_link, float price, String size, String color, Integer quantity, Integer catego_id) {
        this.name = name;
        this.image_link = image_link;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.catego_id=catego_id;
    }
    //public int catego_id;

//    public Products(int id, String name, String image_link, float price, String size, String color, int quantity,int catego_id) {
//        this.id = id;
//        this.name = name;
//        this.image_link = image_link;
//        this.price = price;
//        this.size = size;
//        this.color = color;
//        this.quantity = quantity;
//        this.catego_id=catego_id;
//    }





    public int getId() {
        return id;
    }

    public void setId(int name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image_link;
    }

    public void setImage(String image_link) {
        this.image_link = image_link;
    }
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getCatego_id() {
        return catego_id;
    }

    public void setCatego_id(int catego_id) {
        this.catego_id = catego_id;
    }
}
