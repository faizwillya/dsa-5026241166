package lw04.unguided;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner playlistScanner = new Scanner(
                Main.class.getResourceAsStream("parcels.txt")
            );

            // Read number of parcels
            int n = playlistScanner.nextInt();

            // Store parcel weights
            int[] parcels = new int[n];

            for (int i = 0; i < n; i++) {
                parcels[i] = playlistScanner.nextInt();
            }

            // Read number of targets
            int m = playlistScanner.nextInt();

            // Store target weights
            int[] targets = new int[m];

            for (int i = 0; i < m; i++) {
                targets[i] = playlistScanner.nextInt();
            }

            playlistScanner.close();

            // Array to store shelf after 2 passes
            int[] afterTwoPasses = new int[n];

            // Bubble Sort Descending
            for (int pass = 0; pass < n - 1; pass++) {

                for (int j = 0; j < n - 1 - pass; j++) {

                    if (parcels[j] < parcels[j + 1]) {

                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;

                    }
                }

                // Copy array after exactly 2 passes
                if (pass == 1) {

                    for (int i = 0; i < n; i++) {
                        afterTwoPasses[i] = parcels[i];
                    }

                }
            }

            // Display shelf after 2 passes
            System.out.println("==== After 2 Passes ====");
            printArray(afterTwoPasses);

            // Display fully sorted shelf
            System.out.println();
            System.out.println("==== Fully Sorted ====");
            printArray(parcels);

            // Display search results
            System.out.println();
            System.out.println("==== Search Results ====");

            // Binary Search Descending
            for (int i = 0; i < m; i++) {

                int target = targets[i];

                int low = 0;
                int high = n - 1;

                int steps = 0;
                int foundIndex = -1;

                while (low <= high) {

                    int mid = (low + high) / 2;

                    steps++;

                    if (parcels[mid] == target) {

                        foundIndex = mid;
                        break;

                    } else if (target > parcels[mid]) {

                        // Search left half
                        high = mid - 1;

                    } else {

                        // Search right half
                        low = mid + 1;

                    }

                }

                if (foundIndex != -1) {

                    System.out.println(
                        target + ": FOUND at index "
                        + foundIndex + " (steps: " + steps + ")"
                    );

                } else {

                    System.out.println(
                        target + ": NOT FOUND (steps: "
                        + steps + ")"
                    );

                }

            }

        } catch (NullPointerException e) {

            System.out.println("Error: parcels.txt resource not found.");

        }

    }

    // Method to print array
    public static void printArray(int[] array) {

        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(" ");
            }

        }

        System.out.println();

    }

}