package agh.ics.oop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AnimalTest {
    Animal bear = new Animal();

    @BeforeEach
    void bearGoBack() {
        bear = new Animal();
    }

    @Test
    void testOrientation() {
        Assertions.assertEquals("(2,2) Północ", bear.toString());
        bear.move(MoveDirection.RIGHT);
        Assertions.assertEquals("(2,2) Wschód", bear.toString());
        bear.move(MoveDirection.LEFT);
        bear.move(MoveDirection.LEFT);
        Assertions.assertEquals("(2,2) Zachód", bear.toString());
        bear.move(MoveDirection.LEFT);
        Assertions.assertEquals("(2,2) Południe", bear.toString());
    }

    @Test
    void testPosition() {
        bear.move(MoveDirection.RIGHT);
        bear.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(1, 2)));
        bear.move(MoveDirection.LEFT);
        bear.move(MoveDirection.LEFT);
        bear.move(MoveDirection.FORWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(0, 2)));
    }

    @Test
    void testStayingInMap() {
        bear.move(MoveDirection.FORWARD);
        bear.move(MoveDirection.FORWARD);
        bear.move(MoveDirection.FORWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(2, 4)));
        bear.move(MoveDirection.LEFT);
        bear.move(MoveDirection.BACKWARD);
        bear.move(MoveDirection.BACKWARD);
        bear.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(4, 4)));
        for (int i = 0; i < 6; i++)
            bear.move(MoveDirection.FORWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(0, 4)));
        bear.move(MoveDirection.RIGHT);
        for (int i = 0; i < 10; i++)
            bear.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(bear.isAt(new Vector2d(0, 0)));
    }
}
