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
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                matrix[i][j] = new Rectangle();
                matrix[i][j].setX((j * boxSize) + widthStart);
                matrix[i][j].setY((i * boxSize) + heightStart);
                matrix[i][j].setHeight(boxSize);
                matrix[i][j].setWidth(boxSize);
                // That attribute will indicate whether there is life on the site the site that represent by this rectangle
                matrix[i][j].setCache(true);
                gc.setFill(Color.rgb(50, i, j));
                gc.strokeRect(matrix[i][j].getX(),matrix[i][j].getY(),boxSize, boxSize);
            }
        }
    }

    @FXML
    void nextGeneration(ActionEvent event)
    {

    }



}
