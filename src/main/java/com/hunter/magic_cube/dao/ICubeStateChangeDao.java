package com.hunter.magic_cube.dao;

import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.model.CubeStateChange;
import java.util.List;

/**
 *
 * @author Hunter
 */
public interface ICubeStateChangeDao extends IDao<CubeStateChange>{
    public List<CubeStateChange> getByFrom(Cube c);
    public List<CubeStateChange> getByTo(Cube c);
}
