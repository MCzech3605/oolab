package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected Map<Vector2d, Animal> animals = new HashMap<>();
    @Override
    public boolean place(Animal animal) {
        Vector2d initialPosition = animal.getPosition();
        if(!this.canMoveTo(initialPosition))
            throw new IllegalArgumentException(initialPosition + " is not valid position to place new animal");
        this.animals.put(initialPosition, animal);
        return true;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }



    @Override
    public String toString()
    {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.getLowerLeft(), this.getUpperRight());
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
}
