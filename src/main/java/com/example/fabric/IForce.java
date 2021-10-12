package com.example.fabric;

public interface IForce {
    Vec2d getForce(Point p);
    Vec2d getAcceleration(Point p);
}
