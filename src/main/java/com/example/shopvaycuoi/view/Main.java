package com.example.shopvaycuoi.view;


import com.example.shopvaycuoi.data.DBconnection;
import com.example.shopvaycuoi.model.Admin;
import com.example.shopvaycuoi.model.Products;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {
    public Stage window;
    public  GridPane grid;
    TextField name, pass;
    VBox homep;
    public Scene login,admin,homepage;
    final DBconnection con = new DBconnection();
    private static final String EMPTY = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        grid = new GridPane();
        admin = new Scene(grid, 1250, 600);
        homep= new VBox();

        homepage = new Scene(homep, 1250, 600);
        VBox layoutadmin = new VBox();
        showLogin(layoutadmin);
        login = new Scene(layoutadmin,500,600);
        window.setScene(login);
        window.show();

    }
    void  showLogin(VBox loginPage){
        loginPage.getChildren().clear();
        Label labelLogin =new Label("LOGIN");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        name = new TextField();
        pass = new TextField();
        HBox fieldName = new HBox();
        fieldName.getChildren().addAll(Aname,name);
        fieldName.setSpacing(10);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,pass);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin();
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin);
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);
    }
    void checkLogin(){
        ArrayList<com.example.shopvaycuoi.model.Admin> adminList = con.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(adminList.get(0).name)&& inputPass.equals(adminList.get(0).pass)){
            System.out.println("success!");
            LoginSuccess();

        }else{
            LoginError();
        }
    }

    void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail!");
        alert.show();
    }
    void LoginSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+name.getText());
        alert.setContentText("Login successfully!");
        alert.show();
        AdminShow();
//
    }
    void AdminShow(){

        grid.getChildren().clear();
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> {
            name.setText("");
            pass.setText("");
            window.setScene(login);
            System.out.println("Go back");
        });

        Button btnHOmepage = new Button("Go to home page");
        btnHOmepage.setOnAction(e -> {

            showHome();
            System.out.println("Go to home page");
        });
        grid.add(btnBack,0,8);
        grid.add(btnHOmepage,0,9);
        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Image:"), 1, 0);
        var tfImage = new TextField();
        grid.add(tfImage, 1, 1);
        //
        grid.add(new Label("Price:"), 2, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 2, 1);
        //
        grid.add(new Label("Size:"), 3, 0);
        var tfSize = new TextField();
        grid.add(tfSize, 3, 1);
        //
        grid.add(new Label("Color:"), 4, 0);
        var tfColor = new TextField();
        grid.add(tfColor, 4, 1);
        //
        grid.add(new Label("Quantity:"), 5, 0);
        var tfQuantity = new TextField();
        grid.add(tfQuantity, 5, 1);
