package Java_Project1;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String username;
    private int xp;
    private int level;
    private List<Task> tasks;

    public UserProfile(String username) {
        this.username = username;
        this.xp = 0;
        this.level = 1;
        this.tasks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("✅ Task added: " + task.getName());
    }

    public void addXP(int amount) {
        xp += amount;
        while (xp >= level * 100) {
            xp -= level * 100;
            level++;
            System.out.println("🏆 Level up! New level: " + level);
        }
    }

    public int getXP() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append("\n").append(xp).append("\n").append(level).append("\n");
        for (Task task : tasks) {
            sb.append(task.toFileString()).append("\n");
        }
        return sb.toString();
    }

    public static UserProfile fromFileLines(List<String> lines) {
        String username = lines.get(0);
        int xp = Integer.parseInt(lines.get(1));
        int level = Integer.parseInt(lines.get(2));
        UserProfile user = new UserProfile(username);
        user.xp = xp;
        user.level = level;

        for (int i = 3; i < lines.size(); i++) {
            if (!lines.get(i).isBlank()) {
                user.tasks.add(Task.fromFileString(lines.get(i)));
            }
        }
        return user;
    }

    @Override
    public String toString() {
        return "👤 User: " + username + " | 🧠 Level: " + level + " | 💡 XP: " + xp;
    }
}
