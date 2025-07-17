package view;

import javax.swing.*;
import java.awt.*;

public class counselorPanel extends JPanel {
    public counselorPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Manage Counselors", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.decode("#2C3E50"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F5F7FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JTextField nameField = styledTextField();
        JTextField specField = styledTextField();
        JTextField availField = styledTextField();

        // Labels and Fields
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1;
        formPanel.add(specField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Availability:"), gbc);
        gbc.gridx = 1;
        formPanel.add(availField, gbc);

        // Button
        JButton addBtn = new JButton("➕ Add Counselor");
        addBtn.setBackground(Color.decode("#3498DB"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(addBtn, gbc);

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private JTextField styledTextField() {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        return tf;
    }
}
