package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    int numberOfGrass;
    int maxCoordinateOfGrass;
    protected List<Grass> grassPlacement = new ArrayList<>();
    //protected List<Animal> animals = new ArrayList<>();

    public GrassField(int n) {
        this.numberOfGrass = n;
        maxCoordinateOfGrass = (int) Math.sqrt((double) n * 10);
        generateGrass(n);
    }

    private void generateGrass(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            Vector2d newPosition = new Vector2d(rand.nextInt(maxCoordinateOfGrass), rand.nextInt(maxCoordinateOfGrass));
            while (this.isOccupied(newPosition))
                newPosition = new Vector2d(rand.nextInt(maxCoordinateOfGrass), rand.nextInt(maxCoordinateOfGrass));
            this.grassPlacement.add(new Grass(newPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        if (this.isOccupied(position)) {
            return this.objectAt(position) instanceof Grass;
        }
        return true;
    }

    /*@Override
    public boolean place(Animal animal) {
        Vector2d initialPosition = animal.getPosition();
        if(!this.canMoveTo(initialPosition))
            return false;
        this.animals.add(animal);
        return true;
    }*/

    /*@Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }*/

    @Override
    Vector2d getLowerLeft() {
        return getExtreme(true);
    }

    private Vector2d getExtreme(Boolean trueForLL) {
        Vector2d vec = new Vector2d(0, 0);
        if (animals.size() == 0 && numberOfGrass == 0)
            return vec;
        if (animals.size() == 0)
            vec = grassPlacement.get(0).getPosition();
        else
            vec = animals.get(0).getPosition();
        if (trueForLL) {
            for (Animal animal : animals)
                vec = vec.lowerLeft(animal.getPosition());
            for (Grass grass : grassPlacement)
                vec = vec.lowerLeft(grass.getPosition());
        } else {
            for (Animal animal : animals)
                vec = vec.upperRight(animal.getPosition());
            for (Grass grass : grassPlacement)
                vec = vec.upperRight(grass.getPosition());
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
        for (Grass grass : grassPlacement) {
            if (grass.getPosition().equals(position))
                return grass;
        }
        return null;
    }

    public void refresh(Animal animal) {
        for (int i = 0; i < grassPlacement.size(); i++) {
            Grass grass = grassPlacement.get(i);
            if (animal.isAt(grass.getPosition())) {
                grassPlacement.remove(grass);
                this.generateGrass(1);
            }
        }
    }

    /*@Override
    public String toString()
    {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(0, 0);
        if(animals.size()==0 && numberOfGrass==0)
        {
            return visualizer.draw(lowerLeft, upperRight);
        }
        if(animals.size()==0)
        {
            lowerLeft = grassPlacement.get(0).getPosition();
            upperRight = grassPlacement.get(0).getPosition();
        }
        else
        {
            lowerLeft = animals.get(0).getPosition();
            upperRight = animals.get(0).getPosition();
        }
        for(Animal animal : animals)
        {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
            upperRight = upperRight.upperRight(animal.getPosition());
        }
        for(Grass grass : grassPlacement)
        {
            lowerLeft = lowerLeft.lowerLeft(grass.getPosition());
            upperRight = upperRight.upperRight(grass.getPosition());
        }
        return visualizer.draw(lowerLeft, upperRight);
    }*/
}
