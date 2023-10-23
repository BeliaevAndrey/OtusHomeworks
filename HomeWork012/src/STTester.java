public class STTester {

    public static void main(String[] args) {
        STTester tester = new STTester();
        tester.test1();
    }

    void test1() {
        SplayTree st = new SplayTree(20);
        st.insert(40);
        st.insert(50);
        st.insert(15);
        st.insert(10);
        st.insert(150);
        st.insert(30);
        st.insert(60);
        st.printTree();
        System.out.println();
        System.out.println(st.search(10).key);
        System.out.println(st.search(150).key);

    }
}
