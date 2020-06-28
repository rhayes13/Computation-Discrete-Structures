import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


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
        System.out.print("Enter input file name (file3 or file4): ");
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
		
		
		
		/*** Part 3 Code (not used here, but included)
        int arrows;
        // Read input file with matrix to 2d array for
        Scanner read = new Scanner(inputFile);
        size = read.nextInt(); // Read size
        arrows = read.nextInt(); // Read num arrows
        matrix = new int[size][size];
        //Initialize array to 0s
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }
        
		//Fill with index u, v
        for(int i = 0; i < arrows; i++) {
            matrix[read.nextInt()-1][read.nextInt()-1] = 1;
        }
		***/
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(">>> TRANSITIVE CLOSURE WARSHALL ALGORITHM <<<");
        getUserInput();
        
        //WarshallOperator.java to process the matrix
        WarshallOperator op = new WarshallOperator();
        int transitiveClosure[][] = op.computeTransitiveClosure(matrix, size);
        out = new FileWriter(outputFile);
        
        //Write transitive closure to file
        try {
            out.write(size + "\n");
            for(int i = 0; i < transitiveClosure.length; i++) {
                for(int j = 0; j < transitiveClosure[i].length; j++) {
                    out.write(transitiveClosure[i][j] + " ");
                }
                out.write("\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("The transitive closure of the matrix in " + inputName + 
                            " has been processed using Warshall's algorithm. Results were written to " +
                            outputName);
        out.close();
        
        /*
		//Test cases
        int[][] oneHundred = op.generateMatrix(100);
        long start = System.nanoTime();
        op.computeTransitiveClosure(oneHundred, 100);
        long end = System.nanoTime();
        long duration = (end - start);
        long milliseconds = duration/1000000;
        System.out.println("size 100: " + milliseconds + "ms");
        
        int[][] twoHundred = op.generateMatrix(200);
        start = System.nanoTime();
        op.computeTransitiveClosure(twoHundred, 200);
        end = System.nanoTime();
        duration = (end - start);
        milliseconds = duration/1000000;
        System.out.println("size 200: " + milliseconds + "ms");
        
        int[][] fiveHundred = op.generateMatrix(500);
        start = System.nanoTime();
        op.computeTransitiveClosure(fiveHundred, 500);
        end = System.nanoTime();
        duration = (end - start);
        milliseconds = duration/1000000;
        System.out.println("size 500: " + milliseconds + "ms");
        
        int[][] oneThousand = op.generateMatrix(1000);
        start = System.nanoTime();
        op.computeTransitiveClosure(oneThousand, 1000);
        end = System.nanoTime();
        duration = (end - start);
        milliseconds = duration/1000000;
        System.out.println("size 1000: " + milliseconds + "ms");
		*/
    }
}
