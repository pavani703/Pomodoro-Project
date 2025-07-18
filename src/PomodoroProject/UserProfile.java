package PomodoroProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {
    private String name;
    private int xp;
    private List<Task> tasks;

    public UserProfile(String name) {
        this.name = name;
        this.xp = 0;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("✅ Task added!");
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("🗑️ Task removed.");
        } else {
            System.out.println("❌ Invalid index.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks found.");
        } else {
            System.out.println("📝 Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void viewProfile() {
        System.out.println("👤 Name: " + name);
        System.out.println("⭐ XP: " + xp);
        System.out.println("🎯 Level: " + (xp / 100));
    }

    public void addXP(int amount) {
        xp += amount;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
