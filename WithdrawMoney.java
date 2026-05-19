import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class WithdrawMoney extends JFrame implements ActionListener {

    JTextField accNo, amount;
    JButton withdraw;
    
    JLabel accNoLabel,amountLabel;

    WithdrawMoney(){

        setTitle("Withdraw Money");

        accNo = new JTextField();
        amount = new JTextField();
        withdraw = new JButton("Withdraw");
        
        accNoLabel=new JLabel("Account No:");
        amountLabel=new JLabel("Amount:");
        setLayout(null);
        
        accNoLabel.setBounds(40,50,120,30);
        amountLabel.setBounds(40,100,120,30);

        accNo.setBounds(160,50,150,30);
        amount.setBounds(160,100,150,30);
        withdraw.setBounds(110,170,120,35);
        
        add(accNoLabel);
        add(amountLabel);
        add(accNo);
        add(amount);
        add(withdraw);
        
        accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
        amountLabel.setFont(new Font("Arial",Font.BOLD,14));
        withdraw.addActionListener(this);

        setSize(350,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            int acc = Integer.parseInt(accNo.getText());

            String query="SELECT * FROM accounts WHERE account_no=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1,acc);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                double balance = rs.getDouble("balance");
                int count = rs.getInt("withdraw_count");
                double amt = Double.parseDouble(amount.getText());

                if(!FraudDetection.checkFraud(amt,balance,count)){

                    balance -= amt;
                    count++;

                    String update="UPDATE accounts SET balance=?, withdraw_count=? WHERE account_no=?";

                    PreparedStatement ps2=con.prepareStatement(update);

                    ps2.setDouble(1,balance);
                    ps2.setInt(2,count);
                    ps2.setInt(3,acc);

                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(this,"Withdrawal Successful");

                } else {

                    JOptionPane.showMessageDialog(this,"Fraud Detected! Transaction Blocked");

                }
            }

        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}

