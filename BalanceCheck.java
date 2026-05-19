import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class BalanceCheck extends JFrame implements ActionListener {

    JTextField accNo;
    JButton check;
    JLabel accNoLabel;
    BalanceCheck() {

        setTitle("Check Balance");

        accNo = new JTextField();
        check = new JButton("Check");
        
        accNoLabel=new JLabel("Account No:");

        setLayout(null);
       
        accNoLabel.setBounds(40,50,120,30);

        accNo.setBounds(160,50,150,30);
        check.setBounds(100,110,120,35);
        
        add(accNoLabel);
        add(accNo);
        add(check);
        
        accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
        check.addActionListener(this);

        setSize(350,220);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            int acc = Integer.parseInt(accNo.getText());

            String query = "SELECT balance FROM accounts WHERE account_no = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, acc);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                double balance = rs.getDouble("balance");
                JOptionPane.showMessageDialog(this, "Balance: ₹" + balance);
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}

