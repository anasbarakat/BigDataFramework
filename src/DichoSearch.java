import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DichoSearch {

    public static void main(String[] args) throws IOException{

        Random rand = new Random();
        //generating a random number between 0 and 100:
        //we chose to limit tyhe program to the range 0-100 for simplicity,
        //however we can extend it by using the max int value but in this case
        //can be difficult to find the number easily without any upper bound
        int n = rand.nextInt(101);

        //infinite loop until the program stops with a return to exit the function and the program
        while(true) {

            // Several options are possible even InputStream, we chose the BufferedReader even of the 
            //input string expected is not huge. 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter an integer between 0 and 100:");
            
            //Parse string read into an int
            int i = Integer.parseInt(br.readLine());
            //System.out.println(n); this line was for verification purpose to see the number generated 
            //by the program and test the program

            // "Integer.compare" method is used here to do the comparison properly
            // to avoid some issues even if here the simple condition option (n-i <0) also works
            if (Integer.compare(n,i) < 0) {
                System.out.println("The secret number is smaller than " + i);
            } else if (Integer.compare(n,i) > 0) {
                System.out.println("The secret number is bigger than " + i);
            } else if (Integer.compare(n,i) == 0){
                System.out.println("CONGRATULATIONS ! You found the secret number " + i);
                return;
            }

        }
    }
}
