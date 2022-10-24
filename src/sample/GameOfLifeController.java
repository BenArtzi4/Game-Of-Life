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
    public void creteNextGenerationMatrix() {

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                tempMatrix[i][j] = new Rectangle(matrix[i][j].getX(), matrix[i][j].getY(),matrix[i][j].getWidth(),matrix[i][j].getHeight());

            }

        }

    }





}
