package agh.ics.oop;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
    int numberOfGrass;
    int maxCoordinateOfGrass;
    protected Map<Vector2d, Grass> grassPlacement = new HashMap<>();

    public GrassField(int n) {
        this.numberOfGrass = n;
        maxCoordinateOfGrass = (int) Math.sqrt((double) n * 10);
        generateGrass(n);
        //
    }

    private void generateGrass(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            Vector2d newPosition = new Vector2d(rand.nextInt(maxCoordinateOfGrass), rand.nextInt(maxCoordinateOfGrass));
            while (this.isOccupied(newPosition))
                newPosition = new Vector2d(rand.nextInt(maxCoordinateOfGrass), rand.nextInt(maxCoordinateOfGrass));
            this.grassPlacement.put(newPosition, new Grass(newPosition));
        }
        //
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if (this.isOccupied(position)) {
            return this.objectAt(position) instanceof Grass;
        }
        return true;
    }

    @Override
    Vector2d getLowerLeft() {
        return getExtreme(true);
    }

    private Vector2d getExtreme(Boolean trueForLL) {
        Vector2d vec = new Vector2d(0, 0);
        boolean flag = true;
        for(Vector2d key : animals.keySet())
        {
            if(flag)
            {
                vec = key;
                flag = false;
            }
            else if(trueForLL)
                vec = vec.lowerLeft(key);
            else
                vec = vec.upperRight(key);
        }
        for(Vector2d key : grassPlacement.keySet())
        {
            if(flag)
            {
                vec = key;
                flag = false;
            }
            else if(trueForLL)
                vec = vec.lowerLeft(key);
            else
                vec = vec.upperRight(key);
        }
        return vec;
    }

    @Override
    Vector2d getUpperRight() {
        return getExtreme(false);
    }

    @Override
    public Object objectAt(Vector2d position) {   //returns animal if both animal and grass are on the position
        Object x = super.objectAt(position);
        if (x != null)
            return x;
        return grassPlacement.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        super.positionChanged(oldPosition, newPosition);
        Grass grass = grassPlacement.get(newPosition);
        if(grass != null)
        {
            grassPlacement.remove(newPosition);
            this.generateGrass(1);
        }
    }
}
