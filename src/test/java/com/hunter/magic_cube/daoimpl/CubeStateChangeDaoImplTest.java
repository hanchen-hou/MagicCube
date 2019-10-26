package com.hunter.magic_cube.daoimpl;

import com.hunter.magic_cube.daoimpl.CubeDaoImpl;
import com.hunter.magic_cube.daoimpl.CubeStateChangeDaoImpl;
import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.model.CubeStateChange;
import java.sql.*;
import java.util.*;
import org.junit.*;

/**
 *
 * @author Hunter
 */
public class CubeStateChangeDaoImplTest {

    private CubeDaoImpl cubeDaoImpl;
    private CubeStateChangeDaoImpl cubeStateChangeDaoImpl;

    private Connection inMemoryConnection;

    @Before
    public void before() {
        try {
            inMemoryConnection = DriverManager.getConnection("jdbc:sqlite::memory:");
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        }

        cubeDaoImpl = new CubeDaoImpl(inMemoryConnection);
        cubeStateChangeDaoImpl = new CubeStateChangeDaoImpl(inMemoryConnection);

        cubeDaoImpl.resetTable();
        cubeStateChangeDaoImpl.resetTable();
    }

    @Test
    public void testInsertAndSelection() {
        try {
            double[] tiles1 = {1, 1, 2, 2};
            Cube c1 = new Cube(tiles1, 0);
            cubeDaoImpl.insert(c1);
            
            double[] tiles2 = {3, 3, 4, 4};
            Cube c2 = new Cube(tiles2, 0);
            cubeDaoImpl.insert(c2);
            
            CubeStateChange stageChange = new CubeStateChange(c1, c2, "test");
            cubeStateChangeDaoImpl.insert(stageChange);
            
            List<CubeStateChange> l = cubeStateChangeDaoImpl.getByFrom(c1);
            
            Assert.assertEquals(1, l.size());
            
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void after() {
        try {
            if (inMemoryConnection != null) {
                inMemoryConnection.close();
            }
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        }
    }
}
