package agh.cs.project;

public class Vector2d
{
    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2d(Vector2d v)
    {
        this.x = v.x;
        this.y = v.y;
    }
    public String toString()
    {
        return "("+this.x+","+this.y+")";
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    //
    public boolean equals(Object other) // My Code
    {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return (that.x == this.x && that.y == this.y);
    }
    boolean follows(Vector2d other){
        return (this.x >= other.x && this.y >= other.y);
    }
    boolean precedes(Vector2d other){
        return (this.x <= other.x && this.y <= other.y);
    }
    Vector2d upperRight(Vector2d v){
        return new Vector2d(Math.max(this.x, v.x), Math.max(this.y, v.y));
    }
    Vector2d lowerLeft(Vector2d v){
        return new Vector2d(Math.min(this.x, v.x), Math.min(this.y, v.y));
    }
    Vector2d add(Vector2d v){
        return new Vector2d(this.x + v.x, this.y + v.y);
    }
    Vector2d subtract(Vector2d v){
        return new Vector2d(this.x - v.x, this.y - v.y);
    }public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }
}
