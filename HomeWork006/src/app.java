import sortings.BubbleSort;
import sortings.InsertionSort;
import sortings.ShellSort;
import util.ArrayGen;

import java.util.Arrays;

public class app {
    public static void main(String[] args) {
        int N = 1000_000;
        BubbleSort bubble = new BubbleSort();
        InsertionSort insert = new InsertionSort();
        ShellSort shell = new ShellSort();
        ShellSort shell2 = new ShellSort();
        int[] arr1 = ArrayGen.setRandom(N, true);
        int[] arr2 = ArrayGen.setRandom(N, true);
        int[] arr3 = ArrayGen.setRandom(N, true);
        int[] arr4 = ArrayGen.setRandom(N, true);

        shell.init(arr1);
        long start = System.nanoTime();
        shell.sortCiura();
        double shellCiuraTime = (System.nanoTime() - start) / 1e9;
        System.out.println(isAscending(arr1));

        shell.init(arr3);
        start = System.nanoTime();
        shell.sortKnuth(N);
        double shellKnuthTime = (System.nanoTime() - start) / 1e9;
        System.out.println(isAscending(arr3));

        shell.init(arr4);
        start = System.nanoTime();
        shell.sortSedgewick86(N);
        double shellSedgewickTime = (System.nanoTime() - start) / 1e9;
        System.out.println(isAscending(arr4));

        shell2.init(arr2);
        start = System.nanoTime();
        shell2.sort();
        double shellSimpleTime = (System.nanoTime() - start) / 1e9;
        System.out.println(isAscending(arr2));

        System.out.printf(
                "shellSimpleTime:    %f s" +
                        "\nshellCiuraTime:     %f s" +
                        "\nshellKnuthTime:     %f s" +
                        "\nshellSedgewickTime: %f s\n",
                shellSimpleTime, shellCiuraTime,
                shellKnuthTime, shellSedgewickTime);
    }

    static boolean isAscending(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.println("i-1: " + (i - 1) + " i: " + i + " arr[i-1] " + arr[i - 1] + " arr[i] " + arr[i]);
                return false;

            }
        }
        return true;
    }
}
