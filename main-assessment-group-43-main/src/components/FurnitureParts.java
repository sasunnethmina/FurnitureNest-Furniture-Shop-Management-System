package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class FurnitureParts extends JPanel {
    private JTable table;
    private JButton toggleEditSaveButton;
    private DefaultTableModel tableModel;
    private String filePath = "FurnitureParts.txt";
    private String[] columnNames = {"Part ID", "Furniture Part", "Material", "Availability", "Quantity"};

    public FurnitureParts() {
        setLayout(new BorderLayout());
        initializeComponents();
        loadFromFile();
    }

    private void initializeComponents() {

        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return toggleEditSaveButton.getText().equals("Save");
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        // Edit/Save button
        toggleEditSaveButton = new JButton("Edit");
        toggleEditSaveButton.addActionListener(this::toggleEditSaveAction);
        toggleEditSaveButton.setBorder(new EmptyBorder(0, 10, 20, 10));
        add(toggleEditSaveButton, BorderLayout.SOUTH);
    }

    private void toggleEditSaveAction(ActionEvent e) {
        if ("Edit".equals(toggleEditSaveButton.getText())) {
            toggleEditSaveButton.setText("Save");
            tableModel.fireTableStructureChanged(); // Refresh the table view
        } else {
            toggleEditSaveButton.setText("Edit");
            saveChanges();
        }
    }

    private void saveChanges() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    out.print(tableModel.getValueAt(row, col));
                    out.print(col < tableModel.getColumnCount() - 1 ? "," : "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to file.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    tableModel.addRow(data);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading data from file.",
                        "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
