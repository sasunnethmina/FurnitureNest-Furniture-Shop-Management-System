package components; // Package statement indicating the class belongs to 'components' package

import javax.swing.*;
import java.awt.*;

public class labelComponent {
    public static void main(String[] args) {
        // Load the image
        ImageIcon icon = new ImageIcon("src/images/label.png");

        // Create a JLabel with the image
        JLabel label = new JLabel();
        label.setIcon(icon);

        // Create a JFrame to hold the label
        JFrame frame = new JFrame("Image Label Example");
        frame.setSize(300, 300); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.getContentPane().add(label, BorderLayout.CENTER); // Add label to the center of the frame

        // Display the frame
        frame.setVisible(true);
    }
}