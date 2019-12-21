package agh.cs.project;

import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class WorldMap extends AbstractWorldMap {

    private MapBoundary mapBoundary;

    List<Animal> animalsList = new LinkedList<>();

    Map<Vector2d, Grass> plants = new LinkedHashMap<>();

    WorldMap() {
        this.mapBoundary = new MapBoundary();

        upperRight = new Vector2d(100, 30);
        lowerLeft = new Vector2d(0, 0);

        generateGrassInsideJungle();
    }

    @Override
    public String toString() {

        for (Map.Entry<Vector2d, Animal> entry : animals.entrySet()) {
            Animal animal = entry.getValue();
        }
        return super.toString();
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        else return plants.get(position) != null;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        if (super.place(animal)) {
            mapBoundary.addPosition(animal.getPosition());
            animal.addObserver(mapBoundary);
            mapBoundary.addPosition(animal.getPosition());
        }
        return true;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.get(position) != null) return animals.get(position);
        else if (plants.get(position) != null) return plants.get(position);
        return null;
    }

    private void generateGrassInsideJungle(){
        int x, y;
        int i=0;
        do {
            x = ThreadLocalRandom.current().nextInt(45, 55);
            y = ThreadLocalRandom.current().nextInt(10, 20);
            i++;
        }
        while ((objectAt(new Vector2d(x, y)) instanceof Grass)&&(i<100));
        Vector2d tmpPos = new Vector2d(x, y);
        Grass grass = new Grass(tmpPos);
        plants.put(tmpPos, grass);
        mapBoundary.addPosition(grass.getPosition());
    }
    private void generateGrassOutsideJungle(){
        int x, y;
        Vector2d tmp;
        do {
            x = ThreadLocalRandom.current().nextInt(0, 100);
            y = ThreadLocalRandom.current().nextInt(0, 30);
            tmp = new Vector2d(x,y);
        }
        while ((objectAt(tmp) instanceof Grass) && (tmp.precedes(new Vector2d(55,20)))&&(tmp.follows(new Vector2d(45,10))));
        Vector2d tmpPos = new Vector2d(x, y);
        Grass grass = new Grass(tmpPos);
        plants.put(tmpPos, grass);
        mapBoundary.addPosition(grass.getPosition());
    }

    private void moveAllAnimals() {
        for (Animal animal : animalsList) {
            animal.moveForward();
        }
    }

    private void turnAllAnimals() {
        for (Animal animal : animalsList) {
            animal.turn(animal.myGenes.randomGene());
        }
    }

    private void clearDeadAnimals() {
        List<Animal> animalsListIt = new ArrayList<>();
        animalsListIt.addAll(animalsList);
        int i = 0;
        for (Animal animal : animalsListIt) {
            if (animal.energy <= 0) {
                animalsList.remove(i);
                animals.remove(animal.getPosition());
            } else {
                i++;
            }
        }
    }

    void everydayRoutine() {
        generateGrassInsideJungle();
        generateGrassOutsideJungle();
        downgradeAllAnimalsEnergy();
        moveAllAnimals();
        turnAllAnimals();
        clearDeadAnimals();
    }

    private void downgradeAllAnimalsEnergy() {
        for (Animal animal : animalsList) {
            animal.energy-=animal.moveEnergy;
        }
    }
}
