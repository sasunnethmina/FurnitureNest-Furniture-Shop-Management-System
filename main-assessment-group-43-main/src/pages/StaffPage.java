package pages;

import components.NavigationBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffPage extends JFrame {
    private final List<JPanel> employeePanels;
    private final JPanel contentPanel;
    private final File staffFile;

    public StaffPage() {
        setTitle("Staff");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        NavigationBar navigationBar = new NavigationBar();
        add(navigationBar, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        staffFile = new File("staff.txt");
        employeePanels = new ArrayList<>();
        loadStaffMembers();
    }

    private void loadStaffMembers() {
        try (BufferedReader br = new BufferedReader(new FileReader(staffFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                addEmployeePanel(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEmployeePanel(String[] details) {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        employeePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel("Manager: " + details[0]);
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        editButton.addActionListener(e -> openEditStaffPage(details));
        deleteButton.addActionListener(e -> deleteStaffMember(employeePanel, details[0]));

        employeePanel.add(nameLabel);
        employeePanel.add(editButton);
        employeePanel.add(deleteButton);

        employeePanels.add(employeePanel);
        contentPanel.add(employeePanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void openEditStaffPage(String[] details) {
        EditStaffPage editPage = new EditStaffPage(details, this);
        editPage.setVisible(true);
        this.setVisible(false);
    }

    private void deleteStaffMember(JPanel panel, String name) {
        contentPanel.remove(panel);
        employeePanels.remove(panel);
        saveStaffMembersToFile();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void saveStaffMembersToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(staffFile, false))) {
            for (JPanel panel : employeePanels) {
                JLabel label = (JLabel) panel.getComponent(0); // Assumes first component is the JLabel
                String name = label.getText().replace("Manager: ", "");
                out.println(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshStaffList() {
        contentPanel.removeAll();
        employeePanels.clear();
        loadStaffMembers();
    }
}
