import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DichoSearch {

    public static void main(String[] args) throws IOException{

        Random rand = new Random();
        int n = rand.nextInt(101);

        while(true) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter an integer between 0 and 100:");
            int i = Integer.parseInt(br.readLine());
            System.out.println(n);

            // Integer.compare(n,i)<0)
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
