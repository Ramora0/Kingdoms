package com.kingdoms.helpers.math;

import processing.core.PVector;

public class Vector {
  public double x, y;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Instance methods
  public Vector add(Vector v) {
    this.x += v.x;
    this.y += v.y;
    return this;
  }

  public Vector subtract(Vector v) {
    this.x -= v.x;
    this.y -= v.y;
    return this;
  }

  public Vector mult(double scalar) {
    this.x *= scalar;
    this.y *= scalar;
    return this;
  }

  public Vector div(double scalar) {
    if (scalar != 0) {
      this.x /= scalar;
      this.y /= scalar;
      return this;
    } else {
      throw new IllegalArgumentException("Cannot div by zero");
    }
  }

  public PVector toPVector() {
    return new PVector((float) x, (float) y);
  }

  // Static methods \\

  public static Vector add(Vector v1, Vector v2) {
    return new Vector(v1.x + v2.x, v1.y + v2.y);
  }

  public static Vector subtract(Vector v1, Vector v2) {
    return new Vector(v1.x - v2.x, v1.y - v2.y);
  }

  public static Vector mult(Vector v, double scalar) {
    return new Vector(v.x * scalar, v.y * scalar);
  }

  public static Vector divide(Vector v, double scalar) {
    if (scalar != 0) {
      return new Vector(v.x / scalar, v.y / scalar);
    } else {
      throw new IllegalArgumentException("Cannot divide by zero");
    }
  }
}