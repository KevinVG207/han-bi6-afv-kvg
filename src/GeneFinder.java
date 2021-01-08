import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneFinder {

    private Set<String> set1 = new HashSet<>();
    private Set<String> set2 = new HashSet<>();
    private Set<String> set3 = new HashSet<>();

    /**
     * Used for testing with dummy data before GUI was made.
     */
    public void run() {

        // Fill sets with dummy data
        set1.add("A1AT");
        set1.add("P88");
        set1.add("A2AH");
        set1.add("p63");

        set2.add("A1AB");
        set2.add("P99");
        set2.add("A2AH");
        set2.add("p63");

        set3.add("p63");
        set3.add("p19");


        List<Set<String>> output = compareThreeSets(set1, set2, set3);

        int counter = 0;
        for (Set<String> tmp : output) {
            System.out.println(counter);
            counter++;
            for (String tmp2 : tmp) {
                System.out.println(tmp2);
            }
        }
    }

    /**
     * Calculates intersections of three String Sets.
     * Order: All, 1&2, 1&3, 2&3.
     *
     * @param set1
     * @param set2
     * @param set3
     * @return List with 4 Sets of Strings.
     */
    public List<Set<String>> compareThreeSets(Set<String> set1, Set<String> set2, Set<String> set3) {

        List<Set<String>> output = new ArrayList<>();

        Set<String> intersectAll = new HashSet<>(set1);
        intersectAll.retainAll(set2);
        intersectAll.retainAll(set3);

        Set<String> intersect12 = new HashSet<>(set1);
        intersect12.retainAll(set2);

        Set<String> intersect13 = new HashSet<>(set1);
        intersect13.retainAll(set3);

        Set<String> intersect23 = new HashSet<>(set2);
        intersect23.retainAll(set3);

        output.add(intersectAll);
        output.add(intersect12);
        output.add(intersect13);
        output.add(intersect23);

        return output;
    }

}
