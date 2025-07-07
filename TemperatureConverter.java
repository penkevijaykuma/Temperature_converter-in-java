import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {

    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;
    private JButton celsiusToFahrenheitButton;
    private JButton fahrenheitToCelsiusButton;

    public TemperatureConverter() {
        // Set up the main frame
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // Rows, Cols, HGap, VGap
        frame.setLocationRelativeTo(null); // Center the window

        // Create components
        JLabel instructionLabel = new JLabel("Enter temperature and choose conversion:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);

        celsiusToFahrenheitButton = new JButton("Celsius to Fahrenheit");
        fahrenheitToCelsiusButton = new JButton("Fahrenheit to Celsius");

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setForeground(Color.BLUE);

        // Add action listeners to buttons
        celsiusToFahrenheitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature(true); // True for C to F
            }
        });

        fahrenheitToCelsiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature(false); // False for F to C
            }
        });

        // Add components to the frame
        frame.add(instructionLabel);
        frame.add(inputField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(celsiusToFahrenheitButton);
        buttonPanel.add(fahrenheitToCelsiusButton);
        frame.add(buttonPanel);

        frame.add(resultLabel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private void convertTemperature(boolean isCelsiusToFahrenheit) {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            double convertedValue;
            String unit;

            if (isCelsiusToFahrenheit) {
                convertedValue = celsiusToFahrenheit(inputValue);
                unit = "Fahrenheit";
            } else {
                convertedValue = fahrenheitToCelsius(inputValue);
                unit = "Celsius";
            }
            resultLabel.setText(String.format("Result: %.2f %s", convertedValue, unit));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            resultLabel.setText("Result: Invalid input");
        }
    }

    // Celsius to Fahrenheit conversion
    private static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Fahrenheit to Celsius conversion
    private static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}