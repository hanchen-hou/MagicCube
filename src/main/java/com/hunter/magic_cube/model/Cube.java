package com.hunter.magic_cube.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hunter
 */
public class Cube {

    // represent cube state
    // each element represent a surface
    // e.g. a 2x2 cube has 4 tiles in each surface, 6*4=24 tiltes in total
    private final double[] tiles;
    
    private int numberOfRotations;
    
    public Cube(String tilesInString, int numberOfRotations) {
        if(numberOfRotations < 0)
            throw new RuntimeException("Number of rotations cannot be negative value.");
        
        double[] t = new double[tilesInString.length()];
        
        for(int i = 0; i < t.length; i++){
            t[i] = Double.valueOf(tilesInString.charAt(i) - '0'); // char -> double
        }
        
        this.tiles = t;
        this.numberOfRotations = numberOfRotations;
    }

    public Cube(double[] tiles, int numberOfRotations) {
        if(numberOfRotations < 0)
            throw new RuntimeException("Number of rotations cannot be negative value.");
        
        if(tiles == null) 
            throw new NullPointerException("Cannot initial with null value.");
        
        this.tiles = tiles.clone();
        this.numberOfRotations = numberOfRotations;
    }

    public double[] getTiles() {
        return this.tiles.clone();
    }
    
    public void setTileValueByIndex(int index, double value){
        if(index < 0 || index > this.tiles.length) return;
        
        tiles[index] = value;
    }
    
    public String getTilesAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i = 0; i < this.tiles.length; i++){
            String str = Integer.toString((int)this.tiles[i]);
            stringBuilder.append(str);
        }
        
        return stringBuilder.toString();
    }
    
    public int getNumberOfRotations(){
        return this.numberOfRotations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cube cube = (Cube) o;
        return Arrays.equals(this.getTiles(), cube.getTiles());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        for(int i = 0; i < tiles.length; i++){
            hash = 31 * hash + (int)tiles[i];
        }
        return hash;
    }
}
