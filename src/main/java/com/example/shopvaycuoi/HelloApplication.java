package com.example.shopvaycuoi;

import com.example.shopvaycuoi.data.DBconnection;
import com.example.shopvaycuoi.model.Products;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
        DBconnection conn = new DBconnection();
        VBox vBox = new VBox();
        this.getThemdisplayProducts(vBox, conn);
        Scene scene = new Scene(vBox, 1200.0, 600.0);
        stage.setTitle("Shop váy cưới!");
        stage.setScene(scene);
        stage.show();
    }
    void getThemdisplayProducts(VBox vBox, DBconnection conn) {
        ArrayList<Products> productList = conn.getProducts();


        for(int i = 0; i < productList.size(); ++i) {
            HBox hBoxStudents = new HBox();
            hBoxStudents.setSpacing(20.0);
            Button btnDelete = new Button("DELETE");
            btnDelete.setId(String.valueOf(((Products)productList.get(i)).id));
            btnDelete.setOnAction((e) -> {
                conn.deleteProducts(parseInt(btnDelete.getId()));
                vBox.getChildren().removeAll(vBox.getChildren());
                this.getThemdisplayProducts(vBox, conn);
            });
            Button btnEdit = new Button("EDIT");
            btnEdit.setId(String.valueOf(((Products)productList.get(i)).id));
            int finalI = i;
            btnEdit.setOnAction((e) -> {
                vBox.getChildren().removeAll(vBox.getChildren());
                Label name = new Label("name");
                Label image = new Label("image_link");
                Label price = new Label("price");
                Label sizep = new Label("size");
                Label colorp = new Label("color");
                Label quantity = new Label("quantity");
                TextField textName = new TextField();
                TextField textImage = new TextField();
                TextField textPrice = new TextField();
                TextField textSize = new TextField();
                TextField textColor = new TextField();
                TextField textQuantity = new TextField();
                textName.setText(((Products)productList.get(finalI)).name);
                textImage.setText(((Products)productList.get(finalI)).image_link);
                textPrice.setText(String.valueOf(((Products)productList.get(finalI)).price));
                textSize.setText(((Products)productList.get(finalI)).size);
                textColor.setText(((Products)productList.get(finalI)).color);
                textQuantity.setText(String.valueOf(((Products)productList.get(finalI)).quantity));
                HBox hBoxName = new HBox();
                hBoxName.getChildren().addAll(new Node[]{name, textName});
                HBox hBoxImage = new HBox();
                hBoxImage.getChildren().addAll(new Node[]{image, textImage});
                HBox hBoxPrice = new HBox();
                hBoxPrice.getChildren().addAll(new Node[]{price, textPrice});
                HBox hBoxSize = new HBox();
                hBoxSize.getChildren().addAll(new Node[]{sizep, textSize});
                HBox hBoxColor = new HBox();
                hBoxColor.getChildren().addAll(new Node[]{colorp, textColor});
                HBox hBoxQuantity = new HBox();
                hBoxQuantity.getChildren().addAll(new Node[]{quantity, textQuantity});
                Button btnAdd = new Button("SAVE");
                btnAdd.setOnAction((a) -> {
                    this.saveEditProduct(textName, textImage, textPrice, textSize, textColor, textQuantity, ((Products)productList.get(finalI)).id, conn, vBox);
                });
                vBox.getChildren().addAll(new Node[]{hBoxName,hBoxImage, hBoxPrice,hBoxSize,hBoxColor,hBoxQuantity, btnAdd});
                this.getThemdisplayProducts(vBox, conn);
            });
            Object var10002 = productList.get(i);
            Label id = new Label("" + ((Products)var10002).id);
            var10002 = productList.get(i);
            Label name = new Label("" + ((Products)var10002).name);
            var10002 = productList.get(i);
            Label image_link = new Label("" + ((Products)var10002).image_link);
            var10002 = productList.get(i);
            Label price = new Label("" + ((Products)var10002).price);
            var10002 = productList.get(i);
            Label size = new Label("" + ((Products)var10002).size);
            var10002 = productList.get(i);
            Label color = new Label("" + ((Products)var10002).color);
            var10002 = productList.get(i);
            Label quantity = new Label("" + ((Products)var10002).quantity);
            hBoxStudents.getChildren().addAll(new Node[]{id, name,image_link, price, size, color, quantity, btnDelete, btnEdit});
            vBox.getChildren().add(hBoxStudents);
        }
        this.addProduct(vBox, conn);
    }

    void saveEditProduct( TextField textName, TextField textImage_link, TextField textPrice, TextField textSize, TextField textColor, TextField textQuantity, int id, DBconnection conn, VBox vBox) {
        String editName = textName.getText();
        String editImage = textImage_link.getText();
        Float editPrice = Float.valueOf(textPrice.getText());
        String editSize = textSize.getText();
        String editColor = textColor.getText();
        int editQuantity = parseInt(textQuantity.getText());
        conn.updateProducts(new Products(id, editName, editImage, editPrice, editSize, editColor, editQuantity));
        vBox.getChildren().removeAll(vBox.getChildren());
        this.getThemdisplayProducts(vBox, conn);
    }

    void addProduct(VBox vBox, DBconnection conn) {
        //Label category = new Label("catego_id");
        Label name = new Label("name");
        Label image = new Label("image_link");
        Label price = new Label("price");
        Label sizep = new Label("size");
        Label colorp = new Label("color");
        Label quantity = new Label("quantity");
        //TextField textCatego = new TextField();
        TextField textName = new TextField();
        TextField textImage = new TextField();
        TextField textPrice = new TextField();
        TextField textSize = new TextField();
        TextField textColor = new TextField();
        TextField textQuantity = new TextField();
//        HBox hBoxCategory = new HBox();
//        hBoxCategory.getChildren().addAll(new Node[]{category, textCatego});
        HBox hBoxName = new HBox();
        hBoxName.getChildren().addAll(new Node[]{name, textName});
        HBox hBoxImage = new HBox();
        hBoxImage.getChildren().addAll(new Node[]{image, textImage});
        HBox hBoxPrice = new HBox();
        hBoxPrice.getChildren().addAll(new Node[]{price, textPrice});
        HBox hBoxSize = new HBox();
        hBoxSize.getChildren().addAll(new Node[]{sizep, textSize});
        HBox hBoxColor = new HBox();
        hBoxColor.getChildren().addAll(new Node[]{colorp, textColor});
        HBox hBoxQuantity = new HBox();
        hBoxQuantity.getChildren().addAll(new Node[]{quantity, textQuantity});
        Button btnAdd = new Button("ADD Products");
        vBox.getChildren().addAll(new Node[]{hBoxName, hBoxImage, hBoxPrice, hBoxSize, hBoxColor, hBoxQuantity, btnAdd});
        btnAdd.setOnAction((e) -> {
            //int txtCate = parseInt(textCatego.getText());
            String txtName = textName.getText();
            System.out.println(txtName);
            String txtImage = textImage.getText();
            System.out.println(txtImage);
            Float ftPrice = Float.parseFloat(textPrice.getText());
            System.out.println(textPrice);
            String txtSize = textSize.getText();
            System.out.println(txtSize);
            String txtColor = textColor.getText();
            System.out.println(txtColor);
            int txtQuantity = parseInt(textQuantity.getText());
            System.out.println(txtQuantity);
            conn.insertProducts(new Products(txtName, txtImage, ftPrice, txtSize, txtColor, txtQuantity));
            System.out.println(conn);
            vBox.getChildren().removeAll(vBox.getChildren());
            this.getThemdisplayProducts(vBox, conn);
        });

    }
    public static void main(String[] args) {
        launch(new String[0]);
    }
}
