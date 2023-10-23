import java.util.Arrays;
import java.util.Random;

public class STTester {

    public static void main(String[] args) {
        STTester tester = new STTester();
        tester.test1();
    }

    void test1() {
        SplayTree st = new SplayTree(20);

        int[] tArr = getShuffle(20, 1);
//        System.out.println(Arrays.toString(tArr));
        st.insert(40);
        st.insert(50);
        st.insert(15);
        st.insert(10);
        st.insert(150);
        st.insert(30);
        st.insert(60);
        for (int e : tArr)
            st.insert(e);
        st.printTree();
        System.out.println();
        System.out.println(st.search(10).key);
        System.out.println(st.search(150).key);
        st.printTree();
        System.out.println();
        st.printTree();

        System.out.println();

        System.out.println(st.search(11).key);
        st.printTree();
        System.out.println();

        System.out.println(st.search(1).key);
        st.printTree();

    }

    int[] getShuffle(int len, int start) {
        int[] array = new int[len];
        Random rnd = new Random(1234);
        for (int i = 0; i < len; i++) {
            array[i] = start + i;
            if (i > 2) {
                int k = rnd.nextInt(i);
                array[i] = array[k];
                array[k] = start + i;
            }
        }
        return array;
    }
}
