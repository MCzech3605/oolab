package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test
    void testNext()
    {
        MapDirection x = MapDirection.EAST;
        Assertions.assertTrue(x.next().equals(MapDirection.SOUTH));
        x = MapDirection.SOUTH;
        Assertions.assertTrue(x.next().equals(MapDirection.WEST));
        x = MapDirection.WEST;
        Assertions.assertTrue(x.next().equals(MapDirection.NORTH));
        x = MapDirection.NORTH;
        Assertions.assertTrue(x.next().equals(MapDirection.EAST));
    }

    @Test
    void testPrevious()
    {
        MapDirection x = MapDirection.WEST;
        Assertions.assertTrue(x.previous().equals(MapDirection.SOUTH));
        x = MapDirection.SOUTH;
        Assertions.assertTrue(x.previous().equals(MapDirection.EAST));
        x = MapDirection.EAST;
        Assertions.assertTrue(x.previous().equals(MapDirection.NORTH));
        x = MapDirection.NORTH;
        Assertions.assertTrue(x.previous().equals(MapDirection.WEST));
    }
}
