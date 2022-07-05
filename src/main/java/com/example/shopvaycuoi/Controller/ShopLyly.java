package com.example.shopvaycuoi.Controller;
import com.example.shopvaycuoi.view.Home;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javafx.geometry.Pos.BASELINE_CENTER;
public class ShopLyly extends Application {
    private Scene scene;
    public Scene scene_H;
    public Stage ly;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
    }
    public Scene  renderMainboard(Stage window,Stage stage){
        //Scene1
        Home home= new Home();
        Stage ly =new Stage();
        Label label = new Label("WEllCOME TO LYLYSHOP");
        Button button1 = new Button("HOME");
        button1.setOnAction(e -> {
           // stage.setScene(home. loadScreenOne());
        });
       // stage.setScene(home.loadScreenOne());
        Button button2 = new Button("ADMIN");
        
//        button1.setOnAction(event -> {
//            window.setScene(scene_H);
//        });
        button2.setOnAction(event -> {
            window.setScene(scene);
        });
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label, button1,button2);
        Scene scene1 = new Scene(layout1, 300, 100);
        return  scene1;
    }
}
