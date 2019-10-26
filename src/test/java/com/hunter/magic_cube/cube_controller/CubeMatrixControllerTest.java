package com.hunter.magic_cube.cube_controller;

import com.hunter.magic_cube.cube_controller.CubeMatrixController;
import org.junit.*;
import Jama.*; 

/**
 *
 * @author Hunter
 */
public class CubeMatrixControllerTest {
    @Test
    public void testGenerateRotationMatrix() {
        System.out.println("Test generateRotationMatrix mathod");
        int[] colRowLayout = {1,2,3,0};
        Matrix testMatrix = CubeMatrixController.generateRotationMatrix(colRowLayout);
        
        double[][] a1 = {{11,22,33,44}};
        Matrix v1 = new Matrix(a1);
        Matrix actual = v1.times(testMatrix);
        
        double[][] exp = {{22,33,44,11}};
        Assert.assertArrayEquals(actual.getArray(), exp);
    }
}
