/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scicard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author User
 */
public class SciCard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FontFormatException, IOException {
        int[] score = {0};
        String[][] choices = {
            {"opt1", "opt2", "opt3", "opt4"},
            {"opt5", "opt6", "opt7", "opt8"}
        };
        
        Font font = Font.createFont(Font.TRUETYPE_FONT, SciCard.class.getResourceAsStream("/scicard/assets/cyberspace.ttf")).deriveFont(10f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        
        JLabel emblem = new JLabel();
        BufferedImage img = ImageIO.read(SciCard.class.getResource("/scicard/assets/emblem.png"));
        Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(dimg);
        emblem.setIcon(ico);
        
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout(cardLayout);
        cardPanel.add(new SciLabelPanel("start\nsciquiz", font, cardPanel, cardLayout, score, false));
        cardPanel.add(new SciRadioPanel("test question", font, choices[0], cardPanel, cardLayout, 1, score));
        cardPanel.add(new SciCheckPanel("test question2", font, choices[1], cardPanel, cardLayout, new int[]{0, 1, -1, -1}, score));
        cardPanel.add(new SciTextPanel("test question3", font, "f____", cardPanel, cardLayout, "fuck", score));
        cardPanel.add(new SciLabelPanel("end\nsciquiz", font, cardPanel, cardLayout, score, true));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(480, 480));
        mainPanel.setBackground(Color.decode("#d1f7ff"));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(emblem, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 28, 0), 0, 0));
        mainPanel.add(cardPanel, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        
        JFrame mainFrame = new JFrame("Sci-Card");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
}
