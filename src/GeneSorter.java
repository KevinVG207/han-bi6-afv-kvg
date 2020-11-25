import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeneSorter {

    /**
     * Sorts the list from file path.
     * @param filePath
     */
    public void run(String filePath){
        ArrayList<Gene> geneList = TSVTools.tsvToList(filePath);
        Collections.sort(geneList);
        TSVTools.listToTSV(geneList, filePath+".sorted");
        JOptionPane.showMessageDialog(null,"Sorting completed. File has been saved as .sorted");
    }

}
