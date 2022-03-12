/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author User
 */
public class SciTextPanel extends JPanel implements ActionListener {
    
    private JTextPane question;
    private Font font;
    private String hintText;
    private SciTextField textField;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private String answer;
    private int[] score;
    private SciButton nextButton;
    private boolean isSelected;
    
    public SciTextPanel(String question, Font font, String hintText, JPanel cardPanel, CardLayout cardLayout, String answer, int[] score) {
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
        this.hintText = hintText;
        this.textField = new SciTextField(hintText);
        this.textField.setPreferredSize(new Dimension(210, 60));
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.answer = answer.toLowerCase();
        this.score = score;
        nextButton = new SciButton(font);
        nextButton.addActionListener(this);
        isSelected = false;
        setOpaque(false);
        setLayout(new GridBagLayout());
        add(this.question, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        add(textField, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(26, 0, 16, 0), 0, 0));
        add(nextButton, new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(20, 120, 0, 120), 51, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextButton)) {
            if (textField.getText().toLowerCase().equals(answer)) {
                score[0]++;
                cardLayout.next(cardPanel);
            }
            
            else if (textField.getText().equals(hintText)) {
                JOptionPane.showMessageDialog(question, "Item unanswered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else {
                cardLayout.next(cardPanel);
            }
        }
    }
    
    class SciTextField extends JTextField {
        public SciTextField(String text) {
            setText(text);
            setFont(font);
            setEditable(isSelected);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setCaretColor(Color.decode("#08deea"));
            setForeground(Color.decode("#ff160c"));
            setBorder(BorderFactory.createCompoundBorder(new SciTextField.RoundedBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
            
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isSelected == false) {
                        isSelected = true;
                        setText("");
                        setEditable(isSelected);
                        getCaret().setVisible(isSelected);
                        setForeground(Color.decode("#08deea"));
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
            
            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && getText().equals("")) {
                        isSelected = false;
                        setText(text);
                        setEditable(isSelected);
                        getCaret().setVisible(isSelected);
                        setForeground(Color.decode("#ff160c"));
                    }
                }
            });
            
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {}

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().trim().equals("")) {
                        isSelected = false;
                        setText(text);
                        setEditable(isSelected);
                        getCaret().setVisible(isSelected);
                        setForeground(Color.decode("#ff160c"));
                    }
                }
            });
        }
        
        // Creates rounded corners 
        class RoundedBorder extends AbstractBorder {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                super.paintBorder(c, g, x, y, width, height);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(12));
                g2d.setColor(Color.decode("#d1f7ff"));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
            }
        }
    }
    
}
