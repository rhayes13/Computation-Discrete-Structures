public class RelationOperator {
    
    public boolean reflexive, symmetric, antiSymmetric, transitive, equivalence, partialOrder;
    
    /* Constructor */
    public RelationOperator() {
        reflexive = false;
        symmetric = false;
        antiSymmetric = false;
        transitive = false;
        equivalence = false;
        partialOrder = false;
    }
    
    /* Check conditions */
    public void checkReflexive(int[][] M, int size) {
        //Check if matrix is reflexive
        //Diagonal must be all 1
        int count = 0;
        
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == j && M[i][j] == 1) {
                    count++;
                }
            }
        }
        if(count == size) reflexive = true;
    }
    
    public void checkSymmetric(int[][] M, int size) {
        //Check if matrix is symmetric
        //(i,j) and (j,i) must match
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(M[i][j] != M[j][i]) {
                    symmetric = false;
                    return;
                } else {
                    symmetric = true;
                }
            }
        }
    }
    
    public void checkAntiSymmetric(int[][] M, int size) {
        //Check if matrix is anti symmetric
        //(i,j) and (j,i) must not be 1
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i != j && M[i][j] == 1 && M[j][i] == 1) {
                    antiSymmetric = false;
                    return;
                } else {
                    antiSymmetric = true;
                }
            }
        }
    }
    
    public void checkTransitive(int[][] M, int size) {
        //Check if matrix is transitive, find R^2
        int[][] M2 = getSquaredMatrix(M, M, size);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(M2[i][j] == 1 && M[i][j] != 1) {
                    transitive = false;
                    return;
                } else {
                    transitive = true;
                }
            }
        }
    }
    
    public int[][] getSquaredMatrix(int[][] M, int[][] N, int size) {
        int[][] R2 = new int[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    R2[i][j] += M[i][k] * N[k][j];
                }
            }
        }
        return R2;
    }
    
    public void checkEquivalence(int[][] M, int size) {
        //Check if matrix is equivalence relation
        //Must be reflexive, symmetric, and transitive
        if(reflexive == true && symmetric == true && transitive == true) {
            equivalence = true;
        } else {
            equivalence = false;
        }
    }
    
    public void checkPartialOrder(int[][] M, int size) {
        //Check if matrix is a partial order
        //Must be reflexive, symmetric, and transitive
        if(reflexive == true && antiSymmetric == true && transitive == true) {
            partialOrder = true;
        } else {
            partialOrder = false;
        }
    }
    
    /* Getters */
    public String getReflexive() {
        if(reflexive == true) return "yes";
        return "no";
    }
    
    public String getSymmetric() {
        if(symmetric == true) return "yes";
        return "no";
    }
    
    public String getAntiSymmetric() {
        if(antiSymmetric == true) return "yes";
        return "no";
    }
    
    public String getTransitive() {
        if(transitive == true) return "yes";
        return "no";
    }
    
    public String getEquivalence() {
        if(equivalence == true) return "yes";
        return "no";
    }
    
    public String getPartialOrder() {
        if(partialOrder == true) return "yes";
        return "no";
    }
}