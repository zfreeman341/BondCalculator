package com.zfreeman341.bondCalculator;

import com.zfreeman341.bondCalculator.utils.Stopwatch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Duration;

public class BondCalculatorGui {
    private final BondCalculator bondCalculator;
    private final Stopwatch stopwatch;
    private JFrame frame;
    private JTextField couponField, yearsField, faceField, rateOrPriceField, resultField;
    private CalculatorMode mode = CalculatorMode.PRICE;
    private JLabel timeLabel;

    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 14);

    enum CalculatorMode {
        PRICE("Bond Price Calculator", "Rate", "Yield"),
        YIELD("Bond Yield Calculator", "Price", "Price");

        final String title;
        final String inputLabel;
        final String switchTo;

        CalculatorMode(String title, String inputLabel, String switchTo) {
            this.title = title;
            this.inputLabel = inputLabel;
            this.switchTo = switchTo;
        }
    }

    public BondCalculatorGui(BondCalculator bondCalculator, Stopwatch stopwatch) {
        this.bondCalculator = bondCalculator;
        this.stopwatch = stopwatch;
        setupFrame();
        promptUserForCalculatorChoice();
        initializeGui();
    }

    private void setupFrame() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setMaximumSize(new Dimension(600, 400));
    }

    private void promptUserForCalculatorChoice() {
        Object[] options = {CalculatorMode.PRICE.title, CalculatorMode.YIELD.title};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select a Bond Calculator",
                "Choose Calculator",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        mode = (choice == JOptionPane.YES_OPTION) ? CalculatorMode.PRICE : CalculatorMode.YIELD;
    }

    private void initializeGui() {
        clearFrameContent();
        frame.setTitle(mode.title);
        frame.add(setupBaseInputPanel(), BorderLayout.CENTER);
        frame.add(createSwitchPanel(), BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void clearFrameContent() {
        frame.getContentPane().removeAll();
    }

    private JPanel setupBaseInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        couponField = createAndAddLabelAndTextField(inputPanel, "Coupon");
        yearsField = createAndAddLabelAndTextField(inputPanel, "Years");
        faceField = createAndAddLabelAndTextField(inputPanel, "Face Value");
        rateOrPriceField = createAndAddLabelAndTextField(inputPanel, mode.inputLabel);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this::calculate);
        inputPanel.add(calculateButton);

        resultField = new JTextField();
        resultField.setFont(DEFAULT_FONT);
        resultField.setEditable(false);
        inputPanel.add(resultField);

        timeLabel = new JLabel();
        timeLabel.setFont(DEFAULT_FONT);
        inputPanel.add(timeLabel);

        return inputPanel;
    }

    private JTextField createAndAddLabelAndTextField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(DEFAULT_FONT);
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setFont(DEFAULT_FONT);
        panel.add(textField);

        return textField;
    }

    private JPanel createSwitchPanel() {
        JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton switchButton = new JButton("Switch to " + mode.switchTo + " Calculator");
        switchButton.addActionListener(e -> {
            mode = (mode == CalculatorMode.PRICE) ? CalculatorMode.YIELD : CalculatorMode.PRICE;
            initializeGui();
        });
        switchPanel.add(switchButton);

        return switchPanel;
    }

    private void calculate(ActionEvent e) {
        Duration timeElapsed = stopwatch.runWithTimer(() -> {
            double coupon;
            int years;
            double face;
            double rateOrPrice;

            try {
                coupon = Double.parseDouble(couponField.getText());
                years = Integer.parseInt(yearsField.getText());
                face = Double.parseDouble(faceField.getText());
                rateOrPrice = Double.parseDouble(rateOrPriceField.getText());
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid inputs");
                return;
            }

            double result;
            switch(mode) {
                case PRICE: {
                    result = bondCalculator.calcPrice(coupon, years, face, rateOrPrice);
                    break;
                }
                case YIELD: {
                    result = bondCalculator.calcYield(coupon, years, face, rateOrPrice);
                    break;
                }
                default: {
                    resultField.setText("Invalid calculator mode: " + mode);
                    return;
                }
            }

             resultField.setText(formatResult(result));
        });
        displayTimeElapsed(timeElapsed);
    }

    private static String formatResult(double number) {
        return String.format("%.7f", number);
    }

    private void displayTimeElapsed(Duration timeElapsed) {
        double timeElapsedMs = timeElapsed.toNanos() / 1_000_000.0;
        timeLabel.setText("Calculated in " + timeElapsedMs + "ms");
    }
}
