import util.ViewWriteSvc;
import util.shuffleArrayGen;

import java.util.HashMap;


public class BSTSimpleTests {

    HashMap<String, HashMap<String, Object>> results = new HashMap<>() {{
        put("TreeShuffle", new HashMap<>());
        put("TreeAscending", new HashMap<>());
    }};


    public static void main(String[] args) {

        BSTSimpleTests bstST = new BSTSimpleTests();

        int length1 = (int) 5e7;
        int length2 = (int) 1e6;    // jvm should be started with '-Xss16m' key, otherwise value should be <= 23e3

        {
            BST bst = new BST();
            int[] array = shuffleArrayGen.setShuffle(length1);
            bstST.results.get("TreeShuffle").put("Array type", "Shuffle");
            bstST.results.get("TreeShuffle").put("Array length", length1);
            bstST.startTesting(bst, array, "TreeShuffle");
        }

        {
            BST bst = new BST();
            int[] array = shuffleArrayGen.setShuffle(length2);
            bstST.results.get("TreeAscending").put("Array type", "Ascending");
            bstST.results.get("TreeAscending").put("Array length", length2);
            bstST.startTesting(bst, array, "TreeAscending");
        }
        ViewWriteSvc.printHashMap(bstST.results);
        ViewWriteSvc.writeResultsToFile(bstST.results);

    }

    void startTesting(BST tree, int[] arr, String treeName) {

        testInit(tree, arr, treeName);

        long start = System.nanoTime();
        int[] sortedTree = tree.getSorted();
        double getSortedTime = (System.nanoTime() - start) / 1e9;

        results.get(treeName).put("GetSorted Time (s)", getSortedTime);
        results.get(treeName).put("GetSorted Check", sortedTree != null ? isAscending(sortedTree) : null);

        int testLen = arr.length / 10;
        int[] testArray = treeName.equals("TreeAscending") ?
                shuffleArrayGen.setAscending(testLen, arr.length) :
                shuffleArrayGen.setShuffle(testLen, arr.length);

        testInsert(tree, testArray, treeName);
        testSearch(tree, testArray, treeName);
        testRemove(tree, testArray, treeName);
        if (sortedTree != null)
            results.get(treeName).put("Check", checkConsistency(tree, sortedTree, treeName));
    }

    void testInit(BST tree, int[] arr, String treeName) throws StackOverflowError {
        System.out.println(treeName + " init ");
        long start = System.nanoTime();
        tree.init(arr);
        Double end = (System.nanoTime() - start) / 1e9;
        results.get(treeName).put("Fill Time (s)", end);
    }

    void testInsert(BST tree, int[] arr, String treeName) throws StackOverflowError {
        System.out.println(treeName + " insert ");
        long start = System.nanoTime();
        for (int e : arr) {
            tree.insert(e);
        }
        Double end = (System.nanoTime() - start) / 1e9;
        results.get(treeName).put("Insert Time (s)", end);
    }

    void testSearch(BST tree, int[] arr, String treeName) throws StackOverflowError {
        System.out.println(treeName + " search ");
        boolean[] result = new boolean[arr.length];
        int i = 0;
        long start = System.nanoTime();
        for (int key : arr) {
            result[i++] = tree.search(key);
        }
        Double end = (System.nanoTime() - start) / 1e9;
        boolean checkResult = true;
        for (boolean e : result)
            if (!e) {
                checkResult = false;
                break;
            }

        results.get(treeName).put("Search Time (s)", end);
        results.get(treeName).put("Search Check", checkResult);
    }

    void testRemove(BST tree, int[] arr, String treeName) throws StackOverflowError {
        System.out.println(treeName + " remove ");
        long start = System.nanoTime();
        for (int e : arr) {
            tree.remove(e);
        }
        Double end = (System.nanoTime() - start) / 1e9;
        results.get(treeName).put("Remove Time (s)", end);
    }

    boolean checkConsistency(BST tree, int[] arr, String treeName) {
        System.out.println(treeName + " consistency ");
        int[] result = tree.getSorted();
        if (result.length != arr.length) return false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != result[i]) return false;
        }
        return true;
    }

    static boolean isAscending(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i - 1] > arr[i]) return false;
        return true;
    }
}
