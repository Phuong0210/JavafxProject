package com.example.shopvaycuoi.view;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import com.example.shopvaycuoi.data.DBconnection;
import com.example.shopvaycuoi.model.Products;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Admin extends Application {


    Button button;
    public static void main(String[] args) {
        launch(args);
    }
    private Scene scene,admin;
    private static final String EMPTY = "";
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

            ButtonType buttonTypeYes = new ButtonType("All products", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("Action", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType( "Cancel", ButtonBar. ButtonData. CANCEL_CLOSE);
            alert.getButtonTypes (). setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes)
                getDisplayProducts(grid,DB, stage);
            else if (result.get().getButtonData() == ButtonBar.ButtonData.NO)
                handleProducts(grid,DB,stage);
            else
                System.out.println("CANCELLLLLLLLLLLLL");

            String message = result.get().getText();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information");
            alert1.setHeaderText("Cảm ơn bạn đã ghé cửa hàng nhà Lyly");
            alert1.setContentText(message);
            alert1.showAndWait();
        });
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        admin = new Scene(layout, 300, 250);
        stage.setScene(admin);
        stage.show();

    }
//Hiện thị tất cả sản phẩm đang có
    private void getDisplayProducts(GridPane grid, DBconnection DB, Stage stage) {

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
        scene = new Scene(vboxGrid, 900, 600);
        stage.setTitle("Shop váy cưới");
        stage.setScene(scene);
        stage.show();
    }
    //Thực hiện các thao tác thêm, sửa và xóa sản phẩm
    public void handleProducts(GridPane grid, DBconnection DB, Stage stage) {
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
                DB.insertProducts(new Products(name, image_link, price, size, color, quantity, catego_id));
                try {
                    start(stage);
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

        ArrayList<Products> productsList = DB.getProducts();

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
                TextField tfcatego = (TextField) grid.getChildren().get(13);
                tfcatego.setText("" + productsList.get(id1).getCatego_id());
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
                    int catego_id = Integer.valueOf(tfcatego.getText());
                    if (!name.equals(EMPTY) && !image_link.equals(EMPTY) && !Objects.equals(price, EMPTY) && !size.equals(EMPTY) && !color.equals(EMPTY) && !Objects.equals(quantity, EMPTY) && !Objects.equals(catego_id, EMPTY)) {
                        DB.updateProducts(new Products(id, name, image_link, price, size, color, quantity, catego_id));
                        try {
                            start(stage);
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
            grid.add(btnDelete, 8, i + 2);
        }


        VBox vboxGrid = new VBox();
       butBacks(stage,grid,vboxGrid);
        scene = new Scene(vboxGrid, 1250, 600);
        stage.setTitle("Shop váy cưới");
        stage.setScene(scene);
        stage.show();
    }
    void butBacks(Stage stage, GridPane grid,VBox vboxGrid){
        Button btnBack = new Button("Back");

        vboxGrid.getChildren().addAll(grid,btnBack);

        btnBack.setOnAction(e -> {
            stage.setScene(admin);
            System.out.println("Go back");
        });
    }

}