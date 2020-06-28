

package matrixcalculator;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MatrixCalculator {
    
    private static final Scanner userIn = new Scanner(System.in);
    private static String inputName, outputName;
    private static File inputFile, outputFile;
    private static FileWriter out;
    private static int size;
    private static int[][] matrixOne, matrixTwo;

    private static void getUserInput() throws IOException {
        // User input scanner; read input file; create output file
        // Get input file name (assume user behaves)
        System.out.print("Enter input file name: ");
        inputName = userIn.nextLine();
        inputFile = new File(inputName);
        
        // Read input file to 2d array for first matrix
        Scanner read = new Scanner(inputFile);
        size = read.nextInt(); // Read size
        matrixOne = new int[size][size];
        for(int i = 0; i < matrixOne.length; i++) {
            for(int j = 0; j < matrixOne.length; j++) {
                try {
                    matrixOne[i][j] = read.nextInt();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
        
        // Now read second matrix to 2d array
        size = read.nextInt(); // Read size
        matrixTwo = new int[size][size];
        for(int i = 0; i < matrixTwo.length; i++) {
            for(int j = 0; j < matrixTwo.length; j++) {
                try {
                    matrixTwo[i][j] = read.nextInt();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
         
        // Get output file name
        System.out.print("Enter output file name: ");
        outputName = userIn.nextLine();
        outputFile = new File(outputName);
        
        // Clean-up
        read.close();
        userIn.close();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(">>> SQUARE MATRIX CALCULATOR <<<");
        getUserInput();
        
        //Operator.java to process matrices
        Operator op = new Operator();
        int[][] sum = op.addMatrices(matrixOne, matrixTwo, size);
        int[][] product = op.multiplyMatrices(matrixOne, matrixTwo, size);
        out = new FileWriter(outputFile);
        
        // Write size and summed matrix to file
        try {
            out.write(size + "\n");
            for(int i = 0; i < sum.length; i++) {
                for(int j = 0; j < sum[i].length; j++) {
                    out.write(sum[i][j] + "\t");
                }
                out.write("\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        
        // Write size and multiplied matrix to file
        try {
            out.write("\n" + size + "\n");
            for(int i = 0; i < product.length; i++) {
                for(int j = 0; j < product[i].length; j++) {
                    out.write(product[i][j] + "\t");
                }
                out.write("\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("The matrices in the file " + inputName + 
                            " have been computed. Results were written to " +
                            outputName);
        out.close();
    }
}
