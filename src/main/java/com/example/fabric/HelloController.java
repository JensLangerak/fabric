package com.example.fabric;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
        canvas.setOnMousePressed((MouseEvent event) -> {
            HandleMouseClicked(event);
        });
        canvas.setOnMouseReleased((MouseEvent event) -> {
            if (selectedPoint != null)
                selectedPoint.unmovable = false;
            selectedPoint = null;
        });
        canvas.setOnMouseDragged((MouseEvent event) -> {
            HandleMouseMoved(event);
        });
    }

    private void HandleMouseMoved(MouseEvent event) {
        if (selectedPoint != null) {
            double x = event.getX();
            double y = event.getY();
            selectedPoint.position.x = x;
            selectedPoint.position.y = y;
        }
    }

    private Point selectedPoint;
    private void HandleMouseClicked(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        Point closesPoint = null;
        double smallestDistance = -1;
        for (Point p : this.fabric.points) {
            if (p.unmovable)
                continue;
            double dist = p.position.subtract(new Vec2d(x,y)).lenght();
            if (dist < 100) {
                if (closesPoint == null || dist < smallestDistance) {
                    closesPoint = p;
                    smallestDistance = dist;
                }
            }
        }
        selectedPoint = closesPoint;
        if (selectedPoint != null)
            selectedPoint.unmovable = true;
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
            else if (p == selectedPoint)
                gc.setFill(Color.ORANGE);
            else
                gc.setFill(Color.GREEN);
            gc.fillOval(p.position.x - rad, p.position.y - rad, rad, rad);
        }

    }
}