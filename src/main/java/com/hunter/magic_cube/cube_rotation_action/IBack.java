package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IBack {

    static public final String BACK_CLOCKWISE_NOTATION = "B";

    static public final String BACK_COUNTER_CLOCKWISE_NOTATION = "B'";

    static public final String BACK_CLOCKWISE_2X_NOTATION = "B2";

    double[][] rotateBackClockwise(double[][] states);

    double[][] rotateBackCounterClockwise(double[][] states);

    double[][] rotateBackClockwise2x(double[][] states);
}
