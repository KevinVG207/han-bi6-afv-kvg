import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ChromLink {

    private List<List<String[]>> chromList;
    private List<String> listIndices;

    /**
     * Maakt geneste lijsten met NCBI gene_info-data, vanaf een ingegeven bestandspad.
     * Doet daarop wat testen met lijsten van verschillende grootten en typen.
     * @param filePath String - Pad naar gene_info-bestand.
     */
    public void run(String filePath){

        // Arraylist will only contain ID, Name and Chromosome to limit memory usage.

        System.out.println("Choose Array or Linked list (a/l): ");
        Scanner scanner = new Scanner(System.in);
        String inputVar = "";
        inputVar = scanner.next().toLowerCase();
        int curCountLimit = -1;
        int testCountLimit = 4934147;
        int curTestCount = 1000;
        long beforeMillis;
        long afterMillis;
        long netMillis;

        switch(inputVar){
            case "a":
            case "array":
            case "arr":
            case "arraylist":
                List<String[]> geneListA = new ArrayList<>();
                while(curTestCount < testCountLimit){
                    geneListA = new ArrayList<>();
                    beforeMillis = System.currentTimeMillis();
                    chromList = orderArray(TSVTools.tsvToListString(geneListA, filePath, curTestCount));
                    afterMillis = System.currentTimeMillis();
                    netMillis = afterMillis - beforeMillis;
                    System.out.println("1\t" + curTestCount + "\t" + netMillis);

                    beforeMillis = System.currentTimeMillis();
                    String[] tmpData = chromList.get(chromList.size()-1).get(chromList.get(chromList.size()-1).size()-1);
                    afterMillis = System.currentTimeMillis();
                    netMillis = afterMillis - beforeMillis;
                    System.out.println("2\t" + curTestCount + "\t" + netMillis);

                    curTestCount *= 2;
                }
                //geneListA = new ArrayList<>();
                //chromList = orderArray(TSVTools.tsvToListWhole(geneListA, filePath, curCountLimit));
                break;
            case "l":
            case "linked":
            case "link":
            case "linkedlist":
                List<String[]> geneListL = new LinkedList<>();
                while(curTestCount < testCountLimit){
                    geneListL = new LinkedList<>();
                    beforeMillis = System.currentTimeMillis();
                    chromList = orderArray(TSVTools.tsvToListString(geneListL, filePath, curTestCount));
                    afterMillis = System.currentTimeMillis();
                    netMillis = afterMillis - beforeMillis;
                    System.out.println("1\t" + curTestCount + "\t" + netMillis);

                    beforeMillis = System.currentTimeMillis();
                    String[] tmpData = chromList.get(chromList.size()-1).get(chromList.get(chromList.size()-1).size()-1);
                    afterMillis = System.currentTimeMillis();
                    netMillis = afterMillis - beforeMillis;
                    System.out.println("2\t" + curTestCount + "\t" + netMillis);

                    curTestCount *= 2;
                }
                //List<String[]> geneListL = new LinkedList<>();
                //chromList = orderLinked(TSVTools.tsvToListWhole(geneListL, filePath, curCountLimit));
                break;
            default:
                System.out.println("Invalid input.");
                return;
        }

        System.out.println("done");
        //printData();

    }

    /**
     * Zet genenlijst in geneste lijst per chromosoom, in ingegeven lijst.
     * @param geneList List - De lijst waarin de chromosomen worden opgeslagen.
     * @return List[List[String[]]] - Lijst met daarin per chromosoom een lijst met genen in String[].
     */
    private List<List<String[]>> orderArray(List<String[]> geneList){
        listIndices = new ArrayList<>();
        List<List<String[]>> orderedList = new ArrayList<>();

        for(String[] curGene : geneList){
            String tmpChrom = curGene[2];
            int chromIndex = listIndices.indexOf(tmpChrom);

            if(chromIndex == -1){
                listIndices.add(tmpChrom);
                chromIndex = listIndices.indexOf(tmpChrom);
                orderedList.add(new ArrayList<>());
            }

            orderedList.get(chromIndex).add(curGene);

        }

        return orderedList;
    }

    /**
     * Print wat informatie over de geneste genenlijst.
     */
    private void printData(){
        System.out.println("Main list size");
        System.out.println(chromList.size());
        System.out.println("Sublists size");
        int total = 0;
        for(int i = 0; i < chromList.size(); i++){
            System.out.println("-Chrom: " + listIndices.get(i));
            System.out.println(chromList.get(i).size());
            total += chromList.get(i).size();
        }
        System.out.println("Total:");
        System.out.println(total);
    }

}
