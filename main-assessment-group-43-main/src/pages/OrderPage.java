package pages;

import components.CurrentOrders;
import components.OrderHistory;
import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OrderPage extends JFrame {

    private JPanel dynamicContentPanel;
    private JButton currentOrdersButton;
    private JButton orderHistoryButton;

    public OrderPage() {
        setTitle("Orders Page");
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

        bodyPanel.add(createOrdersPanel());

        dynamicContentPanel = new JPanel(new CardLayout());
        dynamicContentPanel.add(new CurrentOrders(), "CurrentOrders");
        dynamicContentPanel.add(new OrderHistory(), "OrderHistory");

        bodyPanel.add(dynamicContentPanel);

        JScrollPane scrollPane = new JScrollPane(bodyPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        showCurrentOrders(); // Show current orders by default
    }

    private JPanel createOrdersPanel() {
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        currentOrdersButton = createStyledButton("Current Orders");
        currentOrdersButton.addActionListener(e -> showCurrentOrders());

        orderHistoryButton = createStyledButton("Order History");
        orderHistoryButton.addActionListener(e -> showOrderHistory());

        ordersPanel.add(currentOrdersButton);
        ordersPanel.add(orderHistoryButton);

        return ordersPanel;
    }

    private void showCurrentOrders() {
        currentOrdersButton.setBackground(Color.GREEN);
        orderHistoryButton.setBackground(Color.WHITE);
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, "CurrentOrders");
    }

    private void showOrderHistory() {
        orderHistoryButton.setBackground(Color.GREEN);
        currentOrdersButton.setBackground(Color.WHITE);
        CardLayout cl = (CardLayout) (dynamicContentPanel.getLayout());
        cl.show(dynamicContentPanel, "OrderHistory");
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE); // Default background
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new OrderPage().setVisible(true));
    }
}
