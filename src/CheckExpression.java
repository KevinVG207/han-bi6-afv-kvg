import java.util.ArrayList;
import java.util.Scanner;

public class CheckExpression {

    // Characters because I need them to print what kind of bracket it is.
    private final ArrayList<Character> brackets = new ArrayList<>();
    private final ArrayList<Character> squareBrackets = new ArrayList<>();
    private final ArrayList<Character> curlyBrackets = new ArrayList<>();

    private boolean add = true;
    private ArrayList<Character> curList;

    public void run(){
        // Get user input
        String expression = getUserInput();

        // Verify String
        verifyString(expression);

    }

    private String getUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter expression:");
        return scan.nextLine();
    }

    private void verifyString(String expr) {

        for(int i = 0; i < expr.length(); i++){
            char curChar = expr.charAt(i);
            switch(curChar){
                case '(':
                    // Add
                    add = true;
                    curList = brackets;
                    break;
                case ')':
                    // Remove
                    add = false;
                    curList = brackets;
                    break;
                case '[':
                    // Add
                    add = true;
                    curList = squareBrackets;
                    break;
                case ']':
                    // Remove
                    add = false;
                    curList = squareBrackets;
                    break;
                case '{':
                    // Add
                    add = true;
                    curList = curlyBrackets;
                    break;
                case '}':
                    // Remove
                    add = false;
                    curList = curlyBrackets;
                    break;
            }

            // Add or remove from list
            if(add){
                curList.add(curChar);
            }else{
                if(curList.size() >= 1){
                    curList.remove(curList.size()-1);
                }else{
                    // Problem in brackets!
                    System.out.println("Error: At least one too many of " + curChar);
                    return;
                }
            }

        }

        // For loop succeeded: Check if leftovers exist.
        boolean error = false;
        if(brackets.size() > 0){
            System.out.println("Error: At least one too many of " + brackets.get(0));
            error = true;
        }
        if(squareBrackets.size() > 0){
            System.out.println("Error: At least one too many of " + squareBrackets.get(0));
            error = true;
        }
        if(curlyBrackets.size() > 0){
            System.out.println("Error: At least one too many of " + curlyBrackets.get(0));
            error = true;
        }

        // If no errors.
        if(!error){
            System.out.println("No errors in brackets found!");
        }

    }

}
