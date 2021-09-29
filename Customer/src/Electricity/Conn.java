package Electricity;

import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    public Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql:///ebs","root","");
            //c =DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6419860","sql6419860","Q9YIJ2crXS");
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
    public static void main(String args[]){
        new Conn();
    }
}  