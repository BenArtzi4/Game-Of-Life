package sample;
import javafx.scene.shape.Rectangle;


public class GameLogic {

    private final int matrixSize;
    private final int BOX_SIZE;
    private final Rectangle[][] matrix;
    private final Rectangle  [] [] tempMatrix;


    public GameLogic(int size)
    {
        matrixSize = size;
        BOX_SIZE = 6*matrixSize;
        this.matrix = new Rectangle[matrixSize][matrixSize];
        this.tempMatrix = new Rectangle[matrixSize][matrixSize];
        for (int i = 0; i < this.matrixSize; i++)
        {
            for (int j = 0; j < this.matrixSize; j++)
            {
                matrix[i][j] = new Rectangle();
                tempMatrix[i][j] = new Rectangle();

            }
        }

    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public int getBOX_SIZE() {
        return BOX_SIZE;
    }

    public Rectangle[][] getMatrix() {
        return matrix;
    }

    public Rectangle[][] getTempMatrix() {
        return tempMatrix;
    }

    /*
        This  method checks the number of neighboring sites where there is life
         */
    protected int checkLifeSurrounding(int i, int j)
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
    protected boolean lifeOrDeath(int surroundingLife, double life)
    {
        if (life == 0)
        {
            return surroundingLife == 3;
        }
        return surroundingLife == 2 || surroundingLife == 3 ;
    }

}
