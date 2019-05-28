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

    public static void main(String[] args) {
        System.out.println("\nWelcome to your contacts app \nHere are your options \n");
        runApp();

    }







    public static String format(String number){
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

    public static void runApp() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n");

        Scanner sc = new Scanner(System.in);
        int userOption = sc.nextInt();
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
            System.out.println("Thank you");
        }  else{
            System.out.println("Error: Invalid input.");
            runApp();
        }
    }

    public static void read(){
        String directory = "data";

        Path folder = Paths.get(directory);
        Path file = Paths.get(directory, "contacts.txt");
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void search(){
        Scanner sc = new Scanner(System.in);

        System.out.println("What name would you like displayed?");
        String searchedName = sc.nextLine();
        String directory = "data";

        Path file = Paths.get(directory, "contacts.txt");
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                if(name.trim().equalsIgnoreCase(searchedName)){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void add(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name that you would like add.");
        String newName = sc.nextLine();
        System.out.println("Please enter their phone number number");
        String newNumber = sc.nextLine();
        String formattedNumber = format(newNumber);
        String newContact = newName + " | " + formattedNumber;
        String directory = "data";
        List<String> updatedList = new ArrayList<>();
        Path folder = Paths.get(directory);
        Path file = Paths.get(directory, "contacts.txt");
        try {
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                if(name.trim().equalsIgnoreCase(newName)){
                    System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)\n",newName);
                    if (sc.nextLine().equals("yes")){
                        updatedList.add(newContact);
                        continue;
                    } else{
                        add();
                    }

                } else {
                    Files.write(file, Arrays.asList(newContact), StandardOpenOption.APPEND);
                }
                updatedList.add(line);
            }
            Files.write(file, updatedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            Files.write(file, Arrays.asList(newContact), StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void delete(){
        Scanner sc = new Scanner(System.in);
        String directory = "data";
        Path folder = Paths.get(directory);
        Path file = Paths.get(directory, "contacts.txt");
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
