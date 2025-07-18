package PomodoroProject;

import java.io.*;

public class DataManager {
    private static final String FILE_NAME = "userdata.ser";

    public void saveUser(UserProfile user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("❌ Failed to save user: " + e.getMessage());
        }
    }

    public UserProfile loadUser() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (UserProfile) ois.readObject();
        } catch (Exception e) {
            return null; // No saved user yet
        }
    }
}
