package com.hunter.magic_cube.model_factory;

import com.hunter.magic_cube.model_factory.PocketCubeFactory;
import com.hunter.magic_cube.model.Cube;
import org.junit.*;

/**
 *
 * @author Hunter
 */
public class PocketCubeFactoryTest {
    @Test
    public void testCreateSolvedPocketCube(){
        Cube cube = PocketCubeFactory.createSolvedPocketCube();
        
        double[] expected = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5};
        double delta = 0.0;
        Assert.assertArrayEquals(expected, cube.getTiles(), delta);
    }
    
    @Test
    public void testStandardizePocketCube(){
        double[] tiles = {10,10,10,10,21,21,21,21,32,32,32,32,13,13,13,13,24,24,24,24,35,35,35,35};
        Cube cube = PocketCubeFactory.createPocketCube(tiles, 0);
        double[] actual = PocketCubeFactory.standardizePocketCube(cube).getTiles();
        
        double delta = 0.0;
        double[] expected = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5};
        Assert.assertArrayEquals(expected, actual, delta);
    }
}
