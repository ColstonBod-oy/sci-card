/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class SciRadioPanel extends JPanel {
    
    private JLabel question;
    private Font font;
    private SciRadioButton[] choices;
    private int answer;
    private JFrame card;
    
    public SciRadioPanel(String question, Font font, String[] choices, int answer, JFrame card) {
        this.font = font;
        this.question = new JLabel(question);
        this.question.setFont(font.deriveFont(14f));
        this.question.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.choices = new SciRadioButton[choices.length];
        this.answer = answer;
        this.card = card;
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
        containerPanel.add(rightPanelY);
        
        int y1 = 0;
        int y2 = 0;
        
        for (int i = 0; i < choices.length; i++) {
            this.choices[i] = new SciRadioButton(choices[i]);
            
            if (i % 2 == 0) {
                y1 += 50;
                leftPanelY.add(this.choices[i]);
            }
            
            else {
                y2 += 50;
                rightPanelY.add(this.choices[i]);
            }
        }
        
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.question);
        add(containerPanel);
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
