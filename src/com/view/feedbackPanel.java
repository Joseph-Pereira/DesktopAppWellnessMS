package view;

import javax.swing.*;
import java.awt.*;

public class feedbackPanel extends JPanel {
    public feedbackPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F7FA"));

        JLabel title = new JLabel("Submit Feedback", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.decode("#2C3E50"));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#F5F7FA"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Styled components
        JComboBox<Integer> ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ratingCombo.setBackground(Color.WHITE);
        ratingCombo.setPreferredSize(new Dimension(150, 30));

        JTextArea commentArea = new JTextArea(4, 20);
        commentArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JButton submitBtn = new JButton("Submit Feedback");
        submitBtn.setBackground(new Color(52, 152, 219));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        submitBtn.setFocusPainted(false);
        submitBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Rating (1-5):"), gbc);
        gbc.gridx = 1;
        formPanel.add(ratingCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Comments:"), gbc);
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(commentArea), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(submitBtn, gbc);

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
}
