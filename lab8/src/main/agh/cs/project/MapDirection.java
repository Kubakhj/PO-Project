package agh.cs.project;

import java.util.Random;

public enum MapDirection
{
    N,NE,E,SE,S,SW,W,NW;

    public String toString()
    {
        switch(this) {
            case N : return "Północ";
            case NE: return "Polnocny-Wschód";
            case E: return "Wschód";
            case SE: return "Poludniowy-Wschód";
            case S : return "Południe";
            case SW : return "Poludniowy-Zachod";
            case W: return "Zachód";
            case NW: return "Polnocny-Zachod";
        }
        return null;
    }

    public MapDirection next(){
        switch (this){
            case N: return MapDirection.NE;
            case NE: return MapDirection.E;
            case E: return MapDirection.SE;
            case SE: return MapDirection.S;
            case S: return MapDirection.SW;
            case SW: return MapDirection.W;
            case W: return MapDirection.NW;
            case NW: return MapDirection.N;
            default: return null;
        }
    }
    public Vector2d toUnitVector(){
        switch(this){
            case N: return new Vector2d(0,1);
            case NE: return new Vector2d(1,1);
            case E: return new Vector2d(1,0);
            case SE: return new Vector2d(1,-1);
            case S: return new Vector2d(0,-1);
            case SW: return new Vector2d(-1,-1);
            case W: return new Vector2d(-1,0);
            case NW: return new Vector2d(-1,1);
        }
        return null;
    }
    public MapDirection randomDirection(){
        Random r = new Random();
        return MapDirection.values()[r.nextInt(8)];
    }
}
