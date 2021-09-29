package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JFrame implements ActionListener {

    String meter;
    private int Exit_ON_CLOSE;

    Project(String meter, String person) {
        super("Electricity Billing System");
        this.meter = meter;

        setSize(1200, 700);

        /* Adding background image */
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("icon/elec2.jpg"));
        Image i3 = ic.getImage().getScaledInstance(1900, 950, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);

        /* First Column */
        JMenuBar mb = new JMenuBar();

        /* First Column Customer*/
        JMenu info = new JMenu("Information");
        JMenuItem info1 = new JMenuItem("Update Information");
        JMenuItem info2 = new JMenuItem("View Information");

        info.setForeground(Color.BLACK);

        /* ---- Update Information ---- */
        info1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon41 = new ImageIcon(ClassLoader.getSystemResource("icon1/modify.png"));
        Image image41 = icon41.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        info1.setIcon(new ImageIcon(image41));
        info1.setMnemonic('U');
        info1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        info1.setBackground(Color.WHITE);

        /* ---- view info ----*/
        info2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon42 = new ImageIcon(ClassLoader.getSystemResource("icon1/invoice.png"));
        Image image42 = icon42.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        info2.setIcon(new ImageIcon(image42));
        info2.setMnemonic('V');
        info2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        info2.setBackground(Color.WHITE);

        info1.addActionListener(this);
        info2.addActionListener(this);

        // --------------------------------------------------------------------------------------------
        /* Second Column */
        JMenu user = new JMenu("User");
        JMenuItem u1 = new JMenuItem("Pay Bill");
        JMenuItem u3 = new JMenuItem("Bill Details");
        user.setForeground(Color.BLACK);

        /* ---- Pay Bill ---- */
        u1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon1/reload.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        u1.setIcon(new ImageIcon(image4));
        u1.setMnemonic('P');
        u1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        u1.setBackground(Color.WHITE);

        /* ----  Bill Details ----*/
        u3.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon1/search.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        u3.setIcon(new ImageIcon(image6));
        u3.setMnemonic('B');
        u3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        u3.setBackground(Color.WHITE);

        u1.addActionListener(this);
        u3.addActionListener(this);

        // --------------------------------------------------------------------------------------------- 
        /* Third Column*/
        JMenu report = new JMenu("Report");
        JMenuItem r1 = new JMenuItem("Generate Bill");
        report.setForeground(Color.BLACK);

        /* ---- Report ---- */
        r1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon1/print.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        r1.setIcon(new ImageIcon(image7));
        r1.setMnemonic('G');
        r1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        r1.setBackground(Color.WHITE);

        r1.addActionListener(this);

        // -----------------------------------------------------------------------------------------------
        /* Fourth Column*/
        JMenu utility = new JMenu("Utility");
        JMenuItem ut1 = new JMenuItem("Notepad");
        JMenuItem ut2 = new JMenuItem("Calculator");
        JMenuItem ut3 = new JMenuItem("Web Browser");
        utility.setForeground(Color.BLACK);

        /* ---- Notepad ---- */
        ut1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut1.setIcon(new ImageIcon(image8));
        ut1.setMnemonic('N');
        ut1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        ut1.setBackground(Color.WHITE);

        /* ---- Calculator ---- */
        ut2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut2.setIcon(new ImageIcon(image9));
        ut2.setMnemonic('C');
        ut2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        ut2.setBackground(Color.WHITE);

        /* ---- Web Browser ---- */
        ut3.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut3.setIcon(new ImageIcon(image10));
        ut3.setMnemonic('W');
        ut3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
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
        ex.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon1/exit.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));
        ex.setMnemonic('L');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);

        ex.addActionListener(this);

        // ---------------------------------------------------------------------------------------------
        info.add(info1);
        info.add(info2);

        user.add(u1);
        user.add(u3);

        report.add(r1);

        utility.add(ut1);
        utility.add(ut2);
        utility.add(ut3);

        exit.add(ex);

        mb.add(info);
        mb.add(user);
        mb.add(report);

        mb.add(utility);
        mb.add(exit);

        setJMenuBar(mb);
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        setFont(new Font("Senserif", Font.BOLD, 16));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);//center the frame
        setVisible(false);
    }
            

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("Pay Bill")) {
            new PayBill(meter).setVisible(true);

        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
            }
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
            }
        } else if (msg.equals("Web Browser")) {
            try {
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            } catch (Exception e) {
            }
        } else if (msg.equals("Logout")) {
            this.setVisible(false);
            new Login().setVisible(true);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter).setVisible(true);

        } else if (msg.equals("View Information")) {
            new ViewInformation(meter).setVisible(true);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter).setVisible(true);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter).setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Project("", "").setVisible(true);
    }

}
