package com.Nick;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> storedCardNumbers = new ArrayList<String>();

    public static void main(String[] args) {

        menu();

//        int numberOfAccountsOpened = 15;
//        String[] typesOfAccount = {"checking","savings","credit","other"};
//        String[] locations = {"useast","uswest","alaska","hawaii","other"};

        //testing for multiple cards
        //Card number creation based on number of accounts opened
//        for(int i = 1; i<numberOfAccountsOpened;) {
//            //create card number
//            String cardNumber = getCardNumber(typesOfAccount[getRandom(0,3)],locations[getRandom(0,4)]);
//
//            //check if card number already exists in system(ArrayList<String> storedCardNumbers)
//            if(storedCardNumbers.contains(cardNumber)) {
//                break;
//            } else {
//                //if it does contain card number, i increments to insure no missed accounts.
//                i++;
//                System.out.println(cardNumber);
//                System.out.println(" ");
//                storedCardNumbers.add(cardNumber);
//            }
//        }
    }

    //create menu for checking list of cards or creating a new card
    public static void menu(){

        boolean exit = false;

        System.out.println("___________________\n" +
                "Welcome to our bank!\n" + "Choose an option below: \n" +
                "1. Create new bank card.\n" +
                "2. Check existing cards.\n" +
                "3. Exit.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice == 1) {
            createNewCard();
        } else if(choice == 2){
            System.out.println("-------------------------");
            System.out.println("There are " + storedCardNumbers.size() + " cards in the system");
            for (int i = 0; i < storedCardNumbers.size(); i++) {
                System.out.println(storedCardNumbers.get(i).toString());
            }
            System.out.println("-------------------------");
        } else if(choice == 3) {
            System.out.println("Thank you for coming in!");
            exit = true;
        } else {
                System.out.println("Incorrect choice.\n" +
                        "Please choose a valid option: ");
                menu();
            }

        if(exit == false) {
            System.out.println("Would you like to do anything else?");
            menu();
        } else {

        }
        }


    //create new bank card with user params
    public static String createNewCard() {
//        String accountType = setCardAccountType();
//        String location = setAccountLocation();
        String cardNumber = getCardNumber(setCardAccountType(),setAccountLocation());
        System.out.println("New card successfully created.\n" +
                "Your new bank card number is: \n" +
                cardNumber);
        storedCardNumbers.add(cardNumber);
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
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                location.equalsIgnoreCase("useast");
            } else if (choice == 2) {
                location.equalsIgnoreCase("uswest");
            } else if (choice == 3) {
                location.equalsIgnoreCase("alaska");
            } else if (choice == 4) {
                location.equalsIgnoreCase("hawaii");
            } else if(choice == 5) {
                location.equalsIgnoreCase("other");
            }else {
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
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                accountType.equalsIgnoreCase("checking");
            } else if (choice == 2) {
                accountType.equalsIgnoreCase("savings");
            } else if (choice == 3) {
                accountType.equalsIgnoreCase("credit");
            } else if (choice == 4) {
                accountType.equalsIgnoreCase("other");
            } else {
                System.out.println("Entered invalid selection.\n" +
                        "Please enter valid selection.");
                setCardAccountType();
            }

            return accountType;
        }

    //create random number for card
    public static int getRandom(int min, int max) {
        return (int) (Math.random()*(max - min)) + min;
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
        if(accountType.equalsIgnoreCase("checking")) {
            accountKeySet = 4400;
        } else if(accountType.equalsIgnoreCase("savings")) {
            accountKeySet = 4300;
        } else if(accountType.equalsIgnoreCase("credit")) {
            accountKeySet = 4200;
        } else {
            accountKeySet = 4000;
        }


        //Assign a value to each location of client
        if(location.equalsIgnoreCase("useast")) {
            locationKeySet = 1011;
        } else if(location.equalsIgnoreCase("uswest")) {
            locationKeySet = 2011;
        } else if (location.equalsIgnoreCase("Alaska")) {
            locationKeySet = 3011;
        } else if (location.equalsIgnoreCase("hawaii")) {
            locationKeySet = 4011;
        } else {
            locationKeySet = 1022;
        }

        //create a randomized number for last of card number
        lastEightFirst = getRandom(1000,9999);
        lastEightSecond = getRandom(1000,9999);

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
}