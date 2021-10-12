package com.example.fabric;

import java.util.ArrayList;
import java.util.Objects;

public class Point {
    protected Vec2d position;
    protected Vec2d velocity;
    protected Vec2d acceleration = new Vec2d(0,0);
    protected double mass;
    protected boolean unmovable;
    protected double drag = 0.25;
    protected ArrayList<IForce> forces = new ArrayList<>();

    public Point(Vec2d position, double mass) {
        this.position = position;
        this.mass = mass;
        this.velocity = new Vec2d(0, 0);
        this.unmovable = false;
    }

    public Point(Vec2d position, double mass, boolean unmovable) {
        this(position, mass);
        this.unmovable = unmovable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.mass, mass) == 0 && unmovable == point.unmovable && position.equals(point.position) && velocity.equals(point.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity, mass, unmovable);
    }

    public void AddForce(IForce force) {
        forces.add(force);
    }

    public void RemoveForce(IForce force) {
        forces.remove(force);
    }

    public void updatePosition(double timeStep) {
        double x = position.x + velocity.x * timeStep + acceleration.x * timeStep * timeStep * 0.5;
        double y = position.y + velocity.y * timeStep + acceleration.y * timeStep * timeStep * 0.5;
        Vec2d newPosition = new Vec2d(x, y);
        position = newPosition;
        if (position.x < 5)
            position.x = 5;
        if (position.x > 495)
            position.x = 495;
        if (position.y < 5)
            position.y = 5;
        if (position.y > 795)
            position.y = 795;
    }
    public void updateVelAndAcc(double timeStep) {

        double speed = velocity.lenght();
        Vec2d newAcceleration = new Vec2d(-drag * 0.5 * velocity.x * Math.abs(velocity.x), -drag * 0.5 * velocity.y * Math.abs(velocity.y));

        if (!unmovable) {
            for (IForce f : forces) {
                newAcceleration.InternalAdd(f.getAcceleration(this));
            }
        }
        double x = velocity.x + (acceleration.x + newAcceleration.x) * timeStep * 0.5;
        double y = velocity.y + (acceleration.y + newAcceleration.y) * timeStep * 0.5;

        velocity.x = x;
        velocity.y = y;

        acceleration = newAcceleration;



    }

}
