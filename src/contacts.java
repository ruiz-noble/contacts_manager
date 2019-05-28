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
                String name = line.split(" | ")[0];
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
        String newContact = newName + " | " + newNumber;
        String directory = "data";
        List<String> updatedList = new ArrayList<>();
        Path folder = Paths.get(directory);
        Path file = Paths.get(directory, "contacts.txt");
        try {
            Files.write(file, Arrays.asList(newContact), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        add();
        read();
        search();




    }
}
