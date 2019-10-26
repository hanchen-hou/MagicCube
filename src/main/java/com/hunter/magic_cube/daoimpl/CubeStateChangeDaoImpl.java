package com.hunter.magic_cube.daoimpl;

import com.hunter.magic_cube.db.SQliteDatabase;
import com.hunter.magic_cube.model.CubeStateChange;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.hunter.magic_cube.dao.ICubeStateChangeDao;
import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.model_factory.PocketCubeFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Hunter
 */
public class CubeStateChangeDaoImpl implements ICubeStateChangeDao {

    static public final String TABLE_NAME = "cube_state_change";

    static public final String FROM_COL_NAME = "from";
    static public final String TO_COL_NAME = "to";
    static public final String HOW_COL_NAME = "how";

    private Connection db;

    public CubeStateChangeDaoImpl() {
        this(SQliteDatabase.getDbConnection());
    }

    public CubeStateChangeDaoImpl(Connection db) {
        this.db = db;
    }

    @Override
    public void resetTable() {
        String dropTableSql = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(dropTableSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String createTableSql = String.format(
                "CREATE TABLE IF NOT EXISTS %s ("
                + "\"%s\" INTEGER NOT NULL,"
                + "\"%s\" INTEGER NOT NULL,"
                + "\"%s\" TEXT NOT NULL,"
                + "FOREIGN KEY(\"%s\") REFERENCES %s(%s),"
                + "FOREIGN KEY(\"%s\") REFERENCES %s(%s),"
                + "PRIMARY KEY(\"%s\", \"%s\")"
                + ")",
                TABLE_NAME, FROM_COL_NAME, TO_COL_NAME, HOW_COL_NAME,
                FROM_COL_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.ID_COL_NAME,
                TO_COL_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.ID_COL_NAME,
                FROM_COL_NAME, TO_COL_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(createTableSql)) {
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<CubeStateChange> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(CubeStateChange c) {
        if (c == null) {
            return;
        }

        String sql = String.format("INSERT INTO %s (\"%s\", \"%s\", \"%s\") VALUES "
                + "("
                + "(SELECT %s FROM %s WHERE %s=?),"
                + "(SELECT %s FROM %s WHERE %s=?),"
                + "?)",
                TABLE_NAME, FROM_COL_NAME, TO_COL_NAME, HOW_COL_NAME,
                CubeDaoImpl.ID_COL_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.STATE_COL_NAME,
                CubeDaoImpl.ID_COL_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.STATE_COL_NAME);
        try ( PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, c.getFrom().getTilesAsString());
            stmt.setString(2, c.getTo().getTilesAsString());
            stmt.setString(3, c.getHow());
            // create table
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<CubeStateChange> getByFrom(Cube from) {
        List<CubeStateChange> results = new ArrayList<>();

        if (from == null) {
            return results;
        }

        String sql = String.format(
                "SELECT C2.\"%s\", C2.\"%s\", CSC.\"%s\" FROM "
                + "%s AS CSC, %s AS C1, %s AS C2 WHERE "
                + "CSC.\"%s\"=C1.\"%s\" AND "
                + "CSC.\"%s\"=C2.\"%s\" AND "
                + "C1.\"%s\"=?",
                CubeDaoImpl.STATE_COL_NAME, CubeDaoImpl.NUMBER_OF_ROTATION_COL_NAME, HOW_COL_NAME,
                TABLE_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.TABLE_NAME,
                FROM_COL_NAME, CubeDaoImpl.ID_COL_NAME,
                TO_COL_NAME, CubeDaoImpl.ID_COL_NAME,
                CubeDaoImpl.STATE_COL_NAME
        );
        try ( PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, from.getTilesAsString());

            // get result
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tilesInString = rs.getString(CubeDaoImpl.STATE_COL_NAME);
                int numberOfRotations = rs.getInt(CubeDaoImpl.NUMBER_OF_ROTATION_COL_NAME);
                Cube to = new Cube(tilesInString, numberOfRotations);

                String how = rs.getString(HOW_COL_NAME);
                CubeStateChange csc = new CubeStateChange(from, to, how);

                results.add(csc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }
    
    @Override
    public List<CubeStateChange> getByTo(Cube from) {
        List<CubeStateChange> results = new ArrayList<>();

        if (from == null) {
            return results;
        }

        String sql = String.format(
                "SELECT C2.\"%s\", C2.\"%s\", CSC.\"%s\" FROM "
                + "%s AS CSC, %s AS C1, %s AS C2 WHERE "
                + "CSC.\"%s\"=C1.\"%s\" AND "
                + "CSC.\"%s\"=C2.\"%s\" AND "
                + "C2.\"%s\"=?",
                CubeDaoImpl.STATE_COL_NAME, CubeDaoImpl.NUMBER_OF_ROTATION_COL_NAME, HOW_COL_NAME,
                TABLE_NAME, CubeDaoImpl.TABLE_NAME, CubeDaoImpl.TABLE_NAME,
                FROM_COL_NAME, CubeDaoImpl.ID_COL_NAME,
                TO_COL_NAME, CubeDaoImpl.ID_COL_NAME,
                CubeDaoImpl.STATE_COL_NAME
        );
        try ( PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, from.getTilesAsString());

            // get result
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tilesInString = rs.getString(CubeDaoImpl.STATE_COL_NAME);
                int numberOfRotations = rs.getInt(CubeDaoImpl.NUMBER_OF_ROTATION_COL_NAME);
                Cube to = new Cube(tilesInString, numberOfRotations);

                String how = rs.getString(HOW_COL_NAME);
                CubeStateChange csc = new CubeStateChange(from, to, how);

                results.add(csc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

}
