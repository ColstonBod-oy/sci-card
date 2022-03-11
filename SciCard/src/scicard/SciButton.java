/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scicard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class SciButton extends JButton {
    
    public SciButton(Font font) {
        setText("next");
        setFont(font.deriveFont(14f));
        setMaximumSize(new Dimension(155, 50));
        setBackground(Color.decode("#08deea"));
        setForeground(Color.decode("#01012b"));
        setBorder(BorderFactory.createCompoundBorder(new SciButton.TriangularBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                setContentAreaFilled(false);
                setOpaque(true);
                setBackground(Color.decode("#08deea"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setContentAreaFilled(true);
                setBackground(Color.decode("#00fefc"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#00fefc"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.decode("#08deea"));
            }
        });
    }
    
    // Creates triangular corners 
    class TriangularBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.decode("#d1f7ff"));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 50, 50);
        }
    }
    
}
