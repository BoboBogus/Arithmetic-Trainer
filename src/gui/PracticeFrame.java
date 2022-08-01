package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import gui.CompleteFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import settings.*;
import utilities.utils;
import utilities.Stopwatch;

public class PracticeFrame extends JFrame implements ActionListener {
    JLabel Log;
    float answer = 0;
    JTextField Input;
    JButton Output;
    ArrayList<Double> numbers = new ArrayList<Double>();
    static int CorrectQuestions;
    static int IncorrectQuestions;
    static int amountOfQuestions;
    static int questionsAnswered = 1;
    Difficulty_Mode Mode;
    boolean firstSolve = true;
    static boolean initFrame = false;
    static Stopwatch timer;

    public PracticeFrame(Difficulty_Mode gameMode) {
        addComponentsToPane(this);
        Mode = gameMode;

    }

    public void ActivateFrame() {
        QuestionAndAnwer(Mode);
        if (!initFrame) {
            Stopwatch t = new Stopwatch();
            timer = t;
            t.start();
            initFrame = true;
        }
        String title = "(" + questionsAnswered + "/" + amountOfQuestions + ") " + Mode.toString();
        setTitle(title);
        setSize(settings.PRACTICE_FRAME_WIDTH, settings.PRACTICE_FRAME_HEIGHT);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addComponentsToPane(PracticeFrame pane) {
        Log = new JLabel("", SwingConstants.CENTER);
        Log.setPreferredSize(new Dimension(settings.PRACTICE_FRAME_WIDTH, utils.HeightPercent(45)));
        Log.setFont(new Font("Serif", Font.BOLD, 29));
        Input = new JTextField("", SwingConstants.CENTER);
        Input.setPreferredSize(new Dimension(200, utils.HeightPercent(35)));
        Output = new JButton("Submit");
        Output.addActionListener(pane);
        Output.setPreferredSize(new Dimension(200, utils.HeightPercent(20)));

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(Log, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(Input, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(Output, c);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ("Submit"):
                checkSubmit(answer, Input);
                break;
            case ("Exit"):
                break;
            case ("NewQuestion"):
                break;
        }
    }

    public void checkSubmit(float answer, JTextField Input) {
        if (Float.parseFloat(Input.getText()) == answer) {
            Solved(true);
        } else {
            Solved(false);
        }
    }

    private void QuestionAndAnwer(Difficulty_Mode gameMode) {
        final int MIN_NUM_LOW = settings.MIN_NUM_LOW;
        final int MAX_NUM_LOW = settings.MAX_NUM_LOW;
        final int DECIMAL_OFFSET_LOW = settings.DECIMAL_OFFSET_LOW;
        final int AMOUNT_NUM_LOW = settings.AMOUNT_NUM_LOW;

        final int MIN_NUM_MID = settings.MIN_NUM_MID;
        final int MAX_NUM_MID = settings.MAX_NUM_MID;
        final int DECIMAL_OFFSET_MID = settings.DECIMAL_OFFSET_MID;
        final int AMOUNT_NUM_MID = settings.AMOUNT_NUM_MID;

        final int MIN_NUM_HIGH = settings.MIN_NUM_HIGH;
        final int MAX_NUM_HIGH = settings.MAX_NUM_HIGH;
        final int DECIMAL_OFFSET_HIGH = settings.DECIMAL_OFFSET_HIGH;
        final int AMOUNT_NUM_HIGH = settings.AMOUNT_NUM_HIGH;

        switch (gameMode) {
            case ADDITION_LOW:
                for (int x = 0; x < AMOUNT_NUM_LOW; x++) {
                    double number = randomNumber(MIN_NUM_LOW, MAX_NUM_LOW, DECIMAL_OFFSET_LOW);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Addition");
                break;

            case ADDITION_MID:
                for (int x = 0; x < AMOUNT_NUM_MID; x++) {
                    double number = randomNumber(MIN_NUM_MID, MAX_NUM_MID, DECIMAL_OFFSET_MID);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Addition");
                break;

            case ADDITION_HIGH:
                for (int x = 0; x < AMOUNT_NUM_HIGH; x++) {
                    double number = randomNumber(MIN_NUM_HIGH, MAX_NUM_HIGH, DECIMAL_OFFSET_HIGH);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Addition");
                break;

            case SUBTRACTION_LOW:
                for (int x = 0; x < AMOUNT_NUM_LOW; x++) {
                    double number = randomNumber(MIN_NUM_LOW, MAX_NUM_LOW, DECIMAL_OFFSET_LOW);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Subtraction");
                break;
            case SUBTRACTION_MID:
                for (int x = 0; x < AMOUNT_NUM_MID; x++) {
                    double number = randomNumber(MIN_NUM_MID, MAX_NUM_MID, DECIMAL_OFFSET_MID);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Subtraction");
                break;
            case SUBTRACTION_HIGH:
                for (int x = 0; x < AMOUNT_NUM_HIGH; x++) {
                    double number = randomNumber(MIN_NUM_HIGH, MAX_NUM_HIGH, DECIMAL_OFFSET_HIGH);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Subtraction");
                break;
            case MULTIPLICATION_LOW:
                for (int x = 0; x < AMOUNT_NUM_LOW; x++) {
                    double number = randomNumber(MIN_NUM_LOW, MAX_NUM_LOW, DECIMAL_OFFSET_LOW);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Multiplication");
                break;
            case MULTIPLICATION_MID:
                for (int x = 0; x < AMOUNT_NUM_MID; x++) {
                    double number = randomNumber(MIN_NUM_MID, MAX_NUM_MID, DECIMAL_OFFSET_MID);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Multiplication");
                break;
            case MULTIPLICATION_HIGH:
                for (int x = 0; x < AMOUNT_NUM_HIGH; x++) {
                    double number = randomNumber(MIN_NUM_HIGH, MAX_NUM_HIGH, DECIMAL_OFFSET_HIGH);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Multiplication");
                break;

            case DIVISION_LOW:
                for (int x = 0; x < AMOUNT_NUM_LOW; x++) {
                    double number = randomNumber(MIN_NUM_LOW, MAX_NUM_LOW, DECIMAL_OFFSET_LOW);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Division");
                break;
            case DIVISION_MID:
                for (int x = 0; x < AMOUNT_NUM_MID; x++) {
                    double number = randomNumber(MIN_NUM_MID, MAX_NUM_MID, DECIMAL_OFFSET_MID);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Division");
                break;
            case DIVISION_HIGH:
                for (int x = 0; x < AMOUNT_NUM_HIGH; x++) {
                    double number = randomNumber(MIN_NUM_HIGH, MAX_NUM_HIGH, DECIMAL_OFFSET_HIGH);
                    numbers.add(number);
                }
                answer = GenerateQuestion(numbers, "Division");
                break;
        }
    }

    public float GenerateQuestion(ArrayList<Double> numbers, String operator) {
        float result = 0;
        switch (operator) {
            case ("Addition"):
                for (int i = 0; i < numbers.size(); i++) {
                    result += numbers.get(i);
                    if (i == 0) {
                        if (numbers.get(i) < 0) {
                            adjustLog("(" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(numbers.get(i) + " ");
                        }
                    } else {
                        if (numbers.get(i) < 0) {
                            adjustLog(" + (" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(" + " + numbers.get(i));
                        }
                    }
                }
                break;

            case ("Subtraction"):
                for (int i = 0; i < numbers.size(); i++) {
                    result -= numbers.get(i);
                    if (i == 0) {
                        if (numbers.get(i) < 0) {
                            adjustLog("(" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(numbers.get(i) + " ");
                        }
                    } else {
                        if (numbers.get(i) < 0) {
                            adjustLog(" - (" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(" - " + numbers.get(i));
                        }
                    }
                }
                break;

            case ("Multiplication"):
                for (int i = 0; i < numbers.size(); i++) {
                    result *= numbers.get(i);
                    if (i == 0) {
                        if (numbers.get(i) < 0) {
                            adjustLog("(" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(numbers.get(i) + " ");
                        }
                    } else {
                        if (numbers.get(i) < 0) {
                            adjustLog(" x (" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(" x " + numbers.get(i));
                        }
                    }
                }
                break;

            case ("Division"):
                Collections.sort(numbers);
                for (int i = 0; i < numbers.size(); i++) {
                    result /= numbers.get(i);
                    if (i == 0) {
                        if (numbers.get(i) < 0) {
                            adjustLog("(" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(numbers.get(i) + " ");
                        }
                    } else {
                        if (numbers.get(i) < 0) {
                            adjustLog(" / (" + numbers.get(i) + ") ");
                        } else {
                            adjustLog(" / " + numbers.get(i));
                        }
                    }
                }
                break;
        }
        return result;
    }

    private void adjustLog(String text) {
        String string = Log.getText();
        string += text;
        Log.setText(string);
    }

    static private double randomNumber(int min, int max, int DecimalOffset) {
        double result;
        result = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (DecimalOffset != 0)
            result /= DecimalOffset;
        return result;
    }

    private void Solved(boolean success) {
        if (success) {
            if (firstSolve) {
                CorrectQuestions++;
                firstSolve = false;
            }
            if (amountOfQuestions > questionsAnswered) {
                questionsAnswered++;
                new PracticeFrame(Mode).ActivateFrame();
            } else {
                endTest();
            }
            dispose();
        } else {
            if (firstSolve) {
                IncorrectQuestions++;
                firstSolve = false;
            }
        }
    }

    private void endTest() {
        timer.stop();
        long hours = timer.getElapsedHours();
        long minutes = timer.getElapsedMinutes();
        long seconds = timer.getElapsedSeconds();
        long miliseconds = timer.getElapsedMilliseconds();
        new CompleteFrame(CorrectQuestions, amountOfQuestions, IncorrectQuestions, hours, minutes, seconds,
                miliseconds, Mode);
    }

}
