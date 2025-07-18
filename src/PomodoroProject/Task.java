package PomodoroProject;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private int completedPomodoros;
    private long totalTimeSpent;

    public Task(String name) {
        this.name = name;
        this.completedPomodoros = 0;
        this.totalTimeSpent = 0;
    }

    public void markPomodoroComplete(long millis) {
        completedPomodoros++;
        totalTimeSpent += millis;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " | Pomodoros: " + completedPomodoros + " | Time: " + totalTimeSpent / 60000 + " mins";
    }
}
