package components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Furniture extends JPanel {

    public Furniture() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        addFurnitureItems();
    }

    private void addFurnitureItems() {
        // Example data, replace with actual furniture data
        String[][] furnitureData = {
                {"NORDEN Gateleg table", "Inventory ID-033", "PLU - 10867", "4"},
                {"DOUBLE DIVAN SOFA", "Inventory ID-032", "PLU - 10667", "3"},
                {"Dining set MONO", "Inventory ID-012", "PLU - 10237", "15"},
                {"POTTEY flower pot", "Inventory ID-113", "PLU - 04563", "40"}
        };

        String[] imagePaths = {
                "/Image/table1.png",
                "/Image/table2.png",
                "/Image/Lolita.png",
                "/Image/table6.png"
        };

        for (int i = 0; i < furnitureData.length; i++) {
            add(createFurnitureItemPanel(furnitureData[i], imagePaths[i]));
        }
    }

    private JPanel createFurnitureItemPanel(String[] itemData, String imagePath) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        ImageIcon furnitureIcon;
        try {
            // Attempt to load the image
            furnitureIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        } catch (NullPointerException e) {
            // If image loading fails, log an error and use a placeholder
            System.err.println("Failed to load image: " + imagePath);
            furnitureIcon = new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        }

        Image furnitureImage = furnitureIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel furnitureLabel = new JLabel(new ImageIcon(furnitureImage));
        itemPanel.add(furnitureLabel, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel(itemData[0]));
        textPanel.add(new JLabel(itemData[1]));
        textPanel.add(new JLabel(itemData[2]));
        textPanel.add(new JLabel("Available stock: " + itemData[3]));
        itemPanel.add(textPanel, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> itemPanel.setVisible(false)); // Remove itemPanel from view
        itemPanel.add(deleteButton, BorderLayout.EAST);

        return itemPanel;
    }
}
