
package bijectioncalculator;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    public void Helper() {}
    
    public boolean isOneToOne(int[] domain, int[] codomain, int[][] map) {
        int[] tempDomain = new int[map.length];
        int[] tempCodomain = new int[map.length];
        // Split 2d array into two separate arrays
        for(int i = 0; i < map.length; i++) {
            tempDomain[i] = map[i][0];
        }
        for(int i = 0; i < map.length; i++) {
            tempCodomain[i] = map[i][1];
        }
        // Check that no 2 elements have same image
        for(int i = 0; i < tempCodomain.length; i++) {
            for(int k = i+1; k < tempCodomain.length; k++) {
                if(k != i && tempCodomain[k] == tempCodomain[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isOnto(int[] domain, int[] codomain, int[][] map) {
        if(codomain.length > domain.length) {
            return false;
        }
        return true;
    }
}
