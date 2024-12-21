package component;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

public class RoundedComponents {

    // Tạo RoundedButton
    public static JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(true); // Hiển thị nền
        button.setBackground(new Color(255, 255, 255)); // Đặt màu nền nếu cần
        button.setBorder(new RoundedBorder(20)); // Sử dụng RoundedBorder với góc bo là 20
        return button;
    }

    // Tạo RoundedTextField
    public static JTextField createRoundedTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setOpaque(true); // Hiển thị nền
        textField.setBackground(new Color(255, 255, 255)); // Đặt màu nền nếu cần
        textField.setBorder(new RoundedBorder(20)); // Sử dụng RoundedBorder với góc bo là 20
        return textField;
    }

    // Tạo RoundedComboBox
    public static JComboBox<String> createRoundedComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setOpaque(true); // Hiển thị nền
        comboBox.setBackground(new Color(255, 255, 255)); // Đặt màu nền nếu cần
        comboBox.setBorder(new RoundedBorder(20)); // Sử dụng RoundedBorder với góc bo là 20
        return comboBox;
    }

    // Lớp RoundedBorder để bo góc
    static class RoundedBorder extends AbstractBorder {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY); // Màu của đường viền
            g.fillRoundRect(x, y, width - 1, height - 1, radius, radius); // Vẽ viền bo góc
        }

        @Override
        public Insets getBorderInsets(java.awt.Component c) {
            return new Insets(5, 5, 5, 5); // Khoảng cách viền
        }
    }
}
