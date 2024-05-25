package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class LoginPage extends JFrame {
    private static final Color BUTTON_COLOR = new Color(245, 222, 35);
    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;
    private static final Color FIELD_BG_COLOR = new Color(255, 255, 224);
    private static final Color HEADING_TEXT_COLOR = Color.decode("#F1EB4B");
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 24);

    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);

        JLabel heading = new JLabel("Sign in to Modern Nest Furniture", SwingConstants.CENTER);
        heading.setFont(HEADING_FONT);
        heading.setForeground(HEADING_TEXT_COLOR);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(heading);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.setBackground(Color.WHITE);

        JLabel userLabel = new JLabel("Email:");
        userLabel.setFont(LABEL_FONT);
        JTextField userField = new JTextField(20);
        userField.setBackground(FIELD_BG_COLOR);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(LABEL_FONT);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(FIELD_BG_COLOR);

        JButton loginButton = new JButton("Sign In");
        loginButton.setBackground(BUTTON_COLOR);
        loginButton.setForeground(BUTTON_TEXT_COLOR);
        loginButton.setFont(LABEL_FONT);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                AuthStorage storage = new AuthStorage();
                if (storage.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    EventQueue.invokeLater(() -> {
                        HomePage HomePage = new HomePage();
                        HomePage.setVisible(true);
                    });
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        constraints.gridy = 0;
        formPanel.add(userLabel, constraints);

        constraints.gridy++;
        formPanel.add(userField, constraints);

        constraints.gridy++;
        formPanel.add(passwordLabel, constraints);

        constraints.gridy++;
        formPanel.add(passwordField, constraints);

        constraints.gridy++;
        formPanel.add(loginButton, constraints);

        loginPanel.add(formPanel);

        JLabel signUpLink = new JLabel("<html><a href=''>Create an account</a></html>");
        signUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EventQueue.invokeLater(() -> {
                    SignUpPage signUpPage = new SignUpPage();
                    signUpPage.setVisible(true);
                });
                dispose();
            }
        });

        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(signUpLink);

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
        add(loginPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }

    private void openMainPage() {
        System.out.println("Main page opened. Implement this functionality.");
    }

    private static class AuthStorage {
        private final String filePath = "users.txt";

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
            } catch (FileNotFoundException e) {
                return false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginPage frame = new LoginPage();
            frame.setVisible(true);
        });
    }
}
