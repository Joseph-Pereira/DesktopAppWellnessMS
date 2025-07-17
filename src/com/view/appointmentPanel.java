package view;

import controller.appointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class appointmentPanel extends JPanel {
    private JTextField studentField, dateField, timeField;
    private JComboBox<String> counselorCombo;
    private JComboBox<String> statusCombo;
    private JTable table;
    private DefaultTableModel tableModel;

    private final appointmentController controller = new appointmentController();

    public appointmentPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Manage Appointments", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.decode("#2C3E50"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Center layout with form + table + buttons
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.decode("#F5F7FA"));

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F5F7FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        studentField = styledTextField("e.g. John Doe");
        counselorCombo = new JComboBox<>(new String[]{"Dr. Smith", "Dr. Jane"});
        dateField = styledTextField("YYYY-MM-DD");
        timeField = styledTextField("HH:MM");
        statusCombo = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled"});

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Student:"), gbc);
        gbc.gridx = 1; formPanel.add(studentField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Counselor:"), gbc);
        gbc.gridx = 1; formPanel.add(counselorCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; formPanel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Time (HH:MM):"), gbc);
        gbc.gridx = 1; formPanel.add(timeField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; formPanel.add(statusCombo, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = styledButton("Add");
        JButton updateBtn = styledButton("Update");
        JButton deleteBtn = styledButton("Delete");
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Table
        String[] columns = {"Student", "Counselor", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(22);
        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Listeners
        addBtn.addActionListener(e -> addAppointment());
        updateBtn.addActionListener(e -> updateAppointment());
        deleteBtn.addActionListener(e -> deleteAppointment());
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

    private void addAppointment() {
        String student = studentField.getText().trim();
        String counselor = (String) counselorCombo.getSelectedItem();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String status = (String) statusCombo.getSelectedItem();

        if (!controller.validateAppointment(student, counselor, date, time)) return;

        controller.addAppointment(student, counselor, date, time, status);
        tableModel.addRow(new Object[]{student, counselor, date, time, status});
        clearFields();
    }

    private void updateAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment to update.");
            return;
        }

        String student = studentField.getText().trim();
        String counselor = (String) counselorCombo.getSelectedItem();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String status = (String) statusCombo.getSelectedItem();

        if (!controller.validateAppointment(student, counselor, date, time)) return;

        controller.updateAppointment(row, student, counselor, date, time, status);
        tableModel.setValueAt(student, row, 0);
        tableModel.setValueAt(counselor, row, 1);
        tableModel.setValueAt(date, row, 2);
        tableModel.setValueAt(time, row, 3);
        tableModel.setValueAt(status, row, 4);
        clearFields();
    }

    private void deleteAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this appointment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteAppointment(row);
            tableModel.removeRow(row);
            clearFields();
        }
    }

    private void populateFields() {
        int row = table.getSelectedRow();
        if (row != -1) {
            studentField.setText((String) tableModel.getValueAt(row, 0));
            counselorCombo.setSelectedItem(tableModel.getValueAt(row, 1));
            dateField.setText((String) tableModel.getValueAt(row, 2));
            timeField.setText((String) tableModel.getValueAt(row, 3));
            statusCombo.setSelectedItem(tableModel.getValueAt(row, 4));
        }
    }

    private void clearFields() {
        studentField.setText("");
        dateField.setText("");
        timeField.setText("");
        table.clearSelection();
    }
}
