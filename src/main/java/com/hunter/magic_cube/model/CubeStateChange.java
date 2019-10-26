package com.hunter.magic_cube.model;

/**
 *
 * @author Hunter
 */
public class CubeStateChange {

    Cube from;
    Cube to;
    String how;

    public CubeStateChange(Cube from, Cube to, String how) {
        this.from = from;
        this.to = to;
        this.how = how;
    }

    public Cube getFrom() {
        return this.from;
    }

    public Cube getTo() {
        return this.to;
    }

    public String getHow() {
        return this.how;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CubeStateChange cubeStateChange = (CubeStateChange) o;
        return this.getFrom().equals(cubeStateChange.getFrom())
                && this.getTo().equals(cubeStateChange.getTo())
                && this.getHow().equals(this.getHow());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.getFrom().hashCode();
        hash = 31 * hash + this.getTo().hashCode();
        hash = 31 * hash + this.getHow().hashCode();
        return hash;
    }
}
