
package bijectioncalculator;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class BijectionCalculator {
    
    private static final Scanner userIn = new Scanner(System.in);
    private static String inputName, outputName;
    private static File inputFile, outputFile;
    private static FileWriter out;
    private static int size;
    private static int[] domain, codomain;
    private static int[][] mappings;
    private static boolean oneToOne, onto;

    private static void getUserInput() throws IOException {
        // User input scanner; read input file; create output file
        // Get input file name (assume user behaves)
        System.out.print("Enter input file name: ");
        inputName = userIn.nextLine();
        inputFile = new File(inputName);
        
        // Read input file to setOne and setTwo arrays
        Scanner read = new Scanner(inputFile);
        size = read.nextInt();
        domain = new int[size];
        for(int i = 0; i < domain.length; i++) {
            domain[i] = read.nextInt();
        }
        size = read.nextInt();
        codomain = new int[size];
        for(int i = 0; i < codomain.length; i++) {
            codomain[i] = read.nextInt();
        }
        
        // Read input file to 2d mappings Array (2 columns)
        size = read.nextInt();
        mappings = new int[size][2];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < 2; j++) {
                try {
                    mappings[i][j] = read.nextInt();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
         
        // Get output file name
        System.out.print("Enter output file name: ");
        outputName = userIn.nextLine();
        outputFile = new File(outputName);
        
        read.close();
        userIn.close();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(">>> BIJECTION CALCULATOR <<<");
        getUserInput();
        
        //Helper.java to process function
        Helper h = new Helper();
        oneToOne = h.isOneToOne(domain, codomain, mappings);
        onto = h.isOnto(domain, codomain, mappings);
        
        // Write results to output file
        out = new FileWriter(outputFile);
        try {
            if(oneToOne) {
                out.write("one to one" + "\n");
            } else {
                out.write("not one to one" + "\n");
            }
            if(onto) {
                out.write("onto" + "\n");
            } else {
                out.write("not onto" + "\n");
            }
            // If oneToOne and onto are true, then function is bijective
            if(oneToOne && onto) {
                out.write("bijective" + "\n");
            } else {
                out.write("not bijective" + "\n");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("The function in the file " + inputName + 
                            " has been analyzed. Results were written to " +
                            outputName);
        out.close();
    }
}