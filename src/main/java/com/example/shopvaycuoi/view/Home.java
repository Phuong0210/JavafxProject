package com.example.shopvaycuoi.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Home extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loadScreenOne(), 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public VBox loadScreenOne()
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        final Button button = new Button("Switch Screen");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                button.getScene().setRoot(loadScreenTwo());
            }
        });
        Text text = new Text("Screen One");
        vBox.getChildren().addAll(text, button);
        vBox.setStyle("-fx-background-color: #8fbc8f;");
        return vBox;
    }

    public VBox loadScreenTwo()
    {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        final Button button = new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                button.getScene().setRoot(loadScreenOne());
            }
        });
        Text text = new Text("Screen Two");
        vBox.getChildren().addAll(text, button);
        return vBox;
    }

}