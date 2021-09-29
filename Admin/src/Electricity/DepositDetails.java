package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1, b2;
    JLabel l1, l2;
    Choice c1, c2;
   
    int i=0, j=0;
    DepositDetails(){
        super("Deposit Details");
        setSize(700,750);
        //setLocation(600,150);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        l1 = new JLabel("Sort by Month");
        l1.setBounds(20, 20, 150, 20);
        add(l1);
        
        c1 = new Choice();
        
        l2 = new JLabel("Sort By Year");
        l2.setBounds(400, 20, 100, 20);
        add(l2);
        
        c2 = new Choice();
        
        t1 = new JTable();
       
        c1.setBounds(520, 20, 150, 20);
        for(int i=2021;i<2030;i++){
            c1.add(""+i);
        }
        add(c1);
        
        
        c2.setBounds(180,20, 150, 20);
        c2.add("January(1)");
        c2.add("February(2)");
        c2.add("March(3)");
        c2.add("April(4)");
        c2.add("May(5)");
        c2.add("June(6)");
        c2.add("July(7)");
        c2.add("August(8)");
        c2.add("September(9)");
        c2.add("October(10)");
        c2.add("November(11)");
        c2.add("December(12)");
        add(c2);
        
        
        b1 = new JButton("Search");
        b1.setBounds(20, 70, 80, 20);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Print");
        b2.setBounds(120, 70, 80, 20);
        b2.addActionListener(this);
        add(b2);
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            int monthIndex = c2.getSelectedIndex()+1;
            String str = "select meter as 'METER NO',bdate as 'DATE',units as UNITS,total_bill as AMOUNT,status as STATUS from bill where year(bdate) = "+c1.getSelectedItem()+" AND month(bdate) = "+monthIndex+"";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(str);
                t1.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){}
        }else if(ae.getSource() == b2){
            try{
                t1.print();
            }catch(Exception e){}
        }
    }
    
    public static void main(String[] args){
        new DepositDetails().setVisible(true);
    }
    
}
