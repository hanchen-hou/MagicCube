package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface IFront {

    static public final String FRONT_CLOCKWISE_NOTATION = "F";

    static public final String FRONT_COUNTER_CLOCKWISE_NOTATION = "F'";

    static public final String FRONT_CLOCKWISE_2X_NOTATION = "F2";

    double[][] rotateFrontClockwise(double[][] states);

    double[][] rotateFrontCounterClockwise(double[][] states);

    double[][] rotateFrontClockwise2x(double[][] states);
}
