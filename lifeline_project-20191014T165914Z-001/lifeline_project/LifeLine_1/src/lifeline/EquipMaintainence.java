/*
 * EquipMaintainence.java
 *
 * Created on 29 July, 2015, 10:38 AM

 */

package lifeline;

/*
 * @author PRADUMN KUMAR
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class EquipMaintainence
{
    static char ECh;
    String EName,ReqEqup;
    double EQuan,EPriceUnit,ETotalCost;
    
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    Scanner sc=new Scanner(System.in);
    
    //public static void main(String[] args)
    public void displayEqupMenu()
    {
        EquipMaintainence em=new EquipMaintainence();
        try
        {
            System.out.println("----YOU ARE CURRENTLY IN EQUIPMENT MAINTAINENCE SECTION-----");
            System.out.println("PRESS 1 TO Add an Equipment\n" +
                   "PRESS 2 TO View Equipment Record\n" +
                   "PRESS 3 TO Discard an Equipment\n" +
                   "PRESS 4 TO Modify Equipment Details\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
            int EChoice;
            EChoice=Integer.parseInt(br.readLine());
           //EquipMaintainence em=new EquipMaintainence();
           if(EChoice==1)
           {
              em.insertEqupDetails();
           }
           else if(EChoice==2)
           {
               em.viewEqupDetails();               
           }
           else if(EChoice==3)
           {
               em.deleteEqupt();               
           }
           else if(EChoice==4)
           {
                //EquipMaintainence em=new EquipMaintainence();
                //em.stockModify();
                em.modifyEqupDetails();                               
           }
            else if(EChoice==5)
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
            em.displayEqupMenu();
        }
    }
    
   public void insertEqupDetails()
   // public static void main(String[] args)
    {       
        do
        {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        System.out.println("**********FOR ADDING EQUIPMENTS********");
        System.out.println("ENTER THE NAME OF AVAILABLE APPARATUS:");
        EName=br.readLine();
         System.out.println("NO. OF UNITS AVAILABLE:");       
        EQuan=Double.parseDouble(br.readLine());
        System.out.println("PRICE PER UNIT: ");
        EPriceUnit=Double.parseDouble(br.readLine());
        ETotalCost=EQuan*EPriceUnit;        
        System.out.println("REQUIREMENT:");
         ReqEqup=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO EQUIPMENT_MAINTN VALUES(?,?,?,?,?)");
         stat.setString(1,EName);
         stat.setDouble(2,EQuan);
         stat.setString(5,ReqEqup);
         stat.setDouble(3,EPriceUnit);
         stat.setDouble(4,ETotalCost);
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
        ECh=sc.next().charAt(0);
        }
        while(ECh=='Y');        
    }
   
    public void deleteEqupt()
    //public static void main(String[] args)
    {
        do
        {
           try
	   {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK3","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM EQUIPMENT_MAINTN where AvailableEquipments=?");
            System.out.println("**********FOR DISCARDING EQUIPMENTS********");
            System.out.println("ENTER THE NAME OF EQUIPMENT TO BE DELETED: ");
            EName=(br.readLine());
            stat.setString(1,EName);
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
            ECh=sc.next().charAt(0);       
        }
        while(ECh=='Y');
    } 

   
    public void modifyEqupDetails()
    //public static void main(String[] args)
    {
        do
        {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
                PreparedStatement stat=con.prepareStatement("UPDATE EQUIPMENT_MAINTN SET Quantity=? where AvailableEquipments=?");
                String str="SELECT *FROM EQUIPMENT_MAINTN";            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con1=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat1=con1.createStatement();
            ResultSet rs1=stat1.executeQuery(str); 
            System.out.println("**********FOR MODIFYING EQUIPMENT DETAILS********");
            System.out.println("PLEASE ENTER THE NAME OF EQUIPMENT : ");
            String EName2=(br.readLine());
            System.out.println("PLEASE ENTER THE NO.OF UNITS DISCARDED: ");
            Eunit=Integer.parseInt(br.readLine());
            
            while(rs1.next())
            {
                String EName1=rs1.getString("AvailableEquipments");
                System.out.println(EName1);
                if(EName2.equalsIgnoreCase(EName1))
                {
                    int EQuan1=rs1.getInt("Quantity");
                    int ELeft=EQuan1-Eunit;
                    System.out.println("NO.OF UNITS LEFT ARE "+ELeft);
                }
            }
            con1.close();            
                stat.setInt(1,ELeft);
                stat.setString(2,EName);
                stat.executeUpdate();
                con.close();
                System.out.println("RECORD MODIFIED");
            }       
            catch(Exception e2)
            {
                System.out.println("Can't be modified:"+e2);
            }
            System.out.println("PRESS 'Y' FOR MODIFYING MORE RECORDS " +
                "AND 'N' FOR MENU");      
            ECh=sc.next().charAt(0);       
        }
        while(ECh=='Y');       
    }
    
    public void viewEqupDetails()
   //public static void main(String[] args)
    {
        try
	   {
            String str="SELECT *FROM EQUIPMENT_MAINTN";            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_------EQUIPMENT RECORD--------");
            System.out.println("AvailableEquipments\t QUANTITY\tPRICE_PER_UNIT\tTOTAL_PRICE  REQUIRED APPARATUS");
                    
            while(rs.next())
            {
                EName=rs.getString("AvailableEquipments");
                EQuan=rs.getInt("Quantity");
                EPriceUnit=rs.getDouble("PricePerUnit");
                ETotalCost=rs.getDouble("TotalCost");
                ReqEqup=rs.getString("RequiredAppt");
                System.out.print(EName+"\t");
                System.out.print(EQuan+"\t\t");
                System.out.print(EPriceUnit+"\t\t");
                 System.out.print(ETotalCost+"\t\t");
                System.out.print(ReqEqup);
                 System.out.println("");                 
            }
            con.close();
            }
            catch(Exception e2) 
            {      
            System.out.println("ERROR"+e2);  
            } 
    }
    int EQuan1;
    int ELeft;
    int Eunit;
   
}
        


