/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
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
    private SciButton nextButton;
    private int[] answers;
    private int correct;
    private int[] selected;
    private boolean isAnswered;
    
    public SciCheckPanel(String question, Font font, String[] choices, SciButton nextButton, int[] answers, int correct) {
        this.font = font;
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
        this.choices = new SciCheckBox[choices.length];
        this.nextButton = nextButton;
        this.nextButton.addActionListener(this);
        this.answers = answers;
        this.correct = correct;
        this.selected = new int[choices.length];
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
        containerPanel.add(Box.createHorizontalStrut(55));
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.question);
        add(Box.createVerticalStrut(15));
        add(containerPanel);
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
                correct++;
            }
            
            else {
                JOptionPane.showMessageDialog(question, "This item must be answered!", "Error", JOptionPane.ERROR_MESSAGE);
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
