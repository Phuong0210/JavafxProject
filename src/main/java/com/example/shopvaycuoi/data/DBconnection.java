package com.example.shopvaycuoi.data;

import com.example.shopvaycuoi.model.Admin;
import com.example.shopvaycuoi.model.Products;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBconnection {
    public Connection con;

    public DBconnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/shopvaycuoi", "root", "");
            System.out.println("Successful");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }
    }
    public ArrayList<Products> getProducts() {
        ArrayList<Products> list = new ArrayList();
        String sql = "SELECT * FROM product";

        try {
            ResultSet result = this.con.prepareStatement(sql).executeQuery();

            while(result.next()) {
                System.out.println("id: " + result.getInt("id"));
                System.out.println("name: " + result.getString("name"));
                System.out.println("image_link: " + result.getString("image_link"));
                System.out.println("price: " + result.getFloat("price"));
                System.out.println("size: " + result.getString("size"));
                System.out.println("color: " + result.getString("color"));
                System.out.println("quantity: " + result.getInt("quantity"));
                System.out.println("catego_id: " + result.getInt("catego_id"));
                Products product = new Products(result.getInt("id"),result.getString("name"), result.getString("image_link"), result.getFloat("price"), result.getString("size"), result.getString("color"), result.getInt("quantity"), result.getInt("catego_id"));
                list.add(product);
            }

            return list;
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }
    public void insertProducts(Products st){
        String sql = "INSERT INTO product (name, image_link,price, size, color,  quantity,catego_id) VALUE ('"+st.name+"','"+st.image_link+"',"+st.price+",'"+st.size+"','"+st.color+"',"+st.quantity+"," + st.catego_id +" )";
        System.out.println(sql);
        try {
            con.prepareStatement(sql).executeUpdate();
            System.out.println("insert");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProducts(Products st) {
        String sql = "UPDATE product SET name ='" + st.name + "', image_link ='" + st.image_link + "',price=" + st.price + ", size='" + st.size + "',color='" + st.color + "',  quantity=" + st.quantity + ", catego_id=" + st.catego_id +"  WHERE id = " + st.id;
        System.out.println(sql);

        try {
            this.con.prepareStatement(sql).executeUpdate();
            System.out.println("Update successfully");
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }
    public void deleteProducts(int id) {
        String sql = "DELETE FROM product WHERE id = " + id;
        System.out.println(sql);

        try {
            this.con.prepareStatement(sql).executeUpdate();
            System.out.println("DELETE successfully");
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    public ArrayList<Admin> getAdmin(){
        ArrayList<Admin> Admin = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        try {
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("pass"));
                Admin ad = new Admin(
                        rs.getString("name"),
                        rs.getString("pass")
                );
                Admin.add(ad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Admin;
    }

}