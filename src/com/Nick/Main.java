package com.Nick;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

//    private static ArrayList<String> storedCardNumbers = new ArrayList<String>();
    private static File myFile = new File("bankCardNumbers.txt");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        menu();

    }

    //create menu for checking list of cards or creating a new card
    public static void menu() {
        //used to check if client wants to exit program
        boolean exit = false;

        System.out.println("___________________\n" +
                "Welcome to our bank!\n" + "Choose an option below: \n" +
                "1. Create new bank card.\n" +
                "2. Check existing cards.\n" +
                "3. Exit.");

        int choice = scanner.nextInt();

        if (choice == 1) {
            createNewCard();
        } else if (choice == 2) {
            System.out.println("-------------------------");
            printFile();
            System.out.println("-------------------------");
        } else if (choice == 3) {
            System.out.println("Thank you for coming in!");
//            writeFile();
            exit = true;
        } else {
            System.out.println("Incorrect choice.\n" +
                    "Please choose a valid option: ");
            menu();
        }

        if (!exit) {
            System.out.println("Would you like to do anything else?");
            menu();
        }
   }

    //allows the file to print for user
    public static void printFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            double counter = 0;
            String entry;


            System.out.println(countEntriesInFile());
            while ((entry = reader.readLine()) != null) {
                System.out.println(entry);
            }
        } catch (Exception e) {
            System.out.println("Could not retrieve File ");
        }
    }

    //counts the card on the file to notify user of how many entries there are.
    public static String countEntriesInFile() {

        int counter = 0;
        String entry;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));

            while ((entry = reader.readLine()) != null) {
                counter++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "There are " + counter + " cards in the system:";

    }


    //create new bank card with user params
    public static String createNewCard() {
//        String accountType = setCardAccountType();
//        String location = setAccountLocation();
        String cardNumber = getCardNumber(setCardAccountType(), setAccountLocation());
        System.out.println("New card successfully created.\n" +
                "Your new bank card number is: \n" +
                cardNumber);
        writeFile(cardNumber);
        return cardNumber;
    }

    public static String setAccountLocation() {

        String location = "other";

        System.out.println("What is your location?\n" +
                "1. US East\n" +
                "2. US West\n" +
                "3. Hawaii\n" +
                "4. Alaska\n" +
                "5. Other.");

        int choice = scanner.nextInt();

        if (choice == 1) {
            location = "useast";
        } else if (choice == 2) {
            location ="uswest";
        } else if (choice == 3) {
            location ="alaska";
        } else if (choice == 4) {
            location ="hawaii";
        } else if (choice == 5) {
            location ="other";
        } else {
            System.out.println("Entered invalid selection.\n" +
                    "Please enter valid selection.");
            setCardAccountType();
        }

        return location;
    }

    //set the card account type for the creatNewCard method to use
    public static String setCardAccountType() {

        String accountType = "other";

        System.out.println("Which type of account do you have?\n" +
                "1. Checking.\n" +
                "2. Savings.\n" +
                "3. Credit.\n" +
                "4. Other.\n" +
                "Enter corresponding digit: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            accountType = "checking";
        } else if (choice == 2) {
            accountType = "savings";
        } else if (choice == 3) {
            accountType = "credit";
        } else if (choice == 4) {
            accountType = "other";
        } else {
            System.out.println("Entered invalid selection.\n" +
                    "Please enter valid selection.");
            setCardAccountType();
        }

        return accountType;
    }

    //create random number for card
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static String getCardNumber(String accountType, String location) {
        //first set of four
        int accountKeySet;
        //second set of four
        int locationKeySet;
        //third set of four
        int lastEightFirst;
        //fourth set of four
        int lastEightSecond;

        //assign a value to each account type
        if (accountType == "checking") {
            accountKeySet = 4400;
        } else if (accountType == "savings") {
            accountKeySet = 4300;
        } else if (accountType == "credit") {
            accountKeySet = 4200;
        } else {
            accountKeySet = 4000;
        }


        //Assign a value to each location of client
        if (location == "useast") {
            locationKeySet = 1011;
        } else if (location == "uswest") {
            locationKeySet = 2011;
        } else if (location == "Alaska") {
            locationKeySet = 3011;
        } else if (location == "hawaii") {
            locationKeySet = 4011;
        } else {
            locationKeySet = 1022;
        }

        //create a randomized number for last of card number
        lastEightFirst = getRandom(1000, 9999);
        lastEightSecond = getRandom(1000, 9999);

        //construct card number
        StringBuilder key = new StringBuilder();
        key.append(accountKeySet);
        key.append(" ");
        key.append(locationKeySet);
        key.append(" ");
        key.append(lastEightFirst);
        key.append(" ");
        key.append(lastEightSecond);
        return key.toString();
    }

    public void createFile() {
        try {

            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error when creating file!");
            e.getMessage();
        }
    }

    public static void writeFile(String cardNumber) {
        try {

            FileWriter myWriter = new FileWriter(myFile.getName(), true);
//            for(String str: storedCardNumbers) {
//                myWriter.write(str + System.lineSeparator());
//            }
            String formattedCardNumber = cardNumber + System.lineSeparator();
            myWriter.write(formattedCardNumber);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}