package pages;

import components.NavigationBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EditDesignPage extends JFrame {

    private JComboBox<String> colorPicker;
    private JComboBox<String> sizePicker;
    private JButton saveButton;
    private String productName;

    public EditDesignPage(String productName) {
        this.productName = productName;

        setTitle("Edit Design - " + productName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Set up the layout
        setLayout(new BorderLayout());

        // Add NavigationBar at the top
        add(new NavigationBar(), BorderLayout.NORTH);

        // Product Name Label as heading
        JLabel productNameLabel = new JLabel(productName);
        productNameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        productNameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(productNameLabel, BorderLayout.PAGE_START);

        // Body panel to hold color and size pickers
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        // Color picker
        String[] colors = {"Black", "Brown", "Red", "Teak"};
        colorPicker = new JComboBox<>(colors);
        colorPicker.setMaximumSize(new Dimension(800, 30));
        bodyPanel.add(colorPicker);

        // Size picker
        String[] sizes = {"Small", "Medium", "Large"};
        sizePicker = new JComboBox<>(sizes);
        sizePicker.setMaximumSize(new Dimension(800, 30));
        bodyPanel.add(sizePicker);

        // Save button
        saveButton = new JButton("Save Design");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> saveDesign());
        bodyPanel.add(saveButton);

        add(bodyPanel, BorderLayout.CENTER);
    }

    private void saveDesign() {
        // Get the selected color
        String selectedColor = (String) colorPicker.getSelectedItem();

        // Get the selected size
        String selectedSize = (String) sizePicker.getSelectedItem();

        // Write to userdesign.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdesign.txt", true))) {
            writer.write(productName + "," + selectedColor + "," + selectedSize);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Design saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving design.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Switch to DesignPage
        new DesignPage().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        // You can run this page directly for testing
        SwingUtilities.invokeLater(() -> new EditDesignPage("Example Product").setVisible(true));
    }
}
