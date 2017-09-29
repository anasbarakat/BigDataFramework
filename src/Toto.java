public class Toto {

        private static int count = 0;
        private int age;
        private String name;
        private double money;

        public Toto(int age, double money, String name){
            this.age = age;
            this.money = money;
            this.name = name;
            count++;

        }

        public String getName(){
            return name;
        }
        public static int getCount(){
            return count;
        }

        public int getAge(){
            return age;
        }

    }
