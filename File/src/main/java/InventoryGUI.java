import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InventoryGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public InventoryGUI() {
        frame = new JFrame("Inventory Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        tableModel = new DefaultTableModel(new String[]{"Name", "SKU", "Quantity", "Price", "Tags"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");
        JButton editButton = new JButton("Edit Product");
        JButton searchButton = new JButton("Search");
        JButton loadFileButton = new JButton("Load from File");

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(editButton);
        panel.add(searchButton);
        panel.add(loadFileButton);
        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addProductDialog());

        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                InventoryManager.inventory.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                InventoryManagerMethods.updateFile();
                JOptionPane.showMessageDialog(frame, "Product removed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to remove.");
            }
        });

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Product p = InventoryManager.inventory.get(selectedRow);
                String name = JOptionPane.showInputDialog("Enter new name:", p.name);
                String sku = JOptionPane.showInputDialog("Enter new SKU:", p.sku);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter new quantity:", p.quantity));
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter new price:", p.price));
                String tags = JOptionPane.showInputDialog("Enter new tags (comma-separated):", String.join(",", p.tags));

                p.name = name;
                p.sku = sku;
                p.quantity = quantity;
                p.price = price;
                p.tags.clear();
                for (String tag : tags.split(",")) {
                    p.tags.add(tag.trim());
                }

                refreshTable();
                InventoryManagerMethods.updateFile();
                JOptionPane.showMessageDialog(frame, "Product updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.");
            }
        });

        searchButton.addActionListener((ActionEvent e) -> {
            String keyword = JOptionPane.showInputDialog("Enter keyword to search:");
            ArrayList<Product> results = new ArrayList<>();
            for (Product p : InventoryManager.inventory) {
                if (p.name.toLowerCase().contains(keyword.toLowerCase()) ||
                        p.sku.toLowerCase().contains(keyword.toLowerCase()) ||
                        p.tags.stream().anyMatch(tag -> tag.toLowerCase().contains(keyword.toLowerCase()))) {
                    results.add(p);
                }
            }
            showSearchResults(results);
        });

        loadFileButton.addActionListener(e -> {
            InventoryManager.addProductFromFile();
            refreshTable();
        });

        InventoryManager.loadInventoryFromFile();
        refreshTable();
        frame.setVisible(true);
    }

    private void showSearchResults(ArrayList<Product> results) {
        tableModel.setRowCount(0);
        for (Product p : results) {
            tableModel.addRow(new Object[]{p.name, p.sku, p.quantity, p.price, String.join(", ", p.tags)});
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product p : InventoryManager.inventory) {
            tableModel.addRow(new Object[]{p.name, p.sku, p.quantity, p.price, String.join(", ", p.tags)});
        }
    }

    private void addProductDialog() {
        String name = JOptionPane.showInputDialog("Enter product name:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
        String sku = JOptionPane.showInputDialog("Enter SKU:");
        String tagInput = JOptionPane.showInputDialog("Enter tags (comma-separated):");
        ArrayList<String> tags = new ArrayList<>();
        for (String tag : tagInput.split(",")) {
            tags.add(tag.trim());
        }

        Product product = new Product(name, quantity, price, sku, tags);
        InventoryManager.inventory.add(product);
        tableModel.addRow(new Object[]{name, sku, quantity, price, String.join(", ", tags)});
        InventoryManagerMethods.updateFile();
        JOptionPane.showMessageDialog(frame, "Product added successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryGUI::new);
    }
}
