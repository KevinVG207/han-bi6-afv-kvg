import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Main method. Allows user to choose the exercise to show.
         */

        Scanner scanner = new Scanner(System.in);
        String inputVar = "";
        boolean exiting = false;

        while (!exiting) {
            System.out.println("(Typ q om af te sluiten.)");
            System.out.print("Voer afvinkopdr nummer in:\t");
            inputVar = scanner.next().toLowerCase();
            System.out.println("Starting opdracht " + inputVar + "...");
            switch (inputVar) {
                case "1":
                    Afv1.run();
                    break;
                case "2-1":
                    System.out.println("Warning: Pop-up may appear below active window.");
                    GameOfLife gol = new GameOfLife();
                    gol.run();
                    break;
                case "2-2":
                    System.out.println("Warning: Pop-up may appear below active window.");
                    GeneSorter gs = new GeneSorter();
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        gs.run(selectedFile.getAbsolutePath());
                    }
                    break;
                case "3-1":
                    CheckExpression ce = new CheckExpression();
                    ce.run();
                    break;
                case "3-2":
                    System.out.println("Please choose NCBI All_Mammalia.gene_info");
                    System.out.println("Warning: Pop-up may appear below active window.");
                    ChromLink cr = new ChromLink();
                    JFileChooser fileChooser2 = new JFileChooser();
                    fileChooser2.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result2 = fileChooser2.showOpenDialog(null);
                    if (result2 == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser2.getSelectedFile();
                        cr.run(selectedFile.getAbsolutePath());
                    }
                    break;
                case "5-1":
                    GeneFinderGUI GF = new GeneFinderGUI();
                    GF.run();
                    break;
                case "5-2":
                    AminoConvert AC = new AminoConvert();
                    AC.run();
                    break;
                case "q":
                case "quit":
                case "stop":
                case "exit":
                    exiting = true;
                    break;
                default:
                    System.out.println("Fout: Nummer niet gevonden.");
            }

        }
        System.out.println("Exiting...");

    }
}
