package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
/*
    Comparator<IMapElement> comparatorX = new Comparator<IMapElement>() {
        @Override
        public int compare(IMapElement a, IMapElement b) {
            Vector2d aPosition = a.getPosition();
            Vector2d bPosition = b.getPosition();
            if(aPosition.x == bPosition.x)
            {
                if(aPosition.y == bPosition.y)
                {
                    if(a instanceof Animal)
                        return 1;
                    return -1;
                }
                else
                    return aPosition.y - bPosition.y;
            }
            return aPosition.x - bPosition.x;
        }
    };
    Comparator<IMapElement> comparatorY = new Comparator<IMapElement>() {
        @Override
        public int compare(IMapElement a, IMapElement b) {
            Vector2d aPosition = a.getPosition();
            Vector2d bPosition = b.getPosition();
            if(aPosition.y == bPosition.y)
            {
                if(aPosition.x == bPosition.x)
                {
                    if(a==b)
                        return 0;
                    if(a instanceof Animal)
                        return 1;
                    return -1;
                }
                else
                    return aPosition.x - bPosition.x;
            }
            return aPosition.y - bPosition.y;
        }
    };
    SortedSet<IMapElement> itemsX = new TreeSet<>(comparatorX);
    SortedSet<IMapElement> itemsY = new TreeSet<>(comparatorY);

    public void addElement(IMapElement element)
    {
        itemsX.add(element);
        itemsY.add(element);
    }
    public void removeElement(IMapElement element)
    {
        itemsX.remove(element);
        itemsY.remove(element);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if(itemsX.removeIf(element -> (element instanceof Animal && element.getPosition().equals(newPosition))))


    }*/
    Comparator<Vector2d> comparatorX = new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d a, Vector2d b)
        {
            if(a.x == b.x)
                return a.y-b.y;
            return a.x-b.x;
        }
    };
    Comparator<Vector2d> comparatorY = new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d a, Vector2d b)
        {
            if(a.y == b.y)
                return a.x-b.x;
            return a.y-b.y;
        }
    };
    SortedSet<Vector2d> itemsX = new TreeSet<>(comparatorX);
    SortedSet<Vector2d> itemsY = new TreeSet<>(comparatorY);
    public void addElement(Vector2d a)
    {
        itemsX.add(a);
        itemsY.add(a);
    }
    public void removeElement(Vector2d a)
    {
        itemsX.remove(a);
        itemsY.remove(a);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeElement(oldPosition);
        addElement(newPosition);
    }
    public Vector2d lowerLeft()
    {
        return new Vector2d(itemsX.first().x, itemsY.first().y);
    }
    public Vector2d upperRight()
    {
        return new Vector2d(itemsX.last().x, itemsY.last().y);
    }
}
