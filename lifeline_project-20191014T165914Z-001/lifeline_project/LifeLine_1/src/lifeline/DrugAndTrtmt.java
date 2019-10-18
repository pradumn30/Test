/* * DrugAndTrtmt.java
 * Created on 31 July, 2015, 4:35 PM*/

package lifeline;

/* @author PRADUMN KUMAR*/
 
import java.io.*; 
import java.sql.*;
import java.util.Scanner;

public class DrugAndTrtmt 
{
    public void displayTrtmtMenu()
    {
        DrugAndTrtmt dt=new DrugAndTrtmt();        
        try
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----YOU ARE CURRENTLY IN DRUG AND TREATMENT SECTION-----");
            System.out.println("PRESS 1 TO Add a Treatment Record/n"+
                    "PRESS 2 TO View Treatment Records\n"+
                   "PRESS 3 TO Generate Bill\n" +
                   "PRESS 4 TO Delete a Record\n" +
                   "PRESS 5 TO Modify Treatment Details\n" +
                   "PRESS 6 FOR Main Menu\n" +
                   "PRESS 7 FOR Exit");
            
            int DtChoice;
            DtChoice=Integer.parseInt(br.readLine());
           if(DtChoice==1)
           {
              dt.insertTrtmtDet();
           }
           else if(DtChoice==2)
           {
               dt.viewTrtmtDetails();
           }
           else if(DtChoice==3)
           {
               dt.generateBill();
           }
            else if(DtChoice==4)
           {
               dt.delTrtmtDetails();
           }
           else if(DtChoice==5)
           {               
                dt.modifyTetDetails();
           }
           else if(DtChoice==6)
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
           dt.displayTrtmtMenu(); 
        }
    }
    
    public void insertTrtmtDet()
    //public static void main(String[] args)
    {
        try
        {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        String TDetails,TDrugs;
        int TDocId,TPatId,OperId;
        System.out.println("PLEASE ENTER THE FOLLOWING DETAILS : ");
        System.out.println("OPERATIONAL ID:");
        OperId=Integer.parseInt(br.readLine());
        System.out.println("PATIENT ID:");
        TPatId=Integer.parseInt(br.readLine());
         System.out.println("CONCERNED DOCTOR'S ID:");
        TDocId=Integer.parseInt(br.readLine());
        System.out.println("TREATMENT DETAILS: ");
         TDetails=br.readLine();
         System.out.println("DRUGS RECOMMENDED: \t /*USE COMMA SEPARATED ENTRIES*/");
         TDrugs=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO TREATMENT VALUES(?,?,?,?,?)");
         stat.setInt(1,OperId);
         stat.setInt(2,TPatId);
         stat.setInt(3,TDocId);
         stat.setString(4,TDetails);
         stat.setString(5,TDrugs);
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
        
    }
    
    public void delTrtmtDetails()    
    //public static void main(String[] args)
    {
        
           try
	   {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM TREATMENT where OpId=?");
            System.out.println("ENTER THE OPERATIONAL ID TO BE DELETED: ");
            int OpId=Integer.parseInt(br.readLine());
            stat.setInt(1,OpId);
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
                System.out.println("can't be deleted:"+e2);
            }
    }
    
   public void modifyTetDetails()
   //public static void main(String[] args)
{
    try
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE TREATMENT SET Treatment_Details=?,Recommended_Drugs=? where OpId=?");        
        System.out.println("PLEASE ENTER THE OPERATIONAL ID TO BE MODIFIED: ");
        int OpId=Integer.parseInt(br.readLine());
        System.out.println("PLEASE ENTER THE NEW TREATMENT DETAILS: ");
        String TDetails=br.readLine();
        System.out.println("PLEASE ENTER THE REVISED DRUG PRESCRIPTION: ");
        String TDrugs=(br.readLine());
        stat.setString(1,TDetails);
        stat.setString(2,TDrugs);
        stat.setInt(3,OpId);        
        stat.executeUpdate();
        con.close();
        System.out.println("Treatment Details has been modified successfully");
    }
    catch(IOException e1)
    {
        System.out.println(e1);
    }
    catch(Exception e2)
    {
        System.out.println("Oops! Can't be modified:"+e2);
    }
}
   
    public void viewTrtmtDetails()
    //public static void main(String[] args)
    {
        try
	   {
            String str="SELECT *FROM TREATMENT";
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_-------TREATMENT RECORD--------");
            System.out.println("Operational_Id \t Patient_id \t  Doctor_Id \t Treatment_details \t Prescribed_Drugs");
                       
            while(rs.next())
            {
                int OpId=rs.getInt("OpId");
                int TPatId=rs.getInt("Patient_Id");
                int TDocId=rs.getInt("Doctor_Id");
                String TDetails=rs.getString("Treatment_Details");
                String TDrugs=rs.getString("Recommended_Drugs");
                System.out.print(OpId+"\t\t");
                System.out.print(TPatId+"\t\t");
                 System.out.print(TDocId+"\t\t");
                System.out.print(TDetails);
                System.out.print(TDrugs);
                 System.out.println("");
             }
            con.close();
            }
            catch(Exception e2) 
            {      
            System.out.println("ERROR"+e2);  
            }        
    }
    
   public void generateBill()
   //public static void main(String[] args)                                          ////////doubt
    {
        String TDrugs="";
        try
	   {
            String str="SELECT *FROM TREATMENT";            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("*******BILL*****");            
            System.out.println("ENTER THE OPERATIONAL ID WHOSE BILL IS TO BE CREATED:");            
            int InpOpId=Integer.parseInt(br.readLine()); 
            System.out.println("--------LIFELINE----------");
            double PPu;
            double Total=0.0;                   
            while(rs.next())                                                                                     
            {
                int OpId=rs.getInt("OpId");
                if(InpOpId==OpId)
                {
                    int TPatId=rs.getInt("Patient_Id");
                    String TDetails=rs.getString("Treatment_Details");
                    TDrugs=rs.getString("Recommended_Drugs");
                    System.out.println("Operational_Id ="+InpOpId+"\n" +
                            "Patient_id ="+TPatId+"\n" +
                            "Treatment_details ="+TDetails);
                    System.out.print("Prescribed_Drugs= ");
                    String[] array=TDrugs.split(",");
                    String DgName;
                   // double PPu;
                   // double Total=0.0;
                    for(int i=0;i<array.length;i++)
                    {
                        System.out.print(array[i]+"\t");
                    }
                    System.out.println();
                    /*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con1=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
                    Statement stmt=con1.createStatement();
                    ResultSet rs1=stmt.executeQuery("SELECT *from MStore1");
                       
                    for(String drug:array)
                    {                    
                       //String str2="SELECT PricePerUnit from MStore1"; //WHERE Drug_Name IN("+drug+")";
                        
                       while(rs1.next())
                       {
                           DgName=rs1.getString("Drug_Name");
                           if(DgName.equals(drug))
                           {
                               PPu=rs1.getDouble("PricePerUnit");
                               System.out.print(PPu+"\t");
                               Total+=PPu;
                           }
                       }
                    }
                    System.out.println("\n TOTAL="+Total);
                    con1.close();
                */
                }
            }
            con.close();
        }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        }        
    }
}
    
    
   /* public static void main(String[] args)                                          ////////doubt
    {
        //String TDrugs="";
        try
	   {
            String str="SELECT *FROM TREATMENT";            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("*******BILL*****");            
            System.out.println("ENTER THE OPERATIONAL ID WHOSE BILL IS TO BE CREATED:");            
            int InpOpId=Integer.parseInt(br.readLine()); 
            System.out.println("--------LIFELINE----------");
           // double PPu;
           // double Total=0.0; 
            String TDrugs;
            while(rs.next())                                                                                     
            {
                int OpId=rs.getInt("OpId");
                if(InpOpId==OpId)
                {
                    int TPatId=rs.getInt("Patient_Id");
                    String TDetails=rs.getString("Treatment_Details");
                    TDrugs=rs.getString("Recommended_Drugs");
                    System.out.println("Operational_Id ="+InpOpId+"\n" +
                            "Patient_id ="+TPatId+"\n" +
                            "Treatment_details ="+TDetails);
                    System.out.print("Prescribed_Drugs= ");
                    String[] array=TDrugs.split(",");
                    //String DgName;
                    
                    for(int i=0;i<array.length;i++)
                    {
                        System.out.print(array[i]+"\t");
                    }
                    System.out.println();
                    
                    //does not execute from here
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con1=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
                    Statement stmt=con1.createStatement();
                    ResultSet rs1=stmt.executeQuery("SELECT Drug_Name ,PricePerUnit from MStore1");
                    double PPu;
                    double Total=0;   
                    for(String drug:array)
                    {                    
                       //String str2="SELECT PricePerUnit from MStore1"; //WHERE Drug_Name IN("+drug+")";
                        
                       while(rs1.next())
                       {
                           String DgName=rs1.getString("Drug_Name");
                           if(drug.equals(DgName))
                           {
                               PPu=rs1.getDouble("PricePerUnit");
                               System.out.print(PPu+"\t");
                               Total=Total+PPu;
                           }
                       }
                    }
                    System.out.println("\n TOTAL="+Total);
                    con1.close();
                }
            }
            con.close();
        }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        }        
    }
}*/
                     