
package unionintersectioncalculator;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;

public class UnionIntersectionCalculator {
    
    private static final Scanner userIn = new Scanner(System.in);
    private static String inputName, outputName;
    private static int size, maxSize = 0;
    private static int k = 0;
    private static int[] setOne, setTwo, union;
    private static Integer[] finalUnion, finalIntersection;
    private static File inputFile, outputFile;
    private static FileWriter out;
    
    private static void getUserInput() throws IOException {
        // User input scanner; read input file; create output file
        // Get input file name (assume user behaves)
        System.out.print("Enter input file name: ");
        inputName = userIn.nextLine();
        inputFile = new File(inputName);
        
        // Read input file to setOne and setTwo arrays
        Scanner read = new Scanner(inputFile);
        size = read.nextInt();
        maxSize += size;
        setOne = new int[size];
        for(int i = 0; i < setOne.length; i++) {
            setOne[i] = read.nextInt();
        }
        size = read.nextInt();
        maxSize += size;
        setTwo = new int[size];
        for(int i = 0; i < setTwo.length; i++) {
            setTwo[i] = read.nextInt();
        }
        
        // Get output file name
        System.out.print("Enter output file name: ");
        outputName = userIn.nextLine();
        outputFile = new File(outputName);
        
        read.close();
    }
    
    private static void findUnion() {
        // Compute union of sets
        // Convert to union array of maxSize
        union = new int[maxSize];
        for(int i = 0; i < setOne.length; i++) {
            union[k] = setOne[i];
            k++;
        }
        for(int j = 0; j < setTwo.length; j++) {
            if(union[j] != setTwo[j]) {
                union[k] = setTwo[j];
                k++;
            }
        }
        
        // Push thru HashSet to remove duplicate elements
        LinkedHashSet<Integer> temp = new LinkedHashSet<>();
        for(int i = 0; i < union.length; i++) {
            temp.add(union[i]);
        }
        // Convert back to Integer array for writing to file
        finalUnion = new Integer[temp.size()];
        temp.toArray(finalUnion);
    }
    
    private static void writeUnion() throws IOException {
        // Write size and finalUnion to output file
        out = new FileWriter(outputFile);
        try {
            out.write("\n" + finalUnion.length + "\n");
            for(int i = 0; i < finalUnion.length; i++) {
                out.write(finalUnion[i] + " ");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    private static void findIntersection() {
        // Compute intersection of sets
        // Push thru temp HashSets to find common elements
        LinkedHashSet<Integer> temp = new LinkedHashSet<>();
        LinkedHashSet<Integer> temp2 = new LinkedHashSet<>();
        for(int i = 0; i < setOne.length; i++) {
            temp.add(setOne[i]);
        }
        for(int j = 0; j < setTwo.length; j++) {
            if(temp.contains(setTwo[j])) {
                temp2.add(setTwo[j]);
            }
        }
        // Convert back to Integer array for writing to file
        finalIntersection = new Integer[temp2.size()];
        temp2.toArray(finalIntersection);
    }
    
    private static void writeIntersection() throws IOException {
        // Write size and finalIntersection to output file
        try {
            out.write("\n" + "\n" + finalIntersection.length + "\n");
            for(int i = 0; i < finalIntersection.length; i++) {
                out.write(finalIntersection[i] + " ");
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(">>> UNION/INTERSECTION CALCULATOR <<<");
        getUserInput();
        findUnion();
        writeUnion();
        findIntersection();
        writeIntersection();

        System.out.println("Union and intersection of sets in " + 
                            inputName + " have been written to the file " +
                            outputName);
        userIn.close();
        out.close();
    }
}