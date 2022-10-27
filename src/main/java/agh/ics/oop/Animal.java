package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;

    public String toString() {
        return this.position.toString() + " " + this.orientation.toString();
    }

    public Boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void move(MoveDirection direction) {
        Vector2d upperRight = new Vector2d(4, 4);
        Vector2d lowerLeft = new Vector2d(0, 0);
        switch (direction) {
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case FORWARD:
                Vector2d movedf = this.position.add(this.orientation.toUnitVector());
                if (movedf.precedes(upperRight) && movedf.follows(lowerLeft)) {
                    this.position = new Vector2d(movedf.x, movedf.y);
                }
                break;
            case BACKWARD:
                Vector2d movedb = this.position.add(this.orientation.next().next().toUnitVector());
                if (movedb.precedes(upperRight) && movedb.follows(lowerLeft)) {
                    this.position = new Vector2d(movedb.x, movedb.y);
                }
                break;
        }
    }
}
