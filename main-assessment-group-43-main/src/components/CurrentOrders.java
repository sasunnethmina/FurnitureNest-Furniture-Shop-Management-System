package components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CurrentOrders extends JPanel {
    public CurrentOrders() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] columns = new String[]{"Customer Name", "Date of Order", "Quantity", "Order Completion Date"};
        Object[][] data = {
                {"John Doe", "01/04/2024", 2, "15/04/2024"},
                {"Jane Smith", "02/04/2024", 1, "16/04/2024"},
                {"Jim Brown", "03/04/2024", 3, "17/04/2024"},
                {"John Doe", "01/04/2024", 2, "15/04/2024"},
                {"Jane Smith", "02/04/2024", 1, "16/04/2024"},
                {"Jim Brown", "03/04/2024", 3, "17/04/2024"},
                {"sasun nethmina", "13/04/2024", 31, "27/04/2024"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
