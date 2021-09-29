/*
 * Developed by Pravin and Kumar.

 */
package Electricity;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class androidReading extends JFrame implements ActionListener {

    JLabel l1, l2;
    DefaultTableModel model;
    JButton b1, b2, b3;
    JPanel p1, p2;
    JDateChooser cal;

    JTable t1;
    String x[] = {"Meter Number", "Customer Name", "Units"};
    String y[][] = new String[40][8];
    int i = 0, j = 0;

    androidReading() {

        setSize(500, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        //center
        t1 = new JTable(y, x);
        t1.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        JScrollPane sp = new JScrollPane(t1);

        //north
        p1 = new JPanel();
        l1 = new JLabel("Select the Date of Bill ");
        cal = new JDateChooser();
        java.util.Date date = new java.util.Date();
        cal.setDate(date);
        cal.setSize(100, 20);
        b2 = new JButton("Search");
        //south
        p2 = new JPanel();
        b1 = new JButton("Submit Bill");
        b3 = new JButton("Delete");
        p2.add(b1);
        p2.add(b3);
        //++++++++++++++
        p1.add(l1);
        p1.add(cal);
        p1.add(b2);

        add(p1, "North");
        add(sp, "Center");
        add(b1, "South");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        //setLocation(750,100);
        setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            try {
                Conn c = new Conn();
                java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                ResultSet rs = c.s.executeQuery("select customer.meter as 'METER NO',customer.name as 'CUSTOMER NAME',reading.reading as 'UNITS' from customer,reading where customer.meter=reading.meter and reading.rdate='" + sdate + "'");
                t1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b3) {
            try {
                Conn c = new Conn();
                java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                ResultSet rs = c.s.executeQuery("select customer.meter as 'METER NO',customer.name as 'CUSTOMER NAME',reading.reading as 'UNITS' from customer,reading where customer.meter=reading.meter and reading.rdate='" + sdate + "'");
                if (rs.next()) {
                    int result = JOptionPane.showConfirmDialog(rootPane, "Are YOU Sure?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        c.s.executeUpdate("delete from reading where rdate ='" + sdate + "'");
                        JOptionPane.showMessageDialog(rootPane, "Deleted");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {

            try {
                Conn c = new Conn();
                java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());
                ResultSet delta = c.s.executeQuery("select * from reading where rdate='" + sdate + "'");
                int k = 0;
                HashMap<String, String> map = new HashMap<String, String>();

                while (delta.next()) {
                    map.put(delta.getString("meter"), delta.getString("reading"));
                }
                delta.close();

                for (Map.Entry m : map.entrySet()) {
                    //System.out.println(k++);
                    String fmeter, freading;

                    fmeter = m.getKey().toString();
                    freading = m.getValue().toString();
                    //System.out.println(fmeter);
                    

                    String qry2 = "select *from meter_info where meter_number = '" + fmeter + "'";
                    ResultSet rs1 = c.s.executeQuery(qry2);
                    String tlts = "";
                    int tlt;
                    if (rs1.next()) {
                        tlt = Integer.parseInt(freading) - Integer.parseInt(rs1.getString("last_unit"));
                        tlts = Integer.toString(tlt);
                    }
                    rs1.close();
                    //JOptionPane.showMessageDialog(this,tlts);

                    String qry1 = "update meter_info set last_date = '" + sdate + "',last_unit ='" + freading + "' where meter_number = '" + fmeter + "'";
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
                    rs2.close();
                    //total bill calculated------
                    String qry3 = "insert into bill values ('" + fmeter + "','" + tlts + "','" + total_bill + "','Not Paid','" + sdate + "','" + freading + "')";
                    c.s.executeUpdate(qry3);

                }
                JOptionPane.showMessageDialog(this, "Bills are sent to customer of Date " + sdate);
                c.s.executeUpdate("delete from reading where rdate='" + sdate + "'");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new androidReading().setVisible(true);
    }
}
