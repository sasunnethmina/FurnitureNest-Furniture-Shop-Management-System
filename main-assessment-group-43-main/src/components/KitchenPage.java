package components;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class KitchenPage extends JPanel {

    public KitchenPage() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        displayKitchenItems();
    }

    private void displayKitchenItems() {
        removeAll(); // Clear the panel for new items
        add(new JLabel("Kitchen Items")); // Section title

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 4, 10, 10)); // Assuming 4 columns in the grid
        addKitchenItems(itemsPanel);

        add(itemsPanel);
        revalidate();
        repaint();
    }

    private void addKitchenItems(JPanel itemsPanel) {
        // Example image paths, replace with actual paths/names
        String[] imagePaths = {
                "Kimage1.jpg", "Kimage2.jpg", "Kimage3.png", "Kimage4.png", "Kimage5.png", "table6.png", "table7.png", "table8.png"
        };

        for (String imagePath : imagePaths) {
            itemsPanel.add(createItemPanel("/Image/" + imagePath));
        }
    }

    private JPanel createItemPanel(String imagePath) {
        // Adjust path as necessary. The path provided assumes the images are in a folder named 'Image' in your resource directory
        ImageIcon itemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image itemImage = itemIcon.getImage();
        Image newItemImage = itemImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Adjust size as needed
        itemIcon = new ImageIcon(newItemImage);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel imageLabel = new JLabel(itemIcon);
        JLabel nameLabel = new JLabel("kitchen equpment"); // Ideally, item names would be dynamic based on the item
        JLabel priceLabel = new JLabel("RS 24,000.00"); // Replace with actual price

        itemPanel.add(imageLabel);
        itemPanel.add(nameLabel);
        itemPanel.add(priceLabel);

        return itemPanel;
    }
}
