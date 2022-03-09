/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scicard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        Font font = Font.createFont(Font.TRUETYPE_FONT, SciCard.class.getResourceAsStream("/scicard/assets/cyberspace.ttf")).deriveFont(10f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        
        JLabel emblem = new JLabel();
        BufferedImage img = ImageIO.read(SciCard.class.getResource("/scicard/assets/emblem.png"));
        Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(dimg);
        emblem.setIcon(ico);
        
        JFrame frame = new JFrame("Sci-Card");
        frame.setSize(480, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#d1f7ff"));
        frame.setLayout(new GridBagLayout());
        frame.add(emblem, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 100));
        frame.add(new SciRadioPanel("test question", font, new String[]{"opt1111111111", "opt2", "opt3", 
                "opt4"}, 1, frame), new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, 
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 100));
        frame.setVisible(true);
    }
    
}
