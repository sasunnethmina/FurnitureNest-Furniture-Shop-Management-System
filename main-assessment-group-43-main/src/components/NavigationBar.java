package components;

import pages.CartPage;
import pages.DesignPage;
import pages.HomePage;
import pages.InventoryPage;
import pages.OrderPage;
import pages.HelpTutorialsPage;
import pages.StaffPage;
import pages.Room;

import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
    public NavigationBar() {
        NavigationBarSetup();
    }

    private void NavigationBarSetup() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FCEDD6"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon("src/Image/logo.jpg");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        JButton homeButton = new JButton("Home");
        JButton roomButton = new JButton("Room");
        JButton designButton = new JButton("Design");
        JButton inventoryButton = new JButton("Inventory");
        JButton orderButton = new JButton("Orders");
        JButton helpButton = new JButton("Help");
        JButton staffButton = new JButton("Staff");
        JButton cartButton = createCartButton();

        configureButton(homeButton);
        configureButton(roomButton);
        configureButton(designButton);
        configureButton(inventoryButton);
        configureButton(orderButton);
        configureButton(helpButton);
        configureButton(staffButton);

        buttonPanel.add(homeButton);
        buttonPanel.add(roomButton);
        buttonPanel.add(designButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(staffButton);

        JPopupMenu roomMenu = new JPopupMenu();
        JMenuItem livingRoomMenuItem = new JMenuItem("Dining Room");
        livingRoomMenuItem.addActionListener(e -> {

            openRoomPage();
        });
        roomMenu.add(livingRoomMenuItem);

        roomMenu.add(livingRoomMenuItem);
        roomMenu.add(new JMenuItem("Bedroom"));
        roomMenu.add(new JMenuItem("Kitchen"));
        roomMenu.add(new JMenuItem("Bathroom"));
        roomButton.addActionListener(e -> roomMenu.show(roomButton, 0, roomButton.getHeight()));

        homeButton.addActionListener(e -> openHomePage());
        designButton.addActionListener(e -> openDesignPage());
        inventoryButton.addActionListener(e -> openInventoryPage());
        orderButton.addActionListener(e -> openOrderPage());
        helpButton.addActionListener(e -> openHelpTutorialsPage());
        staffButton.addActionListener(e -> openStaffPage());

        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(cartButton, BorderLayout.EAST);
        eastPanel.setOpaque(false);

        add(buttonPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
    }

    private JButton createCartButton() {
        ImageIcon cartIcon = new ImageIcon("src/Image/cart.png");
        Image img = cartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        cartIcon = new ImageIcon(img);

        JButton cartButton = new JButton(cartIcon);
        cartButton.setBorder(BorderFactory.createEmptyBorder());
        cartButton.setContentAreaFilled(false);
        cartButton.addActionListener(e -> openCartPage());
        return cartButton;
    }

    private void configureButton(JButton button) {
        button.setBackground(Color.decode("#FCEDD6"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorderPainted(false);
    }

    private void openHomePage() {
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
        closeCurrentFrame();
    }

    private void openRoomPage() {

        Room roomPage = new Room();

        roomPage.setRoomType("Dining Room");
        roomPage.setVisible(true);

        closeCurrentFrame();
    }


    private void openDesignPage() {
        DesignPage designPage = new DesignPage();
        designPage.setVisible(true);
        closeCurrentFrame();
    }

    private void openInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.setVisible(true);
        closeCurrentFrame();
    }

    private void openOrderPage() {
        OrderPage orderPage = new OrderPage();
        orderPage.setVisible(true);
        closeCurrentFrame();
    }

    private void openHelpTutorialsPage() {
        HelpTutorialsPage helpTutorialsPage = new HelpTutorialsPage();
        helpTutorialsPage.setVisible(true);
        closeCurrentFrame();
    }

    private void openStaffPage() {
        StaffPage staffPage = new StaffPage();
        staffPage.setVisible(true);
        closeCurrentFrame();
    }

    private void openCartPage() {
        CartPage cartPage = new CartPage();
        cartPage.setVisible(true);
        closeCurrentFrame();
    }

    private void closeCurrentFrame() {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
