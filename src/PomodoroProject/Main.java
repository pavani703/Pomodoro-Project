package PomodoroProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for username
        System.out.print("👤 Enter your username (use the same to continue your progress): ");
        String username = scanner.nextLine().trim().toLowerCase();

        // Load or create profile
        UserProfile user = DataManager.loadUser(username);

        // Greet user
        System.out.println("😊 Welcome to FocusQuest " + username + ", your personal productivity companion! 😊");
        System.out.println("🚶‍➡️ Small steps every day lead to big results. Stay focused! 💵💰");

        PomodoroManager pm = new PomodoroManager();

        // Menu loop
        while (true) {
            System.out.println("\n📋 Menu:");
            System.out.println("1️⃣  View Tasks");
            System.out.println("2️⃣  Add Task");
            System.out.println("3️⃣  Remove Task");
            System.out.println("4️⃣  Start Pomodoro");
            System.out.println("5️⃣  View Profile");
            System.out.println("6️⃣  Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    user.viewTasks();
                } else if (choice.equals("2")) {
                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();
                    user.addTask(new Task(taskName));
                } else if (choice.equals("3")) {
                    user.viewTasks();
                    System.out.print("Enter task number to remove: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    user.removeTask(idx);
                } else if (choice.equals("4")) {
                    user.viewTasks();
                    System.out.print("Enter task number to start Pomodoro: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idx < 0 || idx >= user.getTasks().size()) {
                        System.out.println("❌ Invalid task number.");
                        continue;
                    }

                    System.out.print("⏰ Enter Pomodoro duration in minutes: ");
                    String input = scanner.nextLine();
                    if (!input.matches("\\d+")) {
                        System.out.println("⚠️ Invalid number.");
                        continue;
                    }
                    int durationMinutes = Integer.parseInt(input);
                    if (durationMinutes <= 0 || durationMinutes > 180) {
                        System.out.println("⚠️ Please enter a duration between 1 and 180.");
                        continue;
                    }

                    pm.startPomodoro(user.getTasks().get(idx), user, durationMinutes);
                } else if (choice.equals("5")) {
                    user.viewProfile();
                } else if (choice.equals("6")) {
                    DataManager.saveUser(user);
                    System.out.println("💾 Progress saved. Goodbye!");
                    break;
                } else {
                    System.out.println("❌ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
