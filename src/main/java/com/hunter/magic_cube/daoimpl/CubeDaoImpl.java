package com.hunter.magic_cube.daoimpl;

import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.dao.ICubeDao;
import com.hunter.magic_cube.db.SQliteDatabase;
import com.hunter.magic_cube.model_factory.PocketCubeFactory;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Hunter
 */
public class CubeDaoImpl implements ICubeDao {

    static public final String TABLE_NAME = "cube";

    static public final String ID_COL_NAME = "id";
    static public final String STATE_COL_NAME = "state";
    static public final String NUMBER_OF_ROTATION_COL_NAME = "number_of_rotations";

    static private final String STATE_INDEX_NAME = "idx_state";

    private Connection db;

    public CubeDaoImpl() {
        this(SQliteDatabase.getDbConnection());
    }

    public CubeDaoImpl(Connection db) {
        this.db = db;
    }

    @Override
    public List<Cube> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Cube c) {
        if (c == null) {
            return;
        }

        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                TABLE_NAME, STATE_COL_NAME, NUMBER_OF_ROTATION_COL_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, c.getTilesAsString());
            stmt.setInt(2, c.getNumberOfRotations());
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void resetTable() {

        String dropIndexSql = String.format("DROP INDEX IF EXISTS %s", STATE_INDEX_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(dropIndexSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String dropTableSql = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(dropTableSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String createTableSql = String.format(
                "CREATE TABLE IF NOT EXISTS %s ("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "%s TEXT UNIQUE,"
                + "%s INTEGER"
                + ")", TABLE_NAME, ID_COL_NAME, STATE_COL_NAME, NUMBER_OF_ROTATION_COL_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(createTableSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String createIndexSql = String.format(
                "CREATE UNIQUE INDEX IF NOT EXISTS %s ON %s ( %s )",
                STATE_INDEX_NAME, TABLE_NAME, STATE_COL_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(createIndexSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Cube getByState(double[] tiles) {
        Cube c = PocketCubeFactory.createPocketCube(tiles, 0);
        String tilesInString = c.getTilesAsString();

        return getByState(tilesInString);
    }

    @Override
    public Cube getByState(String tiles) {
        Cube result = null;

        String sql = String.format("SELECT * FROM %s WHERE %s=?", TABLE_NAME, STATE_COL_NAME);

        try ( PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, tiles);

            // get result
            ResultSet rs = stmt.executeQuery();

            // Since state is unique, return 1st result
            if (rs.next()) {
                String tilesInString = rs.getString(STATE_COL_NAME);
                int numberOfRotations = rs.getInt(NUMBER_OF_ROTATION_COL_NAME);
                result = PocketCubeFactory.createPocketCube(tilesInString, numberOfRotations);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
