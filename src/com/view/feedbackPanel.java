package view;

import controller.feedbackController;
import model.Feedback;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class feedbackPanel extends JPanel {
    private JTextField studentField;
    private JComboBox<Integer> ratingCombo;
    private JTextArea commentArea;
    private JTable table;
    private DefaultTableModel tableModel;

    private final feedbackController controller = new feedbackController();

    public feedbackPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Manage Feedback", SwingConstants.CENTER);
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

        studentField = styledTextField("e.g. John Doe");
        ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        commentArea = new JTextArea(4, 20);
        commentArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Student:"), gbc);
        gbc.gridx = 1; formPanel.add(studentField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Rating (1–5):"), gbc);
        gbc.gridx = 1; formPanel.add(ratingCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Comments:"), gbc);
        gbc.gridx = 1; formPanel.add(new JScrollPane(commentArea), gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = styledButton("Add");
        JButton updateBtn = styledButton("Update");
        JButton deleteBtn = styledButton("Delete");
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Table
        String[] columns = {"Student", "Rating", "Comments"};
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
        addBtn.addActionListener(e -> addFeedback());
        updateBtn.addActionListener(e -> updateFeedback());
        deleteBtn.addActionListener(e -> deleteFeedback());
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

    private void addFeedback() {
        String student = studentField.getText().trim();
        int rating = (int) ratingCombo.getSelectedItem();
        String comment = commentArea.getText().trim();

        if (!controller.validate(student, rating, comment)) return;

        controller.add(student, rating, comment);
        tableModel.addRow(new Object[]{student, rating, comment});
        clearFields();
    }

    private void updateFeedback() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a feedback entry to update.");
            return;
        }

        String student = studentField.getText().trim();
        int rating = (int) ratingCombo.getSelectedItem();
        String comment = commentArea.getText().trim();

        if (!controller.validate(student, rating, comment)) return;

        controller.update(row, student, rating, comment);
        tableModel.setValueAt(student, row, 0);
        tableModel.setValueAt(rating, row, 1);
        tableModel.setValueAt(comment, row, 2);
        clearFields();
    }

    private void deleteFeedback() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a feedback entry to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this feedback?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.delete(row);
            tableModel.removeRow(row);
            clearFields();
        }
    }

    private void populateFields() {
        int row = table.getSelectedRow();
        if (row != -1) {
            studentField.setText((String) tableModel.getValueAt(row, 0));
            ratingCombo.setSelectedItem(tableModel.getValueAt(row, 1));
            commentArea.setText((String) tableModel.getValueAt(row, 2));
        }
    }

    private void clearFields() {
        studentField.setText("");
        commentArea.setText("");
        table.clearSelection();
    }
}
