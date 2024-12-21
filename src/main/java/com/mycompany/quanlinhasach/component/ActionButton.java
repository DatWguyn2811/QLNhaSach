/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class ActionButton extends JButton {

    private boolean mousePress;
    private Color buttonColor = new Color(0, 160, 0); // Màu mặc định

   
    

    public ActionButton() {
        
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                mousePress = true;
               
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                mousePress = false;
                
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Kích thước hình vuông
        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        int arc = 20; // Độ bo tròn góc (arc width và height)
        // Đổi màu khi nhấn
        if (mousePress) {
            g2.setColor(new Color(158, 158, 158));  // Màu tối khi nhấn
        } else {
            g2.setColor(buttonColor);  // Sử dụng màu từ buttonColor
        }
        
        // Vẽ hình vuông thay vì hình tròn
        g2.fill(new RoundRectangle2D.Double(x, y, size, size, arc, arc));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    public void setColor(Color color) {
        this.buttonColor = color;
        repaint(); // Cập nhật lại giao diện khi màu thay đổi
    }

}
