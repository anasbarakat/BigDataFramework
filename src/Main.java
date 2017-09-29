public class Main {

    public static void main(String[] args){

        Toto t = new Toto(12, 13.5, "Toto");
        t.getAge();
        t.getName();
        int age = t.getAge();
        System.out.println("Hello World");

        for(String a : args){
            System.out.println("parameter"+a+"is correctly read");
        }

        for (int i=0; i<args.length; i++){
            System.out.println("parameter"+args[i]+"is correctly read");
        }

    }
}

