package com.hunter.magic_cube.dao;

import com.hunter.magic_cube.model.Cube;

/**
 *
 * @author Hunter
 */
public interface ICubeDao extends IDao<Cube> {
    public Cube getByState(double[] tiles);
    public Cube getByState(String tiles);
}
