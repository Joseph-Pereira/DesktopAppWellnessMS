package view;

import controller.counselorController;
import model.Counselor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class counselorPanel extends JPanel {
    private JTextField nameField, surnameField, specField, availField;
    private JTable table;
    private DefaultTableModel tableModel;

    private final counselorController controller = new counselorController();

    public counselorPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Manage Counselors", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.decode("#2C3E50"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.decode("#F5F7FA"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F5F7FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = styledTextField("e.g. Jane");
        surnameField = styledTextField("e.g. Doe");
        specField = styledTextField("e.g. Psychology");
        availField = styledTextField("e.g. Mon–Fri 09:00–16:00");

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Surname:"), gbc);
        gbc.gridx = 1; formPanel.add(surnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1; formPanel.add(specField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Availability:"), gbc);
        gbc.gridx = 1; formPanel.add(availField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = styledButton("Add");
        JButton updateBtn = styledButton("Update");
        JButton deleteBtn = styledButton("Delete");
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Table
        String[] columns = {"Name", "Surname", "Specialization", "Availability"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // Events
        addBtn.addActionListener(e -> addCounselor());
        updateBtn.addActionListener(e -> updateCounselor());
        deleteBtn.addActionListener(e -> deleteCounselor());
        table.getSelectionModel().addListSelectionListener(e -> populateFields());
    }

    private JTextField styledTextField(String placeholder) {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        tf.setToolTipText(placeholder);
        return tf;
    }

    private JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void addCounselor() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String spec = specField.getText().trim();
        String avail = availField.getText().trim();

        if (!controller.validate(name, surname, spec, avail)) return;

        controller.add(name, surname, spec, avail);
        tableModel.addRow(new Object[]{name, surname, spec, avail});
        clearFields();
    }

    private void updateCounselor() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a counselor to update.");
            return;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String spec = specField.getText().trim();
        String avail = availField.getText().trim();

        if (!controller.validate(name, surname, spec, avail)) return;

        controller.update(row, name, surname, spec, avail);
        tableModel.setValueAt(name, row, 0);
        tableModel.setValueAt(surname, row, 1);
        tableModel.setValueAt(spec, row, 2);
        tableModel.setValueAt(avail, row, 3);
        clearFields();
    }

    private void deleteCounselor() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a counselor to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.delete(row);
            tableModel.removeRow(row);
            clearFields();
        }
    }

    private void populateFields() {
        int row = table.getSelectedRow();
        if (row != -1) {
            nameField.setText((String) tableModel.getValueAt(row, 0));
            surnameField.setText((String) tableModel.getValueAt(row, 1));
            specField.setText((String) tableModel.getValueAt(row, 2));
            availField.setText((String) tableModel.getValueAt(row, 3));
        }
    }

    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        specField.setText("");
        availField.setText("");
        table.clearSelection();
    }
}
