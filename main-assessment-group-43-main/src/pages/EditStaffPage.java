package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class EditStaffPage extends JFrame {
    private StaffPage parentPage;
    private String[] details;

    public EditStaffPage(String[] details, StaffPage parentPage) {
        this.details = details;
        this.parentPage = parentPage;
        setTitle("Edit Staff Member");
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Name:"));
        JTextField nameField = new JTextField(details[0]);
        add(nameField);

        add(new JLabel("Position:"));
        JTextField positionField = new JTextField(details[1]);
        add(positionField);

        add(new JLabel("Email:"));
        JTextField emailField = new JTextField(details[2]);
        add(emailField);

        add(new JLabel("Contact Number:"));
        JTextField contactField = new JTextField(details[3]);
        add(contactField);

        add(new JLabel("Date of Birth:"));
        JTextField dobField = new JTextField(details[4]);
        add(dobField);

        add(new JLabel("Gender:"));
        JTextField genderField = new JTextField(details[5]);
        add(genderField);

        add(new JLabel("Salary:"));
        JTextField salaryField = new JTextField(details[6]);
        add(salaryField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
            saveStaffMember(new String[] {
                    nameField.getText(),
                    positionField.getText(),
                    emailField.getText(),
                    contactField.getText(),
                    dobField.getText(),
                    genderField.getText(),
                    salaryField.getText()
            });
        });
        add(saveButton);
    }

    private void saveStaffMember(String[] newDetails) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("staff.txt", true))) {
            // Write the new details to the end of the file
            for (String detail : newDetails) {
                bw.write(detail + ",");
            }
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parentPage.refreshStaffList();
        this.dispose();
        parentPage.setVisible(true);
    }
}
