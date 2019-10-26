package com.hunter.magic_cube.permutation_generator;

import com.hunter.magic_cube.cube_controller.PocketCubeMatrixController;
import com.hunter.magic_cube.model.Cube;
import com.hunter.magic_cube.daoimpl.CubeDaoImpl;
import com.hunter.magic_cube.daoimpl.CubeStateChangeDaoImpl;
import com.hunter.magic_cube.model.CubeStateChange;
import com.hunter.magic_cube.model_factory.PocketCubeFactory;
import com.hunter.magic_cube.dao.IDao;
import com.hunter.magic_cube.dao.ICubeDao;
import com.hunter.magic_cube.dao.ICubeStateChangeDao;
import java.util.*;

/**
 *
 * @author Hunter
 */
// Note:
// maximum number of turns required to solve the cube is up to 11 full turns
// https://puzzling.stackexchange.com/questions/1687/maximal-number-of-steps-needed-to-solve-2x2x2-rubiks-cube
// therefore, the height of the tree which contains step-by-step is 11
/*
        [Level 0] 1
        [Level 1] 9
        [Level 2] 54
        [Level 3] 321
        [Level 4] 1847
        [Level 5] 9992
        [Level 6] 50136
        [Level 7] 227536
        [Level 8] 870072
        [Level 9] 1887748
        [Level 10] 623800
        [Level 11] 2644
 */
// same as the table in wiki
// ref: https://en.wikipedia.org/wiki/Pocket_Cube#Permutations
public class PocketCubePermutationGenerator {

    private PocketCubeMatrixController rotationController;

    private Set<Cube> allPermutation;

    private ICubeDao cubeDao;
    private ICubeStateChangeDao cubeStateChangeDao;

    public PocketCubePermutationGenerator() {
        rotationController = PocketCubeMatrixController.getController();
        allPermutation = new HashSet<>();
        cubeDao = new CubeDaoImpl();
        cubeStateChangeDao = new CubeStateChangeDaoImpl();

        // inital the whole db
        cubeDao.resetTable();
        cubeStateChangeDao.resetTable();
    }

    public void executeWithBFS() {
        Cube solvedState = PocketCubeFactory.createSolvedPocketCube();

        allPermutation.add(solvedState);
        cubeDao.insert(solvedState);
        // BFS
        int levelCounter = 0; // start from level 0
        Set<Cube> level = new HashSet<>();
        level.add(solvedState); // add inital state

        while (!level.isEmpty()) {
            System.out.printf("[Level %d] %d\n", levelCounter, level.size());
            
            double[][] statesInArray = level.stream().map(cube -> cube.getTiles()).toArray(double[][]::new);
            Set<Cube> nextLevel = new HashSet<>();
            levelCounter++; // incre levelCounter for "next level"
            
            double[][] newStates;

            IDao.beginTransaction();

            // Note:
            // We only need to rotate Front, Right and Up because Back, Left and Down are opposite of corresponding surface
            // e.g. to rotate Front clockwise is same as rotating Back counter clockwise
            // Front
            newStates = rotationController.rotateFrontClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.FRONT_CLOCKWISE_NOTATION);

            newStates = rotationController.rotateFrontClockwise2x(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.FRONT_CLOCKWISE_2X_NOTATION);

            newStates = rotationController.rotateFrontCounterClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.FRONT_COUNTER_CLOCKWISE_NOTATION);

            // Right
            newStates = rotationController.rotateRightClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.RIGHT_CLOCKWISE_NOTATION);

            newStates = rotationController.rotateRightClockwise2x(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.RIGHT_CLOCKWISE_2X_NOTATION);

            newStates = rotationController.rotateRightCounterClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.RIGHT_COUNTER_CLOCKWISE_NOTATION);

            // Up
            newStates = rotationController.rotateUpClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.UP_CLOCKWISE_NOTATION);

            newStates = rotationController.rotateUpClockwise2x(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.UP_CLOCKWISE_2X_NOTATION);

            newStates = rotationController.rotateUpCounterClockwise(statesInArray);
            updatePermutationAndNextLevel(newStates, nextLevel, levelCounter);
            updateCubeStateChangeDao(statesInArray, newStates, rotationController.UP_COUNTER_CLOCKWISE_NOTATION);

            IDao.endTransaction();

            // done with current level
            // move to next level
            level = nextLevel;
        }
    }

    private void updatePermutationAndNextLevel(double[][] newStates, Set<Cube> nextLevel, int level) {
        for (double[] state : newStates) {
            Cube c = PocketCubeFactory.createPocketCube(state, level);

            // Cube isExistInDb = cubeDao.getByState(c.getTilesAsString());
            // if a state is already visited, then no need to check it again at next level.
            if (!allPermutation.contains(c)) {
                allPermutation.add(c);
                nextLevel.add(c);

                // insert into database
                cubeDao.insert(c);
            }
        }
    }

    private void updateCubeStateChangeDao(double[][] fromList, double[][] toList, String how) {
        for (int i = 0; i < fromList.length && i < toList.length; i++) {

            Cube from = PocketCubeFactory.createPocketCube(fromList[i], 0);
            Cube to = PocketCubeFactory.createPocketCube(toList[i], 0);

            CubeStateChange csc = new CubeStateChange(from, to, how);

            // insert into database
            cubeStateChangeDao.insert(csc);
        }
    }
}
