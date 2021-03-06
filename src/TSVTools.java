import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class with some functions to read/write TSV files.
 */
public class TSVTools {

    /**
     * Loads TSV from file into ArrayList of Gene objects.
     * @param path - String; filepath.
     * @return ArrayList Gene
     */
    public static ArrayList<Gene> tsvToList(String path){
        ArrayList<Gene> geneList = new ArrayList<>();

        BufferedReader r;
        try{
            r = new BufferedReader(new FileReader(path));
            String curLine = r.readLine();
            if (curLine.startsWith("#")) {
                // Header, so skip.
                curLine = r.readLine();
            }
            while(curLine != null){
                String[] splitString = curLine.split("\t");
                geneList.add(new Gene(Integer.parseInt(splitString[1]),splitString[2],splitString[7]));

                //Read next
                curLine = r.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return geneList;
    }

    /**
     * Loads TSV into a String[] and into a list.
     * [0] Gene ID
     * [1] Gene Name
     * [2] Chromosome
     * @param geneList List to add the String arrays to.
     * @param path Filepath.
     * @param countFilter Maximum amount of lines to read from file.
     * @return List[String[]] with genes.
     */
    public static List<String[]> tsvToListString(List<String[]> geneList, String path, int countFilter){
        boolean finished = false;
        boolean countFilterEnabled = true;
        int curCount = 0;

        if (countFilter == -1){
            countFilterEnabled = false;
        }

        BufferedReader r;
        try{
            r = new BufferedReader(new FileReader(path));
            String curLine = r.readLine();
            if (curLine.startsWith("#")) {
                // Header, so skip.
                curLine = r.readLine();
            }
            while(curLine != null && !finished){
                curCount += 1;
                String[] splitString = curLine.split("\t");
                String[] outString = {splitString[1], splitString[2], splitString[6]};
                geneList.add(outString);

                if(countFilterEnabled && curCount >= countFilter){
                    finished = true;
                }

                //Read next
                curLine = r.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return geneList;
    }

    /**
     * Writes ArrayList Gene back into TSV file.
     * @param geneList - Arraylist Gene.
     * @param path - String; filepath to write.
     */
    public static void listToTSV(ArrayList<Gene> geneList, String path) {
        try {
            File fout = new File(path);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (Gene gene : geneList) {
                bw.write(gene.getGene_id() + "\t" + gene.getSymbol() + "\t" + gene.getLocation() + "\n");
            }

            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
