public class FloydOperator {
    
    public FloydOperator() {}
    
     public int[][] computeShortestPath(int[][] M, int size) {
        int[][] W = M;
        //Initialize the matrix
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == j) {
                    W[i][j] = 0;
                } else if(W[i][j] == 0) {
                    W[i][j] = 9999999; //Large number to signify no connection
                }
            }
        }
        //Go through matrix by row/col
        //Check all vertices as start and end point; determine shortest path
        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(W[i][k] + W[k][j] < W[i][j]) {
                        W[i][j] = W[i][k] + W[k][j];
                    }
                }
            }
        }
        return W;
    }
}
