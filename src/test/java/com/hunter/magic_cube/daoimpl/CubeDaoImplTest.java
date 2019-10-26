package com.hunter.magic_cube.daoimpl;

import com.hunter.magic_cube.daoimpl.CubeDaoImpl;
import com.hunter.magic_cube.db.SQliteDatabase;
import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.model_factory.PocketCubeFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.*;

/**
 *
 * @author Hunter
 */
public class CubeDaoImplTest {

    private CubeDaoImpl cubeDaoImpl;

    private Connection inMemoryConnection;

    @Before
    public void before() {
        try {
            inMemoryConnection = DriverManager.getConnection("jdbc:sqlite::memory:");
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
        }

        cubeDaoImpl = new CubeDaoImpl(inMemoryConnection);

        cubeDaoImpl.resetTable();
    }

    @Test
    public void testInsert() {
        try {
            double[] tiles = {1.0, 2.0, 3.0, 4.0, 5.0};
            Cube c = new Cube(tiles, 0);

            cubeDaoImpl.insert(c);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testGetByState() {
        try {
            Cube c = PocketCubeFactory.createSolvedPocketCube();
            cubeDaoImpl.insert(c);

            Cube actual = cubeDaoImpl.getByState(c.getTiles());

            Assert.assertNotNull(actual);

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
