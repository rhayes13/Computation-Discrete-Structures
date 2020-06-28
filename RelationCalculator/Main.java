import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    
    private static final Scanner userIn = new Scanner(System.in);
    private static String inputName, outputName;
    private static File inputFile, outputFile;
    private static FileWriter out;
    private static int size;
    private static int[][] matrix;
    
    private static void getUserInput() throws IOException {
        // User input scanner; read input file; create output file
        // Get input file name (assume user behaves)
        System.out.print("Enter input file name (file1 or file2): ");
        inputName = userIn.nextLine();
        inputFile = new File(inputName);
        
        // Read input file with matrix to 2d array for
        Scanner read = new Scanner(inputFile);
        size = read.nextInt(); // Read size
        matrix = new int[size][size];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                try {
                    matrix[i][j] = read.nextInt();
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
        System.out.println(">>> MATRIX RELATION CHARACTERISTICS <<<");
        getUserInput();
        
        //RelationOperator.java to process the relation
        RelationOperator op = new RelationOperator();
        op.checkReflexive(matrix, size);
        op.checkSymmetric(matrix, size);
        op.checkAntiSymmetric(matrix, size);
        op.checkTransitive(matrix, size);
        op.checkEquivalence(matrix, size);
        op.checkPartialOrder(matrix, size);
        out = new FileWriter(outputFile);
        
        // Write 6 lines of output to file
        try {
            out.write("\n" + "reflexive" + "\t" + "- " + op.getReflexive()  + "\n"
                           + "symmetric" + "\t" + "- " + op.getSymmetric() + "\n"
                           + "anti symmetric" + "\t" + "- " + op.getAntiSymmetric()  + "\n"
                           + "transitive" + "\t" + "- " + op.getTransitive()  + "\n"
                           + "equivalence" + "\t" + "- " + op.getEquivalence()  + "\n"
                           + "partial order" + "\t" + "- " + op.getPartialOrder()  + "\n");
            
            } catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("The relation of the matrix in " + inputName + 
                            " has been processed. Results were written to " +
                            outputName);
        out.close();
    }
    
}
