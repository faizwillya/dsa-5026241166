package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.util.Iterator;

public class Main {

    static LinkedList<String[]> orders = new LinkedList<>();
    static LinkedList<String[]> foods = new LinkedList<>();
    static LinkedList<String[]> drinks = new LinkedList<>();
    static LinkedList<String[]> successfulOrders = new LinkedList<>();

    static Stack<String[]> failedOrders = new Stack<>();

    public static void main(String[] args) {

        readOrders();
        addFoods();
        addDrinks();

        Queue<String[]> queue = new LinkedList<>();

        while (!orders.isEmpty()) {
            queue.offer(orders.removeFirst());
        }

        processOrders(queue);
        showResult();
    }

    // Membaca data dari orders.txt
    public static void readOrders() {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");

            orders.add(data);
        }

        scanner.close();
    }

    // Menambahkan makanan dan stok awal
    public static void addFoods() {

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});
    }

    // Menambahkan minuman dan stok awal
    public static void addDrinks() {

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});
    }

    // Memproses order menggunakan Queue
    public static void processOrders(Queue<String[]> queue) {

        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            // Cek makanan
            if (!food.equals("-")) {
                foodAvailable = checkStock(foods, food);
            }

            // Cek minuman
            if (!drink.equals("-")) {
                drinkAvailable = checkStock(drinks, drink);
            }

            // Jika semua tersedia
            if (foodAvailable && drinkAvailable) {

                if (!food.equals("-")) {
                    reduceStock(foods, food);
                }

                if (!drink.equals("-")) {
                    reduceStock(drinks, drink);
                }

                successfulOrders.add(order);

            } else {
                // Jika gagal, masukkan ke Stack
                failedOrders.push(order);
            }
        }
    }

    // Mengecek apakah stok masih tersedia
    public static boolean checkStock(
            LinkedList<String[]> list, String item) {

        Iterator<String[]> iterator = list.iterator();

        while (iterator.hasNext()) {

            String[] data = iterator.next();

            if (data[0].equals(item)) {

                int stock = Integer.parseInt(data[1]);

                if (stock > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    // Mengurangi stok sebanyak 1
    public static void reduceStock(
            LinkedList<String[]> list, String item) {

        Iterator<String[]> iterator = list.iterator();

        while (iterator.hasNext()) {

            String[] data = iterator.next();

            if (data[0].equals(item)) {

                int stock = Integer.parseInt(data[1]);
                stock = stock - 1;

                data[1] = String.valueOf(stock);
            }
        }
    }

    // Menampilkan hasil
    public static void showResult() {

        System.out.println("=== Successfully Processed Orders ===");

        Iterator<String[]> successIterator =
                successfulOrders.iterator();

        while (successIterator.hasNext()) {

            String[] order = successIterator.next();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }

        System.out.println("=== Remaining Food Stock ===");

        Iterator<String[]> foodIterator = foods.iterator();

        while (foodIterator.hasNext()) {

            String[] food = foodIterator.next();

            System.out.println(
                food[0] + " : " + food[1]
            );
        }

        System.out.println("=== Remaining Drink Stock ===");

        Iterator<String[]> drinkIterator = drinks.iterator();

        while (drinkIterator.hasNext()) {

            String[] drink = drinkIterator.next();

            System.out.println(
                drink[0] + " : " + drink[1]
            );
        }

        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {

            String[] order = failedOrders.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}