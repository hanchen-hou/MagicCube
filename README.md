## Pocket Cube Layout
### Surface Layout
```
+---+---+---+---+---+---+---+---+
|   |   | U | U |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   | U | U |   |   |   |   |
+---+---+---+---+---+---+---+---+
| L | L | F | F | R | R | B | B |
+---+---+---+---+---+---+---+---+
| L | L | F | F | R | R | B | B |
+---+---+---+---+---+---+---+---+
|   |   | D | D |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   |   | D | D |   |   |   |   |
+---+---+---+---+---+---+---+---+
```

* F = Front
* B = Back
* R = Right
* L = Left
* U = Up
* D = Down

### Tile Array Index Layout (24 tiles)
```
+---+---+----+----+----+----+----+----+
|   |   | 8  | 9  |    |    |    |    |
+---+---+----+----+----+----+----+----+
|   |   | 11 | 10 |    |    |    |    |
+---+---+----+----+----+----+----+----+
| 4 | 5 | 0  | 1  | 12 | 13 | 20 | 21 |
+---+---+----+----+----+----+----+----+
| 7 | 6 | 3  | 2  | 15 | 14 | 23 | 22 |
+---+---+----+----+----+----+----+----+
|   |   | 16 | 17 |    |    |    |    |
+---+---+----+----+----+----+----+----+
|   |   | 19 | 18 |    |    |    |    |
+---+---+----+----+----+----+----+----+
```

For example, if we say RED=0, YELLOW=1, BLUE=2, WHITE=3, GREEN=4 and ORANGE=5, then the solved state is represenented as ```[0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5]```.
If a front clockwise rotation is done, then the state will be ```[0,0,0,0,1,4,4,1,2,2,1,1,2,3,3,2,3,3,4,4,5,5,5,5]```

Note: cubes can have different colors on their surfaces, e.g. The red surface maybe not a neighbour of the yellow surface. 
Please see ```com.hunter.magic_cube.model_factory.PocketCubeFactory::standardizePocketCube```[link](https://github.com/hanchen-hou/MagicCube/blob/bf0dde70864466b6ac0e3766702458cbc84fec8a/src/main/java/com/hunter/magic_cube/model_factory/PocketCubeFactory.java#L47-L64).


## Database
### Table Structure
```
cube table:
+-----------------------+---------------------+--------------------------------+
|           id          |        state        | number_of_rotations            |
+-----------------------+---------------------+--------------------------------+
| <row#, autoincrement> | <String, length=24> | <number, distance from solved> |
+-----------------------+---------------------+--------------------------------+
```
```
cube_state_change table:
+--------------------------------+--------------------------------+---------------------------------+
|              from              |               to               | how                             |
+--------------------------------+--------------------------------+---------------------------------+
| <row#, foreign key -> cube.id> | <row#, foreign key -> cube.id> | <String, Rubik's Cube Notation> |
+--------------------------------+--------------------------------+---------------------------------+
```
In short, "cube" table stores vertices and "cube_state_change" table stores edges.


## technology
* Matrix Operation
* DAO Design Pattern
* Factory Design Pattern
* OO Design
* Model and Controller
* SQLite