import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
public class main {
    public static void main(String[] args) {
        // Example input
        String[] towelPatterns; // = {"r", "wr", "b", "g", "bwu", "rb", "gb", "br"};
        //String[] desiredDesigns; // = {"brwrr", "bggr", "gbbr", "rrbgbr", "ubwu", "bwurrg", "brgr", "bbrgwb"};
        ArrayList<String> desiredDesignArrayList = new ArrayList<>();
 
        System.out.println("Advent of Code Day 19\n\r");
   
        boolean full = true;
        Scanner scanner = null;
 
        try {
            if (full) {
                scanner = new Scanner(new File("input_full.txt"));
            } else {
                scanner = new Scanner(new File("input_test.txt"));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        String line = null;
 
        line = scanner.nextLine();
        System.out.println(line);
        towelPatterns = line.split(", ");
        scanner.nextLine();
 
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            System.out.println(line);
            desiredDesignArrayList.add(line);
        }
        int i = 0;
        String desiredDesigns[] = new String[desiredDesignArrayList.size()]; // (String[])desiredDesignArrayList.toArray();
        for (String s : desiredDesignArrayList) {
            desiredDesigns[i] = desiredDesignArrayList.get(i++);
        }
        int possibleDesigns = countPossibleDesigns(towelPatterns, desiredDesigns);
        System.out.println("\n\rNumber of possible designs: " + possibleDesigns);
        long total = 0;
        for (String design : desiredDesigns) {
            long ways = countWaysToFormDesign(towelPatterns, design);
            System.out.println("Number of ways to form design " + design + ": " + ways);
            if (ways > 0) total += ways;
        }
        System.out.println("\n\rNumber of ways to form all designs " + total);
    }
 
    public static long countWaysToFormDesign(String[] towelPatterns, String design) {
        int n = design.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // There's one way to form an empty design
 
        for (int i = 1; i <= n; i++) {
            for (String pattern : towelPatterns) {
                int len = pattern.length();
                if (i >= len && design.substring(i - len, i).equals(pattern)) {
                    dp[i] += dp[i - len];
                }
            }
        }
 
        return dp[n];
    }
 
    public static int countPossibleDesigns(String[] towelPatterns, String[] desiredDesigns) {
        int possibleCount = 0;
 
        for (String design : desiredDesigns) {
            if (canFormDesign(towelPatterns, design)) {
                possibleCount++;
            }
        }
 
        return possibleCount;
    }
 
    public static boolean canFormDesign(String[] towelPatterns, String design) {
        int n = design.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
 
        for (int i = 1; i <= n; i++) {
            for (String pattern : towelPatterns) {
                int len = pattern.length();
                if (i >= len && design.substring(i - len, i).equals(pattern)) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
 
        return dp[n];
    }
}