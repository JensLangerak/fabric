package com.example.fabric;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        HelloController controller = fxmlLoader.getController();


        //TODO move to better place
        Fabric fabric = new Fabric(50,50, new Vec2d(100,100), 5);
        controller.SetFabric(fabric);
        controller.Draw();
        Timeline mainLoop = new Timeline(
                new KeyFrame(Duration.millis(1000/30f),
                        event -> {
                            for (int i = 0; i < 50; i++)
                                fabric.update(1f/100f);
                            controller.Draw();
                        }));
        mainLoop.setCycleCount(Timeline.INDEFINITE);
        mainLoop.play();



    }

    public static void main(String[] args) {
        launch();
    }
}