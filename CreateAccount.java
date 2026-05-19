import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class CreateAccount extends JFrame implements ActionListener {

    JTextField accNo, name, balance;
    JButton create;
    JLabel accNoLabel,nameLabel,balanceLabel;

    CreateAccount() {

        setTitle("Create Account");

        accNo = new JTextField();
        name = new JTextField();
        balance = new JTextField();

        create = new JButton("Create");
        accNoLabel=new JLabel("Account No:");
        nameLabel=new JLabel("Name:");
        balanceLabel=new JLabel("Balance:");

        setLayout(null);
        accNoLabel.setBounds(40,50,120,30);
        nameLabel.setBounds(40,100,120,30);
        balanceLabel.setBounds(40,150,120,30);

        accNo.setBounds(170,50,150,30);
        name.setBounds(170,100,150,30);
        balance.setBounds(170,150,150,30);
        create.setBounds(130,220,120,35);
        
        add(accNoLabel);
        add(nameLabel);
        add(balanceLabel);

        add(accNo);
        add(name);
        add(balance);
        add(create);
        
        accNoLabel.setFont(new Font("Arial",Font.BOLD,14));
        nameLabel.setFont(new Font("Arial",Font.BOLD,14));
        balanceLabel.setFont(new Font("Arial",Font.BOLD,14));

        create.addActionListener(this);

        setSize(400,350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO accounts VALUES (?,?,?,0)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1,Integer.parseInt(accNo.getText()));
            ps.setString(2,name.getText());
            ps.setDouble(3,Double.parseDouble(balance.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Account Created");

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}

