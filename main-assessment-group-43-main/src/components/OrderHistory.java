package components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JPanel {
    public OrderHistory() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] columns = new String[]{"Customer Name", "Date of Order", "Quantity", "Order Completed Date", "Status"};
        Object[][] data = {
                {"John Doe", "01/03/2024", 2, "15/03/2024", "Completed"},
                {"Jane Smith", "02/03/2024", 1, "16/03/2024", "Canceled"},
                {"Jim Brown", "03/03/2024", 3, "17/03/2024", "Completed"},
                {"John Doe", "01/03/2024", 2, "15/03/2024", "Completed"},
                {"Jane Smith", "02/03/2024", 1, "16/03/2024", "Canceled"},
                {"Jim Brown", "03/03/2024", 3, "17/03/2024", "Completed"},
                {"John Doe", "01/03/2024", 2, "15/03/2024", "Completed"},
                {"Jane Smith", "02/03/2024", 1, "16/03/2024", "Canceled"},
                {"Jim Brown", "03/03/2024", 3, "17/03/2024", "Completed"}
                // ... add more rows as needed
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
