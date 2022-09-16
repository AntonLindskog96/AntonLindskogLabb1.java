

package sw.iths;

import java.sql.Time;
import java.util.Arrays;
import java.util.Scanner;

public class Labb {
    static TimePrice[] prices;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Elpriser\n" +
                    "========\n" +
                    "1. Inmatning\n" +
                    "2. Min, Max och Medel\n" +
                    "3. Sortera\n" +
                    "4. Bästa Laddningstid (4h)\n" +
                    "e. Avsluta");
            System.out.println("Välj ett alternativ: ");

            String input = scanner.nextLine();
            if (input.equals("E"))
                break;
            if (input.equals("e"))
                break;


            if (input.equals("1")) {
                userPickPrice(); //Kommentera bort om du vill ha random priser.
                //pricesRandom(); // Lägg till dessa om random priser
                // printPrice(); // Lägg till dessa om random priser

            }
            if (input.equals("2")) {
                highestLowest();
                avgPrice();
            }
            if (input.equals("3")) {
                sortPrice(bubbleSort());
            }
            if (input.equals("4")) {
                compareTime4h();

            }
        }
    }

    /*
            public static void pricesRandom() {
                prices = new TimePrice[24];
                Random rd = new Random();
                for (int i = 0; i < prices.length; i++) {
                    prices[i] = new TimePrice(i, rd.nextInt(50, 500));


                }
            }

     */
    public static void userPickPrice() {
        System.out.println(" Mata in dina priser: ");
        prices = new TimePrice[24];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Mellan kl: " + printHour((i)) + " kostar det: ");
            prices[i] = new TimePrice(i, sc.nextInt());
        }
    }
/*
        public static void printPrice() {
            for (int i = 0; i < 24; i++) {
                System.out.println("Mellan kl: " + printHour(prices[i].getTime()) + " kostar det: " + prices[i].getPrice() + " öre");
            }
        }
*/

    public static void highestLowest() {
        TimePrice highest = new TimePrice(0, 0);
        TimePrice smallest = new TimePrice(0, 1000);
        for (int i = 0; i <= 23; i++) {
            if (prices[i].getPrice() < smallest.getPrice()) {
                smallest.setTime(prices[i].getTime());
                smallest.setPrice(prices[i].getPrice());
            }
            if (prices[i].getPrice() > highest.getPrice()) {
                highest.setTime(prices[i].getTime());
                highest.setPrice(prices[i].getPrice());
            }
        }
        System.out.println("Högsta priset är kl: " + highest.getTime() + "-" + (highest.getTime() + 1) + " då kostar det " + highest.getPrice() + " öre per kWh");
        System.out.println("Lägsta priset är kl: " + smallest.getTime() + "-" + (smallest.getTime() + 1) + " då kostar det " + smallest.getPrice() + " öre per kWh");
    }

    public static String printHour(int i) {
        int helpTimer = i + 1;
        if (i < 9)
            return "0" + i + "-0" + helpTimer;
        else if (i < 10)
            return "0" + i + "-" + helpTimer;
        else
            return i + "-" + helpTimer;

    }

    public static void avgPrice() {
        float total = 0f;
        for (int i = 0; i < prices.length; i++) {
            total = total + prices[i].getPrice();
        }
        float avg = total / prices.length;
        System.out.format("Medelpriset är: %.2f", avg);
        System.out.println(" öre per kWh");

    }

    public static void sortPrice(TimePrice[] sorted) {
        for (int i = 0; i < sorted.length; i++) {

            System.out.println("Mellan kl: " + printHour(sorted[i].getTime()) + " Kostar det: " + sorted[i].getPrice() + " öre per kWh");
        }
    }

    public static TimePrice[] bubbleSort() {
        TimePrice[] sortedArray = Arrays.copyOf(prices,24);
        boolean bubbleSort = true;
        while (bubbleSort) {
            bubbleSort = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                for (int j = i; j < sortedArray.length - 1; j++) {
                    if (sortedArray[i].getPrice() > sortedArray[j + 1].getPrice()) {

                        TimePrice temp = sortedArray[j + 1];
                        sortedArray[j + 1] = sortedArray[i];
                        sortedArray[i] = temp;
                        bubbleSort = true;
                    }
                }

            }
        }
        return sortedArray;
    }


    public static void compareTime4h() {

        int lowestPrice = Integer.MAX_VALUE;
        int time = 0;
        double sum = 0;

        for (int i = 0; i < prices.length - 3; i++) {
            if (lowestPrice > prices[i].getPrice() + prices[i + 1].getPrice() + prices[i + 2].getPrice() + prices[i + 3].getPrice()) {
                time = prices[i].getTime();
                lowestPrice = prices[i].getPrice() + prices[i + 1].getPrice() + prices[i + 2].getPrice() + prices[i + 3].getPrice();

                sum = lowestPrice;
            }

        }
        System.out.println("Den billigaste perioden att ladda, är mellan: " + " "  + prices[time].getTime() + "-" + (prices[time].getTime() + 4));
        System.out.println("Medelpriset är då:" + " " +sum/4 + " öre per kWh");
    }

}
