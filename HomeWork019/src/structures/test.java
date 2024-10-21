package structures;


import java.util.Random;



public class test {

    public static void main(String[] args) {



        CustomLinkedList<String> llc = new CustomLinkedList<>();
        int testLen = 20;

        for (int i = 0; i < testLen; i++) {
            llc.add(String.format("line #%d", i));
        }

        for (int i = 0; i < testLen; i++) {
            System.out.println(llc.get(i));
        }

        llc.clear();

        System.out.println("=".repeat(40));
        for (int i = 0; i < testLen / 4; i++) {
            llc.add(String.format("line #%d", i));
        }

        for (int i = 0; i < llc.size(); i++) {
            System.out.println(llc.get(i));
        }

        System.out.println("=".repeat(40));

        llc.reverse();

        System.out.println("=".repeat(40));
        for (int i = 0; i < llc.size(); i++) {
            System.out.println(llc.get(i));
        }
    }


    public static void main1(String[] args) {
        Random rnd = new Random();
        int[] ar = new int[20];
        for (int i = 0; i < ar.length; i++) ar[i] = rnd.nextInt(10, 100);
        for (int x : ar) System.out.print(x + " ");



    }

    public static void main0(String[] args) {
        CustomStack ls = new CustomStack();

        while (!ls.isEmpty())
            System.out.println(ls.peek());

        for (int i = 0; i < 30; i++) {
            System.out.println(ls.push(String.format("test %d", i)));
        }
        System.out.println('\n' + "=".repeat(80));
        System.out.println("=".repeat(80) + '\n');

        while (!ls.isEmpty()) {
            System.out.print(ls.peek() + "  \t:\t ");
            System.out.println(ls.pop());
        }


        System.out.println('\n' + "=".repeat(80));
        System.out.println("=".repeat(80) + '\n');

        ls.push("End of test");
        System.out.println(ls.peek());
        System.out.println(ls.pop());

    }
}
