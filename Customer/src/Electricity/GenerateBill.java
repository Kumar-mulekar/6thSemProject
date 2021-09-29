package Electricity;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class GenerateBill extends JFrame implements ActionListener {

    JLabel l1, l2;

    JTable t2;
    DefaultTableModel model;
    JButton b1;
    Choice c2;
    JPanel p1;
    String meter;
    JDateChooser cal;
    Object[] row = new Object[2];
    
    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500, 700);
        setLayout(new BorderLayout());
        t2 = new JTable();
        model=new DefaultTableModel();
        Object[] column={"",""};
        t2.setShowGrid(false);
        t2.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        t2.setRowHeight(25);
        model.setColumnIdentifiers(column);
        t2.setModel(model);
        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        l2 = new JLabel(meter);
        cal = new JDateChooser();
        java.util.Date date = new java.util.Date();
        cal.setDate(date);

        

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(l2);
        p1.add(cal);
        add(p1, "North");

        add(t2,"Center");
        add(b1, "South");

        b1.addActionListener(this);

        //setLocation(750,100);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        model.setRowCount(0);
        try {
            Conn c = new Conn();
            java.sql.Date sdate = new java.sql.Date(cal.getDate().getTime());

            

            ResultSet rs = c.s.executeQuery("select * from customer where meter=" + meter);

            if (rs.next()) {
       
            row[0]="Customer Name";
            row[1]= rs.getString("name");           
            model.addRow(row);
            
            row[0]="Meter Number";
            row[1]=rs.getString("meter");
            model.addRow(row);
            
            row[0]="Address";
            row[1]=rs.getString("address");
            model.addRow(row);
            
            row[0]="State";
            row[1]=rs.getString("state");
            model.addRow(row);
            
            row[0]="City";
            row[1]=rs.getString("city");
            model.addRow(row);
            
            row[0]="Phone Number";
            row[1]=rs.getString("phone");
            model.addRow(row);
            
            row[0]="";
            row[1]="";
            //model.addRow(row);
            model.addRow(row);

            }

            rs = c.s.executeQuery("select * from meter_info where meter_number = " + meter);

            if (rs.next()) {
               
                row[0]="Meter Location"; 
                row[1]=rs.getString("meter_location");
                model.addRow(row);
                
                row[0]="Meter Type";  
                row[1]=rs.getString("meter_type");
                model.addRow(row);
                
                row[0]="Phase Code";  
                row[1]=rs.getString("phase_code");
                model.addRow(row);
                
                row[0]="Bill Type";  
                row[1]=rs.getString("bill_type");
                model.addRow(row);
                
                row[0]="Days";  
                row[1]=rs.getString("days");
                model.addRow(row);
                
                row[0]="";
            row[1]="";
            //model.addRow(row);
            model.addRow(row);
                
            }
            rs = c.s.executeQuery("select * from tax");
            if (rs.next()) {
           
                row[0]="Cost per Unit";  
                row[1]=rs.getString("cost_per_unit");
                model.addRow(row);
                
                row[0]="Meter Rent";  
                row[1]= rs.getString("meter_rent");
                model.addRow(row);
                
                row[0]="Service Charge";  
                row[1]=rs.getString("service_charge");
                model.addRow(row);
                
                row[0]=  "Service Tax"; 
                row[1]=rs.getString("service_tax");
                model.addRow(row);
                
                row[0]="Swacch Bharat Cess";  
                row[1]=rs.getString("swacch_bharat_cess");
                model.addRow(row);
                
                row[0]="Fixed Tax";
                row[1]=rs.getString("fixed_tax");
                model.addRow(row);
                
                row[0]="";
            row[1]="";
            //model.addRow(row);
            model.addRow(row);
                

            }
           
            rs = c.s.executeQuery("select * from bill where meter=" + meter + " AND bdate = '" + sdate + "'");
            if (rs.next() == false) {
                JOptionPane.showMessageDialog(null, "Bill Not Found");
                model.setRowCount(0);
            } else {

                do {
                    
                    row[0]="Bill Date";
                    row[1]=rs.getString("bdate");
                    model.addRow(row);
                    
                    row[0]="Units Consumed";
                    row[1]=rs.getString("units");
                    model.addRow(row);
                    
                    row[0]="TOTAL PAYABLE";
                    row[1]=rs.getString("total_bill");
                    model.addRow(row);
                } while (rs.next());
            }
           
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("").setVisible(true);
    }
}
