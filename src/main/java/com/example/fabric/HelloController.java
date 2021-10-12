package com.example.fabric;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas canvas;
    protected GraphicsContext gc;
    protected Fabric fabric;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void SetFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public void Draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double rad = 1;

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (Spring s:fabric.springs) {
            gc.strokeLine(s.p1.position.x - rad * 0.5, s.p1.position.y - rad* 0.5, s.p2.position.x - rad* 0.5, s.p2.position.y - rad* 0.5);
        }

        for (Point p : fabric.points) {
            if (p.unmovable)
                gc.setFill(Color.RED);
            else
                gc.setFill(Color.GREEN);
            gc.fillOval(p.position.x - rad, p.position.y - rad, rad, rad);
        }

    }
}