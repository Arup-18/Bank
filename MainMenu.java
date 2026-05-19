import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {

    JButton create, deposit, withdraw, balance, exit;
    MainMenu() {

        setTitle("Bank Management System");
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(135, 206, 250),   // Light Blue
                        0, getHeight(), new Color(255, 255, 255) // White
                );

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(null);
        setContentPane(panel);

        // Buttons
        create = new JButton("Create Account");
        deposit = new JButton("Deposit Money");
        withdraw = new JButton("Withdraw Money");
        balance = new JButton("Check Balance");
        exit = new JButton("Exit");

        // Layout
        int btnWidth = 200;
        int frameWidth = 400;
        int centerX = (frameWidth - btnWidth) / 2;

        create.setBounds(centerX, 40, btnWidth, 40);
        deposit.setBounds(centerX, 100, btnWidth, 40);
        withdraw.setBounds(centerX, 160, btnWidth, 40);
        balance.setBounds(centerX, 220, btnWidth, 40);
        exit.setBounds(centerX, 280, btnWidth, 40);

        JButton[] buttons = {create, deposit, withdraw, balance, exit};

        for (JButton btn : buttons) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            panel.add(btn);
        }

        // Action Listeners
        create.addActionListener(this);
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        balance.addActionListener(this);
        exit.addActionListener(this);

        // Frame settings
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            new CreateAccount();
        }

        if (e.getSource() == deposit) {
            new DepositMoney();
        }

        if (e.getSource() == withdraw) {
            new WithdrawMoney();
        }

        if (e.getSource() == balance) {
            new BalanceCheck();
        }

        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}

