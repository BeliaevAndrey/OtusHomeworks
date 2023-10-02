import java.util.Arrays;

import static util.shuffleArrayGen.setShuffle;

public class App010 {
    public static void main(String[] args) {
        BST bst1 = new BST();
        BST bst2 = new BST();
        int length1 = (int) 2e2;
        int length2 = (int) 1e6;

        int[] arr1 = setShuffle(length1);
        int[] arr2 = setShuffle(length1);

        bst1.init(arr1);
        bst2.init(arr2);

        for (int key : arr1) {
            boolean out = bst1.search(key);
            if (!out) System.out.println(key);
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(bst1.getSorted()));
        for (int x : arr1) {
            System.out.println(Arrays.toString(bst1.getSorted()));
            bst1.remove(x);
            System.out.println(Arrays.toString(bst1.getSorted()));
            System.out.println();
        }
    }
}
