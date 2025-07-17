package Java_Project1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserProfile user = DataManager.load();

        if (user == null) {
            System.out.print("👤 Enter your username: ");
            String username = scanner.nextLine();
            user = new UserProfile(username);
            System.out.println("📄 New profile created for " + username + "!");
        }

        System.out.println("\n👋 Welcome to FocusQuest, " + user.getUsername() + "!");
        System.out.println("🚶‍➡️ Small steps every day lead to big results. Stay focused! 💰");

        boolean running = true;

        while (running) {
            System.out.println("\n📋 Menu:");
            System.out.println("1️⃣ View Profile");
            System.out.println("2️⃣ View Tasks");
            System.out.println("3️⃣ Add Task");
            System.out.println("4️⃣ Start Pomodoro");
            System.out.println("5️⃣ Save & Exit");
            System.out.print("👉 Choice: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        System.out.println("\n👤 Your Profile:");
                        System.out.println(user);
                        break;
                    case 2:
                        System.out.println("\n📄 Your Tasks:");
                        user.viewTasks();
                        break;
                    case 3:
                        System.out.print("📝 Task name: ");
                        String name = scanner.nextLine();
                        System.out.print("🔢 Estimated Pomodoros: ");
                        int est = Integer.parseInt(scanner.nextLine());
                        user.addTask(new Task(name, est));
                        break;
                    case 4:
                        PomodoroManager.startPomodoro(user, scanner);
                        break;
                    case 5:
                        DataManager.save(user);
                        System.out.println("💾 Progress saved. 👋 Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("❌ Invalid option. Please choose from 1️⃣ to 5️⃣.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
