package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class SignUpPage extends JFrame {
    private static final Color BUTTON_COLOR = new Color(245, 222, 35); // A gold-like color
    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;
    private static final Color FIELD_BG_COLOR = new Color(255, 255, 224); // Light yellow color
    private static final Color LINK_COLOR = Color.BLUE;
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font LINK_FONT = new Font("Arial", Font.BOLD, 14);

    public SignUpPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Sign Up Panel on the RIGHT
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBackground(Color.WHITE);

        JLabel heading = new JLabel("Create your Modern Nest Account", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(0xF1EB4B)); // Yellow color
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        signUpPanel.add(heading);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(LABEL_FONT);
        JTextField usernameField = new JTextField(20);
        usernameField.setBackground(FIELD_BG_COLOR);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(LABEL_FONT);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(FIELD_BG_COLOR);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(LABEL_FONT);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBackground(FIELD_BG_COLOR);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(BUTTON_COLOR);
        signUpButton.setForeground(BUTTON_TEXT_COLOR);
        signUpButton.setFont(LABEL_FONT);

        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(SignUpPage.this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AuthStorage storage = new AuthStorage();
            if (storage.registerUser(username, password)) {
                JOptionPane.showMessageDialog(SignUpPage.this, "User registered successfully", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                EventQueue.invokeLater(() -> {
                    LoginPage LoginPage = new LoginPage();
                    LoginPage.setVisible(true);
                });
                dispose();
            } else {
                JOptionPane.showMessageDialog(SignUpPage.this, "Failed to register user", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel loginLink = new JLabel("Already have an account? Login");
        loginLink.setForeground(LINK_COLOR);
        loginLink.setFont(LINK_FONT);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EventQueue.invokeLater(() -> {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                });
                dispose();
            }
        });

        // Adding components to formPanel
        constraints.gridy = 0;
        formPanel.add(usernameLabel, constraints);

        constraints.gridy++;
        formPanel.add(usernameField, constraints);

        constraints.gridy++;
        formPanel.add(passwordLabel, constraints);

        constraints.gridy++;
        formPanel.add(passwordField, constraints);

        constraints.gridy++;
        formPanel.add(confirmPasswordLabel, constraints);

        constraints.gridy++;
        formPanel.add(confirmPasswordField, constraints);

        constraints.gridy++;
        formPanel.add(signUpButton, constraints);

        // Adding formPanel and login link to signUpPanel
        signUpPanel.add(formPanel);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        signUpPanel.add(loginLink);

        // Image panel on the LEFT
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/Image/Rectangle 4.png");
                g.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(600, 600));
        imagePanel.setBackground(Color.WHITE);

        add(imagePanel, BorderLayout.WEST);
        add(signUpPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }

    private static class AuthStorage {
        private final String filePath = "users.txt"; // Adjust path as necessary

        public boolean registerUser(String username, String password) {
            try (FileWriter fw = new FileWriter(filePath, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(username + ":" + password);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        public boolean authenticate(String username, String password) {
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    String[] credentials = scanner.nextLine().split(":");
                    if (credentials[0].equals(username) && credentials[1].equals(password)) {
                        return true;
                    }
                }
            } catch ( FileNotFoundException e) {
                return false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SignUpPage frame = new SignUpPage();
            frame.setVisible(true);
        });
    }
}
