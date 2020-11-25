import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Main method. Allows user to choose the exercise to show.
         */

        System.out.println((-1 % 100));

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
                    GameOfLife gol = new GameOfLife();
                    gol.run();
                    break;
                case "2-2":
                    GeneSorter gs = new GeneSorter();
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        gs.run(selectedFile.getAbsolutePath());
                    }
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
