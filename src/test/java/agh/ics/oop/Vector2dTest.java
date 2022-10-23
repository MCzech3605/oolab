package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    Vector2d x = new Vector2d(1, 2);
    Vector2d y = new Vector2d(2, 1);
    Vector2d z = new Vector2d(2, 3);

    @Test
    void testEquals()
    {
        Assertions.assertTrue(x.equals(new Vector2d(1, 2)));
        Assertions.assertFalse(x.equals(y));
    }

    @Test
    void testToString()
    {
        Assertions.assertEquals("(1,2)", x.toString());
    }

    @Test
    void testPrecedes()
    {
        Assertions.assertTrue(x.precedes(z)&&y.precedes(z));
        Assertions.assertFalse(x.precedes(y));
    }

    @Test
    void testFollows()
    {
        Assertions.assertTrue(z.follows(y)&&z.follows(x));
        Assertions.assertFalse(x.follows(z) || x.follows(y));
    }

    @Test
    void testUpperRight()
    {
        Vector2d a = x.upperRight(y);
        Assertions.assertTrue(a.x == 2 && a.y == 2);
    }

    @Test
    void testLowerLeft()
    {
        Vector2d a = x.lowerLeft(y);
        Assertions.assertTrue(a.x == 1 && a.y == 1);
    }

    @Test
    void testAdd()
    {
        Vector2d a = x.add(y);
        Assertions.assertTrue(a.equals(new Vector2d(3, 3)));
    }

    @Test
    void testSubtract()
    {
        Vector2d a = x.subtract(y);
        Assertions.assertTrue(a.x == -1 && a.y == 1);
    }

    @Test
    void testOposite()
    {
        Vector2d a = x.opposite();
        Assertions.assertTrue(a.x == -x.x && a.y == -x.y);
    }
    // warto uzywac @BeforeEach i @BeforeAll
}
