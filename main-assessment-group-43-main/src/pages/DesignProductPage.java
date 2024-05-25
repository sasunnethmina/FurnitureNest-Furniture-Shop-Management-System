package pages;

import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DesignProductPage extends JFrame {

    private int quantity = 0; // Keep track of the quantity for this product
    private JLabel productdLabel;
    private JLabel nameLabel;

    public DesignProductPage() {
        setTitle("Product Design");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        NavigationBar navigationBar = new NavigationBar();
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);

        JPanel bodyPanel = new JPanel(new BorderLayout());
        nameLabel = new JLabel("NORDEN Gateleg table");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        productdLabel = new JLabel("<html> birch <br/>PLU: 430173</html>");
        productdLabel.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon productIcon = new ImageIcon("src/Image/table1.png");
        JLabel productLabel = new JLabel(new ImageIcon(productIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));

        JTextArea descriptionArea = new JTextArea("Gateleg tables have been around for centuries – with the space-saving design. This table a straightforward, Scandinavian look and added a few drawers for flatware and napkins.");
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(new EmptyBorder(30, 0, 0, 0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 5));// Panel for buttons
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(Color.BLUE);
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.addActionListener(e -> addToCart());

        JButton editDesignButton = new JButton("Edit Design");
        editDesignButton.addActionListener(e -> {
            dispose();
            openEditDesignPage(nameLabel.getText());
        });

        buttonPanel.add(addToCartButton);
        buttonPanel.add(editDesignButton);

        bodyPanel.add(buttonPanel, BorderLayout.PAGE_END); // Add button panel to the bottom of bodyPanel

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(nameLabel);
        leftPanel.add(productdLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(productLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(descriptionArea);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        bodyPanel.add(leftPanel, BorderLayout.LINE_START); // Align leftPanel to the left
        bodyPanel.add(rightPanel, BorderLayout.CENTER); // Align rightPanel to the center

        contentPane.add(bodyPanel, BorderLayout.CENTER);
    }

    private void addToCart() {
        quantity++; //  quantity
        String data = nameLabel.getText() + " | " + "src/Image/table1.png" + " | " + "Quantity=" + quantity + "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt", true))) {
            writer.write(data);

            JOptionPane.showMessageDialog(this, "Item added to cart successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEditDesignPage(String productName) {
        // Create an instance of EditDesignPage and pass the productName
        EditDesignPage editDesignPage = new EditDesignPage(productName);

        // Close the current page
        dispose();

        // Set the EditDesignPage visible
        editDesignPage.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new DesignProductPage().setVisible(true));
    }
}
