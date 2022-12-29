# Game of Life Program

Welcome to the Game of Life program, written in Java with a GUI implemented using JavaFX!

The Game of Life is a simulation game developed by mathematician John Conway as a model for studying the life cycle of living organisms. The game is played on a large matrix whose cells represent possible life sites. Each site can have one of two states:

* Life: A cell with life will be marked as a filled square.
* death: A cell without life will be marked as an empty square.

## The Game of Life follows these basic rules of genetics:

[ ] Birth: In every site where there is "no life" that has exactly 3 living neighbors, there will be a birth in the next generation. Otherwise, the site remains "lifeless" (empty).

[ ] Death: At any site where there is "life" that has 0 or 1 living neighbors, death will occur in the next generation as a result of loneliness. In any site where "there is life" and if there are 4 or more living neighbors, death will occur in the next generation as a result of "population explosion".

[ ] Existence: Any site where "there is life" and has 2 or 3 living neighbors will continue to exist in the next generation.

The processes of birth, death, and existence occur simultaneously in all sites and create a new state of life called a new generation.
