package Java_Project1;

public class Task {
    private String name;
    private int estimatedPomodoros;
    private int completedPomodoros;
    private int totalTimeSpent; // in minutes

    public Task(String name, int estimatedPomodoros) {
        this.name = name;
        this.estimatedPomodoros = estimatedPomodoros;
        this.completedPomodoros = 0;
        this.totalTimeSpent = 0;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedPomodoros() {
        return estimatedPomodoros;
    }

    public int getCompletedPomodoros() {
        return completedPomodoros;
    }

    public int getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void incrementPomodoros() {
        completedPomodoros++;
    }

    public void addTime(int minutes) {
        totalTimeSpent += minutes;
    }

    public String toFileString() {
        return name + "|" + estimatedPomodoros + "|" + completedPomodoros + "|" + totalTimeSpent;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split("\\|");
        Task task = new Task(parts[0], Integer.parseInt(parts[1]));
        task.completedPomodoros = Integer.parseInt(parts[2]);
        task.totalTimeSpent = Integer.parseInt(parts[3]);
        return task;
    }

    @Override
    public String toString() {
        return name + " (Done: " + completedPomodoros + "/" + estimatedPomodoros + ", Time: " + totalTimeSpent + " min)";
    }
}
