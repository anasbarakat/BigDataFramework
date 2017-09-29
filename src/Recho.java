public class Recho {

    public static void main(String[] args) {

        //principal case to treat: we begin from the end of the args tab
        //and read teh elements in reverse
        if (args.length > 2) {

            for (int i = 1; i < args.length; i++) {
                System.out.print(args[args.length - i] + " ");
            }

            System.out.print(args[0]);

        }

        // treating remaining cases which do not work with the previous if block
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
