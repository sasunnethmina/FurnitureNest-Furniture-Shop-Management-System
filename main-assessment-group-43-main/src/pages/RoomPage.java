package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import components.NavigationBar;

public class RoomPage extends JFrame {
    private JLabel lengthLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel colorLabel;

    public RoomPage(double length, double height, double width, String color) {
        setTitle("Room Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 900); // Set the size of the window

        // Create a panel with GridLayout for the room details
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Add gaps between components
        detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add some padding

        lengthLabel = new JLabel(length + " m");
        widthLabel = new JLabel(width + " m");
        heightLabel = new JLabel(height + " m");
        colorLabel = new JLabel("Color: " + color);

        detailsPanel.add(new JLabel("Room Length:"));
        detailsPanel.add(lengthLabel);
        detailsPanel.add(new JLabel("Room Width:"));
        detailsPanel.add(widthLabel);
        detailsPanel.add(new JLabel("Room Height:"));
        detailsPanel.add(heightLabel);
        detailsPanel.add(new JLabel("Room Color:"));
        detailsPanel.add(colorLabel);

        // Create and add the navigation bar
        NavigationBar navigationBar = new NavigationBar();
        navigationBar.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Set the layout and add components to the content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(navigationBar, BorderLayout.NORTH);
        getContentPane().add(detailsPanel, BorderLayout.CENTER);
    }

    // A static method to display room details
    public static void showRoomDetails(double length, double height, double width, String color) {
        EventQueue.invokeLater(() -> {
            RoomPage roomPage = new RoomPage(length, height, width, color);
            roomPage.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Test RoomPage
        RoomPage.showRoomDetails(5.0, 3.0, 4.0, "Red");
    }
}
