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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author User
 */
public class SciRadioPanel extends JPanel implements ActionListener {
    
    private JTextPane question;
    private Font font;
    private SciRadioButton[] choices;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private int answer;
    private int[] score;
    private SciButton nextButton;
    private ButtonGroup buttonGroup;
    private int selected;
    
    public SciRadioPanel(String question, Font font, String[] choices, JPanel cardPanel, CardLayout cardLayout, int answer, int[] score) {
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
        this.choices = new SciRadioButton[choices.length];
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.answer = answer;
        this.score = score;
        nextButton = new SciButton(font);
        nextButton.addActionListener(this);
        buttonGroup = new ButtonGroup();
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
            this.choices[i] = new SciRadioButton(choices[i]);
            this.choices[i].addActionListener(this);
            buttonGroup.add(this.choices[i]);
            
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
            if (e.getSource() == choices[i]) {
                selected = i;
            }
        }
        
        if (e.getSource().equals(nextButton)) {
            if (selected == answer) {
                score[0]++;
                cardLayout.next(cardPanel);
            }
            
            else if (buttonGroup.getSelection() == null) {
                JOptionPane.showMessageDialog(question, "Item unanswered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else {
                cardLayout.next(cardPanel);
            }
        }
    }
    
    class SciRadioButton extends JRadioButton {
        public SciRadioButton(String text) {
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
                
                g2d.fillOval(1, ly, 16, 16);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(2, ly + 1, 14, 14);
                
                if (isEnabled()) {
                    g2d.setColor(getBackground());
                }
                
                else {
                    g2d.setColor(Color.GRAY);
                }
                
                // Draws dot icon
                g2d.fillOval(5, ly + 4, 8, 8);
            }
            
            else {
                if (isFocusOwner()) {
                    g2d.setColor(getBackground());
                }
                
                else {
                    g2d.setColor(Color.GRAY);
                }
                
                g2d.fillOval(1, ly, 16, 16);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(2, ly + 1, 14, 14);
            }
            
            g2d.dispose();
        }
    }
    
}
