package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GrassFieldTest {
    IWorldMap map;
    List<Animal> animals;

    @BeforeEach
    public void initializeTest() {
        map = new GrassField(10);
        animals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Animal bear = new Animal(map, new Vector2d(i / 2, 2 * i), (IPositionChangeObserver) map);
            animals.add(bear);
        }
        animals.get(0).move(MoveDirection.RIGHT);
        animals.get(0).move(MoveDirection.FORWARD);
        //
    }

    @Test
    public void canMoveToTest() {
        for (int i = 1; i < 5; i++)
            Assertions.assertFalse(map.canMoveTo(new Vector2d(i / 2, 2 * i)));

        Assertions.assertTrue(map.canMoveTo(new Vector2d(-1, 0)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(1, 11)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(1, 0)));

        for (int i = 1; i < 11; i++)
            Assertions.assertTrue(map.canMoveTo(new Vector2d(i, i)));
    }

    @Test
    public void placeTest() {
        Vector2d initialPosition = new Vector2d(0, 0);
        Animal bear = new Animal(map, initialPosition);   //place is inside animal declaration
        Animal bear2 = (Animal) map.objectAt(initialPosition);
        Assertions.assertSame(bear2, bear);
    }

    @Test
    public void isOccupiedTest() {
        Assertions.assertTrue(map.isOccupied(new Vector2d(1, 0)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 2)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(0, 10)));
    }

    @Test
    public void objectAtTest() {
        Animal bear = (Animal) map.objectAt(new Vector2d(1, 0));
        Assertions.assertSame(animals.get(0), bear);
        Vector2d v = new Vector2d(9, 9);
        Assertions.assertTrue(map.objectAt(v) instanceof Grass || map.objectAt(v) == null);
    }

}
