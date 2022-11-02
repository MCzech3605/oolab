package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void run(Direction[] args) {
        for (Direction arg : args) {
            String output = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu\n";
                case BACKWARD -> "Zwierzak idzie do tyłu\n";
                case LEFT -> "Zwierzak skręca w lewo\n";
                case RIGHT -> "Zwierzak skręca w prawo\n";
                default -> "";
            };
            out.print(output);
        }
    }

    public static void testy() {
        MapDirection x = MapDirection.NORTH;
        if (x.next().equals(MapDirection.EAST))
            out.println("Next 1");
        else
            out.println("Next 0");
        if (x.previous().equals(MapDirection.WEST))
            out.println("Previous 1");
        else
            out.println("Previous 0");
        if (x.toUnitVector().equals(new Vector2d(0, 1)))
            out.println("To Unit Vector 1");
        else
            out.println("To Unit Vector 0");
        out.print("All that for ");
        out.println(x);
    }

    public static void main(String[] args) {
        out.println("Start");
        //String args2[] = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.print("Stop");
    }
}
