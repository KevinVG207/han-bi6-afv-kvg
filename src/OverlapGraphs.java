import java.util.ArrayList;
import java.util.List;

public class OverlapGraphs {

    public void run(){

        // Amount of nt to use for overlap.
        int k = 3;

        // This could be anything, but the pdf doesn't specify any particular way of getting data.
        // So this is the sample data on the exercise webpage.
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{">Rosalind_0498","AAATAAA"});
        data.add(new String[]{">Rosalind_2391","AAATTTT"});
        data.add(new String[]{">Rosalind_2323","TTTTCCC"});
        data.add(new String[]{">Rosalind_0442","AAATCCC"});
        data.add(new String[]{">Rosalind_5013","GGGTGGG"});

        // Determine overlap of data
        List<String[]> output = determineOverlap(data, k);

        // Print overlapping fasta headers
        for(String[] out : output){
            System.out.println(out[0] + " " + out[1]);
        }

    }

    /**
     * Determines if given input String[1] overlaps with another input String[1] by k characters.
     * @param s String[2] with [0] being FASTA header and [1] being sequence.
     * @param t String[2] with [0] being FASTA header and [1] being sequence.
     * @param k int amount to look for overlap with.
     * @return true if input overlaps, false if not.
     */
    private boolean overlaps(String[] s, String[] t, int k){
        return s[1].substring(s[1].length() - k).equals(t[1].substring(0, k));
    }

    /**
     * Determine overlap of the given data and given k.
     * @param data List of String[2]; input data.
     * @param k int amount to look for overlap with.
     * @return List of String[2] with FASTA headers that overlap paired.
     */
    private List<String[]> determineOverlap(List<String[]> data, int k){
        List<String[]> output = new ArrayList<>();

        // This nested for-loop goes through all combinations of two sequences only once.
        for (int i = 0; i < data.size()-1; i++){
            for (int j = i+1; j < data.size(); j++){
                if (overlaps(data.get(i), data.get(j), k)){
                    output.add(new String[]{data.get(i)[0], data.get(j)[0]});
                }
                if (overlaps(data.get(j), data.get(i), k)){
                    output.add(new String[]{data.get(j)[0], data.get(i)[0]});
                }
            }
        }
        return output;
    }

}
