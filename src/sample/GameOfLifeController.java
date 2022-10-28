package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameOfLifeController
{
    final int MATRIX_SIZE = 10;
    final int BOX_SIZE = 60;
    Rectangle  [] [] matrix = new Rectangle[MATRIX_SIZE][MATRIX_SIZE];
    Rectangle  [] [] tempMatrix = new Rectangle[MATRIX_SIZE][MATRIX_SIZE];
    int [] yellowColor = {247, 220, 111};
    int [] whiteColor = {248, 249, 249};

    @FXML
    private Canvas cnv;

    private GraphicsContext gc;

    public void initialize()
    {
        gc = cnv.getGraphicsContext2D();
        int randomLife;
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                matrix[i][j] = new Rectangle();
                matrix[i][j].setX((j * BOX_SIZE));
                matrix[i][j].setY((i * BOX_SIZE));
                matrix[i][j].setHeight(BOX_SIZE);
                matrix[i][j].setWidth(BOX_SIZE);
                /*
                That attribute will indicate whether there is life on the site that represent by this rectangle
                0 - Means "no life"
                1 - Means "there is life"
                 */
                randomLife = (int)(Math.round(Math.random()));
                // Choose a random number 1 and 0, if there is life it will be colored yellow, otherwise white
                matrix[i][j].setOpacity(randomLife);
                gc.strokeRect(matrix[i][j].getX(),matrix[i][j].getY(),BOX_SIZE, BOX_SIZE);
                if (randomLife == 1)
                {
                    gc.setFill(Color.rgb(yellowColor[0], yellowColor[1], yellowColor[2]));
                }
                else
                {
                    gc.setFill(Color.rgb(whiteColor[0], whiteColor[1], whiteColor[2]));
                }
                gc.fillRect(matrix[i][j].getX(),matrix[i][j].getY(),BOX_SIZE, BOX_SIZE);
            }
        }
    }

    /*
    The action performed by pressing the "next generation" button
     */
    @FXML
    void nextGeneration()
    {
        createNextGenerationMatrix();
    }

    /*
    This method calculates the state of the site's life by Conway's laws of genetics
     */
    private void createNextGenerationMatrix()
    {
        int surroundingLife;
        // change the life or death in rectangle in the temporary matrix
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                tempMatrix[i][j] = new Rectangle();
                // Calculation of the number of neighboring sites with life
                surroundingLife = checkLifeSurrounding(i, j);
                // Check and change the temp matrix if there suppose to life next generation or not
                if (lifeOrDeath(surroundingLife, matrix[i][j].getOpacity()))
                {
                    tempMatrix[i][j].setOpacity(1);
                } else
                {
                    tempMatrix[i][j].setOpacity(0);
                }

            }
        }
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                // Set the new life (Opacity) values by the temp values
                matrix[i][j].setOpacity(tempMatrix[i][j].getOpacity());
                // If theres life in the next generation fill in the right color
                if (matrix[i][j].getOpacity() == 1)
                {
                    gc.setFill(Color.rgb(yellowColor[0], yellowColor[1], yellowColor[2]));
                }
                else
                {
                    gc.setFill(Color.rgb(whiteColor[0], whiteColor[1], whiteColor[2]));
                }
                // Changes the rectangles colors according to the received data
                gc.strokeRect(matrix[i][j].getX(),matrix[i][j].getY(),BOX_SIZE, BOX_SIZE);
                gc.fillRect(matrix[i][j].getX(),matrix[i][j].getY(),BOX_SIZE, BOX_SIZE);
            }
        }
    }

    /*
    This  method checks the number of neighboring sites where there is life
     */
    private int checkLifeSurrounding(int i, int j)
    {
        int surroundingLife = 0;
        int maxSize = 9;

        // Checks the life on the site up and left side
        if (i != 0 && j != 0)
        {
            if(matrix[i-1][j-1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }
        // Checks the life on the site up
        if (i!= 0)
        {
            if(matrix[i-1][j].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site up and right side
        if(i != 0 && j != maxSize)
        {
            if(matrix[i-1][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site on the left side
        if (j != 0)
        {
            if(matrix[i][j-1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site on the right side
        if (j != maxSize)
        {
            if(matrix[i][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site on the bottom left side
        if (i != maxSize && j != 0)
        {
            if(matrix[i+1][j-1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site on the bottom
        if (i!= maxSize)
        {
            if(matrix[i+1][j].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // Checks the life on the site on the right bottom side
        if (i != maxSize && j != maxSize)
        {
            if(matrix[i+1][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }
        return surroundingLife;
    }

    /*
    This method determines according to the life situation on the site and in the neighbors
    and in addition according to Conway's basic laws of genetics whether there will be life in the next generation
     */
    private boolean lifeOrDeath(int surroundingLife, double life)
    {
        if (life == 0)
        {
            return surroundingLife == 3;
        }
        return surroundingLife == 2 || surroundingLife == 3 ;
    }
}