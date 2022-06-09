package com.example.shopvaycuoi;

import java.util.ArrayList;
import java.util.Optional;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class HelloApplication extends Application {


    Button button;
    public static void main(String[] args) {
        launch(args);
    }
    private Scene scene;
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBconnection DB = new DBconnection();

        stage.setTitle("Alert");
        button = new Button();
        button.setText("Wellcome to Lyly@Shop");
        button.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn có chắc chắn muốn khám phá cửa mình không ạ?");
            alert.setContentText("Choose your option");

            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType( "Cancel", ButtonBar. ButtonData. CANCEL_CLOSE);
            alert.getButtonTypes (). setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();



            if (result.get() == buttonTypeYes)
            getDisplayProducts(grid,DB, stage);
            else if (result.get().getButtonData() == ButtonBar.ButtonData.NO)
                System.out.println("NOOOOOOOOOOOOOOOOOOOOOO");
            else
                System.out.println("CANCELLLLLLLLLLLLL");

            String message = result.get().getText();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Cảm ơn bạn đã ghé cửa hàng nhà Lyly");
            alert1.setContentText(message);
            alert1.show();
        });
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();

    }


//Hiện thị các sản phẩm

    private void getDisplayProducts(GridPane grid, DBconnection DB, Stage stage) {

        grid.add(new Label("Name"), 1, 0);

        //
        grid.add(new Label("Image"), 3, 0);

        //
        grid.add(new Label("Price"), 5, 0);

        //
        grid.add(new Label("Size"),7,  0);

        //
        grid.add(new Label("Color"),9,  0);

        //
        grid.add(new Label("Quantity"),11,  0);

        ArrayList<Products> productsList = DB.getProducts();

        for(int i = 0; i < productsList.size(); i++) {

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


        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        //alert.setContentText("Bạn phải điền đầy đủ thông tin");
        alert.showAndWait();

    };


    scene = new Scene(grid, 900, 600);
        stage.setTitle("Shop váy cưới");
        stage.setScene(scene);
        stage.show();
}}