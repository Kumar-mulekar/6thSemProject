package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewCustomer extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l11;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JButton b1, b2;

    NewCustomer() {

        //setLocation(600,200);
        setSize(700, 500);
        setLocationRelativeTo(null);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        p.setBackground(new Color(173, 216, 230));

        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);

        l1 = new JLabel("Customer Name");
        l1.setBounds(100, 80, 100, 20);

        t1 = new JTextField();
        t1.setBounds(240, 80, 200, 20);

        p.add(l1);
        p.add(t1);
        l2 = new JLabel("Meter No");
        l2.setBounds(100, 120, 100, 20);
        l11 = new JLabel();
        l11.setBounds(240, 120, 200, 20);
        p.add(l2);
        p.add(l11);
        l3 = new JLabel("Address");
        l3.setBounds(100, 160, 100, 20);
        t3 = new JTextField();
        t3.setBounds(240, 160, 200, 20);
        p.add(l3);
        p.add(t3);
        l5 = new JLabel("City");
        l5.setBounds(100, 200, 100, 20);
        t5 = new JTextField();
        t5.setBounds(240, 200, 200, 20);
        p.add(l5);
        p.add(t5);
        l4 = new JLabel("State");
        l4.setBounds(100, 240, 100, 20);
        t4 = new JTextField();
        t4.setBounds(240, 240, 200, 20);
        p.add(l4);
        p.add(t4);

        l6 = new JLabel("Email");
        l6.setBounds(100, 280, 100, 20);
        t6 = new JTextField();
        t6.setBounds(240, 280, 200, 20);
        p.add(l6);
        p.add(t6);
        l7 = new JLabel("Phone Number");
        l7.setBounds(100, 320, 100, 20);
        t7 = new JTextField();
        t7.setBounds(240, 320, 200, 20);
        t7.setColumns(10);
        p.add(l7);
        p.add(t7);

        b1 = new JButton("Next");
        b1.setBounds(120, 390, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(250, 390, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());

        add(p, "Center");
        //hicon1.jpg
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("Pic/newcust.png"));
        Image i3 = ic1.getImage().getScaledInstance(200, 500, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);

        add(l8, "West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);

        
        l11.setText(Integer.toString(autoMeter()));//auto meter no
        
        //clearing text boxes
        clearText();
        
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (validateCustomer()) {
                String name = t1.getText();
                String meter = l11.getText();
                String address = t3.getText();
                String state = t4.getText();
                String city = t5.getText();
                String email = t6.getText();
                String phone = t7.getText();

                String q1 = "insert into customer values('" + name + "','" + meter + "','" + address + "','" + city + "','" + state + "','" + email + "','" + phone + "')";
                String q2 = "insert into login values('" + meter + "', '', '', '', '')";
                try {
                    Conn c1 = new Conn();
                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                    this.setVisible(false);
                    new MeterInfo(meter).setVisible(true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }

    //auto meter no
    private int autoMeter() {
        try {
            Conn c2 = new Conn();
            ResultSet m = c2.s.executeQuery("select meter from customer");
            if (m.next()) {
                m.last();
                int mid = Integer.parseInt(m.getString("meter")) + 1;
                return mid;
            } else {
                return 6000;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 6000;
        }

    }

    private boolean validateCustomer() {
        boolean flag1=true,flag2=true,flag3=true,flag4=true;
        if (t1.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("") || t7.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill all the Fields of Form", "Error in Adding", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                Pattern pat = Pattern.compile("[a-zA-Z' ']+");
                Pattern pat1 = Pattern.compile("[0-9]+");
                Matcher mat;
                mat = pat.matcher(t1.getText());
                if (mat.matches()) {
                    flag1 = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Check Name", "Error in Adding", JOptionPane.ERROR_MESSAGE);
                    t1.setText("");
                    flag1 = false;
                }
                mat = pat.matcher(t5.getText());
                if (mat.matches()) {
                    flag2 = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Check City Name", "Error in Adding", JOptionPane.ERROR_MESSAGE);
                    t5.setText("");
                    flag2 = false;
                }
                mat = pat.matcher(t4.getText());
                if (mat.matches()) {
                    flag3 = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Check State Name", "Error in Adding", JOptionPane.ERROR_MESSAGE);
                    t4.setText("");
                    flag3 = false;
                }
                mat = pat1.matcher(t7.getText());
                if (mat.matches() && t7.getText().length()==10) {                   
                        flag4 = true;                   
                } else {
                    JOptionPane.showMessageDialog(null, "Check Phone Number", "Error in Adding", JOptionPane.ERROR_MESSAGE);
                    t7.setText("");
                    flag4 = false;
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(flag1==true && flag2==true && flag3==true && flag4==true)
                return true;
            else
                return false;
        }
    }
    private void clearText(){
        t1.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
    }

    public static void main(String[] args) {
        new NewCustomer().setVisible(true);
    }
}
