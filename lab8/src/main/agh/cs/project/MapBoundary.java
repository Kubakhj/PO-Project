package agh.cs.project;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private SortedSet<Vector2d> xSet = new TreeSet<>((a, b) -> {
        if (a.x != b.x) return a.x - b.x;
        return a.y - b.y;
    });
    private SortedSet<Vector2d> ySet = new TreeSet<>((a, b) -> {
        if (a.x != b.x) return a.y - b.y;
        return a.x - b.x;
    });

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xSet.remove(oldPosition);
        xSet.add(newPosition);
        ySet.remove(oldPosition);
        ySet.add(newPosition);
    }
    void addPosition(Vector2d position) {
        xSet.add(position);
        ySet.add(position);
    }
}
