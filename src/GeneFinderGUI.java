import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneFinderGUI {

    JPanel pnPanel0;
    JTextArea taArea0;
    JTextArea taArea1;
    JTextArea taArea2;
    JButton btBut0;
    JLabel lbLabel0;
    JTextArea taArea3;
    JComboBox cmbCombo1;
    GeneFinder GF;
    List<Set<String>> compareOutput;

    /**
     * Creates the GUI.
     */
    public void run() {
        GF = new GeneFinder();

        // Initial JFrame design created using Jvider (http://www.jvider.com/)

        JFrame f = new JFrame("Gene Finder Alpha");
        f.setLayout(new FlowLayout());
        f.setSize(500, 400);
        f.setResizable(false);

        pnPanel0 = new JPanel();

        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout(gbPanel0);

        taArea0 = new JTextArea(8, 10);

        JScrollPane scpArea0 = new JScrollPane(taArea0);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 6;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(0, 20, 0, 20);
        gbPanel0.setConstraints(scpArea0, gbcPanel0);
        pnPanel0.add(scpArea0);

        taArea1 = new JTextArea(8, 10);

        JScrollPane scpArea1 = new JScrollPane(taArea1);
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 6;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(0, 0, 0, 20);
        gbPanel0.setConstraints(scpArea1, gbcPanel0);
        pnPanel0.add(scpArea1);

        taArea2 = new JTextArea(8, 10);

        JScrollPane scpArea2 = new JScrollPane(taArea2);
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 6;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(0, 0, 0, 20);
        gbPanel0.setConstraints(scpArea2, gbcPanel0);
        pnPanel0.add(scpArea2);

        btBut0 = new JButton("Calculate");

        gbcPanel0.gridx = 3;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(0, 0, 0, 20);
        gbPanel0.setConstraints(btBut0, gbcPanel0);
        pnPanel0.add(btBut0);

        lbLabel0 = new JLabel("Enter three lists of gene names:                 (Not case sensitive)");

        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 0;
        gbcPanel0.gridwidth = 6;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(20, 20, 0, 0);
        gbPanel0.setConstraints(lbLabel0, gbcPanel0);
        pnPanel0.add(lbLabel0);

        taArea3 = new JTextArea(6, 10);
        JScrollPane scpArea3 = new JScrollPane(taArea3);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 8;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(10, 20, 20, 20);
        gbPanel0.setConstraints(scpArea3, gbcPanel0);
        pnPanel0.add(scpArea3);

        String[] dataCombo1 = {"Overlap in all", "Overlap 1 & 2", "Overlap 1 & 3",
                "Overlap 2 & 3"};
        cmbCombo1 = new JComboBox(dataCombo1);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 2;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(20, 20, 0, 0);
        gbPanel0.setConstraints(cmbCombo1, gbcPanel0);
        pnPanel0.add(cmbCombo1);

        f.add(pnPanel0);


        btBut0.addActionListener(e ->
                calculate());

        cmbCombo1.addActionListener(e ->
                dropdownUpdate());

        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    /**
     * On button press: Calculate intersections of the three JTextAreas.
     * Then update the results.
     */
    private void calculate() {
        compareOutput = GF.compareThreeSets(textAreaToSet(taArea0), textAreaToSet(taArea1), textAreaToSet(taArea2));
        dropdownUpdate();
    }

    /**
     * Turns JTextArea text into Set of Strings, split by newline.
     *
     * @param area
     * @return Set of Strings of the rows in the JTextArea.
     */
    private Set<String> textAreaToSet(JTextArea area) {
        Set<String> output = new HashSet<>();
        String[] areaText = area.getText().toUpperCase().split("\n");
        Collections.addAll(output, areaText);

        return output;
    }

    /**
     * Updates the results visible in the output JTextArea.
     * Results shown are based on the currently selected JComboBox index.
     */
    private void dropdownUpdate() {
        int curSelect = cmbCombo1.getSelectedIndex();
        taArea3.setText("");

        Set<String> curSelSet = compareOutput.get(curSelect);
        for (String item : curSelSet) {
            taArea3.setText(taArea3.getText() + item + "\n");
        }
    }

}
