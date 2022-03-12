/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author User
 */
public class SciCheckPanel extends JPanel implements ActionListener {
    
    private JTextPane question;
    private Font font;
    private SciCheckBox[] choices;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private int[] answers;
    private int[] score;
    private int[] selected;
    private SciButton nextButton;
    private boolean isAnswered;
    
    public SciCheckPanel(String question, Font font, String[] choices, JPanel cardPanel, CardLayout cardLayout, int[] answers, int[] score) {
        this.question = new JTextPane();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attr, "cyberspace");
        StyleConstants.setFontSize(attr, 17);
        StyleConstants.setForeground(attr, Color.decode("#01012b"));
        this.question.setParagraphAttributes(attr, true);
        this.question.setText(question);
        this.question.setEditable(false);
        this.question.setOpaque(false);
        this.font = font;
        this.choices = new SciCheckBox[choices.length];
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.answers = answers;
        this.score = score;
        this.selected = new int[choices.length];
        nextButton = new SciButton(font);
        nextButton.addActionListener(this);
        isAnswered = false;
        JPanel leftPanelY = new JPanel();
        leftPanelY.setOpaque(false);
        leftPanelY.setLayout(new BoxLayout(leftPanelY, BoxLayout.Y_AXIS));
        JPanel rightPanelY = new JPanel();
        rightPanelY.setOpaque(false);
        rightPanelY.setLayout(new BoxLayout(rightPanelY, BoxLayout.Y_AXIS));
        JPanel containerPanel = new JPanel();
        containerPanel.setOpaque(false);
        containerPanel.setLayout(new FlowLayout());
        containerPanel.add(leftPanelY);
        containerPanel.add(Box.createHorizontalStrut(15));
        containerPanel.add(rightPanelY);
        
        for (int i = 0; i < choices.length; i++) {
            this.choices[i] = new SciCheckBox(choices[i]);
            this.choices[i].addActionListener(this);
            
            if (i % 2 == 0) {
                leftPanelY.add(Box.createVerticalStrut(10));
                leftPanelY.add(this.choices[i]);
            }
            
            else {
                rightPanelY.add(Box.createVerticalStrut(10));
                rightPanelY.add(this.choices[i]);
            }
        }
        
        setOpaque(false);
        setLayout(new GridBagLayout());
        add(this.question, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        add(containerPanel, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(17, 0, 13, 0), 0, 0));
        add(nextButton, new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(20, 120, 0, 120), 51, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < choices.length; i++) {
            if (e.getSource() == choices[i] && choices[i].isSelected()) {
                selected[i] = i;
            }
            
            else if (!choices[i].isSelected()) {
                selected[i] = -1;
            }
        }
        
        if (e.getSource().equals(nextButton)) {
            for (SciCheckBox choice : choices) {
                if (choice.isSelected()) {
                    isAnswered = true;
                }
            }
            
            if (Arrays.equals(selected, answers) && isAnswered) {
                score[0]++;
                cardLayout.next(cardPanel);
            }
            
            else if (!isAnswered) {
                JOptionPane.showMessageDialog(question, "Item unanswered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else {
                cardLayout.next(cardPanel);
            }
        }
    }
    
    class SciCheckBox extends JCheckBox { 
        public SciCheckBox(String text) {
            setText(text);
            setFont(font);
            setOpaque(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBackground(Color.decode("#08deea"));
            setForeground(Color.decode("#01012b"));
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int ly = (getHeight() - 16) / 2;
            
            if (isSelected()) {
                if (isEnabled()) {
                    g2d.setColor(getBackground());
                }
                
                else {
                    g2d.setColor(Color.GRAY);
                }
                
                g2d.fillRoundRect(1, ly, 16, 16, 4, 4);
                
                // Draws check icon
                int px[] = {4, 8, 14, 12, 8, 6};
                int py[] = {ly + 8, ly + 14, ly + 5, ly + 3, ly + 10, ly + 6};
                g2d.setColor(Color.WHITE);
                g2d.fillPolygon(px, py, px.length);
            }
            
            else {
                g2d.setColor(Color.GRAY);
                g2d.fillRoundRect(1, ly, 16, 16, 4, 4);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(2, ly + 1, 14, 14, 4, 4);
            }
            
            g2d.dispose();
        }
    }
    
}
