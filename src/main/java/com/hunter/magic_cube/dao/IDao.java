package com.hunter.magic_cube.dao;

import com.hunter.magic_cube.db.SQliteDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hunter
 */
public interface IDao<T> {

    static void beginTransaction() {
        Connection db = SQliteDatabase.getDbConnection();
        
        if(db != null){
            try {
                db.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    };

    static void endTransaction() {
        Connection db = SQliteDatabase.getDbConnection();
        
        if(db != null){
            try {
                db.commit();
                db.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    };

    public void resetTable();

    public List<T> getAll();

    // public List<T> select(Map<String, Object> dic);
    public void insert(T obj);
}
