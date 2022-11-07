package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected List<Animal> animals = new ArrayList<>();
    @Override
    public boolean place(Animal animal) {
        Vector2d initialPosition = animal.getPosition();
        if(!this.canMoveTo(initialPosition))
            return false;
        this.animals.add(animal);
        return true;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    abstract Vector2d getLowerLeft();
    abstract Vector2d getUpperRight();

    @Override
    public String toString()
    {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.getLowerLeft(), this.getUpperRight());
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
}
