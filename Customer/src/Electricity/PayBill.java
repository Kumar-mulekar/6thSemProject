package Electricity;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6;
    JLabel l11, l12, l13, l14, l15;
    JTextField t1;
    Choice c1, c2;
    JButton b1, b2, b3;
    String meter;
    JDateChooser cal;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);

        setBounds(550, 220, 900, 600);

        JLabel title = new JLabel("Electricity Bill");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(120, 5, 400, 30);
        add(title);

        l1 = new JLabel("Meter No");
        l1.setBounds(35, 80, 200, 20);
        add(l1);

        JLabel l11 = new JLabel();
        l11.setBounds(300, 80, 200, 20);
        add(l11);

        JLabel l2 = new JLabel("Name");
        l2.setBounds(35, 140, 200, 20);
        add(l2);

        JLabel l12 = new JLabel();
        l12.setBounds(300, 140, 200, 20);
        add(l12);

        l3 = new JLabel("Month");
        l3.setBounds(35, 200, 200, 20);
        add(l3);

        cal = new JDateChooser();
        cal.setBounds(300, 200, 150, 20);
        //todays date             
        java.util.Date date = new java.util.Date();
        cal.setDate(date);
        add(cal);

        b3 = new JButton("->");
        b3.setBounds(460, 200, 40, 20);
        add(b3);

        l4 = new JLabel("Units");
        l4.setBounds(35, 260, 200, 20);
        add(l4);

        l13 = new JLabel();
        l13.setBounds(300, 260, 200, 20);
        add(l13);

        l5 = new JLabel("Total Bill");
        l5.setBounds(35, 320, 200, 20);
        add(l5);

        l14 = new JLabel();
        l14.setBounds(300, 320, 200, 20);
        add(l14);

        l6 = new JLabel("Status");
        l6.setBounds(35, 380, 200, 20);
        add(l6);

        l15 = new JLabel();
        l15.setBounds(300, 380, 200, 20);
        l15.setForeground(Color.RED);
        add(l15);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '" + meter + "'");
            while (rs.next()) {
                l11.setText(rs.getString("meter"));
                l12.setText(rs.getString("name"));
            }
            java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
            rs = c.s.executeQuery("select * from bill where meter = '" + meter + "' AND bdate = sdate ");
            while (rs.next()) {
                l13.setText(rs.getString("units"));
                l14.setText(rs.getString("total_bill"));
                l15.setText(rs.getString("status"));
            }
        } catch (Exception e) {
        }
        cal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                try {
                    java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                    JOptionPane.showMessageDialog(rootPane, sdate);
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter = '" + meter + "' AND bdate = '" + sdate + "'");
                    while (rs.next()) {
                        l13.setText(rs.getString("units"));
                        l14.setText(rs.getString("total_bill"));
                        l15.setText(rs.getString("status"));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        b1 = new JButton("Pay");
        b1.setBounds(100, 460, 100, 25);
        add(b1);
        b2 = new JButton("Back");
        b2.setBounds(230, 460, 100, 25);
        add(b2);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l21 = new JLabel(i3);
        l21.setBounds(400, 120, 600, 300);
        add(l21);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (l15.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Select Bill");
            } else if (l15.getText().equals("Paid")) {
                JOptionPane.showMessageDialog(this, "Bill Already Paid");
            } else {
                try {
                    java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                    Conn c = new Conn();
                    c.s.executeUpdate("update bill set status = 'Paid' where meter = '" + meter + "' AND bdate = '" + sdate + "'");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.setVisible(false);
                new Paytm(meter).setVisible(true);
            }

        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        } else if (ae.getSource() == b3) {
            try {
                java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                //JOptionPane.showMessageDialog(rootPane, sdate);
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from bill where meter = '" + meter + "' AND bdate = '" + sdate + "'");
                //validation
                if (rs.next() == false) {
                    JOptionPane.showMessageDialog(null, "Not Found");
                } else {
                    //
                    do {
                        //JOptionPane.showMessageDialog(rootPane,rs.getString("units"));
                        l13.setText(rs.getString("units"));
                        l14.setText(rs.getString("total_bill"));
                        l15.setText(rs.getString("status"));
                    } while (rs.next());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PayBill("").setVisible(true);
    }

}
