package Java_Project1;

import java.util.Scanner;
import java.awt.Toolkit;

public class PomodoroManager {

    public static void startPomodoro(UserProfile user, Scanner scanner) {
        if (user.getTasks().isEmpty()) {
            System.out.println("📭 No tasks available. Add tasks first.");
            return;
        }

        // Show tasks
        for (int i = 0; i < user.getTasks().size(); i++) {
            Task task = user.getTasks().get(i);
            System.out.println((i + 1) + ". Task: " + task.getName()
                    + " | Completed: " + task.getCompletedPomodoros()
                    + "/" + task.getEstimatedPomodoros()
                    + " | Time: " + task.getTotalTimeSpent() + " mins");
        }

        // Choose task
        System.out.print("Choose task number: ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input.");
            return;
        }

        if (choice < 1 || choice > user.getTasks().size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Task selectedTask = user.getTasks().get(choice - 1);

        // Enter duration
        System.out.print("Enter duration (e.g., 25m, 1500s, 1h): ");
        String timeInput = scanner.nextLine().trim();
        int duration = parseDuration(timeInput); // duration in seconds

        System.out.println("🔔 Pomodoro started for task: " + selectedTask.getName());

        // Countdown
        for (int i = duration; i > 0; i--) {
            System.out.print("\r⏳ Time left: " + i + " sec   ");
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (InterruptedException ignored) {}
        }
        System.out.println(); // move to new line after countdown

        // Finish task
        selectedTask.incrementPomodoros();
        selectedTask.addTime(duration / 60); // store time in minutes
        user.addXP(20);
        Toolkit.getDefaultToolkit().beep();
        System.out.println("✅ Pomodoro completed!");
    }

    public static int parseDuration(String input) {
        try {
            input = input.toLowerCase().trim();
            if (input.endsWith("h")) {
                return Integer.parseInt(input.replace("h", "")) * 3600;
            } else if (input.endsWith("m")) {
                return Integer.parseInt(input.replace("m", "")) * 60;
            } else if (input.endsWith("s")) {
                return Integer.parseInt(input.replace("s", ""));
            } else {
                return Integer.parseInt(input); // assume seconds
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid time format. Defaulting to 25 minutes.");
            return 25 * 60;
        }

    }
}
