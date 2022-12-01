package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        try{
            Application.launch(App.class, args);
        }
        catch (IllegalArgumentException ex)
        {
            out.println(ex.getMessage());
        }
    }   //stwórz diagram UML, zaprojektuj ogólnie jak to ma wyglądać
    //na koniec projektu w readme napisz co zostało zaimplementowane, a co nie
}