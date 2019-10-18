 // Created on 21 July, 2015, 6:14 PM
package lifeline;
// @author PRADUMN KUMAR

import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class PayrollMang
{
    String DoP,Remark;
    double Sal;
    int DocId;
    static char PCh; 
    
    Scanner sc=new Scanner(System.in);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    
    
    //public static void main(String[] args)
    public void displayPayMenu()
    {
        PayrollMang pm=new PayrollMang();
        try
        {
            System.out.println("----YOU ARE CURRENTLY IN PAYROLL SECTION-----");
            System.out.println("PRESS 1 TO Add a Record\n" +
                   "PRESS 2 TO View Records\n" +
                   "PRESS 3 TO Remove a Record\n" +
                   "PRESS 4 TO Modify Existing Details\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
            
            int PChoice;
            PChoice=Integer.parseInt(br.readLine());
           if(PChoice==1)           
              pm.insertPayDetails();
           else if(PChoice==2)
              pm.viewPayDetails();               
           else if(PChoice==3)
               pm.deletePayRecord();               
           else if(PChoice==4)               
                pm.modifyPayDetails(); 
           else if(PChoice==5)
           {
                MainMenu mm=new MainMenu();
                mm.displayMainMenu();               
           }
           else
           {
               System.out.println("______CLOSING THE SOFTWARE_______");
               System.exit(0);              
           }
        }
       catch(IOException io)
       {
           System.out.println("ERROR:"+io);
       }
       catch(Exception e)
       {
           System.out.println("ERROR:"+e);
       }
        finally
        {            
           pm.displayPayMenu(); 
        }
    }
    
    public void insertPayDetails()
    //public static void main(String[] args)
    {        
        do
         {
             try
             {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," "); 
        System.out.println("**********FOR ADDING PAYROLL RECORD********");
        System.out.println("ENTER THE DOCTOR'S ID:");
        DocId=Integer.parseInt(br.readLine());
        System.out.println("AMOUNT TO BE PAID:");       
        Sal=Double.parseDouble(br.readLine());
        System.out.println("DATE OF PAYMENT: ");
        DoP=(br.readLine());
        System.out.println("REMARK:");
         Remark=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO PAYROLL1 VALUES(?,?,?,?)");
         stat.setInt(1,DocId);
         stat.setDouble(2,Sal);
         stat.setString(3,DoP);
         stat.setString(4,Remark);
         stat.executeUpdate();
         con.close();
         stat.close();
         System.out.println("RECORD SAVED");
        }
        catch(IOException io)
        {
            System.out.println("ERROR:"+io);
        }
         catch(Exception e)
        {
            System.out.println("ERROR:"+e);
        }
        System.out.println("PRESS 'Y' FOR INSERTING NEW RECORD " +
               "AND 'N' FOR MENU"); 
        PCh=sc.next().charAt(0);
        }
        while(PCh=='Y');
    } 
    
    public void deletePayRecord()
   // public static void main(String[] args)
    {
        do
        {
           try
	   {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK3","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM PAYROLL1 where DocId=?");
            System.out.println("**********FOR REMOVING PAYROLL RECORD********");
            System.out.println("ENTER THE DOCTOR'S ID TO BE DELETED: ");
            DocId=Integer.parseInt(br.readLine());
            stat.setInt(1,DocId);
            stat.executeUpdate();
            con.close();
            System.out.println("RECORD DELETED");
            } 
            catch(IOException e1)
            {   
                System.out.println(e1);
            }   
            catch(Exception e2) 
             {      
            System.out.println("Can't be deleted:"+e2);  
            }
            System.out.println("PRESS 'Y' FOR DELETING MORE RECORDS " +
                "AND 'N' FOR MENU");      
            PCh=sc.next().charAt(0);       
        }
        while(PCh=='Y');
    }
    
    public void modifyPayDetails()
    //public static void main(String[] args)
    {
        do
        {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
                PreparedStatement stat=con.prepareStatement("UPDATE PAYROLL1 SET SALARY=?,DATEofPAY=?,REMARKS=? where DocId=?"); 
                System.out.println("**********FOR UPDATING PAYROLL RECORD********");
                System.out.println("PLEASE ENTER THE DOCTOR'S ID: ");
                DocId=Integer.parseInt(br.readLine());
                System.out.println("PLEASE ENTER THE NEW SALARY: ");
                Sal=Double.parseDouble(br.readLine());
                System.out.println("PLEASE ENTER THE NEW PAYMENT DATE: ");
                DoP=br.readLine();
                System.out.println("PLEASE UPDATE THE REMARK: ");
                Remark=br.readLine();
                stat.setInt(4,DocId);
                stat.setString(3,Remark);
                stat.setString(2,DoP);
                stat.setDouble(1,Sal);
                stat.executeUpdate();
                con.close();
                System.out.println("RECORD MODIFIED");
            }
            catch(IOException e1)
            {
                System.out.println(e1);
            }
            catch(Exception e2)
            {
                System.out.println("Can't be modified:"+e2);
            }
            System.out.println("PRESS 'Y' FOR MODIFYING MORE RECORDS " +
                "AND 'N' FOR MENU");      
            PCh=sc.next().charAt(0);       
        }
        while(PCh=='Y');       
    }
    
     public void viewPayDetails()
    //try including a feature to display the pending payments along with the total amount to be paid
    //public static void main(String[] args)
    {
        try
	   {
            String str="SELECT *FROM PAYROLL1";            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_------PAYROLL RECORD--------");

            System.out.println("DOCTOR_ID  SALARY\t PAYMENT_DATE  REMARKS\t");          
            while(rs.next())
            {
                DocId=rs.getInt("DocId");
                Sal=rs.getDouble("SALARY");
                DoP=rs.getString("DATEofPAY");
                Remark=rs.getString("REMARKS");
                System.out.print(DocId+"\t  ");
                System.out.print(Sal+"\t");             
                System.out.print(DoP);
                 System.out.print(Remark);
                 System.out.println("");                 
            }
            con.close();
        }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        } 
    }
}