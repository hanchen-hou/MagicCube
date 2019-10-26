package com.hunter.magic_cube.cube_controller;

import Jama.*;

/*

Index Layout

+---+---+----+----+----+----+----+----+
|   |   | 8  | 9  |    |    |    |    |
+---+---+----+----+----+----+----+----+
|   |   | 11 | 10 |    |    |    |    |
+---+---+----+----+----+----+----+----+
| 4 | 5 | 0  | 1  | 12 | 13 | 20 | 21 |
+---+---+----+----+----+----+----+----+
| 7 | 6 | 3  | 2  | 15 | 14 | 23 | 22 |
+---+---+----+----+----+----+----+----+
|   |   | 16 | 17 |    |    |    |    |
+---+---+----+----+----+----+----+----+
|   |   | 19 | 18 |    |    |    |    |
+---+---+----+----+----+----+----+----+

 */
/**
 *
 * @author Hunter
 */
public class PocketCubeMatrixController extends CubeMatrixController {

    private static PocketCubeMatrixController _pocketCubeController = null;

    public static PocketCubeMatrixController getController() {
        if (_pocketCubeController == null) {
            _pocketCubeController = new PocketCubeMatrixController();
        }

        return _pocketCubeController;
    }

    private PocketCubeMatrixController() {
        // generate matices
        int[] rotateXAxisClockwiseMatrixColRow = {16, 17, 18, 19, 5, 6, 7, 4, 0, 1, 2, 3, 15, 12, 13, 14, 22, 23, 20, 21, 10, 11, 8, 9};
        rotateXAxisClockwiseMatrix = CubeMatrixController.generateRotationMatrix(rotateXAxisClockwiseMatrixColRow);
        rotateXAxisCounterClockwiseMatrix = rotateXAxisClockwiseMatrix.times(rotateXAxisClockwiseMatrix).times(rotateXAxisClockwiseMatrix);

        int[] rotateYAxisClockwiseMatrixColRow = {12, 13, 14, 15, 0, 1, 2, 3, 11, 8, 9, 10, 20, 21, 22, 23, 17, 18, 19, 16, 4, 5, 6, 7};
        rotateYAxisClockwiseMatrix = CubeMatrixController.generateRotationMatrix(rotateYAxisClockwiseMatrixColRow);
        rotateYAxisCounterClockwiseMatrix = rotateYAxisClockwiseMatrix.times(rotateYAxisClockwiseMatrix).times(rotateYAxisClockwiseMatrix);

        int[] rotateZAxisClockwiseMatrixColRow = {3, 0, 1, 2, 19, 16, 17, 18, 7, 4, 5, 6, 11, 8, 9, 10, 15, 12, 13, 14, 21, 22, 23, 20};
        rotateZAxisClockwiseMatrix = CubeMatrixController.generateRotationMatrix(rotateZAxisClockwiseMatrixColRow);
        rotateZAxisCounterClockwiseMatrix = rotateZAxisClockwiseMatrix.times(rotateZAxisClockwiseMatrix).times(rotateZAxisClockwiseMatrix);

        int[] rotateFrontClockwiseMatrixColRow = {3, 0, 1, 2, 4, 16, 17, 7, 8, 9, 5, 6, 11, 13, 14, 10, 15, 12, 18, 19, 20, 21, 22, 23};
        rotateFrontClockwiseMatrix = CubeMatrixController.generateRotationMatrix(rotateFrontClockwiseMatrixColRow);
        rotateFrontClockwise2xMatrix = rotateFrontClockwiseMatrix.times(rotateFrontClockwiseMatrix);
        rotateFrontCounterClockwiseMatrix = rotateFrontClockwise2xMatrix.times(rotateFrontClockwiseMatrix);

        rotateRightClockwiseMatrix = rotateYAxisClockwiseMatrix.times(rotateFrontClockwiseMatrix).times(rotateYAxisCounterClockwiseMatrix);
        rotateRightClockwise2xMatrix = rotateRightClockwiseMatrix.times(rotateRightClockwiseMatrix);
        rotateRightCounterClockwiseMatrix = rotateRightClockwise2xMatrix.times(rotateRightClockwiseMatrix);
        
        rotateUpClockwiseMatrix = rotateXAxisCounterClockwiseMatrix.times(rotateFrontClockwiseMatrix).times(rotateXAxisClockwiseMatrix);
        rotateUpClockwise2xMatrix = rotateUpClockwiseMatrix.times(rotateUpClockwiseMatrix);
        rotateUpCounterClockwiseMatrix = rotateUpClockwise2xMatrix.times(rotateUpClockwiseMatrix);
        
        rotateLeftClockwiseMatrix = rotateYAxisCounterClockwiseMatrix.times(rotateFrontClockwiseMatrix).times(rotateYAxisClockwiseMatrix);
        rotateLeftClockwise2xMatrix = rotateLeftClockwiseMatrix.times(rotateLeftClockwiseMatrix);
        rotateLeftCounterClockwiseMatrix = rotateLeftClockwise2xMatrix.times(rotateLeftClockwiseMatrix);
        
        rotateDownClockwiseMatrix = rotateXAxisClockwiseMatrix.times(rotateFrontClockwiseMatrix).times(rotateXAxisCounterClockwiseMatrix);
        rotateDownClockwise2xMatrix = rotateDownClockwiseMatrix.times(rotateDownClockwiseMatrix);
        rotateDownCounterClockwiseMatrix = rotateDownClockwise2xMatrix.times(rotateDownClockwiseMatrix);
        
        rotateBackClockwiseMatrix = rotateYAxisClockwiseMatrix.times(rotateYAxisClockwiseMatrix).times(rotateFrontClockwiseMatrix).times(rotateYAxisCounterClockwiseMatrix).times(rotateYAxisCounterClockwiseMatrix);
        rotateBackClockwise2xMatrix = rotateBackClockwiseMatrix.times(rotateBackClockwiseMatrix);
        rotateBackCounterClockwiseMatrix = rotateBackClockwise2xMatrix.times(rotateBackClockwiseMatrix);
    }

