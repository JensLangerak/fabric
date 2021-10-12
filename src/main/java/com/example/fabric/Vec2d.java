package com.example.fabric;

import java.util.Objects;

public class Vec2d {
    public double x;
    public double y;
    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d(Vec2d source, double scalar) {
        this.x = source.x * scalar;
        this.y = source.y * scalar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2d vec2d = (Vec2d) o;
        return Double.compare(vec2d.x, x) == 0 && Double.compare(vec2d.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vec2d subtract(Vec2d o) {
        return new Vec2d(x- o.x, y- o.y);
    }

    public void InternalAdd(Vec2d acceleration) {
        x += acceleration.x;
        y += acceleration.y;
    }

    public double lenght() {
        return (double) Math.sqrt(x * x + y * y);
    }
}
