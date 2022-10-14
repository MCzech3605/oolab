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
        out.print("Stop");
    }
}
