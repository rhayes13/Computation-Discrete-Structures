package goldbachconjecture;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GoldbachConjecture {

    private static final Scanner userIn = new Scanner(System.in);
    private static String outputName;
    private static File outputFile;
    private static FileWriter out;
    private static int m, n;
    private static final int SIZE = 3000000;
    private static final ArrayList<Integer> primes = new ArrayList<>();
    
    public static void getUserInput() throws IOException {
        // Get lower and upper limits
        System.out.print("Enter lower limit (m): ");
        m = Integer.parseInt(userIn.nextLine());
        
        System.out.print("Enter upper limit (n): ");
        n = Integer.parseInt(userIn.nextLine());
        
        // Get output file name
        System.out.print("Enter output file name: ");
        outputName = userIn.nextLine();
        outputFile = new File(outputName);
        
        userIn.close();
    }
    
    public static void generatePrimes() {
        // Filter to generate ArrayList of prime numbers up to max size
        // Reference from Sieve of Sundaram
        boolean[] flag = new boolean[SIZE/2 + 100];
        
        // Flag all numbers that are not prime (2*i+1)
        for(int i = 1; i <= (Math.sqrt(SIZE) - 1) / 2; i++) {
            for(int j = (i * (i + 1)) << 1; j <= SIZE / 2; j = j + 2 * i + 1) {
                flag[j] = true;
            }
        }
        
        primes.add(2); // 2 is prime
        
        // Create prime number array
        for(int i = 1; i <= SIZE / 2; i++) {
            if(flag[i] == false) {
                primes.add(2 * i + 1);
            }
        }
    }
    
    // Check the conjecture using generated primes, writes to file
    public static void checkGoldbach(int m, int n) throws IOException {
        //Check if m is even and greater than 2
        if(m <= 2 || m % 2 != 0) {
            return;
        }
        
        out = new FileWriter(outputFile);
        for(int i = m; i <= n; i += 2) {
            for(int j = 0; primes.get(j) <= i / 2; j++) {
                int difference = i - primes.get(j);
                // Check if difference is prime
                if(primes.contains(difference)) {
                    try {
                        out.write(i + " = " + primes.get(j) +
                                " + " + difference + "\n");
                        break; // Only find first combination of primes
                    } catch(IOException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        
        out.close();
    }
    
    
    public static void main(String[] args) throws IOException {
        System.out.println(">>> GOLDBACH'S CONJECTURE <<<");
        generatePrimes(); // Create prime number array
        getUserInput();
        
        // Write to file
        checkGoldbach(m, n); //Find prime numbers for Goldbach conjecture
        
        System.out.println("Data has been written to " + outputName);
    }
}
