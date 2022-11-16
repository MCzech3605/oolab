package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private IWorldMap map;
    private Vector2d position;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private MapDirection orientation = MapDirection.NORTH;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
        if (!map.place(this))
            this.map = null;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        if (!map.place(this))
            this.map = null;

    }
    public Animal(IWorldMap map, Vector2d initialPosition, IPositionChangeObserver observer) {
        this.map = map;
        this.position = initialPosition;
        if (!map.place(this))
            this.map = null;
        else
            this.addObserver(observer);
    }
    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return this.orientation.toString();
    }

    public Boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for(IPositionChangeObserver observer : observers)
            observer.positionChanged(oldPosition, newPosition);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case FORWARD:
                Vector2d movedf = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(movedf)) {
                    this.positionChanged(this.position, movedf);
                    this.position = new Vector2d(movedf.x, movedf.y);
                }
                break;
            case BACKWARD:
                Vector2d movedb = this.position.add(this.orientation.next().next().toUnitVector());
                if (map.canMoveTo(movedb)) {
                    this.positionChanged(this.position, movedb);
                    this.position = new Vector2d(movedb.x, movedb.y);
                }
                break;
        }

    }

    @Override
    public Vector2d getPosition() {
        return position;
    }
}
