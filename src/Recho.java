public class Recho {

    public static void main(String[] args) {

        if (args.length > 2) {

            for (int i = 1; i < args.length; i++) {
                System.out.print(args[args.length - i] + " ");
            }

            System.out.print(args[0]);

        }

        if(args.length == 2){
            System.out.print(args[1] + " " + args[0]);
        }

        if(args.length == 1){
            System.out.print(args[0]);
        }

        else{
            System.out.print("");
        }
    }
}
