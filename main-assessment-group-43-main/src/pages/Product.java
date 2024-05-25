package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import components.NavigationBar;

public class Product extends JFrame {
    public Product(String productName) {
        setTitle(productName + " - Product Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1060, 900); // Set the size according to your preference

        // Create a content pane with BorderLayout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE); // Set background color if needed

        // Add the NavigationBar component
        NavigationBar navigationBar = new NavigationBar();
        // Set an empty border with zero size to remove any space around the NavigationBar
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);

        // Create JPanel to hold product information and image
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        // Increase left padding for the product panel
        productPanel.setBorder(new EmptyBorder(10, 20, 10, 20)); // Adjust the padding as needed

        // Unified CSS for all JLabels to ensure consistency
        String cssStyle = "<style>" +
                "h1 { font-size: 24pt; font-weight: bold; color: #007BFF; }" +
                "h2 { font-size: 18pt; font-weight: normal; color: #333333; margin: 10px 0; }" +
                "h3 { font-size: 14pt; }" +
                "div { text-align: center; margin: 10px; }" +
                "</style>";

        // Adjust the JLabel for the product name and PLU
        JLabel labelText = new JLabel("<html>" + cssStyle + "<div>" + productName + "<br>PLU 730172</div></html>");
        productPanel.add(labelText);

        // Load the image from file
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/" + productName + ".png")); // Ensure this path matches your resource folder structure
        JLabel imageLabel = new JLabel(imageIcon);
        productPanel.add(imageLabel);

        // Adjust the JLabel for the product price
        JLabel labelText1 = new JLabel("<html>" + cssStyle + "<h1>Rs 24,000</h1></html>");
        productPanel.add(labelText1);

        // Add the "Add to Cart" button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Product added to cart successfully!"));
        productPanel.add(addToCartButton);

        // Adjust the JLabel for product description
        JLabel labelText2 = new JLabel("<html>" + cssStyle + "<h3>One sofa, lots of possibilities. " +
                "Need extra beds or a comfy reading corner? No problem. Just choose the pieces you like, combine them as you want – and change when you feel like it. " +
                "It’s a great way to add comfy seating to a smaller space or complement a larger sofa in the room. " +
                "The inside frame of the arm and backrest is cut out and covered with a soft cushion. This makes both the armrest and the backrest very " +
                "soft and optimizes your comfort.</h3></html>");
        productPanel.add(labelText2);

        // Adjust the JLabel for dimensions
        JLabel labelText3 = new JLabel("<html>" + cssStyle + "<h3>Dimensions<br>" +
                "1) Seater: Length – 188cm | Width – 83cm | Height – 95cm)<br>" +
                "2) Seater: Length – 90cm | Width – 83cm | Height – 95cm</h3></html>");
        productPanel.add(labelText3);

        // Add the product panel to the content pane
        contentPane.add(productPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Ensure you replace "Lolita" with the actual product name that matches your resource paths
        SwingUtilities.invokeLater(() -> new Product("Lolita"));
    }
}