//
        grid.add(new Label("Catego_id:"), 6, 0);
        var tfcatego_id = new TextField();
        grid.add(tfcatego_id, 6, 1);
        // Thêm các sản phẩm mới
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String image_link = tfImage.getText();
            float price = Float.valueOf(tfPrice.getText());
            String size = tfSize.getText();
            String color = tfColor.getText();
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            Integer catego_id = Integer.valueOf(tfcatego_id.getText());
            if (!name.equals(EMPTY) && !image_link.equals(EMPTY) && !Objects.equals(price, EMPTY) && !size.equals(EMPTY) && !color.equals(EMPTY) && !quantity.equals(EMPTY) && !catego_id.equals(EMPTY)) {
                con.insertProducts(new Products(name, image_link, price, size, color, quantity, catego_id));
                try {
                    AdminShow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bạn phải điền đầy đủ thông tin");
            alert.showAndWait();
        });
        grid.add(btnAdd, 7, 1);

        //Hiện thị các sản phẩm

        ArrayList<Products> productsList = con.getProducts();

        for (int i = 0; i < productsList.size(); i++) {

            Image image = new Image(productsList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label(productsList.get(i).getName()), 0, i + 2);
            grid.add(imageView, 1, i + 2);
            grid.add(new Label(String.valueOf(productsList.get(i).getPrice() + "VND")), 2, i + 2);
            grid.add(new Label(productsList.get(i).getSize()), 3, i + 2);
            grid.add(new Label(productsList.get(i).getColor()), 4, i + 2);
            grid.add(new Label(String.valueOf(productsList.get(i).getQuantity())), 5, i + 2);
            grid.add(new Label(String.valueOf(productsList.get(i).getCatego_id())), 6, i + 2);


            // Cập nhật sản phẩm đã có trong của hàng( sửa lại thông tin sp)
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                tfName.setText("" + productsList.get(id1).getName());
                tfImage.setText("" + productsList.get(id1).getImage());
                tfPrice.setText("" + productsList.get(id1).getPrice());
                tfSize.setText("" + productsList.get(id1).getSize());
                tfColor.setText("" + productsList.get(id1).getColor());
                tfQuantity.setText("" + productsList.get(id1).getQuantity());
                tfcatego_id.setText("" + productsList.get(id1).getCatego_id());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(newe -> {
                    Integer id = productsList.get(id1).id;
                    String name = tfName.getText();
                    String image_link = tfImage.getText();
                    float price = Float.valueOf(tfPrice.getText());
                    String size = tfSize.getText();
                    String color = tfColor.getText();
                    int quantity = Integer.valueOf(tfQuantity.getText());
                    int catego_id = Integer.valueOf(tfcatego_id.getText());
                    if (!name.equals(EMPTY) && !image_link.equals(EMPTY) && !Objects.equals(price, EMPTY) && !size.equals(EMPTY) && !color.equals(EMPTY) && !Objects.equals(quantity, EMPTY) && !Objects.equals(catego_id, EMPTY)) {
                        con.updateProducts(new Products(id, name, image_link, price, size, color, quantity, catego_id));
                        try {
                            AdminShow();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn phải điền đầy đủ thông tin");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 7, 1);
            });
            grid.add(btnUpdate, 7, i + 2);

            // Xóa sản phẩm không còn bán
            var btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(productsList.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                con.deleteProducts(id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    AdminShow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 8, i + 2);
        }


        window.setTitle("Shop váy cưới");
        window.setScene(admin);
        window.show();
    }

    void showHome() {

        grid.getChildren().clear();
//        Button btnGoadmin = new Button("Go to admin");
//        btnGoadmin.setOnAction(e -> {
//            AdminShow();
//        });
//        grid.getChildren().addAll(btnGoadmin);
        ArrayList<Products> productsList = con.getProducts();
        int ygrid =0;
        int xgrid = 0;
        for(int i = 0; i < productsList.size(); i++){

            VBox vBox = new VBox();
            Image image = new Image(productsList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);


            Label name = new Label("Name: " + productsList.get(i).getName());
            name.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            name.setPrefSize(400, 400);

            Label price = new Label("Price: " + productsList.get(i).getPrice());
            price.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            price.setPrefSize(400, 400);

            Label size = new Label("Size: " + productsList.get(i).getSize());
            size.setStyle("-fx-font-size: 11px;-fx-font-weight: bold;" + "-fx-text-fill: #5F9EA0;" + "-fx-font-family: helvetica, arial, sans-serif");
            size.setPrefSize(400, 400);

            Label color = new Label("Color: " + productsList.get(i).getColor());
            color.setStyle("-fx-font-size: 11px;-fx-font-weight: bold;" + "-fx-text-fill: #5F9EA0;" + "-fx-font-family: helvetica, arial, sans-serif");
            color.setPrefSize(400, 400);

            Button button2 = new Button("Đặt ngay");
            button2.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            button2.setPrefSize(100, 100);

            name.setPadding(new Insets(2));
            price.setPadding(new Insets(2));
            size.setPadding(new Insets(2));
            color.setPadding(new Insets(2));

            vBox.getChildren().addAll(imageView,name,price, size,color,button2);
            vBox.setSpacing(10);
            grid.add((vBox), xgrid, ygrid);
//            window.setTitle("All products");
//            window.setScene(homepage);
//            window.show();
            xgrid +=1;
            if( xgrid == 2){
                xgrid = 0;
                ygrid +=1;
            }
        }
    }
}
