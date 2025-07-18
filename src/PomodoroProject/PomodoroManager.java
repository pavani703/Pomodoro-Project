package PomodoroProject;

public class PomodoroManager {
    private static final int XP_PER_POMODORO = 20;

    public void startPomodoro(Task task, UserProfile user, int durationMinutes) {
        int secondsRemaining = durationMinutes * 60;
        System.out.println("🔔 Starting " + durationMinutes + "-minute Pomodoro for task: " + task.getName());
        System.out.println("⏳ Stay focused...");

        try {
            while (secondsRemaining > 0) {
                int minutes = secondsRemaining / 60;
                int seconds = secondsRemaining % 60;
                System.out.printf("⏱️  %02d:%02d remaining\r", minutes, seconds);
                Thread.sleep(1000);
                secondsRemaining--;
            }
            System.out.println("\n✅ Pomodoro completed!");
            try {
                for (int i = 0; i < 3; i++) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    Thread.sleep(1000); // Half-second pause between beeps.
                }
            }
            catch (InterruptedException e) {
                System.out.println("Beep sequence interrupted.");
            }

// ASCII confetti celebration
            System.out.println("\n🎉🎉🎉 Congratulations! 🎉🎉🎉");
            task.markPomodoroComplete(durationMinutes * 60 * 1000L);
            user.addXP(XP_PER_POMODORO);
        } catch (InterruptedException e) {
            System.out.println("❌ Timer interrupted.");
        }
    }
}
