package lw04.prelab;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner playlistScanner = new Scanner(
                Main.class.getResourceAsStream("branches.txt")
        );

        int n = playlistScanner.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = playlistScanner.nextInt();
        }

        int m = playlistScanner.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = playlistScanner.nextInt();
        }

        playlistScanner.close();

        insertionSort(branches);

        for (int i = 0; i < n; i++) {
            System.out.print(branches[i]);

            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < m; i++) {
            int index = linearSearch(branches, targets[i]);

            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i <= 3; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] < key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
