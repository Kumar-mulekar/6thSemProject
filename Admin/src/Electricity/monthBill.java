package Electricity;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class monthBill extends JFrame implements ActionListener {

    JLabel l1, l2, l4;
    JTextField t1;
    JButton b1, b2, b3;
    JPanel p;
    boolean fileCheck = false;
    private com.toedter.calendar.JDateChooser cal;

    monthBill() {

        p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));

        l1 = new JLabel("Monthly Electricity Bill");
        l1.setBounds(30, 10, 400, 30);

        l2 = new JLabel("Billing Date");
        l2.setBounds(60, 70, 100, 30);

        //todays date
        cal = new JDateChooser();
        cal.setBounds(200, 70, 180, 20);
        java.util.Date date = new java.util.Date();
        cal.setDate(date);

        JLabel l6 = new JLabel("Select File");
        l6.setBounds(60, 120, 100, 30);

        t1 = new JTextField();
        t1.setBounds(200, 120, 100, 30);
        b3 = new JButton("Open");
        b3.setBounds(305, 120, 80, 30);

        b1 = new JButton("Submit");
        b1.setBounds(60, 170, 100, 30);
        b2 = new JButton("Cancel");
        b2.setBounds(200, 170, 100, 30);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        //hicon2.jpg
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Pic/mbill.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l4 = new JLabel(i3);

        l1.setFont(new Font("Senserif", Font.PLAIN, 26));
        //Move the label to center
        l1.setHorizontalAlignment(JLabel.CENTER);

        p.add(l1);
        p.add(l2);
        p.add(cal);
        p.add(l6);
        p.add(t1);
        p.add(b3);
        p.add(b1);
        p.add(b2);

        setLayout(new BorderLayout(30, 30));

        add(p, "Center");
        add(l4, "West");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(950, 500);
        //setLocation(550,220);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            //Reading from txt file
            try {
                if (fileCheck) {
                    BufferedReader br = new BufferedReader(new FileReader(t1.getText()));

                    String s1 = "";
                    // date chooser
                    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date sqldate = new java.sql.Date(cal.getDate().getTime());

                    while ((s1 = br.readLine()) != null) {

                        String meter_unit[] = s1.split("\\s");

                        // select last date and last unit from meter info and update from text file;
                        Conn c = new Conn();
                        String qry2 = "select *from meter_info where meter_number = '" + meter_unit[0] + "'";

                        ResultSet rs = c.s.executeQuery(qry2);
                        String tlts = "";
                        int tlt;
                        if (rs.next()) {
                            tlt = Integer.parseInt(meter_unit[1]) - Integer.parseInt(rs.getString("last_unit"));
                            tlts = Integer.toString(tlt);
                        }
                        //JOptionPane.showMessageDialog(this,tlts);

                        String qry1 = "update meter_info set last_date = '" + sqldate + "',last_unit ='" + meter_unit[1] + "' where meter_number = '" + meter_unit[0] + "'";
                        c.s.executeUpdate(qry1);
                        //---------- data updated in meter info
                        //main work adding data to bill table and generating bill
                        //total bill
                        int total_bill = 0;
                        ResultSet rs2 = c.s.executeQuery("select * from tax");
                        if (rs2.next()) {
                            total_bill = Integer.parseInt(tlts) * Integer.parseInt(rs2.getString("cost_per_unit")); // 120 * 7
                            total_bill += Integer.parseInt(rs2.getString("meter_rent"));
                            total_bill += Integer.parseInt(rs2.getString("service_charge"));
                            total_bill += Integer.parseInt(rs2.getString("service_tax"));
                            total_bill += Integer.parseInt(rs2.getString("swacch_bharat_cess"));
                            total_bill += Integer.parseInt(rs2.getString("fixed_tax"));
                        }
                        //total bill calculated------
                        String qry3 = "insert into bill values ('" + meter_unit[0] + "','" + tlts + "','" + total_bill + "','Not Paid','" + sqldate + "','" + meter_unit[1] + "')";
                        c.s.executeUpdate(qry3);

                    }
                    JOptionPane.showMessageDialog(this, "Bills are sent to customer of Date " + sqldate);
                } else {
                    JOptionPane.showMessageDialog(this, "File does not exists");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        } else if (ae.getSource() == b3) {
            JFileChooser fc = new JFileChooser("C:\\Users\\HYDRA\\Desktop\\Electricity-Billing-System\\Admin\\src\\Readings");
            int i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String filepath = f.getPath();
                t1.setText(filepath);
                if (f.exists()) {
                    fileCheck = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        new monthBill().setVisible(true);
    }
}
