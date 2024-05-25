package pages;

import components.NavigationBar;
import pages.Product;
import pages.Product2;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 800);


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);


        NavigationBar navigationBar = new NavigationBar();

        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);

        JPanel imageAndProductsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageAndProductsPanel.setBackground(Color.WHITE);


        ImageIcon productImageIcon = new ImageIcon(getClass().getResource("/image/label.png"));


        Image originalImage = productImageIcon.getImage();

        Image scaledImage = originalImage.getScaledInstance(-1, 300, Image.SCALE_SMOOTH);

        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Set the new ImageIcon to the JLabel
        JLabel productImageLabel = new JLabel(scaledImageIcon);
        productImageLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        imageAndProductsPanel.add(productImageLabel);

        contentPane.add(imageAndProductsPanel, BorderLayout.CENTER);

        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(Color.WHITE);


        JLabel productsLabel = new JLabel("<html> products<br></html>");
        productsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productsLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
        productPanel.add(productsLabel, BorderLayout.NORTH);

        // Create a panel to hold multiple product images and their descriptions
        JPanel innerProductPanel = new JPanel(new FlowLayout());
        innerProductPanel.setBackground(Color.WHITE);

        // Add product images and descriptions to the product panel
        innerProductPanel.add(createProductPanel("/image/Lolita.png", "" +
                "<html>" +
                "<h1>Lolita</h1>" +
                "<p>Luxury big sofa</p>" +
                "<h3>Rs 75 000</h3>" +
                "</html>"));
        innerProductPanel.add(Box.createHorizontalStrut(30)); // Add empty space between images
        innerProductPanel.add(createProductPanel("/image/Respira.png", "" +
                "<html>" +
                "<h1>Respira</h1>" +
                "<p>Minimalistic fan</p>" +
                "<h3>Rs 150000</h3>" +
                "</html>"));
        innerProductPanel.add(Box.createHorizontalStrut(30)); // Add empty space between images
        innerProductPanel.add(createProductPanel("/image/sylthenic.png", "" +
                "<html>" +
                "<h1>Syltherine</h1>" +
                "<p>stylish cafe chair</p>" +
                "<h3>Rs 2500</h3>" +
                "</html>"));

        productPanel.add(innerProductPanel, BorderLayout.CENTER);

        contentPane.add(productPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createProductPanel(String imagePath, String description) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JButton productButton = new JButton();

        ImageIcon productImageIcon = new ImageIcon(getClass().getResource(imagePath));
        JLabel productImageLabel = new JLabel(productImageIcon);
        productButton.add(productImageLabel);

        panel.add(productButton, BorderLayout.CENTER);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(descriptionLabel, BorderLayout.SOUTH);

        // Set action listener for redirection
        productButton.addActionListener(e -> {
            if (imagePath.equals("/image/Lolita.png")) {
                new Product("Lolita").setVisible(true);
            } else if (imagePath.equals("/image/Respira.png")) {
                new Product2("Respira").setVisible(true);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage::new);
    }
}
