package agh.ics.oop;

public class Grass implements IMapElement {
    private Vector2d position;

    public Grass(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String getImageOfElement() {
        return "src/main/resources/grass1.png";
    }

    @Override
    public String toString() {
        return "*";
    }
}
