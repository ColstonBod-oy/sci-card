/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author User
 */
public class SciLabelPanel extends JPanel implements ActionListener {
    
    private JTextPane text;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private int[] score;
    private boolean isLastCard;
    private SciButton nextButton;
    
    public SciLabelPanel(String text, Font font, JPanel cardPanel, CardLayout cardLayout, int[] score, boolean isLastCard) {
        this.text = new JTextPane();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attr, "cyberspace");
        StyleConstants.setFontSize(attr, 39);
        StyleConstants.setForeground(attr, Color.decode("#01012b"));
        this.text.setParagraphAttributes(attr, true);
        this.text.setText(text);
        this.text.setEditable(false);
        this.text.setOpaque(false);
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.score = score;
        this.isLastCard = isLastCard;
        nextButton = new SciButton(font);
        nextButton.addActionListener(this);
        setOpaque(false);
        setLayout(new GridBagLayout());
        add(this.text, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(29, 0, 0, 0), 0, 0));
        add(nextButton, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(58, 120, 0, 120), 51, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextButton) && isLastCard) {
            String finalScore = Arrays.toString(score).replace("[", "").replace("]", "");
            int selected = JOptionPane.showConfirmDialog(text, "Score: " + finalScore + "/" + 
                    (cardPanel.getComponentCount() - 2) + "\nWould you like to try again?", "Message", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (selected == 0) {
                score[0] = 0;
                cardLayout.first(cardPanel);
            }
            
            else {
                System.exit(0);
            }
        }
        
        else {
            cardLayout.next(cardPanel);
        }
    }
    
}
