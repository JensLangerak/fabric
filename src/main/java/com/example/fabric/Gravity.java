package com.example.fabric;

public class Gravity implements IForce {
    double gravity;

    public Gravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public Vec2d getForce(Point p) {
        return new Vec2d(0, gravity * p.mass);
    }

    @Override
    public Vec2d getAcceleration(Point p) {
        return new Vec2d(0, gravity);
    }
}
