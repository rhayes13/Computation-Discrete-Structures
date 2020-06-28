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
        System.out.println(">>> SHORTEST PATH FLOYD ALGORITHM <<<");
        getUserInput();
        
        //WarshallOperator.java to process the matrix
        FloydOperator op = new FloydOperator();
        int shortestPath[][] = op.computeShortestPath(matrix, size);
        out = new FileWriter(outputFile);
        
        //Write shortest path matrix to file
        try {
            out.write(size + "\n");
            for(int i = 0; i < shortestPath.length; i++) {
                for(int j = 0; j < shortestPath[i].length; j++) {
                    out.write(shortestPath[i][j] + "\t");
                }
                out.write("\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("The shortest path of the matrix in " + inputName + 
                            " has been processed using Floyd's algorithm. Results were written to " +
                            outputName);
        out.close();
    }
}
