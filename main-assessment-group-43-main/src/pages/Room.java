package pages;

import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Room extends JFrame {
    private JPanel mainPanel;
    private JLabel textLabel;
    private JLabel imageLabel;
    private JPanel innerProductPanel;

    public Room() {
        // Initialize components
        initComponents();
    }

    private void initComponents() {
        // Set layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 900); // Adjust size as needed
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);


        NavigationBar navigationBar = new NavigationBar();

        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(navigationBar, BorderLayout.NORTH);
        // Create a JPanel to hold the image and text
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS)); // Vertical alignment

        // Create a JLabel for the text with increased font size
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment

        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add text and image labels to the image panel
        imagePanel.add(Box.createVerticalStrut(10));
        imagePanel.add(textLabel);
        imagePanel.add(Box.createVerticalStrut(10));
        imagePanel.add(imageLabel);

        // Add image panel to the main panel
        mainPanel.add(imagePanel);

        // Panel for products (dynamically populated based on room type)
        innerProductPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerProductPanel.setBackground(Color.WHITE);

        // Add product panel to the main panel
        mainPanel.add(innerProductPanel);

        add(mainPanel);
        setVisible(true);
    }

    public void setRoomType(String roomType) {

        setTitle(roomType);
        textLabel.setText("<html><h1>Welcome to " + roomType + "!<h1></html>");


        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/room.jpg"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledImageIcon);


        innerProductPanel.removeAll();
        innerProductPanel.add(createroomPanel("/Image/Diningset1.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset2.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset1.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset3.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset1.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset2.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset1.png", 250, 200));
        innerProductPanel.add(createroomPanel("/Image/Diningset3.png", 250, 200));



        innerProductPanel.revalidate();
        innerProductPanel.repaint();
    }

    private JPanel createroomPanel(String imagePath, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JButton productButton = new JButton();

        ImageIcon productImageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = productImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledProductImageIcon = new ImageIcon(image);
        JLabel productImageLabel = new JLabel(scaledProductImageIcon);
        productButton.add(productImageLabel);

        panel.add(productButton, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Room room = new Room();
            room.setRoomType("Living Room");
        });
    }
}
