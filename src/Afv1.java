import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Afv1 {

    /**
     * Main function.
     */
    public static void run() {

        System.out.println("Opdracht 2, bubble sort");
        int COUNT = 10000;
        int MAX = 1000000000;

        List<Integer> intList = generateRandomList(COUNT, MAX);

        System.out.println("Before:");
        for(int curInt : intList){
            System.out.print(curInt+",");
        }
        System.out.print("\n");
        System.out.println("After:");
        long timeBefore = System.currentTimeMillis();
        List<Integer> outList = bubbleSort(intList);
        long timeAfter = System.currentTimeMillis();
        long totalTime = timeAfter - timeBefore;
        for(int curInt : outList){
            System.out.print(curInt+",");
        }
        System.out.print("\n");
        System.out.println("Time (ms):\t" + totalTime);


        // Test with differently sized lists

        HashMap<Integer, Integer> timeResults = new HashMap<>();

        int maxCount = 70000;
        int curCount = 8;
        System.out.println("Time test:");
        System.out.println("count\ttime(ms)");
        while (curCount < maxCount){
            timeBefore = System.currentTimeMillis();
            List<Integer> outTemp = bubbleSort(generateRandomList(curCount, MAX));
            timeAfter = System.currentTimeMillis();
            totalTime = timeAfter - timeBefore;
            System.out.println(curCount + "\t" + totalTime);

            curCount *= 2;
        }

    }

    /**
     * Bubble Sort algorithm, based on: https://www.geeksforgeeks.org/bubble-sort/
     * @param intList List of integers.
     * @return Sorted list of integers.
     */
    private static List<Integer> bubbleSort(List<Integer> intList) {
        int len = intList.size();

        for(int i = 0; i < len-1; i++){  // We need len amount of iterations
            for (int j = 0; j < len-i-1; j++){  // Check every pair
                if (intList.get(j) > intList.get(j+1)){ // If j > j+1: swap
                    int swap = intList.get(j);
                    intList.set(j, intList.get(j+1));
                    intList.set(j+1, swap);
                }
            }
        }

        return intList;
    }

    /**
     * Generates a list with random ints.
     * @param count - Integer; Amount of integers to generate.
     * @param max - Integer; Maximum value of the integers.
     * @return Integer ArrayList; Randomly generated integers list.
     */
    private static List<Integer> generateRandomList(int count, int max){
        List<Integer> intList = new ArrayList<>();
        Random r = new Random();

        for(int i = 0; i < count; i++){
            intList.add(r.nextInt(max));
        }

        return intList;
    }

}
