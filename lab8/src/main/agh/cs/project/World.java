package agh.cs.project;

import javax.swing.*;
import java.util.Random;

public class World {
    public static void main(String[] args) throws InterruptedException {

        WorldMap map = new WorldMap();

        int startEnergy = 100;
        int moveEnergy = 1;
        int numberOfAnimals = 50;
        int numberOfDays = 5000;
        int delay = 400; // delay in milliseconds

        for (int i = 0; i < numberOfAnimals; i++) {
            Random random = new Random();
            Animal animal = new Animal(map, new Vector2d(random.nextInt(100), random.nextInt(30)),startEnergy);
            animal.setMoveEnergy(moveEnergy);
            map.place(animal);
            map.animalsList.add(animal);
        }

        LifeSimulation lifeSimulation = new LifeSimulation();
        JTextArea t = new JTextArea(map.toString());
        lifeSimulation.setT(t);
        System.out.println(map.toString());
        lifeSimulation.display();
        Thread.sleep(delay);
        for (int i = 0; i < numberOfDays; i++) {
            lifeSimulation.pane.remove(lifeSimulation.t);
            t = new JTextArea(map.toString());
            map.everydayRoutine();
            lifeSimulation.setT(t);
            lifeSimulation.display();
            System.out.println(map.toString());
            Thread.sleep(delay);
        }
    }
}

