package photon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class GameCountDown {
    private static final int TOTAL_COUNTDOWN_SECONDS = 30;
    private int remainingTime = TOTAL_COUNTDOWN_SECONDS;
    @SuppressWarnings("unused")
    private JWindow countdownWindow; // JWindow for displaying the countdown
    private JLabel countdownLabel; // JLabel to display the countdown
    private CountDownListener listener;

    public GameCountDown(CountDownListener listener) {
        this.listener = listener;

        // Create and configure the countdown window (similar to the splash screen)
        countdownWindow = new JWindow();
        countdownLabel = new JLabel("Game starts in: 30 seconds", SwingConstants.CENTER);
        countdownLabel.setForeground(Color.WHITE); // Set text color to white
        countdownLabel.setBackground(Color.BLACK); // Set background color to black
        countdownLabel.setOpaque(true);
        countdownLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Add a border

        countdownWindow.getContentPane().add(countdownLabel, BorderLayout.CENTER);
        countdownWindow.setSize(400, 100); // Set the window size

        // Center the countdown window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - countdownWindow.getSize().width) / 2;
        int y = (screenSize.height - countdownWindow.getSize().height) / 2;
        countdownWindow.setLocation(x, y);
    }

    public void showCountdown() {
        countdownWindow.setVisible(true); // Show the countdown window
        startCountdown(); // Start the countdown
    }

    public void closeCountdown() {
        countdownWindow.setVisible(false); // Hide the countdown window
        countdownWindow.dispose(); // Dispose of the countdown window
    }

    private void startCountdown() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                countdownLabel.setText("Game starts in: " + remainingTime + " seconds");
                countdownWindow.repaint(); // Repaint to update the label

                if (remainingTime <= 0) {
                    timer.cancel();
                    closeCountdown();
                    listener.onCountdownFinished();
                }
            }
        };

        // Schedule the countdown task to run every second
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}

