package com.example.fabric;

import java.util.ArrayList;

public class Fabric {
    protected ArrayList<Point> points = new ArrayList<>();
    protected ArrayList<Spring> springs = new ArrayList<>();

    private int sizeX;
    private int sizeY;

    public Fabric(int sizeX, int sizeY, Vec2d topLeft, double springDistance) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        createPoints(topLeft, springDistance);
        createSprings(springDistance);


    }

    private void createSprings(double springDistance) {
        for (int x = 0; x < sizeX -1; x++) {
            for (int y =0; y < sizeY; y++) {
                Point p1 = points.get(getPointIndex(x,y));
                Point p2 = points.get(getPointIndex(x + 1,y));
                Spring spring = new Spring(p1, p2);
                springs.add(spring);
                p1.AddForce(spring);
                p2.AddForce(spring);
            }
        }


        for (int x = 0; x < sizeX; x++) {
            for (int y =0; y < sizeY - 1; y++) {
                Point p1 = points.get(getPointIndex(x,y));
                Point p2 = points.get(getPointIndex(x,y + 1));
                Spring spring = new Spring(p1, p2);
                springs.add(spring);
                p1.AddForce(spring);
                p2.AddForce(spring);
            }
        }
    }

    private void createPoints(Vec2d topLeft, double springDistance) {
        Gravity gravity = new Gravity(1f);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                boolean unmovable = y == 0 && (x == 0|| x == sizeX - 1);
//                boolean unmovable = x==0; //|| x==sizeX-1;
                Point point = new Point(new Vec2d(x * springDistance + topLeft.x, y * springDistance + topLeft.y),1, unmovable );
                points.add(point);
                point.AddForce(gravity);
            }
        }
    }

    private int getPointIndex(int x, int y) {
        return x * sizeY + y;

    }

    public void update(double timeStep) {
        for (Point p : points) {
            p.updateVelAndAcc(timeStep);
            p.updatePosition(timeStep);

        }

    }
}
