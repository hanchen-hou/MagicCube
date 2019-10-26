package com.hunter.magic_cube.cube_rotation_action;

/**
 *
 * @author Hunter
 */
public interface ILeft {
    static public final String LEFT_CLOCKWISE_NOTATION = "L";

    static public final String LEFT_COUNTER_CLOCKWISE_NOTATION = "L'";

    static public final String LEFT_CLOCKWISE_2X_NOTATION = "L2";

    double[][] rotateLeftClockwise(double[][] states);

    double[][] rotateLeftCounterClockwise(double[][] states);

    double[][] rotateLeftClockwise2x(double[][] states);
}
