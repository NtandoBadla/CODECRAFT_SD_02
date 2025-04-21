import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    private int secretNumber;
    private int attempts;
    private int min = 1;
    private int max = 100;

    // GUI components
    private JFrame frame;
    private JTextField guessField, minField, maxField;
    private JLabel resultLabel, promptLabel;
    private JButton guessButton, playAgainButton;

    public Main() {
        frame = new JFrame("🎮 Custom Number Guessing Game");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel rangeLabel = new JLabel("Enter min and max:");
        rangeLabel.setBounds(30, 10, 150, 25);
        frame.add(rangeLabel);

        minField = new JTextField("1");
        minField.setBounds(160, 10, 50, 25);
        frame.add(minField);

        maxField = new JTextField("100");
        maxField.setBounds(220, 10, 50, 25);
        frame.add(maxField);

        JButton setRangeButton = new JButton("Set Range");
        setRangeButton.setBounds(290, 10, 120, 25);
        frame.add(setRangeButton);

        promptLabel = new JLabel("Guess the number:");
        promptLabel.setBounds(30, 50, 200, 30);
        frame.add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(160, 50, 100, 30);
        frame.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(270, 50, 80, 30);
        frame.add(guessButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(30, 90, 350, 30);
        frame.add(resultLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(160, 130, 120, 30);
        playAgainButton.setVisible(false);
        frame.add(playAgainButton);

        setRangeButton.addActionListener(e -> {
            try {
                min = Integer.parseInt(minField.getText());
                max = Integer.parseInt(maxField.getText());
                if (min >= max) {
                    JOptionPane.showMessageDialog(frame, "Min must be less than Max!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                generateNewNumber();
                attempts = 0;
                resultLabel.setText("New number set! Try guessing.");
                guessButton.setEnabled(true);
                playAgainButton.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid range numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        guessButton.addActionListener(e -> {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < secretNumber) {
                    resultLabel.setText("Too low! Try again.");
                } else if (guess > secretNumber) {
                    resultLabel.setText("Too high! Try again.");
                } else {
                    resultLabel.setText("🎉 Correct! Attempts: " + attempts);
                    JOptionPane.showMessageDialog(frame, "🎉 You guessed it in " + attempts + " attempts!", "Well Done!", JOptionPane.INFORMATION_MESSAGE);
                    guessButton.setEnabled(false);
                    playAgainButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        });

        playAgainButton.addActionListener(e -> {
            guessField.setText("");
            attempts = 0;
            generateNewNumber();
            resultLabel.setText("New game started! Guess again.");
            guessButton.setEnabled(true);
            playAgainButton.setVisible(false);
        });

        // Initial setup
        generateNewNumber();
        frame.setVisible(true);
    }

    private void generateNewNumber() {
        Random rand = new Random();
        secretNumber = rand.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        new Main();
    }
}
