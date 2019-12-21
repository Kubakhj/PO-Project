package agh.cs.project;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.*;

public class RectangularMapTest {
    Vector2d v3_3 = new Vector2d(3,3);
    Vector2d v5_5 = new Vector2d(5,5);
    WorldMap map = new WorldMap();
    Animal malpa3_3 = new Animal(map,v3_3,100);
    Animal malpa5_5 = new Animal(map,v5_5,100);

    @Test
    public void notAbleToPlace() {
        map.place(malpa3_3);
        try {
            assert(map.place(malpa3_3));
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
    @Test
    public void canMoveTo() {
        assertTrue(map.canMoveTo(v3_3));
    }

    @Test
    public void isOccupied() {
        assertFalse(map.isOccupied(v3_3));
        map.place(malpa3_3);
        assertTrue(map.isOccupied(v3_3));
    }

    @Test
    public void objectAt() {
        map.place(malpa3_3);
        assertEquals(malpa3_3, map.objectAt(v3_3));
    }

}