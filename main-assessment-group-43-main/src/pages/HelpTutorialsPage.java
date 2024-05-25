package pages;

import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HelpTutorialsPage extends JFrame {
    public HelpTutorialsPage() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Help and Tutorials - Modern Nest");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 800);

        NavigationBar navigationBar = new NavigationBar();
        setLayout(new BorderLayout());
        add(navigationBar, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
       // contentPanel.setBackground(Color.WHITE);


        JPanel faqPanel = new JPanel();
        faqPanel.setLayout(new BoxLayout(faqPanel, BoxLayout.Y_AXIS));
        faqPanel.setBorder(new EmptyBorder(60, 20, 0, 0));
        JLabel faqTitle = new JLabel("Frequently Asked Questions (FAQ)");
        faqTitle.setFont(new Font("Arial", Font.BOLD, 20));
        faqPanel.add(faqTitle);

// Example FAQ questions and answers
        String[][] faqs = {
                {"How do I place an order?", "You can place an order through our website or by contacting our sales team."},
                {"What is your return policy?", "We offer a 30-day return policy on all orders."},
                {"Do you offer customization?", "Yes, we offer customization options for many of our furniture pieces."}
        };

        for (String[] faq : faqs) {
            JLabel question = new JLabel(faq[0]);
            question.setFont(new Font("Arial", Font.PLAIN, 16));
            faqPanel.add(question);

            JLabel answer = new JLabel("<html><i>" + faq[1] + "</i></html>");
            answer.setFont(new Font("Arial", Font.ITALIC, 14));
            faqPanel.add(answer);
        }

        contentPanel.add(faqPanel);

// Contact Section
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBorder(new EmptyBorder(30, 20, 0, 0));
        JLabel contactTitle = new JLabel("Contact Us");
        contactTitle.setFont(new Font("Arial", Font.BOLD, 20));
        contactPanel.add(contactTitle);

// Example contact information
        JLabel emailLabel = new JLabel("Email: contact@modernnestfurniture.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contactPanel.add(emailLabel);

        JLabel phoneLabel = new JLabel("Phone: +1 (555) 123-4567");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        contactPanel.add(phoneLabel);

        contentPanel.add(contactPanel);

// Tutorials Section
        JPanel tutorialsPanel = new JPanel();
        tutorialsPanel.setLayout(new BoxLayout(tutorialsPanel, BoxLayout.Y_AXIS));
        tutorialsPanel.setBorder(new EmptyBorder(30, 20, 0, 0));
        JLabel tutorialsTitle = new JLabel("Tutorials");
        tutorialsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        tutorialsPanel.add(tutorialsTitle);

// Example tutorials
        String[] tutorials = {
                "Guide to Choosing the Right Sofa",
                "How to Maintain Solid Wood Furniture",
                "5 Tips for Arranging Your Living Room"
        };

        for (String tutorial : tutorials) {
            JLabel tutorialLabel = new JLabel(tutorial);
            tutorialLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            tutorialsPanel.add(tutorialLabel);
        }

        contentPanel.add(tutorialsPanel);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HelpTutorialsPage().setVisible(true));
    }
}
