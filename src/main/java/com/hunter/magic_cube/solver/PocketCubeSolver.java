package com.hunter.magic_cube.solver;

import com.hunter.magic_cube.dao.ICubeDao;
import com.hunter.magic_cube.dao.ICubeStateChangeDao;
import com.hunter.magic_cube.daoimpl.CubeDaoImpl;
import com.hunter.magic_cube.daoimpl.CubeStateChangeDaoImpl;
import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.model.CubeStateChange;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author Hunter
 */
public class PocketCubeSolver {

    private ICubeDao cubeDao;
    private ICubeStateChangeDao cubeStateChangeDao;

    public PocketCubeSolver() {
        cubeDao = new CubeDaoImpl();
        cubeStateChangeDao = new CubeStateChangeDaoImpl();
    }

    public List<CubeStateChange> solve(Cube c) {
        List<CubeStateChange> solution = new ArrayList<>();

        Cube from = cubeDao.getByState(c.getTiles());

        if (from == null) {
            throw new RuntimeException("Cannot find this state. Invalid cube state. " + c.getTilesAsString());
        }

        for (int level = from.getNumberOfRotations(); level > 0; level--) {

            List<CubeStateChange> states = cubeStateChangeDao.getByFrom(from);
            List<CubeStateChange> nextStates = new ArrayList<>();

            for (CubeStateChange csc : states) {
                if (csc.getTo().getNumberOfRotations() < level) { // heuristic algorithm, similar to A*
                    nextStates.add(csc);
                }
            }
            int randomIndex = new Random().nextInt(nextStates.size());
            CubeStateChange next = nextStates.get(randomIndex);
            solution.add(next);

            from = next.getTo();
        }

        return solution;
    }
}
