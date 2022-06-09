package com.example.shopvaycuoi;

import com.example.shopvaycuoi.data.DBconnection;
import com.example.shopvaycuoi.model.Products;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;
public class MainShopvaycuoi extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene;
    private static final String EMPTY = "";

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBconnection DB = new DBconnection();
        ArrayList<Products> productsList = DB.getProducts();
//        DB.insertStudent(new Student("LyLy",8));
//        DB.updateStudent(new Student(2,"Olaha",6));
//        DB.deleteStudent(1);
//        DB.getStudents();

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
        grid.add(new Label("Size:"),3,  0);
        var tfSize= new TextField();
        grid.add(tfSize, 3, 1);
        //
        grid.add(new Label("Color:"),4,  0);
        var tfColor= new TextField();
        grid.add(tfColor, 4, 1);
        //
        grid.add(new Label("Quantity:"),5,  0);
        var tfQuantity= new TextField();
        grid.add(tfQuantity, 5, 1);
        // add
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String image_link = tfImage.getText();
            float price = Float.valueOf(tfPrice.getText());
            String size = tfSize.getText();
            String color = tfColor.getText();
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            if (!name.equals(EMPTY) && !image_link.equals(EMPTY) && !Objects.equals(price, EMPTY) && !size.equals(EMPTY) && !color.equals(EMPTY) && !quantity.equals(EMPTY) ){
                DB.insertProducts(new Products(name, image_link, price,size,color,quantity));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 6, 1);

        //show
        for(int i = 0; i < productsList.size(); i++){

            Image image = new Image(productsList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label (productsList.get(i).getName()), 0, i+2);
            grid.add(imageView, 1, i+2);
            grid.add(new Label (String.valueOf(productsList.get(i).getPrice()+"VND")), 2, i+2);
            grid.add(new Label (productsList.get(i).getSize()), 3, i+2);
            grid.add(new Label (productsList.get(i).getColor()), 4, i+2);
            grid.add(new Label (String.valueOf(productsList.get(i).getQuantity())), 5, i+2);
            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(1);
                tfname.setText("" + productsList.get(id1).getName());
                TextField tfimage = (TextField) grid.getChildren().get(3);
                tfimage.setText("" + productsList.get(id1).getImage());
                TextField tfprice = (TextField) grid.getChildren().get(5);
                tfprice.setText("" + productsList.get(id1).getPrice());
                TextField tfsize = (TextField) grid.getChildren().get(7);
                tfsize.setText("" + productsList.get(id1).getSize());
                TextField tfcolor = (TextField) grid.getChildren().get(9);
                tfcolor.setText("" + productsList.get(id1).getColor());
                TextField tfquantity = (TextField) grid.getChildren().get(11);
                tfquantity.setText("" + productsList.get(id1).getQuantity());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(newe -> {
                    Integer id = productsList.get(id1).id;
                    String name = tfname.getText();
                    String image_link = tfimage.getText();
                    float price = Float.valueOf(tfprice.getText());
                    String size = tfSize.getText();
                    String color = tfcolor.getText();
                    int quantity = Integer.valueOf(tfQuantity.getText());
                    if (!name.equals(EMPTY) && !image_link.equals(EMPTY) && !Objects.equals(price, EMPTY) && !size.equals(EMPTY) && !color.equals(EMPTY) && !Objects.equals(quantity, EMPTY)) {
                        DB.updateProducts(new Products(id, name, image_link, price, size, color, quantity));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 6, 1);
            });
            grid.add(btnUpdate, 6, i+2);

            // Delete
            var btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(productsList.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                DB.deleteProducts(id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 7, i+2);
        }

        scene = new Scene(grid, 1200, 600);
        stage.setTitle("Shop váy cưới");
        stage.setScene(scene);
        stage.show();
    }
}
