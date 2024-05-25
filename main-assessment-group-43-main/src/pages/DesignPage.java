package pages;

import components.FurnitureCataloguePage;
import components.KitchenPage;
import components.NavigationBar;

import javax.swing.*;
import java.awt.*;

public class DesignPage extends JFrame {

    private JPanel dynamicContentPanel;

    public DesignPage() {
        setTitle("Design Page");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        NavigationBar navigationBar = new NavigationBar();
        getContentPane().add(navigationBar, BorderLayout.NORTH);

        initializeUI();
    }

    private void initializeUI() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

        bodyPanel.add(createTopTextPanel());
        bodyPanel.add(createRoomDesignButton());
        bodyPanel.add(createFurnitureLabelAndUserDesignButtonPanel());
        bodyPanel.add(new JSeparator());
        bodyPanel.add(createRoomsTitlePanel());
        bodyPanel.add(createFurniturePanel());

        dynamicContentPanel = new JPanel(new CardLayout());
        dynamicContentPanel.add(new FurnitureCataloguePage("Bedroom"), "FurnitureCatalogue");
        dynamicContentPanel.add(new KitchenPage(), "Kitchen");

        bodyPanel.add(dynamicContentPanel);

        JScrollPane scrollPane = new JScrollPane(bodyPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        showFurnitureCatalogue(); // Show furniture catalogue by default
    }

    private JPanel createTopTextPanel() {
        JTextArea topText = new JTextArea("Start with a blank slate\nCreate your own room designs. " +
                "Most spaces are about 15 ft wide and all products are to-scale.");
        topText.setEditable(false);
        topText.setOpaque(false);
        topText.setWrapStyleWord(true);
        topText.setLineWrap(true);
        topText.setFont(new Font("Arial", Font.PLAIN, 14));
        topText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(topText);
        return textPanel;
    }

    private JButton createRoomDesignButton() {
        ImageIcon originalIcon = new ImageIcon("src/Image/img.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton roomDesignButton = new JButton(new ImageIcon(scaledImage));
        roomDesignButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        roomDesignButton.setContentAreaFilled(false);
        roomDesignButton.addActionListener(e -> openRoomDesignPage());
        roomDesignButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return roomDesignButton;
    }

    private void openRoomDesignPage() {
        Design design = new Design();
        setVisible(true);
        this.dispose();
    }

    private JPanel createFurnitureLabelAndUserDesignButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(Box.createVerticalStrut(10), BorderLayout.NORTH);

        JLabel furnitureLabel = new JLabel("Furniture");
        panel.add(furnitureLabel, BorderLayout.WEST);

        JButton usersDesignsButton = new JButton("User's Designs");
        usersDesignsButton.addActionListener(e -> openUserDesignPage());
        panel.add(usersDesignsButton, BorderLayout.EAST);

        panel.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
        furnitureLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        usersDesignsButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        return panel;
    }

    private void openUserDesignPage() {
        UserDesignPage userDesignPage = new UserDesignPage(); // Assuming UserDesignPage is a class that extends JFrame
        userDesignPage.setVisible(true);
        this.dispose();
    }

    private JPanel createRoomsTitlePanel() {
        JPanel roomsTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel roomsLabel = new JLabel("ROOMS");
        roomsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomsTitlePanel.add(roomsLabel);
        return roomsTitlePanel;
    }

    private JPanel createFurniturePanel() {
        JPanel furniturePanel = new JPanel();
        furniturePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Adjust spacing as needed

        // Create a sub-panel for each category to align the image and text vertically
        JPanel bedroomPanel = createCategoryButtonWithLabel("src/Image/Bedroom.jpg", "FurnitureCatalogue", "Bedroom");
        JPanel kitchenPanel = createCategoryButtonWithLabel("src/Image/Kitchen.jpeg", "Kitchen", "Kitchen");

        // Add the sub-panels to the main furniture panel
        furniturePanel.add(bedroomPanel);
        furniturePanel.add(kitchenPanel);

        return furniturePanel;
    }

    private JPanel createCategoryButtonWithLabel(String iconPath, String category, String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical alignment of button and label

        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH); // Adjust size as needed
        JButton button = new JButton(new ImageIcon(image));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.addActionListener(e -> switchToFurnitureCatalogue(category));

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the label under the button

        panel.add(button);
        panel.add(label);

        return panel;
    }


    private void switchToFurnitureCatalogue(String category) {
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, category);
    }

    private void showFurnitureCatalogue() {
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, "FurnitureCatalogue");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new DesignPage().setVisible(true));
    }
}
