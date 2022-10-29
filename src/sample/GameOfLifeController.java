package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOfLifeController
{
    GameLogic game = new GameLogic(10);

    @FXML
    private Canvas cnv;

    private GraphicsContext gc;

    public void initialize()
    {
        gc = cnv.getGraphicsContext2D();
        int randomLife;
        int [] yellowColor = {247, 220, 111};
        int [] whiteColor = {248, 249, 249};
        for (int i = 0; i < game.getMatrixSize(); i++)
        {
            for (int j = 0; j < game.getMatrixSize(); j++)
            {

                game.getMatrix()[i][j].setX((j * game.getBOX_SIZE()));
                game.getMatrix()[i][j].setY((i * game.getBOX_SIZE()));
                game.getMatrix()[i][j].setHeight(game.getBOX_SIZE());
                game.getMatrix()[i][j].setWidth(game.getBOX_SIZE());
                /*
                That attribute will indicate whether there is life on the site that represent by this rectangle
                0 - Means "no life"
                1 - Means "there is life"
                 */
                randomLife = (int)(Math.round(Math.random()));
                // Choose a random number 1 and 0, if there is life it will be colored yellow, otherwise white
                game.getMatrix()[i][j].setOpacity(randomLife);
                gc.strokeRect(game.getMatrix()[i][j].getX(),game.getMatrix()[i][j].getY(),game.getBOX_SIZE(), game.getBOX_SIZE());
                if (randomLife == 1)
                {
                    gc.setFill(Color.rgb(yellowColor[0], yellowColor[1], yellowColor[2]));
                }
                else
                {
                    gc.setFill(Color.rgb(whiteColor[0], whiteColor[1], whiteColor[2]));
                }
                gc.fillRect(game.getMatrix()[i][j].getX(),game.getMatrix()[i][j].getY(),game.getBOX_SIZE(), game.getBOX_SIZE());
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
        int [] yellowColor = {247, 220, 111};
        int [] whiteColor = {248, 249, 249};
        // change the life or death in rectangle in the temporary matrix
        for (int i = 0; i < game.getMatrixSize(); i++)
        {
            for (int j = 0; j < game.getMatrixSize(); j++)
            {
                game.getTempMatrix()[i][j] = new Rectangle();
                // Calculation of the number of neighboring sites with life
                surroundingLife = game.checkLifeSurrounding(i, j);
                // Check and change the temp matrix if there suppose to life next generation or not
                if (game.lifeOrDeath(surroundingLife, game.getMatrix()[i][j].getOpacity()))
                {
                    game.getTempMatrix()[i][j].setOpacity(1);
                } else
                {
                    game.getTempMatrix()[i][j].setOpacity(0);
                }

            }
        }
        for (int i = 0; i < game.getMatrixSize(); i++)
        {
            for (int j = 0; j < game.getMatrixSize(); j++)
            {
                // Set the new life (Opacity) values by the temp values
                game.getMatrix()[i][j].setOpacity(game.getTempMatrix()[i][j].getOpacity());
                // If theres life in the next generation fill in the right color
                if (game.getMatrix()[i][j].getOpacity() == 1)
                {
                    gc.setFill(Color.rgb(yellowColor[0], yellowColor[1], yellowColor[2]));
                }
                else
                {
                    gc.setFill(Color.rgb(whiteColor[0], whiteColor[1], whiteColor[2]));
                }
                // Changes the rectangles colors according to the received data
                gc.strokeRect(game.getMatrix()[i][j].getX(), game.getMatrix()[i][j].getY(), game.getBOX_SIZE(), game.getBOX_SIZE());
                gc.fillRect(game.getMatrix()[i][j].getX(), game.getMatrix()[i][j].getY(),game.getBOX_SIZE(), game.getBOX_SIZE());
            }
        }
    }


}