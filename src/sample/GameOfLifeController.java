package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class GameOfLifeController {

    final int MATRIX_SIZE = 10;
    final int BOX_SIZE = 40;


    Rectangle  [] [] matrix = new Rectangle[MATRIX_SIZE][MATRIX_SIZE];
    Rectangle  [] [] tempMatrix = new Rectangle[MATRIX_SIZE][MATRIX_SIZE];
    int [] yellowColor = {247, 220, 111};
    int [] whiteColor = {248, 249, 249};


    @FXML
    private Button btn;

    @FXML
    private Canvas cnv;

    private GraphicsContext gc;

    public void initialize()
    {
        gc = cnv.getGraphicsContext2D();
        int heightStart = 100;
        int widthStart = 80;
        int randomLife;
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                matrix[i][j] = new Rectangle();
                matrix[i][j].setX((j * BOX_SIZE) + widthStart);
                matrix[i][j].setY((i * BOX_SIZE) + heightStart);
                matrix[i][j].setHeight(BOX_SIZE);
                matrix[i][j].setWidth(BOX_SIZE);
                /*
                That attribute will indicate whether there is life on the site the site that represent by this rectangle
                0 - Means "no life"
                1 - Means "there is life"
                 */
                randomLife = (int)(Math.round(Math.random()));
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

    @FXML
    void nextGeneration(ActionEvent event)
    {
        creteNextGenerationMatrix();
    }

    public void creteNextGenerationMatrix()
    {
        int surroundingLife;
        // change the life or death in rectangle in the temporary matrix
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                surroundingLife = checkLifeSurrounding(i, j);
                // if there suppose to life next generation
                if (lifeOrDeath(surroundingLife, matrix[i][j].getOpacity())) {
                    tempMatrix[i][j].setOpacity(1);
                } else {
                    tempMatrix[i][j].setOpacity(0);
                }
            }
        }

        //change the life or death value in the main matrix and set color
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                matrix[i][j].setOpacity(tempMatrix[i][j].getOpacity());
                if (matrix[i][j].getOpacity() == 1)
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

    public int checkLifeSurrounding(int i, int j)
    {
        int surroundingLife = 0;
        int maxSize = 9;
        //check Left up site's life
        if (i != 0 && j != 0)
        {
            if(matrix[i-1][j-1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }
        //check up site's life
        if (i!= 0)
        {
            if(matrix[i-1][j].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check up right site's life
        if(i != 0 && j != maxSize)
        {
            if(matrix[i-1][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check left site's life
        if (j != 0)
        {
            if(matrix[i][j-1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check right site's life
        if (j != maxSize)
        {
            if(matrix[i][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check left bottom site's life
        if (j != 0 && i != maxSize)
        {
            if(matrix[i-1][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check bottom site's life
        if (i!= maxSize)
        {
            if(matrix[i+1][j].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }

        // check right bottom site's life
        if (i != maxSize && j != maxSize)
        {
            if(matrix[i+1][j+1].getOpacity() == 1)
            {
                surroundingLife++;
            }
        }
        return surroundingLife;
    }

    public boolean lifeOrDeath(int surroundingLife, double life)
    {

        if (life == 0)
        {
            return surroundingLife == 3;
        }

        return surroundingLife != 1 && surroundingLife != 0 && surroundingLife < 4;
    }


}
