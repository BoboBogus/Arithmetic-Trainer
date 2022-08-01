package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import settings.*;
import javax.swing.BoxLayout;

public class CompleteFrame extends JFrame {
    boolean newHighScore = false;
    long hours;
    long minutes;
    long seconds;
    long milliseconds;
    String newScore;
    Difficulty_Mode Mode;
    String Difference;

    public CompleteFrame(int amountAnswered, int amountCorrect, int amountIncorrect, long hours, long minutes,
            long seconds, long milliseconds, Difficulty_Mode Mode) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.Mode = Mode;
        this.newScore = (hours + ":" + minutes + ":" + seconds + ":" + milliseconds);
        EvaluateScores();
        JLabel Congrats = new JLabel("NEW HIGH SCORE");
        if (newHighScore) {
            setTitle("Complete (NEW HIGH SCORE)");
        } else {
            setTitle("Complete Frame");
            Congrats.setText("Just off By: " + Difference);
        }
        JPanel contentPane = new JPanel();
        contentPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setSize(new Dimension(300, 400));
        JLabel label = new JLabel("ratio correct to incorrect " + amountCorrect + ":" + amountIncorrect);
        JLabel accuracy = new JLabel("accuracy percent: " + (amountCorrect / amountAnswered) * 100 + "%");
        JLabel time = new JLabel(hours + ":" + minutes + ":" + seconds + ":" + milliseconds);

        time.setFont(new Font("Bauhaus 93", Font.BOLD, 29));
        Congrats.setFont(new Font("Bauhaus 93", Font.ITALIC, 14));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(label);
        add(Box.createRigidArea(new Dimension(0, 15)));
        accuracy.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(accuracy);
        add(Box.createRigidArea(new Dimension(0, 15)));
        time.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(time);
        add(Box.createRigidArea(new Dimension(0, 15)));
        Congrats.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Congrats);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    void EvaluateScores() {
        int LineToRead = 0;
        switch (Mode) {
            case ADDITION_LOW:
                LineToRead = 1;
                break;
            case ADDITION_MID:
                LineToRead = 2;
                break;
            case ADDITION_HIGH:
                LineToRead = 3;
                break;
            case SUBTRACTION_LOW:
                LineToRead = 4;
                break;
            case SUBTRACTION_MID:
                LineToRead = 5;
                break;
            case SUBTRACTION_HIGH:
                LineToRead = 6;
                break;
            case MULTIPLICATION_LOW:
                LineToRead = 7;
                break;
            case MULTIPLICATION_MID:
                LineToRead = 8;
                break;
            case MULTIPLICATION_HIGH:
                LineToRead = 9;
                break;
            case DIVISION_LOW:
                LineToRead = 10;
                break;
            case DIVISION_MID:
                LineToRead = 11;
                break;
            case DIVISION_HIGH:
                LineToRead = 12;
                break;
        }
        try {
            File data = new File("DATA/data.txt");
            Scanner dataRead = new Scanner(data);
            for (int lineNum = 1; dataRead.hasNext(); lineNum++) {
                if (lineNum == LineToRead) {
                    String timeValue = getTime(dataRead.nextLine());
                    long hour = grabNum(timeValue, -1);
                    long minute = grabNum(timeValue, 0);
                    long second = grabNum(timeValue, 1);
                    long millisecond = grabNum(timeValue, 2);

                    long hourD = hours - hour;
                    long minuteD = minutes - minute;
                    long secondD = seconds - second;
                    long millisecondD = milliseconds - millisecond;
                    Difference = (hourD + ":" + minuteD + ":" + secondD + ":" + millisecondD);
                    if (hour < hours && hour != 0) {
                        return;
                    }
                    if (minute < minutes && minute != 0) {
                        return;
                    }
                    if (second < seconds && second != 0) {
                        return;
                    }
                    if (millisecond < milliseconds && millisecond != 0) {
                        return;
                    }
                    newHighScore = true;
                    replaceSelected(data, newScore, LineToRead);
                    return;
                }
            }
            dataRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    String getTime(String data) {
        String result = "";
        for (int i = data.indexOf("=") + 2; i < data.length(); i++) {
            result += data.charAt(i) + "";
        }
        return result;
    }

    long grabNum(String data, int index) {
        int chosenIndex = 0;
        int followUp = data.length();
        int indexCheck = 0;
        boolean first = false;
        // fix the :recognition
        // if zero get (0):0:0:0

        if (index == -1) {
            chosenIndex = 0;
            first = false;
        }
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ':') {
                if (indexCheck == index && !first) {
                    chosenIndex = i + 1;
                }
                if (indexCheck == index + 1) {
                    followUp = i;
                }
                indexCheck++;
            }
        }
        String num = "";
        for (int i = chosenIndex; i < followUp; i++) {
            num += data.charAt(i) + "";

        }
        return Long.parseLong(num);

    }

    public void replaceSelected(File file, String score, int line) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader Bfile = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String newLine;

            int LineCount = 1;
            while ((newLine = Bfile.readLine()) != null) {

                if (LineCount == line) {
                    newLine = (Mode.toString() + " = " + score);
                }
                inputBuffer.append(newLine);
                inputBuffer.append('\n');
                LineCount++;
            }
            Bfile.close();
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
