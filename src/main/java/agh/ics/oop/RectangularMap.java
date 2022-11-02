package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RectangularMap implements IWorldMap {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    List<Animal> animals = new ArrayList<>();

    RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(min(width, 0), min(height, 0));
        this.upperRight = new Vector2d(max(0, width), max(0, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(this.lowerLeft) && position.precedes(this.upperRight)) {
            return !this.isOccupied(position);
        }
        else
            return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position))
                return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal, Vector2d initialPosition) {
        if(this.canMoveTo(initialPosition) && animal.isAt(initialPosition))
        {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : this.animals)
        {
            if(animal.isAt(position))
                return animal;
        }
        return null;
    }

    @Override
    public String toString()
    {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }
}
