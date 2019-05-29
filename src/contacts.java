import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class contacts {

    private static String directory = "data";
    private static String fileName = "contacts.txt";
    private static Path file = Paths.get(directory, fileName);
    private static Scanner sc = new Scanner(System.in);
    private static String leftAlignFormat = "   | | %-14s | %-14s | | %n";

    private static String topPhone = "      .-------------------------------.  \n" +
                                  "    .'              .'  `.             `.\n" +
                                  "   ;                :    :               ;\n" +
                                  "   | .---------------------------------. |\n" +
                                  "   | |                                 | |\n" +
                                  "   | |                                 | |\n" +
                                  "   | |                                 | |\n" +
                                  "   | |                                 | |\n" +
                                  "   | | Contact Name   |   Number       | |\n";
    private static String bottomPhone = "   | |                                 | |\n" +
                                     "   | |                                 | |\n" +
                                     "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +

                                     "   | |                                 | |\n" +
                                     "   | `---------------------------------' |\n" +
                                     "   |                                     |\n" +
                                     "    '.__________________________________.'\n";



    private static String welcome = "      .-------------------------------.  \n" +
            "    .'              .'  `.             `.\n" +
            "   ;                :    :               ;\n" +
            "   | .---------------------------------. |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |             Welcome!            | |\n" +
            "   | |                                 | |\n" +
            "   | |               to                | |\n" +
            "   | |                                 | |\n" +
            "   | |           your contacts         | |\n" +
            "   | |                                 | |\n" +
            "   | |               app               | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | `---------------------------------' |\n" +
            "   |                                     |\n" +
            "    '.__________________________________.'\n";
    private static String options = "      .-------------------------------.  \n" +
            "    .'              .'  `.             `.\n" +
            "   ;                :    :               ;\n" +
            "   | .---------------------------------. |\n" +
            "   | |                                 | |\n" +
            "   | |    1. View Contacts             | |\n" +
            "   | |    2. Add New Contact           | |\n" +
            "   | |    3. Search Contact            | |\n" +
            "   | |    4. Delete Contact            | |\n" +
            "   | |    5. Add Favorite              | |\n" +
            "   | |    6. View Favorites            | |\n" +
            "   | |    7. Exit                      | |\n" +
            "   | |                                 | |\n" +
            "   | |    Enter an option              | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | `---------------------------------' |\n" +
            "   |                                     |\n" +
            "    '.__________________________________.'\n";
    private static String goodBye = "      .-------------------------------.  \n" +
            "    .'              .'  `.             `.\n" +
            "   ;                :    :               ;\n" +
            "   | .---------------------------------. |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |             Goodbye!            | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | `---------------------------------' |\n" +
            "   |                                     |\n" +
            "    '.__________________________________.'\n";
    private static  String deleted = "      .-------------------------------.  \n" +
            "    .'              .'  `.             `.\n" +
            "   ;                :    :               ;\n" +
            "   | .---------------------------------. |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |         Contact Deleted         | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | |                                 | |\n" +
            "   | `---------------------------------' |\n" +
            "   |                                     |\n" +
            "    '.__________________________________.'\n";

    public static void main(String[] args) throws Exception{
        printWithDelays(welcome, TimeUnit.MILLISECONDS, 2);
        
        runApp();

    }
    private static void printWithDelays(String data, TimeUnit unit, long delay)
            throws InterruptedException {
        for (char ch:data.toCharArray()) {
            System.out.print(ch);
            unit.sleep(delay);
        }
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

    private static void runApp() throws InterruptedException {
        printWithDelays(options, TimeUnit.MILLISECONDS, 2);
        System.out.println("Input here: ");
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
        } else if (userOption == 5) {
            addFavorite();
            runApp();
        } else if (userOption == 6){
            showFavorites();
            runApp();
        }else if(userOption == 7){
            printWithDelays(goodBye, TimeUnit.MILLISECONDS, 2);
        }  else{
            System.out.println("Error: Invalid input.");
            runApp();
        }
    }

    private static void read(){
        try {
            List<String> contacts = Files.readAllLines(file);
            printWithDelays(topPhone, TimeUnit.MILLISECONDS, 2);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                System.out.format(leftAlignFormat, name, number);
            }
            printWithDelays(bottomPhone, TimeUnit.MILLISECONDS, 2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void search(){
        System.out.println("What contact would you like displayed?");
        String searchedName = sc.nextLine();
        boolean foundContact = false;

        try {
            printWithDelays(topPhone, TimeUnit.MILLISECONDS, 2);
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts){
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                if(name.trim().toLowerCase().contains(searchedName.toLowerCase())){
                    System.out.format(leftAlignFormat, name, number);
                    foundContact = true;
                } else if (number.trim().toLowerCase().contains(searchedName.toLowerCase())){
                    System.out.format(leftAlignFormat, name, number);
                    foundContact = true;
                }
            }
            printWithDelays(bottomPhone, TimeUnit.MILLISECONDS, 2);

            if (!foundContact){
                String name = "no match";
                String number = "no match";
                System.out.format(leftAlignFormat,"| " + name, number);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void add() throws InterruptedException {
        System.out.println("Please enter the name that you would like add.");
        String newName = sc.nextLine();
        if(newName.length() > 13){
            System.out.println("Name too large consider abbreviation");
            runApp();
        }
        System.out.println("Please enter their phone number number");
        String newNumber = sc.nextLine();
        String formattedNumber = format(newNumber);
        if(formattedNumber.length() > 14){
            System.out.println("Invalid phone number");
            runApp();
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
                if((name.trim().equalsIgnoreCase(contactToDelete)) || (name.trim().equalsIgnoreCase(contactToDelete +
                        " *"))){
                    printWithDelays(deleted, TimeUnit.MILLISECONDS, 2);
                    continue;
                }
                updatedList.add(line);
            }
            Files.write(file, updatedList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void addFavorite(){
        System.out.println("Who who would you like to add to your favorites?");
        String contactFavorite = sc.nextLine();
        List<String> updatedList = new ArrayList<>();

        try {
            List<String> contacts = Files.readAllLines(file);
            for (String line: contacts) {
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                if(name.trim().equalsIgnoreCase(contactFavorite)){
                    updatedList.add(name + " *|" + number);
                    continue;
                }
                updatedList.add(line);
            }
            Files.write(file, updatedList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showFavorites(){

        try {
            printWithDelays(topPhone, TimeUnit.MILLISECONDS, 2);
            List<String> contacts = Files.readAllLines(file);
            for(String line : contacts) {
                String name = line.split("\\|")[0];
                String number = line.split("\\|")[1];
                if (name.trim().toLowerCase().contains("*")) {
                    System.out.format(leftAlignFormat, name, number);
                }
            }
            printWithDelays(bottomPhone, TimeUnit.MILLISECONDS, 2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
