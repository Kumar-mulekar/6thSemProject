package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JFrame implements ActionListener{
    String meter;
    private int Exit_ON_CLOSE;
    Project(String meter, String person){
        super("Electricity Billing System");
        this.meter = meter;
        
        setSize(1200,700);
        
        /* Adding background image */
        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("icon/elec2.jpg"));
        Image i3 = ic.getImage().getScaledInstance(1900, 950,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);
       
        /* First Column */
        JMenuBar mb  = new JMenuBar();
        JMenu master = new JMenu("Master");
        JMenuItem m1 = new JMenuItem("New Customer");        
        JMenuItem m4 = new JMenuItem("Calculate Bill");
        //adding new menu         
        master.setForeground(Color.BLACK);
        JMenuItem m6 = new JMenuItem("Pay Bill");
        
        
        
        /* ---- new Customer Details ---- */
        m1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon1/customer.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m1.setIcon(new ImageIcon(image1));
        m1.setMnemonic('N');
        m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        m1.setBackground(Color.WHITE);
        
        
        
         
        //----- calculate bill
        m4.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon1/Time.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m4.setIcon(new ImageIcon(image5));
        m4.setMnemonic('C');
        m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        m4.setBackground(Color.WHITE);
        
       
        //------pay bill
        m6.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image6=icon6.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        m6.setIcon(new ImageIcon(image6));
        m6.setMnemonic('P');
        m6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        m6.setBackground(Color.WHITE);
        
        
        m1.addActionListener(this);
        m4.addActionListener(this);
        m6.addActionListener(this);
        
        //-----------------------------
        
        /* Second Column */
        JMenu report = new JMenu("Report");
        JMenuItem rpt1 = new JMenuItem("Customer Details");
        JMenuItem rpt2 = new JMenuItem("Deposit Details");
        
        report.setForeground(Color.BLACK);
        
        /* ---- customer Details ---- */
        rpt1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        rpt1.setIcon(new ImageIcon(image2));
        rpt1.setMnemonic('U');
        rpt1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        rpt1.setBackground(Color.WHITE);
        
        /* ---- Deposit Details  ----- */
        rpt2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        rpt2.setIcon(new ImageIcon(image3));
        rpt2.setMnemonic('D');
        rpt2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        rpt2.setBackground(Color.WHITE);
        
        
        rpt1.addActionListener(this);
        rpt2.addActionListener(this);
        
        
        
        
       
       
       
        
        // -----------------------------------------------------------------------------------------------
        
        /* Fourth Column*/
        JMenu utility = new JMenu("Utility");
        JMenuItem ut1 = new JMenuItem("Notepad");
        JMenuItem ut2 = new JMenuItem("Calculator");
        JMenuItem ut3 = new JMenuItem("Web Browser");
        utility.setForeground(Color.BLACK); 
        
        /* ---- Calender ---- */
        ut1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut1.setIcon(new ImageIcon(image8));
        ut1.setMnemonic('W');
        ut1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        ut1.setBackground(Color.WHITE);
        
        /* ---- Calculator ---- */
        ut2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut2.setIcon(new ImageIcon(image9));
        ut2.setMnemonic('X');
        ut2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        ut2.setBackground(Color.WHITE);
        
        /* ---- Web Browser ---- */
        ut3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut3.setIcon(new ImageIcon(image10));
        ut3.setMnemonic('B');
        ut3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        ut3.setBackground(Color.WHITE);
        
        
        ut1.addActionListener(this);
        ut2.addActionListener(this);
        ut3.addActionListener(this);
        
        // ---------------------------------------------------------------------------------------
        
        /*Fifth Column */
        JMenu exit = new JMenu("Logout");
        JMenuItem ex = new JMenuItem("Logout");
        exit.setForeground(Color.BLACK);
        
        /* ---- Exit ---- */
        ex.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon1/loggoff.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));
        ex.setMnemonic('L');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);
        
        ex.addActionListener(this);
        /*Fourth Column */
        JMenu about=new JMenu("About");
        JMenuItem ab =new JMenuItem("About");
        about.setForeground(Color.BLACK);
        /*-----About-----*/
        ab.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon1/helper.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ab.setIcon(new ImageIcon(image12));
        ab.setMnemonic('A');
        ab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        
        
        // ---------------------------------------------------------------------------------------------
               
        
        master.add(m1);
        master.add(m4);
        //addinng month bill
        
        master.add(m6);
        
        report.add(rpt1);
        report.add(rpt2);
        
       
        
        utility.add(ut1);
        utility.add(ut2);
        utility.add(ut3);
        
        about.add(ab);
        
        exit.add(ex);
         

        mb.add(master);
        mb.add(report);
        mb.add(utility);
        mb.add(about);
        mb.add(exit);
        
        setJMenuBar(mb);  
        
         addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        
        setFont(new Font("Senserif",Font.BOLD,16));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);//center the frame
        
        setVisible(false);
    }
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("Customer Details")){
            new CustomerDetails().setVisible(true);
            
        }else if(msg.equals("Monthly Bill")){
            new monthBill().setVisible(true);
            
        
        }else if(msg.equals("New Customer")){
            new NewCustomer().setVisible(true);
            
        }else if(msg.equals("Calculate Bill")){
            //new monthBill().setVisible(true);
            new androidReading().setVisible(true);
            
        }else if(msg.equals("Notepad")){
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch(Exception e){ }
        }else if(msg.equals("Calculator")){
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch(Exception e){ }
        }else if(msg.equals("Web Browser")){
            try{
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            }catch(Exception e){ }
        }else if(msg.equals("Logout")){
            this.setVisible(false);
            new Login().setVisible(true);
        }else if(msg.equals("Deposit Details")){
            new DepositDetails().setVisible(true);
        }else if(msg.equals("Bill Details")){
            new BillDetails(meter).setVisible(true);
        }else if(msg.equals("Pay Bill")){
            new payBillAdmin().setVisible(true);
        }else if(msg.equals("About")){
            new About().setVisible(true);
        }
        
        
    }
    
    
    public static void main(String[] args){
        new Project("", "").setVisible(true);
    }
    
}
