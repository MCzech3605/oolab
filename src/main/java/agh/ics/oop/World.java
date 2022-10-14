package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void run(Direction[] args)
    {
        // tu byla zbedna linia
        for(Direction arg : args)
        {
            String output = switch (arg)
            {
                case FORWARD -> "Zwierzak idzie do przodu\n";
                case BACKWARD -> "Zwierzak idzie do tyłu\n";
                case LEFT -> "Zwierzak skręca w lewo\n";
                case RIGHT -> "Zwierzak skręca w prawo\n";
                default -> "";
            };
            out.print(output);
        }
    }
    public static void testy()
    {
        MapDirection x = MapDirection.NORTH;
        if(x.next().equals(MapDirection.EAST))
            out.println("Next 1");
        else
            out.println("Next 0");
        if(x.previous().equals(MapDirection.WEST))
            out.println("Previous 1");
        else
            out.println("Previous 0");
        if(x.toUnitVector().equals(new Vector2d(0, 1)))
            out.println("To Unit Vector 1");
        else
            out.println("To Unit Vector 0");
        out.print("All that for ");
        out.println(x.toString());
    }
    public static void main(String[] args) {
        out.println("Start");
        int n = args.length;
        Direction[] toFunction;
        toFunction = new Direction[n];
        for(int i=0; i<n; i++)
        {
            Direction temp = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> Direction.UNKNOWN;
            };
            toFunction[i] = temp;
        }
        run(toFunction);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        testy();
        out.print("Stop");
    }
}
