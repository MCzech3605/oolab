package agh.ics.oop;


public class Animal implements IMapElement {
    private IWorldMap map;
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
        if (!map.place(this))
            this.map = null;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        if (!map.place(this))
            this.map = null;

    }

    public String toString() {
        return this.orientation.toString();
    }

    public Boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case FORWARD:
                Vector2d movedf = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(movedf)) {
                    this.position = new Vector2d(movedf.x, movedf.y);
                }
                break;
            case BACKWARD:
                Vector2d movedb = this.position.add(this.orientation.next().next().toUnitVector());
                if (map.canMoveTo(movedb)) {
                    this.position = new Vector2d(movedb.x, movedb.y);
                }
                break;
        }
        map.refresh(this);
    }

    public Vector2d getPosition() {
        return position;
    }
}
