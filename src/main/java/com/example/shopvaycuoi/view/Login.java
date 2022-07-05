package com.example.shopvaycuoi.view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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

import static javafx.application.Application.launch;

public class Login extends Application {
    TextField name, pass;
    public Scene screenLogin,homepages;


    private Scene scene,admin1;
    Button button;
    private int Admin;


    @Override
    public void start(Stage stage)  {

        DBconnection DB = new DBconnection();
        // LOGIN

        VBox loginPage = new VBox();
        this.showLogin(loginPage, DB,stage);
        screenLogin = new Scene(loginPage, 400, 400);

//homepage
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        VBox vBoxhompage = new VBox();

//        homepage
        vBoxhompage.setPadding(new Insets(150));
        this.getDisplayProducts(grid,DB, stage);
        vBoxhompage.getChildren().add(grid);

        // WELCOME LYLY SHOP
        homepages = new Scene(vBoxhompage, 1000, 1000);



        stage.setScene(screenLogin);
        stage.show();

    }

    void  showLogin(VBox loginPage , DBconnection db,Stage stage){
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
        Button btnGoBack = new Button("Register");
        btnGoBack.setOnAction(actionEvent -> {
            stage.setScene(screenLogin);
        });
        Button btnLogin = new Button("LOGIN");
        btnLogin.setOnAction(actionEvent -> {
            this.checkLogin(db,stage);
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin,btnGoBack);
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);
    }
    void checkLogin(DBconnection db,Stage stage){
        ArrayList<Admin> ad = new ArrayList<>(Admin);
        ad = (ArrayList<Admin>) db.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(ad.get(0).name)&& inputPass.equals(ad.get(0).pass)){
            System.out.println("success!");
            LoginSuccess();

            stage.setScene(homepages);
            //stage.show();

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
    }

    private void getDisplayProducts(GridPane grid, DBconnection DB, Stage stage) {
        grid.getChildren().clear();
        grid.add(new Label("Name"), 1, 0);

        //
        grid.add(new Label("Image"), 3, 0);

        //
        grid.add(new Label("Price"), 5, 0);

        //
        grid.add(new Label("Size"), 7, 0);

        //
        grid.add(new Label("Color"), 9, 0);

        //
        grid.add(new Label("Quantity"), 11, 0);

        ArrayList<Products> productsList = DB.getProducts();

        for (int i = 0; i < productsList.size(); i++) {

            Image image = new Image(productsList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label(productsList.get(i).getName()), 1, i + 2);
            grid.add(imageView, 3, i + 2);
            grid.add(new Label(String.valueOf(productsList.get(i).getPrice() + "VND")), 5, i + 2);
            grid.add(new Label(productsList.get(i).getSize()), 7, i + 2);
            grid.add(new Label(productsList.get(i).getColor()), 9, i + 2);
            grid.add(new Label(String.valueOf(productsList.get(i).getQuantity())), 11, i + 2);

        }
        VBox vboxGrid = new VBox();
        butBacks(stage,grid,vboxGrid);
        Scene scene = new Scene(vboxGrid, 900, 600);
        stage.setTitle("Shop váy cưới");
        stage.setScene(scene);
        stage.show();
    }
    void butBacks(Stage stage, GridPane grid,VBox vboxGrid){
        Button btnBack = new Button("Back");

        vboxGrid.getChildren().addAll(grid,btnBack);

        btnBack.setOnAction(e -> {
            Scene admin;
            stage.setScene(admin1);
            System.out.println("Go back");
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
