package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RectangularMapTest {

    IWorldMap map;
    List<Animal> animals;

    @BeforeEach
    public void initializeTest() {
        map = new RectangularMap(10, 10);
        animals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Animal bear = new Animal(map, new Vector2d(i / 2, 2 * i));
            animals.add(bear);
        }
        animals.get(0).move(MoveDirection.RIGHT);
        animals.get(0).move(MoveDirection.FORWARD);
    }

    @Test
    public void canMoveToTest() {
        for (int i = 1; i < 5; i++)
            Assertions.assertFalse(map.canMoveTo(new Vector2d(i / 2, 2 * i)));

        Assertions.assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(1, 11)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(1, 0)));

        for (int i = 0; i < 11; i++)
            Assertions.assertTrue(map.canMoveTo(new Vector2d(i, i)));
    }

    @Test
    public void placeTest() {
        Vector2d initialPosition = new Vector2d(0, 0);
        Animal bear = new Animal(map, initialPosition);   //place is inside animal declaration
        Animal bear2 = (Animal) map.objectAt(initialPosition);
        Assertions.assertTrue(bear2 == bear);
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
        Assertions.assertTrue(animals.get(0) == bear);
        Assertions.assertNull(map.objectAt(new Vector2d(9, 9)));
    }
}
