package pages;

import java.io.*;
import java.util.*;

public class AuthStorage {
    private final String filePath = "users.txt";

    public boolean registerUser(String username, String password) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + ":" + password);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] credentials = scanner.nextLine().split(":");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
}
