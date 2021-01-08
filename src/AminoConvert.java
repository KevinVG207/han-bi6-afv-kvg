import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class AminoConvert {

    // These three HashMaps have one type of writing as key,
    // and the other two as their value in String[].
    // I'm sure there are other methods of doing this,
    // and this takes up 3x as much memory,
    // but it was easier to work it out in this way ;)
    public HashMap<String, String[]> oneChar = new HashMap<>();
    public HashMap<String, String[]> threeChar = new HashMap<>();
    public HashMap<String, String[]> fullName = new HashMap<>();

    /**
     * Program loop;
     * Asks for user input of an amino acid and converts it to the other two ways of writing.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inputVar = "";
        boolean exiting = false;
        String[] tmp;

        prepareMaps();

        while (!exiting) {
            System.out.print("Enter amino acid (one/three letter code or full name):\t");
            inputVar = scanner.next().toLowerCase();
            switch (inputVar) {
                case "q":
                case "quit":
                case "exit":
                    exiting = true;
                    break;
                default:
                    if (inputVar.length() == 1) {
                        // 1 letter
                        inputVar = inputVar.toUpperCase();
                        if (oneChar.containsKey(inputVar)) {
                            tmp = oneChar.get(inputVar);
                            System.out.println("Three letter code: " + tmp[0] + "\tFull name: " + tmp[1]);
                        } else {
                            System.out.println("Invalid input.");
                        }
                    } else if (inputVar.length() == 3) {
                        // 3 letters
                        if (threeChar.containsKey(inputVar)) {
                            tmp = threeChar.get(inputVar);
                            System.out.println("One letter code: " + tmp[0] + "\tFull name: " + tmp[1]);
                        } else {
                            System.out.println("Invalid input.");
                        }
                    } else {
                        // Full length (maybe)
                        if (fullName.containsKey(inputVar)) {
                            tmp = fullName.get(inputVar);
                            System.out.println("One letter code: " + tmp[0] + "\tThree letter code: " + tmp[1]);
                        } else {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Loads the three methods of writing an amino acid from file into HashMaps.
     */
    public void prepareMaps() {
        String path = "src/resources/amino-acids.txt";

        BufferedReader r;
        try {
            r = new BufferedReader(new FileReader(path));
            String curLine = r.readLine();
            while (curLine != null) {
                String[] splitLine = curLine.split("\t");
                oneChar.put(splitLine[2], new String[]{splitLine[1], splitLine[0]});
                threeChar.put(splitLine[1], new String[]{splitLine[2], splitLine[0]});
                fullName.put(splitLine[0], new String[]{splitLine[2], splitLine[1]});
                curLine = r.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
