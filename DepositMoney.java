import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class DepositMoney extends JFrame implements ActionListener {

    JTextField accNo, amount;
    JButton deposit;
    
    JLabel accNoLabel,amountLabel;

    DepositMoney() {

        setTitle("Deposit Money");
        
        accNo = new JTextField();
        amount = new JTextField();
        deposit = new JButton("Deposit");

        accNoLabel=new JLabel("Account No:");
        amountLabel=new JLabel("Amount:");

        setLayout(null);
        
        accNoLabel.setBounds(40,50,120,30);
        amountLabel.setBounds(40,100,120,30);

        accNo.setBounds(160,50,150,30);
        amount.setBounds(160,100,150,30);
        deposit.setBounds(110,170,120,35);
        
        add(accNoLabel);
        add(amountLabel);
        add(accNo);
        add(amount);
        add(deposit);
        
        accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
        amountLabel.setFont(new Font("Arial",Font.BOLD,14));
        deposit.addActionListener(this);

        setSize(350,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            int acc = Integer.parseInt(accNo.getText());
            double amt = Double.parseDouble(amount.getText());

            if(amt <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
                return;
            }

            String query = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, amt);
            ps.setInt(2, acc);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                JOptionPane.showMessageDialog(this, "Deposit Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
