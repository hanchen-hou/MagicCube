package com.hunter.magic_cube.cube_controller;

import Jama.*; 
import com.hunter.magic_cube.cube_rotation_action.IAxis;
import com.hunter.magic_cube.cube_rotation_action.IBack;
import com.hunter.magic_cube.cube_rotation_action.IDown;
import com.hunter.magic_cube.cube_rotation_action.IFront;
import com.hunter.magic_cube.cube_rotation_action.ILeft;
import com.hunter.magic_cube.cube_rotation_action.IRight;
import com.hunter.magic_cube.cube_rotation_action.IUp;

/**
 *
 * @author Hunter
 */
abstract public class CubeMatrixController implements IAxis, IFront, IRight, IUp, ILeft, IDown, IBack {
    
    /**
     * index of colRowLayout indicate col index
     * value indicate row index
     * the seleted entity will be set to 1
     * 
     * e.g. colRowLayout = {1,2,3,0}
     * then set (0,1), (1,2), (2,3), (3,0) to 1
     * 
     * @param colRowLayout
     * @return a n*n Matrix where n is the length of colRowLayout
     */
    static public Matrix generateRotationMatrix(int[] colRowLayout){
        int size = colRowLayout.length;
        Matrix result = new Matrix(size, size);
        
        for(int col = 0; col < size; col++){
            int row = colRowLayout[col];
            result.set(row, col, 1);
        }
        
        return result;
    } 
}
