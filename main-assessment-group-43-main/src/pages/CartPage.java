package pages;

import components.NavigationBar;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends JFrame {

    private final List<String> cartItems = new ArrayList<>();
    private final JPanel itemsPanel;

    public CartPage() {
        setTitle("Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        NavigationBar navigationBar = new NavigationBar();
        topPanel.add(navigationBar, BorderLayout.NORTH);

        // Adding a text field for the customer name
        JTextField customerNameField = new JTextField();
        customerNameField.setBorder(BorderFactory.createTitledBorder("Customer Name"));
        topPanel.add(customerNameField, BorderLayout.SOUTH);

        contentPane.add(topPanel, BorderLayout.NORTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        loadCartItems();
        refreshItemsPanel();

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            dispose();

        });
        bottomPanel.add(placeOrderButton);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadCartItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cartItems.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshItemsPanel() {
        itemsPanel.removeAll(); // Clear the panel

        for (String item : cartItems) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            String[] parts = item.split(" \\| ");
            JLabel nameLabel = new JLabel(parts[0]);
            ImageIcon icon = new ImageIcon(parts[1]);
            icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel iconLabel = new JLabel(icon);
            JLabel quantityLabel = new JLabel(parts[2]);

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> deleteItem(item));

            itemPanel.add(iconLabel);
            itemPanel.add(nameLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(deleteButton);

            itemsPanel.add(itemPanel);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void deleteItem(String item) {
        cartItems.remove(item);
        saveCartItems();
        refreshItemsPanel();
    }

    private void saveCartItems() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
            for (String item : cartItems) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new CartPage().setVisible(true));
    }
}
