import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AminoConvert {

    public HashMap<String,String[]> oneChar = new HashMap<>();
    public HashMap<String,String[]> threeChar = new HashMap<>();
    public HashMap<String,String[]> fullName = new HashMap<>();

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
                        if(oneChar.containsKey(inputVar)){
                            tmp = oneChar.get(inputVar);
                            System.out.println("Three letter code: " + tmp[0] + "\tFull name: " + tmp[1]);
                        }else{
                            System.out.println("Invalid input.");
                        }
                    }else if (inputVar.length() == 3){
                        // 3 letters
                        if(threeChar.containsKey(inputVar)){
                            tmp = threeChar.get(inputVar);
                            System.out.println("One letter code: " + tmp[0] + "\tFull name: " + tmp[1]);
                        }else{
                            System.out.println("Invalid input.");
                        }
                    }else{
                        // Full length (maybe)
                        if(fullName.containsKey(inputVar)){
                            tmp = fullName.get(inputVar);
                            System.out.println("One letter code: " + tmp[0] + "\tThree letter code: " + tmp[1]);
                        }else{
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
            }
        }
    }

    public void prepareMaps(){
        String path = "src/resources/amino-acids.txt";

        BufferedReader r;
        try{
            r = new BufferedReader(new FileReader(path));
            String curLine = r.readLine();
            while(curLine != null) {
                String[] splitLine = curLine.split("\t");
                oneChar.put(splitLine[2], new String[]{splitLine[1], splitLine[0]});
                threeChar.put(splitLine[1], new String[]{splitLine[2], splitLine[0]});
                fullName.put(splitLine[0], new String[]{splitLine[2], splitLine[1]});
                curLine = r.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
