package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class GameOfLifeController {

    final int SIZE = 10;

    Rectangle  [] [] matrix = new Rectangle[SIZE][SIZE];
    Rectangle  [] [] tempMatrix = new Rectangle[SIZE][SIZE];
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
        int boxSize = 40;
        int heightStart = 100;
        int widthStart = 80;
        int randomLife;
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                matrix[i][j] = new Rectangle();
                matrix[i][j].setX((j * boxSize) + widthStart);
                matrix[i][j].setY((i * boxSize) + heightStart);
                matrix[i][j].setHeight(boxSize);
                matrix[i][j].setWidth(boxSize);
                /*
                That attribute will indicate whether there is life on the site the site that represent by this rectangle
                0 - Means "no life"
                1 - Means "there is life"
                 */
                randomLife = (int)(Math.round(Math.random()));
                matrix[i][j].setOpacity(randomLife);
                gc.strokeRect(matrix[i][j].getX(),matrix[i][j].getY(),boxSize, boxSize);
                if (randomLife == 1)
                {
                    gc.setFill(Color.rgb(yellowColor[0], yellowColor[1], yellowColor[2]));
                }
                else
                {
                    gc.setFill(Color.rgb(whiteColor[0], whiteColor[1], whiteColor[2]));
                }
                gc.fillRect(matrix[i][j].getX(),matrix[i][j].getY(),boxSize, boxSize);
            }
        }
    }

    @FXML
    void nextGeneration(ActionEvent event)
    {

    }
    /*

     */
    public void creteNextGenerationMatrix()
    {
        int surroundingLife;
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                surroundingLife = checkLifeSurrounding(i, j);
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






}
