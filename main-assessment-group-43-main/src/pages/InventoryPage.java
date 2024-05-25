package pages;

import components.Furniture;
import components.FurnitureParts;
import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InventoryPage extends JFrame {

    private JPanel dynamicContentPanel;
    private JButton furnitureButton;
    private JButton furniturePartsButton;

    public InventoryPage() {
        setTitle("Inventory Page");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        NavigationBar navigationBar = new NavigationBar(); // Placeholder for your actual navigation bar
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);

        initializeUI();
    }

    private void initializeUI() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        bodyPanel.add(createInventoryPanel());

        dynamicContentPanel = new JPanel(new CardLayout());
        dynamicContentPanel.add(new Furniture(), "Furniture");
        dynamicContentPanel.add(new FurnitureParts(), "FurnitureParts");

        bodyPanel.add(dynamicContentPanel);

        JScrollPane scrollPane = new JScrollPane(bodyPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        showFurniture(); // Show furniture by default
    }

    private JPanel createInventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        furnitureButton = createStyledButton("Furniture");
        furnitureButton.addActionListener(e -> showFurniture());

        furniturePartsButton = createStyledButton("Furniture Parts");
        furniturePartsButton.addActionListener(e -> showFurnitureParts());

        inventoryPanel.add(furnitureButton);
        inventoryPanel.add(furniturePartsButton);

        return inventoryPanel;
    }

    private void showFurniture() {
        furnitureButton.setBackground(Color.BLUE);
        furniturePartsButton.setBackground(null);
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, "Furniture");
    }

    private void showFurnitureParts() {
        furniturePartsButton.setBackground(Color.BLUE);
        furnitureButton.setBackground(null);
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, "FurnitureParts");
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(null); // Default background
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new InventoryPage().setVisible(true));
    }
}
