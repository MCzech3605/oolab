package agh.ics.oop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SimulationEngineTest {
    @Test
    void simulationTest1()
    {
        String args2[] = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args2);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal bear = (Animal) map.objectAt(new Vector2d(2, 0));
        Assertions.assertNotNull(bear);
        Assertions.assertEquals(bear.toString(), "v");
        Animal squirrel = (Animal) map.objectAt(new Vector2d(3, 5));
        Assertions.assertNotNull(squirrel);
        Assertions.assertEquals(squirrel.toString(), "^");
        for(int i=0; i<11; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(!((i==2 && j==0) || (i==3 && j==5)))
                {
                    Assertions.assertNull(map.objectAt(new Vector2d(i, j)));
                }
            }
        }
    }
}
