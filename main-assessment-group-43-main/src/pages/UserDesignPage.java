package pages;

import components.NavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDesignPage extends JFrame {

    private final List<String[]> userData = new ArrayList<>();
    private final Container contentPane; // Declare contentPane as a class member variable

    public UserDesignPage() {
        setTitle("User Design Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 800);

        contentPane = getContentPane(); // Initialize contentPane
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        NavigationBar navigationBar = new NavigationBar();
        contentPane.add(navigationBar, BorderLayout.NORTH);

        // Load user data
        loadUserData();

        // Create table
        String[] columnNames = {"Product Name", "Product Color", "Product Size", "Delete"};
        Object[][] rowData = new Object[userData.size()][columnNames.length];
        for (int i = 0; i < userData.size(); i++) {
            rowData[i] = userData.get(i);
        }
        JTable table = new JTable(rowData, columnNames);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), table)); // Pass the JTable instance
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Add back button under navigation bar
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DesignPage().setVisible(true);
            }
        });
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        contentPane.add(topPanel, BorderLayout.NORTH);
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userdesign.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    // Add a "Delete" button placeholder
                    String[] row = {parts[0], parts[1], parts[2], "Delete"};
                    userData.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new UserDesignPage().setVisible(true));
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private JTable table; // Reference to the JTable
    private String label;
    private boolean isPushed;
    private int currentRow;

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table; // Initialize the reference to the JTable
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        currentRow = row;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // Button has been clicked
            // Delete the row from the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(currentRow);
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
