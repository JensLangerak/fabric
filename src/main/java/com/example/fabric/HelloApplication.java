package com.example.fabric;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Fabric fabric = new Fabric(30,30, new Vec2d(100,100), 5);
        controller.SetFabric(fabric);
        controller.Draw();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 20
                ; i++)
                fabric.update(1f/100f);
                controller.Draw();
            }
        }, 0, 1000/30);
    }

    public static void main(String[] args) {
        launch();
    }
}