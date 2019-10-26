package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IUp {

    static public final String UP_CLOCKWISE_NOTATION = "U";

    static public final String UP_COUNTER_CLOCKWISE_NOTATION = "U'";

    static public final String UP_CLOCKWISE_2X_NOTATION = "U2";

    double[][] rotateUpClockwise(double[][] states);

    double[][] rotateUpCounterClockwise(double[][] states);

    double[][] rotateUpClockwise2x(double[][] states);
}
