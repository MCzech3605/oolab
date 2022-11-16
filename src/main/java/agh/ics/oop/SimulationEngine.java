package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private MoveDirection[] directions;
    private IWorldMap map;
    private IPositionChangeObserver observer;
    private Vector2d[] initialPositions;
    SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer)
    {
        this.directions = directions;
        this.observer = observer;
        this.map = map;
        this.initialPositions = initialPositions;
    }
    List<Animal> animals = new ArrayList<>();

    @Override
    public void run() {

        for(Vector2d position : this.initialPositions)
        {
            Animal animal = new Animal(this.map, position, this.observer);
            animals.add(animal);
        }
        int n = animals.size();
        int i = 0;
        for(MoveDirection direction : this.directions)
        {
            Animal animal = animals.get(i);
            i++;
            i=i%n;
            animal.move(direction);
        }
    }
}
