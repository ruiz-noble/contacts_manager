import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class contacts {

    private static String directory = "data";
    private static String fileName = "contacts.txt";
    private static Path file = Paths.get(directory, fileName);
    private static Scanner sc = new Scanner(System.in);
    private static String leftAlignFormat = "%-14s | %-14s |%n";


    public static void main(String[] args) {
        System.out.println("\nWelcome to your contacts app \nHere are your options");
        runApp();

    }

    private static String format(String number){
        if(number.length() == 7) {
            String first = number.substring(0, 3);
            String second = number.substring(3, 7);
            return first + "-" + second;
        } else if (number.length() == 10){
            String first = number.substring(0, 3);
            String second = number.substring(3, 6);
            String third = number.substring(6, 10);
            return first + "-" + second + "-" + third;
        }  else if (number.length() > 10){
            return number;
        }  else {
            return "Invalid entry";
        }
    }

    private static void runApp() {
        System.out.println("\n1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name or number.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n");

        int userOption = Integer.valueOf(sc.nextLine());
        if(userOption == 1){
             read();
             runApp();
        } else if (userOption == 2){
             add();
             runApp();
        } else if (userOption == 3){
              search();
              runApp();
        } else if (userOption == 4){
              delete();
              runApp();
        } else if (userOption == 5){
            System.out.println("Goodbye!");
        }  else{
            System.out.println("Error: Invalid input.");
            runApp();
        }
    }

    private static void read(){
        System.out.format("+--------------|----------------+%n");
        System.out.format("| Contact Name | Number         |%n");
        System.out.format("+--------------|----------------+%n");
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                System.out.format(leftAlignFormat,"| " + name, number);
            }
            System.out.format("+--------------|----------------+%n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void search(){
        System.out.println("What contact would you like displayed?");
        String searchedName = sc.nextLine();
        boolean foundContact = false;

        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                if(name.trim().toLowerCase().contains(searchedName.toLowerCase())){
                    System.out.format(leftAlignFormat,"| " + name, number);
                    foundContact = true;
                } else if (number.trim().toLowerCase().contains(searchedName.toLowerCase())){
                    System.out.format(leftAlignFormat,"| " + name, number);
                    foundContact = true;
                }
            }
            if (!foundContact){
                System.out.println("Your input did not match any contact.");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void add(){
        System.out.println("Please enter the name that you would like add.");
        String newName = sc.nextLine();
        if(newName.length() > 13){
            System.out.println("Name too large consider abbreviation");
            add();
        }
        System.out.println("Please enter their phone number number");
        String newNumber = sc.nextLine();
        String formattedNumber = format(newNumber);
        if(formattedNumber.length() > 14){
            System.out.println("Invalid phone number");
            add();
        }
        String newContact = newName + "|" + formattedNumber;
        List<String> updatedList = new ArrayList<>();
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                if(name.trim().equalsIgnoreCase(newName)){
                    System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)\n",newName);
                    if (sc.nextLine().equals("yes")){
                        updatedList.add(newContact);
                        continue;
                    } else{
                        add();
                    }
                } else if (number.trim().equalsIgnoreCase(formattedNumber.trim())){
                    System.out.printf("There's already a contact with the number %s. Do you want to overwrite it? " +
                            "(Yes/No)\n",formattedNumber);
                    if (sc.nextLine().equals("yes")){
                        updatedList.add(newContact);
                        continue;
                    } else{
                        add();
                    }
                }
                updatedList.add(line);
            }
            Files.write(file, updatedList);
            if (updatedList.contains(newContact)){
                Files.write(file, updatedList);
            } else {
                Files.write(file, Arrays.asList(newContact), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void delete(){
        System.out.println("What contact would you like to delete?");
        String contactToDelete = sc.nextLine();
        List<String> updatedList = new ArrayList<>();
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                if(name.trim().equalsIgnoreCase(contactToDelete)){
                    continue;
                }
                updatedList.add(line);
            }
            Files.write(file, updatedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
