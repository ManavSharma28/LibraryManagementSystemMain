package File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    
    public void MenuRead(String fileName)
    {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
    }
}

