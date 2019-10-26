package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IAxis {
    
    /**
     * x - rotate the entire cube on R
     * @param states
     * @return new states
     */
    public double[][] rotateXAxisClockwise(double[][] states);
    
    /**
     * x - rotate the entire cube on R'
     * @param states
     * @return new states
     */
    public double[][] rotateXAxisCounterClockwise(double[][] states);
    
        /**
     * x - rotate the entire cube on U
     * @param states
     * @return new states
     */
    public double[][] rotateYAxisClockwise(double[][] states);
    
    /**
     * y - rotate the entire cube on U'
     * @param states
     * @return new states
     */
    public double[][] rotateYAxisCounterClockwise(double[][] states);
    
    /**
     * z - rotate the entire cube on F
     * @param states
     * @return new states
     */
    public double[][] rotateZAxisClockwise(double[][] states);
    
    /**
     * z - rotate the entire cube on F'
     * @param states
     * @return new states
     */
    public double[][] rotateZAxisCounterClockwise(double[][] states);

}
