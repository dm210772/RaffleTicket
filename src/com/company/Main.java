package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Main {

    static String name = "";
    static HashMap<String, Integer> tickets = new HashMap<>();
    static int raffleNumber;
    static boolean flag = false;

    public static void purchase() {
        System.out.println("What is your name?");
        name = getStringInput();
        raffleNumber = generateRandomNumber();
        tickets.put(name, raffleNumber);
        System.out.println("Your raffle number is " + raffleNumber);
    }

    public static void check() {
        System.out.println("What is your name?");
        name = getStringInput();
        try {
            raffleNumber = tickets.get(name);
        } catch (Exception ex) {
            System.out.println("You don't have a ticket: " + ex);
            return;
        }
        
        for (int i = 2; i <= raffleNumber / 2; ++i) {
            if (raffleNumber % i == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose! Too bad.");
        }
    }

    public static String getStringInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return input;
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    public static void main(String[] args) {
        // write your code here
        while (!flag) {
            System.out.println("Would you like to buy or check a ticket?");
            String choice = getStringInput();

            switch (choice.toLowerCase(Locale.ROOT)) {
                case "buy":
                    purchase();
                    break;
                case "check":
                    check();
                    break;
                default:
            }
            System.out.println("Would you like to continue/play again?");
            if (getStringInput().equalsIgnoreCase("no")) {
                flag = true;
                break;
            }
        }
    }
}
