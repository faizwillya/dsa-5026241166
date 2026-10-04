package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // =========================
        // Problem 1 - Playlist
        // =========================

        ArrayList<String> playlist = new ArrayList<>();

        Scanner playlistScanner = new Scanner(
                Main.class.getResourceAsStream("playlist.txt")
        );

        while (playlistScanner.hasNextLine()) {
            String line = playlistScanner.nextLine();
            String[] data = line.split(" ", 2);

            String operation = data[0];

            if (operation.equals("ADD")) {
                String song = data[1];
                playlist.add(song);

            } else if (operation.equals("INSERT")) {
                String[] insertData = data[1].split(" ", 2);

                int index = Integer.parseInt(insertData[0]);
                String song = insertData[1];

                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {
                String song = data[1];

                if (playlist.contains(song)) {
                    playlist.remove(song);
                }
            }
        }

        playlistScanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }


        // =========================
        // Problem 2 - Participants
        // =========================

        LinkedHashSet<String> participants = new LinkedHashSet<>();
        int duplicate = 0;

        Scanner participantScanner = new Scanner(
                Main.class.getResourceAsStream("participants.txt")
        );

        while (participantScanner.hasNextLine()) {
            String name = participantScanner.nextLine();

            if (participants.contains(name)) {
                duplicate++;
            } else {
                participants.add(name);
            }
        }

        participantScanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicate);


        // =========================
        // Problem 3 - Inventory
        // =========================

        LinkedHashSet<String> productOrder = new LinkedHashSet<>();
        Map<String, Integer> inventory = new HashMap<>();

        int failedSales = 0;

        Scanner inventoryScanner = new Scanner(
                Main.class.getResourceAsStream("inventory.txt")
        );

        while (inventoryScanner.hasNextLine()) {
            String line = inventoryScanner.nextLine();
            String[] data = line.split(" ");

            String type = data[0];
            String product = data[1];
            int quantity = Integer.parseInt(data[2]);

            if (!productOrder.contains(product)) {
                productOrder.add(product);
            }

            if (type.equals("ADD")) {
                int stock = inventory.getOrDefault(product, 0);
                inventory.put(product, stock + quantity);
                
            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)
                        && inventory.get(product) >= quantity) {

                    int stock = inventory.get(product);
                    inventory.put(product, stock - quantity);

                } else {
                    failedSales++;
                }
            }
        }

        inventoryScanner.close();

        System.out.println("===== Problem 3 =====");

        for (String product : productOrder) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}

