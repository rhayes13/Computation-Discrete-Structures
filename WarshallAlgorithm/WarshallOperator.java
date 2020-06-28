import java.util.Random;


public class WarshallOperator {
    
    public WarshallOperator() {}
    
     public int[][] computeTransitiveClosure(int[][] M, int size) {
        int[][] W = M;
        //Go through matrix by row/col
        //Use AND/OR operations to produce transitive closure matrix
        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if((W[i][j] != 0) || ((W[i][k] != 0) && (W[k][j] != 0))) {
                        W[i][j] = 1;
                    } else {
                        W[i][j] = 0;
                    }
                }
            }
        }
        return W;
    }
     
    public int[][] generateMatrix(int size) {
        //Generate a random binary matrix of size
        int[][] matrix = new int[size][size];
        Random r = new Random();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = r.nextInt(2);
            }
        }
        return matrix;
    }
}
