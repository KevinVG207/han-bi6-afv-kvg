import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TSVTools {

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
