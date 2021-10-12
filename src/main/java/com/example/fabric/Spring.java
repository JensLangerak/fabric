package com.example.fabric;

import java.util.GregorianCalendar;

public class Spring implements IForce {
    public Point p1;
    public Point p2;
    protected double defaultLength;
    protected double stiffness = 10;
    protected double damping = 0.5;

    public Spring(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        defaultLength = currentLength();
    }

    public double currentLength() {
        Vec2d diff = p1.position.subtract(p2.position);
        return diff.lenght();
    }

    @Override
    public Vec2d getForce(Point p) {
        Vec2d diff = p1.position.subtract(p2.position);
        double length = currentLength();
        double force = (length - defaultLength) * stiffness ; //positive to the other point, negative away from point
        Vec2d vec;
        if (p.equals(p1)) {
            vec = new Vec2d(diff, -force);
        } else if (p.equals(p2)) {
            vec = new Vec2d(diff, force);
        } else {
            throw new IllegalArgumentException("Argument does not belong to the spring");
        }
        return vec;
    }

    @Override
    public Vec2d getAcceleration(Point p) {
        if (p.equals(p1)) {
            return new Vec2d(getForce(p1), p1.mass / (p1.mass + p2.mass));
        } else if (p.equals(p2)) {
            return new Vec2d(getForce(p2), p2.mass / (p1.mass + p2.mass));
        } else {
            throw new IllegalArgumentException("Argument does not belong to the spring");
        }
    }


}
