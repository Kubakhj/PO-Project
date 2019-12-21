package agh.cs.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal
{
    private WorldMap map;
    private MapDirection mapD = MapDirection.N;
    private Vector2d position = new Vector2d(2,2);
    int energy;
    int moveEnergy;
    Gene myGenes;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(WorldMap map, Vector2d initialPosition,int startEnergy){
        this.map=map;
        this.mapD = mapD.randomDirection();
        this.position=initialPosition;
        this.energy = startEnergy;
        this.myGenes = new Gene();
    }
    public String toString() {
        switch (this.mapD) {
            case N: return "↑";
            case NE: return "↗";
            case E: return "→";
            case SE: return "↘";
            case S: return "↓";
            case SW: return "↙";
            case W:  return "←";
            case NW: return "↖";
            default: return null;
        }
    }
    Vector2d getPosition() {return position;}
    void moveForward() // animal moves
    {
        Vector2d tmp = new Vector2d(position);

        tmp = position.add(Objects.requireNonNull(mapD.toUnitVector()));

        if (map.canMoveTo(tmp)) {
            this.positionChanged(this.position,tmp);
            map.positionChanged(this.position,tmp);
            position = new Vector2d(tmp);
        }
        else if(map.objectAt(tmp) instanceof Grass)
        {
            Grass grass = (Grass) map.objectAt(tmp);
            eatGrass(grass);
            this.energy+=30;
        }
    }
    void turn(int key){
        for(int i=0;i<key;i++){
            mapD = mapD.next();
        }
    }
    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    private void eatGrass(Grass grass){
        map.plants.remove(grass.getPosition());
        }
    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
    void setMoveEnergy(int moveEnergy){
        this.moveEnergy = moveEnergy;
    }

    }
