
package matrixcalculator;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Operator {
    
    public void Operator() {}
    
    // Adds two 2d arrays
    public int[][] addMatrices(int[][] A, int[][] B, int size) {
        int[][] summedMatrix = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                summedMatrix[i][j] = A[i][j] + B[i][j];
            }
        }
        return summedMatrix;
    }
    
    // Multiples two 2d arrays
    public int[][] multiplyMatrices(int[][] A, int[][] B, int size) {
        int[][] productMatrix = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                for(int k = 0; k < size; k++) {
                    productMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return productMatrix;
    }
}