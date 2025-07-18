package PomodoroProject;

import java.io.*;

public class DataManager {
    private static final String SAVE_DIR = System.getProperty("user.home") + File.separator + "PomodoroData";

    public static void saveUser(UserProfile user) {
        try {
            File dir = new File(SAVE_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, user.getUserName() + ".ser");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(user);
                System.out.println("💾 Saved to: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("❌ Save failed: " + e.getMessage());
        }
    }

    public static UserProfile loadUser(String username) {
        File file = new File(SAVE_DIR, username + ".ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                System.out.println("📂 Loaded from: " + file.getAbsolutePath());
                return (UserProfile) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("❌ Load failed: " + e.getMessage());
            }
        }
        System.out.println("📄 Creating new profile for: " + username);
        return new UserProfile(username);
    }
}
