package agh.cs.project;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    Vector2d lowerLeft = new Vector2d(0,0);
    Vector2d upperRight;

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(this.lowerLeft, this.upperRight);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position){
        return ((!this.isOccupied(position))&&(position.follows(lowerLeft))&&(position.precedes(upperRight)));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
            return animals.get(position)!=null;
    }
}
