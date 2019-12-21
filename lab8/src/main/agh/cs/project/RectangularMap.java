package agh.cs.project;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        upperRight = new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position));
    }
}
