package components;

import pages.DesignProductPage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class FurnitureCataloguePage extends JPanel {

    public FurnitureCataloguePage(String category) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        displayItemsForCategory(category);
    }

    private void displayItemsForCategory(String category) {
        removeAll(); // Clear the panel for new items
        add(new JLabel(category + " Items")); // Category title

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 4, 10, 10)); // Assuming 4 columns in the grid
        addCatalogueItems(itemsPanel);

        add(itemsPanel);
        revalidate();
        repaint();
    }

    private void addCatalogueItems(JPanel itemsPanel) {
        // Example image paths, replace with actual paths/names
        String[] imagePaths = {
                "table1.png", "table2.png", "table3.png", "table4.png",
                "table5.png", "table6.png", "table7.png", "table8.png"
        };

        for (String imagePath : imagePaths) {
            itemsPanel.add(createItemPanel("/Image/" + imagePath));
        }
    }

    private JPanel createItemPanel(String imagePath) {
        ImageIcon itemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image itemImage = itemIcon.getImage();
        Image newItemImage = itemImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        itemIcon = new ImageIcon(newItemImage);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel imageLabel = new JLabel(itemIcon);
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Here you would navigate to the DesignProductPage
                openDesignProductPage();
            }
        });
        JLabel nameLabel = new JLabel("Table");
        JLabel priceLabel = new JLabel("Rs 10,000.00");

        itemPanel.add(imageLabel);
        itemPanel.add(nameLabel);
        itemPanel.add(priceLabel);

        return itemPanel;
    }

    private void openDesignProductPage() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DesignProductPage designProductPage = new DesignProductPage();
        designProductPage.setVisible(true);

        // Check if the parent frame is not null before disposing
        if(parentFrame != null) {
            parentFrame.dispose();
        }
    }

}
