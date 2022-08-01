package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import settings.*;
import utilities.utils;

public class MainFrame extends JWindow implements ItemListener {
    // Where instance variables are declared:
    static public int CorrectQuestions = 0;
    static public int IncorrectQuestions = 0;
    boolean initialized = false;
    JPanel cards;
    JButton ShortB;
    JButton MidB;
    JButton LongB;

    public MainFrame() {
        setSize(settings.MAIN_FRAME_WIDTH, settings.MAIN_FRAME_HEIGHT);
        setBackground(Color.BLACK);
        ShortB = new JButton("5 questions");
        MidB = new JButton("15 questions");
        LongB = new JButton("30 questions");
        ShortB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PracticeFrame.amountOfQuestions = 5;
                ShortB.setBackground(Color.DARK_GRAY);
                MidB.setBackground(Color.WHITE);
                LongB.setBackground(Color.WHITE);
            }
        });
        MidB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PracticeFrame.amountOfQuestions = 15;
                MidB.setBackground(Color.DARK_GRAY);
                LongB.setBackground(Color.WHITE);
                ShortB.setBackground(Color.WHITE);
            }
        });
        LongB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PracticeFrame.amountOfQuestions = 30;
                LongB.setBackground(Color.DARK_GRAY);
                ShortB.setBackground(Color.WHITE);
                MidB.setBackground(Color.WHITE);
            }
        });
        add(ShortB, BorderLayout.NORTH);
        add(MidB, BorderLayout.NORTH);
        add(LongB, BorderLayout.NORTH);
        cards = new JPanel(new CardLayout());

        String comboBoxItems[] = new String[Difficulty_Mode.values().length];
        int i = 0;
        for (Difficulty_Mode mode : Difficulty_Mode.values()) {
            JPanel card = new JPanel();
            card.setBackground(Color.LIGHT_GRAY);
            card.setPreferredSize(new Dimension(utils.MainWidthPercent(70), utils.MainHeightPercent(70)));
            createPanelBasic(card, new PracticeFrame(mode));
            cards.add(card, mode.toString());
            comboBoxItems[i] = mode.toString();
            i++;
        }

        JPanel comboBoxPane = new JPanel(); // use FlowLayout
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        add(comboBoxPane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.SOUTH);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void createPanelBasic(JPanel panel, PracticeFrame PF) {
        JButton Initialize = new JButton("Initialize");
        Initialize.setMnemonic(KeyEvent.VK_ENTER);
        Initialize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!initialized && PracticeFrame.amountOfQuestions > 0) {
                    PF.ActivateFrame();
                    initialized = true;
                    dispose();
                    setVisible(false);
                }
            }
        });
        panel.add(Initialize, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

}