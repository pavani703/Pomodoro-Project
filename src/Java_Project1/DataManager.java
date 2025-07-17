package Java_Project1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static final String FILE_NAME = "user_data.txt";

    public static void save(UserProfile user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(user.toFileString());
            System.out.println("💾 Data saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Failed to save data: " + e.getMessage());
        }
    }

    public static UserProfile load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return UserProfile.fromFileLines(lines);
        } catch (IOException e) {
            System.out.println("❌ Failed to load data: " + e.getMessage());
            return null;
        }
    }
}
