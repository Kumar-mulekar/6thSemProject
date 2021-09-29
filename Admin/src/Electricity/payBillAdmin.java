package Electricity;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class payBillAdmin extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;
    JTextField t1;
    JButton b1, b2;
    JPanel p1;
    JDateChooser cal;

    public payBillAdmin() {
        super("Pay Bill");
        setLocation(500, 200);
        setSize(500, 500);
        getContentPane().setBackground(Color.WHITE);

        l1 = new JLabel("Meter No");
        t1 = new JTextField();
        l2 = new JLabel("Date");
        cal = new JDateChooser();
        java.util.Date date = new java.util.Date();
        cal.setDate(date);
        l3 = new JLabel("Amount");
        l4 = new JLabel();

        b1 = new JButton("SEARCH");
        b2 = new JButton("PAY");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b1.addActionListener(this);
        b2.addActionListener(this);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l5 = new JLabel(i3);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2, 10, 10));
        p1.setBackground(Color.WHITE);

        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(cal);
        p1.add(l3);
        p1.add(l4);
        p1.add(b1);
        p1.add(b2);

        JLabel s1 = new JLabel("     ");
        s1.setSize(50, 500);
        JLabel s2 = new JLabel("     ");
        s2.setSize(50, 500);
        JLabel s3 = new JLabel("     ");
        s3.setSize(50, 500);

        add(s1, BorderLayout.EAST);
        add(s2, BorderLayout.WEST);
        add(l5, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(s3, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ev) {
        Conn c = new Conn();
        ResultSet rs;
        java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
        if (ev.getSource() == b1) {

            String qry = "select total_bill from bill where meter='" + t1.getText() + "' and bdate='" + sdate + "'";
            try {
                rs = c.s.executeQuery(qry);
                if (rs.next()) {
                    l4.setText(rs.getString("total_bill"));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Bill Not Found");
                //ex.printStackTrace();
            }
        } else if (ev.getSource() == b2) {
            if (l4.getText() == "") {
                JOptionPane.showMessageDialog(this,"Bill Not Found");
            } else {
                String qry = "update bill set status ='Paid' where meter='" + t1.getText() + "' and bdate='" + sdate + "'";
                try {
                    c.s.executeUpdate(qry);

                    JOptionPane.showMessageDialog(this, "Bill Paid");
                    t1.setText("");
                    l4.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        new payBillAdmin().setVisible(true);
    }

}
