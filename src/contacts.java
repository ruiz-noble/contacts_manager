import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        read();



    }
}
