import java.util.ArrayList;
import java.util.Scanner;

public class CheckExpression {

    // Characters because I need them to print what kind of bracket it is.
    private final ArrayList<Character> brackets = new ArrayList<>();

    private boolean add = true;
    private char expectedBracket;


    /**
     * Gets a string through user input and verifies validity of brackets.
     */
    public void run(){
        // Get user input.
        String expression = getUserInput();

        // Verify String.
        verifyString(expression);

    }


    /**
     * Gets user input String.
     * @return String - User input String.
     */
    private String getUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter expression:");
        return scan.nextLine();
    }


    /**
     * Verify if brackets are correct in given input String.
     * @param expr String - Input String.
     */
    private void verifyString(String expr) {

        for(int i = 0; i < expr.length(); i++){
            char curChar = expr.charAt(i);
            switch(curChar){
                case '(':
                case '[':
                case '{':
                    add = true;
                    break;
                case ')':
                    add = false;
                    expectedBracket = '(';
                    break;
                case ']':
                    add = false;
                    expectedBracket = '[';
                    break;
                case '}':
                    // Remove
                    add = false;
                    expectedBracket = '{';
                    break;
            }

            // Add or remove from list.
            if(add){
                brackets.add(curChar);
            }else{
                if(brackets.size() >= 1){
                    if(brackets.get(brackets.size()-1) == expectedBracket){
                        // Closure as expected, just remove.
                        brackets.remove(brackets.size()-1);
                    }else{
                        // Incorrect closure.
                        System.out.println("Error: Incorrect bracket closure of " + curChar + " at position " + i);
                        return;
                    }
                }else{
                    // Incorrect closure.
                    System.out.println("Error: Incorrect bracket closure of " + curChar + " at position " + i);
                    return;
                }
            }
        }

        // For loop succeeded: Check if leftovers exist.
        if(brackets.size() > 0){
            // Not enough closures.
            System.out.println("Error: Not enough closing brackets. Unclosed brackets are:");
            for(char bracket : brackets){
                System.out.println(bracket);
            }
        }else{
            // No errors found.
            System.out.println("No errors in brackets found!");
        }

    }

}
