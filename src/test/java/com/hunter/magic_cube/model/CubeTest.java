package com.hunter.magic_cube.model;

import com.hunter.magic_cube.model.Cube;
import org.junit.*;

/**
 *
 * @author Hunter
 */
public class CubeTest {

    @Test
    public void testGetTilesAsString() {
        double[] tiles = {1.0, 2.0, 3.0, 4.0, 5.0};
        String expected = "12345";

        Cube cube = new Cube(tiles, 0);
        String actual = cube.getTilesAsString();

        Assert.assertEquals(expected, actual);
    }
}
