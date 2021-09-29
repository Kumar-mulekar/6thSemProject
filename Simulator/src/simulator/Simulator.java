/*
 * Developed by Pravin and Kumar.

 */
package simulator;



import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator extends JFrame implements ActionListener {
    JButton b1;
    private com.toedter.calendar.JDateChooser dt;
 public Simulator(){
     setLayout(new BorderLayout());
     setLocation(600,200);
     setSize(300,500);
     
     JLabel m = new JLabel();
     ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("meter.jpg"));
     Image i3 = ic1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
     ImageIcon ic2 = new ImageIcon(i3);
     m = new JLabel(ic2);
     
     
     b1=new JButton("Take Reading");
     b1.addActionListener(this);
     
     dt=new JDateChooser();
     java.util.Date date = new java.util.Date();
     dt.setDate(date);
     
     add(m,BorderLayout.CENTER);
     add(dt,BorderLayout.NORTH);
     add(b1,BorderLayout.SOUTH);
     setVisible(true);
     
 }
    @Override
 public void actionPerformed(ActionEvent e){
   if(e.getSource()==b1){
       try{
          Conn c = new Conn();
          String str = "select * from meter_info";
          ResultSet rs = c.s.executeQuery(str);
          int un=0;
          //creating file
          String path = "C:\\Users\\HYDRA\\Desktop\\Electricity-Billing-System\\Admin\\src\\Readings\\";
          SimpleDateFormat formater =new SimpleDateFormat("MMMM-dd-yyyy");
          String fdate=formater.format(dt.getDate()) + ".txt";
          fdate=fdate.replaceAll("\\s","");
          
          
          
          
          File myObj=new File(path,fdate);
          if(myObj.createNewFile()){
              
          }
          
          BufferedWriter bw = new BufferedWriter(new FileWriter(myObj.getAbsoluteFile()));
          while(rs.next()){
              un=0;
              //JOptionPane.showMessageDialog(this,rs.getString("last_unit"));
              un=Integer.parseInt(rs.getString("last_unit"));
              int randomN = ThreadLocalRandom.current().nextInt(20,201) + un;
              
              bw.append(rs.getString("meter_number")+" "+Integer.toString(randomN)+"\n");
              //myWriter.write("this is");
              //myWriter.flush();
              
          }
          
          bw.close();
          rs.close();
          
          JOptionPane.showMessageDialog(this,"Readings are successfully taken");
          
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }  
 }
 
 
 public static void main(String args[]){
     new Simulator().setVisible(true);
 }
}
