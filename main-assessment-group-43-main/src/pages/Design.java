package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Design {

    private JFrame frame;
    private JTextField designNameField;
    private JTextField customerNameField;
    private JTextField roomLengthField;
    private JTextField roomWidthField;
    private JPanel drawingArea;

    public Design() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create JFrame for the design page
        frame = new JFrame("Design Room");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1080, 800);
        frame.getContentPane().setBackground(new Color(240, 233, 212));
        frame.setLayout(new BorderLayout());


        // Central Panel
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Design Name field
        designNameField = new JTextField(15);
        centralPanel.add(createLabel("Design Name"), gbc);
        centralPanel.add(designNameField, gbc);

        // Customer Name field
        customerNameField = new JTextField(15);
        centralPanel.add(createLabel("Customer Name"), gbc);
        centralPanel.add(customerNameField, gbc);

        // Room Length field
        roomLengthField = new JTextField(15);
        centralPanel.add(createLabel("Room Length"), gbc);
        centralPanel.add(roomLengthField, gbc);

        // Room Width field
        roomWidthField = new JTextField(15);
        centralPanel.add(createLabel("Room Width"), gbc);
        centralPanel.add(roomWidthField, gbc);



        // Start Designing button
        JButton startDesignButton = new JButton("Start Designing");
        startDesignButton.setBackground(Color.WHITE); // Set button color to #947351
        startDesignButton.setForeground(new Color(135, 86, 59)); // Set button text color to white
        startDesignButton.addActionListener(e -> startDesigning());
        centralPanel.add(startDesignButton, gbc);

        frame.add(centralPanel, BorderLayout.CENTER);

        // Position the window in the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startDesigning() {
        // Dispose the current DesignPage frame
        frame.dispose();

        new RoomDesignPage();


    }

    private void drawRoomShape(Graphics g) {
        String lengthText = roomLengthField.getText();
        String widthText = roomWidthField.getText();
        if (!lengthText.isEmpty() && !widthText.isEmpty()) {
            try {
                int length = Integer.parseInt(lengthText);
                int width = Integer.parseInt(widthText);
                // Now we have the length and width to draw with

                Graphics2D g2d = (Graphics2D) g;
                g2d.clearRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
                g2d.setColor(Color.BLUE); // Set the drawing color
                Rectangle2D roomShape = new Rectangle2D.Double(10, 10, length, width);
                g2d.draw(roomShape);
            } catch (NumberFormatException e) {
                System.err.println("Invalid room length or width format");
            }
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.LEFT);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DesignPage::new);
    }
}