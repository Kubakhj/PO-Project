package agh.cs.project;

public class Grass {
    private Vector2d position;

    Grass(Vector2d position) {
        this.position = position;
    }

    Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return "*";
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.position.x * 31;
        hash += this.position.y * 17;
        return hash;
    }

    public boolean equals(Object other)
    {
        if (this == other)
            return true;
        if (!(other instanceof Grass))
            return false;
        Grass that = (Grass) other;
        return (that.position.x == this.position.x && that.position.y == this.position.y);
    }
}