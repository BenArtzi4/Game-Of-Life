package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameOfLifeController
{
    GameLogic game = new GameLogic();

    @FXML
    private Canvas cnv;

    private GraphicsContext gc;

    public void initialize()
    {
        gc = cnv.getGraphicsContext2D();
        game.newGame(gc);
    }

    /*
    The action performed by pressing the "next generation" button
     */
    @FXML
    void nextGeneration()
    {
        GameLogic.createNextGenerationMatrix(gc);
    }


}