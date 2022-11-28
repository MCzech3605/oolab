package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationEngine implements IEngine, Runnable{
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final IPositionChangeObserver observer;
    private final Vector2d[] initialPositions;
    private final App applicationObserver;
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer)
    {
        this.directions = directions;
        this.observer = observer;
        this.map = map;
        this.initialPositions = initialPositions;
        applicationObserver = null;
    }
    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer, App applicationObserver)
    {
        this.directions = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT};
        this.observer = observer;
        this.map = map;
        this.initialPositions = initialPositions;
        this.applicationObserver = applicationObserver;
    }
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer, App applicationObserver)
    {
        this.directions = directions;
        this.observer = observer;
        this.map = map;
        this.initialPositions = initialPositions;
        this.applicationObserver = applicationObserver;
    }
    List<Animal> animals = new ArrayList<>();

    @Override
    public void run() {

            for (Vector2d position : this.initialPositions) {
                Animal animal = new Animal(this.map, position, this.observer);
                animals.add(animal);
            }
            try {
                if (applicationObserver != null) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            applicationObserver.positionChanged();
                        }
                    });
                    sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Exception: Simulation interrupted");
            }
            int n = animals.size();
            int i = 0;
            for (MoveDirection direction : this.directions) {
                try {
                    if (applicationObserver != null) {
                        sleep(300);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                applicationObserver.positionChanged();
                            }
                        });

                    }
                } catch (InterruptedException ex) {
                    System.out.println("Exception: Simulation interrupted");
                }
                Animal animal = animals.get(i);
                i++;
                i = i % n;
                animal.move(direction);


            }
    }
}
