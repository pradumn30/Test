/*
 * MedicalStore.java
 * Created on 30 July, 2015, 8:43 PM
 */

package lifeline;

/**
 *
 * @author PRADUMN KUMAR
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class MedicalStore
{
    String DgName,DgExpDate;;
    double DgPriceUnit;
    int DgQuan;
    char DgCh;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    
    //public static void main(String[] args)
    public void displayMStoreMenu()
    {
        MedicalStore ms=new MedicalStore();
        try
        {            
            System.out.println("----YOU ARE CURRENTLY IN MEDICAL STORE SECTION-----");
            System.out.println("PRESS 1 TO Add a drug\n" +
                   "PRESS 2 TO View out of stock drugs\n" +
                   "PRESS 3 TO Discard a drug\n" +
                   "PRESS 4 TO Modify Drug Details\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
            int MsChoice;
            MsChoice=Integer.parseInt(br.readLine());           
           if(MsChoice==1)
           {
              ms.insertDrug();
           }
           else if(MsChoice==2)
           {
              ms.lowStockDrugs();
           }
           else if(MsChoice==3)
           {
               ms.discardDrug();
           }
           else if(MsChoice==4)
           {               
                ms.modifyDrugDetails(); 
           }
            else if(MsChoice==5)
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
            ms.displayMStoreMenu();
        }
    }
    
    public void lowStockDrugs()
    //public static void main(String[] args)
    {
        try
	   {
            String str="SELECT *FROM MStore1";            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_------LOW IN STOCK DRUGS--------");
            System.out.println("DRUG NAME\t QUANTITY PRICE(PER UNIT)");
            
            while(rs.next())
            { 
                DgName=rs.getString("Drug_Name");
                DgPriceUnit=rs.getDouble("PricePerUnit");
                DgQuan=rs.getInt("Quantity");                
                if(DgQuan<=5)
                {                    
                    System.out.print(DgName+"\t\t");
                    System.out.print(DgQuan+"\t");
                    System.out.print(DgPriceUnit);
                    System.out.println("");    
                }
            }
           con.close();
            }
            catch(Exception e2) 
            {      
            System.out.println("ERROR"+e2);  
            } 
    }
    
    public void insertDrug()
    //public static void main(String[] args)
    {
        do   {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");      
        System.out.println("ENTER THE NAME OF AVAILABLE DRUGS:");
        DgName=br.readLine();
        System.out.println("NO. OF UNITS AVAILABLE:");       
        DgQuan=Integer.parseInt(br.readLine());
        System.out.println("PRICE PER UNIT: ");
        DgPriceUnit=Double.parseDouble(br.readLine());       
        System.out.println("EXPIRY DATE:");
         DgExpDate=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO MStore1 VALUES(?,?,?,?)");
         stat.setString(1,DgName);
         stat.setInt(2,DgQuan);
         stat.setDouble(3,DgPriceUnit);
         stat.setString(4,DgExpDate);
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
        
        System.out.println("PRESS 'Y' FOR INSERTING NEW DRUG " +
                "AND 'N' FOR MENU"); 
        Scanner sc=new Scanner(System.in);
        DgCh=sc.next().charAt(0);
        }
        while(DgCh=='Y');        
    }
     
     public void discardDrug()
     //public static void main(String[] args)
     {
        do  {
           try
	   {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK3","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM MStore1 where Drug_Name=?");
            System.out.println("ENTER THE NAME OF DRUG TO BE DISCARDED: ");
            String DgName=(br.readLine());
            stat.setString(1,DgName);
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
            System.out.println("PRESS 'Y' FOR DISCARDING MORE DRUGS " +
                "AND 'N' FOR MENU");  
            Scanner sc=new Scanner(System.in);
           DgCh=sc.next().charAt(0);       
        }
        while(DgCh=='Y');
     }
     
      public void modifyDrugDetails()
    //public static void main(String[] args)
    {        
        do   {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
                PreparedStatement pstmt=con.prepareStatement("UPDATE MStore1 SET Quantity=? where Drug_Name=?");                 
                System.out.println("PLEASE ENTER THE NAME OF DRUG: ");
                String InpName=(br.readLine());
                System.out.println("PLEASE ENTER THE NO.OF UNITS DISCARDED: ");
                int DgUnit=Integer.parseInt(br.readLine());
                //try try try try try try try try try try try try try try try try try try try try try try try try try try try try try  
                String str="SELECT *FROM MStore1"; 
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(str);
                int DgQuan=0;
                 while(rs.next())
                 {
                    String SearchName=rs.getString("Drug_Name");                
                    if(SearchName.equals(InpName))
                    {
                        DgQuan=rs.getInt("Quantity");                      
                    }
                 }
                int DgLeft=0;
                DgLeft=DgQuan-DgUnit;
                System.out.println("NO.OF UNITS LEFT ARE "+DgLeft);
                pstmt.setInt(1,DgLeft);
                pstmt.setString(2,InpName);          
                pstmt.executeUpdate();
                con.close();
                System.out.println("RECORD MODIFIED");
            }
            catch(IOException e1)
            {
                System.out.println("ERROR: "+e1);
            }
            catch(Exception e2)
            {
                System.out.println("Can't be modified:"+e2);
            }
            Scanner sc=new Scanner(System.in);
           DgCh=sc.next().charAt(0);       
        }
        while(DgCh=='Y');      
    } 
}
   