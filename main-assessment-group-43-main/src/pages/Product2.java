package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import components.NavigationBar;

public class Product2 extends JFrame {
    public Product2(String productName) {
        setTitle(productName + " Product Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a content pane with BorderLayout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        // Add the NavigationBar component
        NavigationBar navigationBar = new NavigationBar();
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);

        // Create JPanel to hold product information and image
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Unified CSS for all JLabels to ensure consistency
        String cssStyle = "<style>" +
                "h1 { font-size: 24pt; font-weight: bold; color: #007BFF; }" +
                "h2 { font-size: 18pt; font-weight: normal; color: #333333; margin: 10px 0; }" +
                "h3 { font-size: 14pt; }" +
                "div { text-align: center; margin: 10px; }" +
                "</style>";

        // Create JLabel with HTML text for product information
        JLabel labelText = new JLabel("<html>" + cssStyle + "<div style='text-align: center; font-size: 20pt;'>" + productName + "<br>Minimalistic fan</div></html>");
        productPanel.add(labelText);

        // Load the image from file
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/Respira.png"));
        JLabel imageLabel = new JLabel(imageIcon);
        productPanel.add(imageLabel);

        // Create JLabel with HTML text for product price
        JLabel labelText1 = new JLabel("<html>" + cssStyle + "<h1>Rs 24,000</h1></html>");
        productPanel.add(labelText1);

        // Create the "Add to Cart" button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Product added to cart successfully!"));
        productPanel.add(addToCartButton);

        // Create JLabel with HTML text for product description
        JLabel labelText2 = new JLabel("<html>" + cssStyle + "<h3>Get electric fans to save on bills. Complements any room with its minimalistic design and efficient performance.</h3></html>");
        productPanel.add(labelText2);

        // Create JLabel with HTML text for dimensions
        JLabel labelText3 = new JLabel("<html>" + cssStyle + "<h3>Dimensions<br>Length – 188cm | Width – 5cm | Height – 10cm</h3></html>");
        productPanel.add(labelText3);

        // Add the product panel to the content pane
        contentPane.add(productPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Product2("Respira"));
    }
}
