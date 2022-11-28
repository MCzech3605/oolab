package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        try{
            out.println("Start");
            Application.launch(App.class, args);
            /*String[] args2 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            out.println(map);
            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions, (IPositionChangeObserver) map);
            engine.run();
            out.println(map);*/
            out.print("Stop");
        }
        catch (IllegalArgumentException ex)
        {
            out.println(ex.getMessage());
        }
    }   //w samouczekprogramisty.pl masz dobre omówienie wątków
    // spytaj dra Pohla czy mamy kończyć wątek przed rozpoczęciem nowego
}
/*
things to do:
1. Pause SimulationEngine every time when an animal moves
*/