package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IRight {

    static public final String RIGHT_CLOCKWISE_NOTATION = "R";

    static public final String RIGHT_COUNTER_CLOCKWISE_NOTATION = "R'";

    static public final String RIGHT_CLOCKWISE_2X_NOTATION = "R2";

    double[][] rotateRightClockwise(double[][] states);

    double[][] rotateRightCounterClockwise(double[][] states);

    double[][] rotateRightClockwise2x(double[][] states);
}
