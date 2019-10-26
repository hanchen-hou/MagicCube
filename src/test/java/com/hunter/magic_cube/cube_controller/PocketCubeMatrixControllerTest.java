package com.hunter.magic_cube.cube_controller;

import com.hunter.magic_cube.cube_controller.PocketCubeMatrixController;
import Jama.Matrix;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 *
 * @author Hunter
 */
public class PocketCubeMatrixControllerTest {

    PocketCubeMatrixController pocketCubeMatrixController = null;

    @Before
    public void beforeAll() {
        System.out.println("Create PocketCubeMatrixController instance.");
        pocketCubeMatrixController = PocketCubeMatrixController.getController();
    }

    // https://stackoverflow.com/questions/24136055
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Test
    public void testRotateXAxisClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateXAxisClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateXAxisClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateXAxisClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateXAxisClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateXAxisCounterClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateXAxisCounterClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateXAxisCounterClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateXAxisCounterClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateXAxisCounterClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateYAxisClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateYAxisClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateYAxisClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateYAxisClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateYAxisClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateYAxisCounterClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateYAxisCounterClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateYAxisCounterClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateYAxisCounterClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateYAxisCounterClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateZAxisClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateZAxisClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateZAxisClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateZAxisClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateZAxisClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateZAxisCounterClockwise() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray = testVector.getArray();

        // rotate 4 times should return the same result
        double[][] rotate1x = pocketCubeMatrixController.rotateZAxisCounterClockwise(testArray);
        double[][] rotate2x = pocketCubeMatrixController.rotateZAxisCounterClockwise(rotate1x);
        double[][] rotate3x = pocketCubeMatrixController.rotateZAxisCounterClockwise(rotate2x);
        double[][] rotate4x = pocketCubeMatrixController.rotateZAxisCounterClockwise(rotate3x);

        Assert.assertArrayEquals(rotate4x, testArray);
    }

    @Test
    public void testRotateFront() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray1 = testVector.getArrayCopy();
        double[][] testArray2 = testVector.getArrayCopy();
        double[][] testArray3 = testVector.getArrayCopy();

        for (int i = 0; i < 2; i++) {
            testArray1 = pocketCubeMatrixController.rotateFrontClockwise(testArray1);
        }
        for (int i = 0; i < 2; i++) {
            testArray2 = pocketCubeMatrixController.rotateFrontCounterClockwise(testArray2);
        }
        for (int i = 0; i < 1; i++) {
            testArray3 = pocketCubeMatrixController.rotateFrontClockwise2x(testArray3);
        }

        Assert.assertArrayEquals(testArray1, testArray2);
        Assert.assertArrayEquals(testArray1, testArray3);

    }

    @Test
    public void testRotateRight() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray1 = testVector.getArrayCopy();
        double[][] testArray2 = testVector.getArrayCopy();
        double[][] testArray3 = testVector.getArrayCopy();

        for (int i = 0; i < 2; i++) {
            testArray1 = pocketCubeMatrixController.rotateRightClockwise(testArray1);
        }
        for (int i = 0; i < 2; i++) {
            testArray2 = pocketCubeMatrixController.rotateRightCounterClockwise(testArray2);
        }
        for (int i = 0; i < 1; i++) {
            testArray3 = pocketCubeMatrixController.rotateRightClockwise2x(testArray3);
        }

        Assert.assertArrayEquals(testArray1, testArray2);
        Assert.assertArrayEquals(testArray1, testArray3);

    }

    @Test
    public void testRotateUp() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] testArray1 = testVector.getArrayCopy();
        double[][] testArray2 = testVector.getArrayCopy();
        double[][] testArray3 = testVector.getArrayCopy();

        for (int i = 0; i < 2; i++) {
            testArray1 = pocketCubeMatrixController.rotateUpClockwise(testArray1);
        }
        for (int i = 0; i < 2; i++) {
            testArray2 = pocketCubeMatrixController.rotateUpCounterClockwise(testArray2);
        }
        for (int i = 0; i < 1; i++) {
            testArray3 = pocketCubeMatrixController.rotateUpClockwise2x(testArray3);
        }

        Assert.assertArrayEquals(testArray1, testArray2);
        Assert.assertArrayEquals(testArray1, testArray3);

    }

    @Test
    public void testRotateCombinationUpAndRight() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] expected = testVector.getArrayCopy();
        double[][] actual = testVector.getArrayCopy();

        for(int steps = 15; steps > 0; steps--){
            actual = pocketCubeMatrixController.rotateUpClockwise(actual);
            actual = pocketCubeMatrixController.rotateRightClockwise(actual);
        }
        
        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testRotateCombinationUpAndFront() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] expected = testVector.getArrayCopy();
        double[][] actual = testVector.getArrayCopy();

        for(int steps = 15; steps > 0; steps--){
            actual = pocketCubeMatrixController.rotateUpClockwise(actual);
            actual = pocketCubeMatrixController.rotateFrontClockwise(actual);
        }
        
        Assert.assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testRotateCombinationFrontAndRight() {
        Matrix testVector = Matrix.random(1, 24);
        double[][] expected = testVector.getArrayCopy();
        double[][] actual = testVector.getArrayCopy();

        for(int steps = 15; steps > 0; steps--){
            actual = pocketCubeMatrixController.rotateFrontClockwise(actual);
            actual = pocketCubeMatrixController.rotateRightClockwise(actual);
        }
        
        Assert.assertArrayEquals(expected, actual);
    }
}