    private Matrix rotateXAxisClockwiseMatrix = null;

    @Override
    public double[][] rotateXAxisClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateXAxisClockwiseMatrix).getArray();
    }

    private Matrix rotateXAxisCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateXAxisCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateXAxisCounterClockwiseMatrix).getArray();
    }

    private Matrix rotateYAxisClockwiseMatrix = null;

    @Override
    public double[][] rotateYAxisClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateYAxisClockwiseMatrix).getArray();
    }

    private Matrix rotateYAxisCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateYAxisCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateYAxisCounterClockwiseMatrix).getArray();
    }

    private Matrix rotateZAxisClockwiseMatrix = null;

    @Override
    public double[][] rotateZAxisClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateZAxisClockwiseMatrix).getArray();
    }

    private Matrix rotateZAxisCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateZAxisCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateZAxisCounterClockwiseMatrix).getArray();
    }

    /*
     * Front
     */
    private Matrix rotateFrontClockwiseMatrix = null;
    private Matrix rotateFrontClockwise2xMatrix = null;
    private Matrix rotateFrontCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateFrontClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateFrontClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateFrontCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateFrontCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateFrontClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateFrontClockwise2xMatrix).getArray();
    }

    /*
     * Right
     */
    private Matrix rotateRightClockwiseMatrix = null;
    private Matrix rotateRightClockwise2xMatrix = null;
    private Matrix rotateRightCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateRightClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateRightClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateRightCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateRightCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateRightClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateRightClockwise2xMatrix).getArray();
    }

    /*
     * Up
     */
    private Matrix rotateUpClockwiseMatrix = null;
    private Matrix rotateUpClockwise2xMatrix = null;
    private Matrix rotateUpCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateUpClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateUpClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateUpCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateUpCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateUpClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateUpClockwise2xMatrix).getArray();
    }
    
    /*
     * Left
     */
    private Matrix rotateLeftClockwiseMatrix = null;
    private Matrix rotateLeftClockwise2xMatrix = null;
    private Matrix rotateLeftCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateLeftClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateLeftClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateLeftCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateLeftCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateLeftClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateLeftClockwise2xMatrix).getArray();
    }
    
    /*
     * Down
     */
    private Matrix rotateDownClockwiseMatrix = null;
    private Matrix rotateDownClockwise2xMatrix = null;
    private Matrix rotateDownCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateDownClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateDownClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateDownCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateDownCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateDownClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateDownClockwise2xMatrix).getArray();
    }
    
    /*
     * Back
     */
    private Matrix rotateBackClockwiseMatrix = null;
    private Matrix rotateBackClockwise2xMatrix = null;
    private Matrix rotateBackCounterClockwiseMatrix = null;

    @Override
    public double[][] rotateBackClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateBackClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateBackCounterClockwise(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateBackCounterClockwiseMatrix).getArray();
    }

    @Override
    public double[][] rotateBackClockwise2x(double[][] states) {
        Matrix vectors = new Matrix(states);
        return vectors.times(rotateBackClockwise2xMatrix).getArray();
    }
}
