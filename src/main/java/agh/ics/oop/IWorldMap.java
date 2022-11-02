package agh.ics.oop;

public interface IWorldMap {
    boolean canMoveTo(Vector2d position);
    boolean place(Animal animal, Vector2d initialPosition);
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);
}
