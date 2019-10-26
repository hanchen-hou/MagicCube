package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IDown {

    static public final String DOWN_CLOCKWISE_NOTATION = "D";

    static public final String DOWN_COUNTER_CLOCKWISE_NOTATION = "D'";

    static public final String DOWN_CLOCKWISE_2X_NOTATION = "D2";

    double[][] rotateDownClockwise(double[][] states);

    double[][] rotateDownCounterClockwise(double[][] states);

    double[][] rotateDownClockwise2x(double[][] states);
}
