package view;

import javax.swing.*;
import java.awt.*;

public class appointmentPanel extends JPanel {
    public appointmentPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Manage Appointments", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.decode("#2C3E50"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F5F7FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Styled components
        JComboBox<String> counselorCombo = new JComboBox<>(new String[]{"Dr. Smith", "Dr. Jane"});
        counselorCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        counselorCombo.setBackground(Color.WHITE);
        counselorCombo.setPreferredSize(new Dimension(150, 30));

        JTextField dateField = styledTextField("YYYY-MM-DD");
        JTextField timeField = styledTextField("HH:MM");

        JButton bookBtn = new JButton("Book Appointment");
        bookBtn.setBackground(new Color(52, 152, 219));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bookBtn.setFocusPainted(false);
        bookBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Counselor:"), gbc);
        gbc.gridx = 1;
        formPanel.add(counselorCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Time (HH:MM):"), gbc);
        gbc.gridx = 1;
        formPanel.add(timeField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(bookBtn, gbc);

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
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
}
