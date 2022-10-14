package agh.ics.oop;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    final int x;
    final int y;
    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }
    public boolean precedes(Vector2d other)
    {
        return this.x <= other.x && this.y <= other.y;
    }
    public boolean follows(Vector2d other)
    {
        return this.x >= other.x && this.y >= other.y;
    }
    public Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }
    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    public Vector2d upperRight(Vector2d other)
    {
        return new Vector2d(max(this.x, other.x), max(this.y, other.y));
    }
    public Vector2d lowerLeft(Vector2d other)
    {
        return new Vector2d(min(this.x, other.x), min(this.y, other.y));
    }
    public Vector2d opposite()
    {
        return new Vector2d(-this.x, -this.y);
    }
    public boolean equals(Vector2d other)
    {
        return this.x == other.x && this.y == other.y;
    }

}
