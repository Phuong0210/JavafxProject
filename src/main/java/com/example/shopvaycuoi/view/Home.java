package com.example.shopvaycuoi.view;

import com.example.shopvaycuoi.Controller.ShopLyly;
import com.example.shopvaycuoi.data.DBconnection;
import com.example.shopvaycuoi.model.Products;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Home extends Application {
public Scene scene_H;
private  Stage primaryStage;
    private javafx.scene.Scene Scene;

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene_H = new Scene(loadScreenOne(primaryStage), 200, 200);
        stage.setScene(scene_H);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public VBox loadScreenOne(Stage stage)
    {  //vP.getChildren().clear();
        VBox vBox = new VBox();
        vBox.getChildren().clear();
        vBox.setAlignment(Pos.CENTER);
        final Button button = new Button("Switch Screen");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Stage primaryStage = new Stage();
                button.getScene().setRoot(loadScreenTwo(primaryStage));
            }
        });
        Text text = new Text("Screen One");
        vBox.getChildren().addAll(text, button);
        vBox.setStyle("-fx-background-color: #8fbc8f;");
        return vBox;
    }

    public VBox loadScreenTwo(Stage primaryStage) {

        VBox vBox = new VBox();
        vBox.getChildren().clear();
        Stage Lyly =new Stage();
        ShopLyly P = new ShopLyly();
        Button button1 = new Button("MAIN HOME");
        button1.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
        button1.setPrefSize(100, 100);
        button1.setOnAction(e -> {
            primaryStage.setScene(P.renderMainboard(primaryStage,Lyly));
        });

        vBox.getChildren().add(button1);
        DBconnection DB = new DBconnection();
        ArrayList<Products> productsList = DB.getProducts();

        for (int i = 0; i < productsList.size(); i++) {
            HBox row = new HBox();
            vBox.setSpacing(10);

            Image image = new Image(productsList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);
            vBox.getChildren().addAll(imageView);
            // Label
            Label name = new Label("Name: " + productsList.get(i).getName());
            name.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            name.setPrefSize(400, 400);
            vBox.getChildren().addAll(name);
            ///
            Label price = new Label("Price: " + productsList.get(i).getPrice());
            price.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            price.setPrefSize(400, 400);
            vBox.getChildren().addAll(price);
            ///
            Label size = new Label("Size: " + productsList.get(i).getSize());
            size.setStyle("-fx-font-size: 11px;-fx-font-weight: bold;" + "-fx-text-fill: #5F9EA0;" + "-fx-font-family: helvetica, arial, sans-serif");
            size.setPrefSize(400, 400);
            vBox.getChildren().addAll(size);
///
            Label color = new Label("Color: " + productsList.get(i).getColor());
            color.setStyle("-fx-font-size: 11px;-fx-font-weight: bold;" + "-fx-text-fill: #5F9EA0;" + "-fx-font-family: helvetica, arial, sans-serif");
            color.setPrefSize(400, 400);
            vBox.getChildren().addAll(color);
            ///
            Button button2 = new Button("Đặt ngay");
            button2.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;" + "-fx-text-fill: #3b5998;" + "-fx-font-family: helvetica, arial, sans-serif");
            button2.setPrefSize(100, 100);
            vBox.getChildren().add(button2);
            //
            row.getChildren().addAll();
            Scene scene_H1 = new Scene(vBox, 550, 750);
            primaryStage.setTitle("All products");
            primaryStage.setScene(scene_H1);
            primaryStage.show();
        }

//            vBox.setAlignment(Pos.CENTER);
//            final Button button = new Button("Back");
//            button.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent arg0) {
//                    button.getScene().setRoot(loadScreenOne(primaryStage));
//                }
//            });
//
//
//            Text text = new Text("Screen Two");
//            vBox.getChildren().addAll(text, button);

            return vBox;
        }


//Met
    }