/*
 * PatReg.java
 *
 * Created on 22 July, 2015, 9:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lifeline;

/**
 *
 * @author PRADUMN KUMAR
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class PatReg
{
    char PrCh;
    String PName,PAddress,Problem,PHistory,PreHosp,PreDoc;
    int PDocId,PatId;
    
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    Scanner sc=new Scanner(System.in);
    
    public void displayPatRegMenu()
    {
        try
        {        
        System.out.println("----YOU ARE CURRENTLY IN PATIENT REGISTRATION SECTION-----");
        System.out.println("PLEASE ENTER YOUR CHOICE : ");
        System.out.println("PRESS 1 TO Add a Patient \n" +
                   "PRESS 2 FOR Modifying Existing Details\n" +
                   "PRESS 3 TO Discharge a Patient\n" +
                   "PRESS 4 TO View Existing Patient Records\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
             int Pchoice;
            Pchoice=Integer.parseInt(br.readLine());
            PatReg pg=new PatReg();
           if(Pchoice==1)
           {
              pg.insertPatDetails();
           }
           else if(Pchoice==2)
           {
              pg.modifyPatRecord();                             
           }
           else if(Pchoice==3)
           {
               pg.deletePatRecord();
               
           }
           else if(Pchoice==4)
           {
                pg.viewPatRecord();
           }
            else if(Pchoice==5)
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
            PatReg pr=new PatReg();
            pr.displayPatRegMenu();
        }
    }
    public void deletePatRecord()
   // public static void main(String[]args)
    {       
        do
        {
           try
	   {            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM PATIENT_DETAILS where PatId=?");
            System.out.println("*******FOR DISCHARGING A PATIENT*******");
            System.out.println("ENTER THE PATIENT ID TO BE DELETED: ");
            PatId=Integer.parseInt(br.readLine());
            stat.setInt(1,PatId);
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
            System.out.println("PRESS 'Y' FOR DISCHARGING MORE PATIENTS " +
               "AND 'N' FOR MENU"); 
            PrCh=sc.next().charAt(0);
            }
            while(PrCh=='Y');
    }
    
    public void modifyPatRecord()
   // public static void main(String[]args)
    {        
        do
        {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE PATIENT_DETAILS SET Address=?,Issue=?,DocId=? where PatId=?");
        System.out.println("*******FOR DETAILS MODIFICATION*******");
        System.out.println("PLEASE ENTER THE PATIENT ID: ");
        PatId=Integer.parseInt(br.readLine());
        System.out.println("PLEASE ENTER THE NEW ADDRESS: ");
        PAddress=br.readLine();
        System.out.println("PLEASE ENTER THE ISSUE: ");
        Problem=br.readLine();
        System.out.println("PLEASE ENTER THE NEW DOCTOR ID: ");
        PDocId=Integer.parseInt(br.readLine());
        stat.setString(1,PAddress);
        stat.setString(2,Problem);
        stat.setInt(3,PDocId);        
        stat.setInt(4,PatId);
        stat.executeUpdate();
        con.close();
        System.out.println("Patient Details has been modified");
    }
    catch(IOException e1)
    {
        System.out.println(e1);
    }
    catch(Exception e2)
    {
        System.out.println("Oops! Can't be modified:"+e2);
    }
    System.out.println("PRESS 'Y' FOR MODIFYING ANOTHER PATIENT DETAILS " +
               "AND 'N' FOR MENU"); 
    PrCh=sc.next().charAt(0);
    }
    while(PrCh=='Y'); 
    }

    public void insertPatDetails() 
   //public static void main(String[] args )
    { 
        do
        {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        System.out.println("*******FOR PATIENT REGISTRATION*******");
        System.out.println("PLEASE ENTER THE FOLLOWING DETAILS ABOUT PATIENT ");
        System.out.println(" ID:");
        PatId=Integer.parseInt(br.readLine());
        System.out.println("CONCERNED DOCTOR'S ID:");
        PDocId=Integer.parseInt(br.readLine());
        System.out.println("NAME:");
        PName=br.readLine();
        System.out.println("ADDRESS:");
        PAddress=br.readLine();
        System.out.println("ISSUE:");
        Problem=br.readLine();
        System.out.println("PATIENT'S MEDICAL HISTORY:");
        PHistory=br.readLine();
        System.out.println("PREVIOUS HOSPITAL:");
        PreHosp=br.readLine();
        System.out.println("PREVIOUS DOCTOR:");
        PreDoc=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO PATIENT_DETAILS VALUES(?,?,?,?,?,?,?,?)");
         stat.setInt(1,PatId);
         stat.setString(3,PName);
         stat.setString(4,PAddress);
         stat.setString(5,PHistory);
         stat.setString(6,PreHosp);
         stat.setString(8,PreDoc);
         stat.setInt(2,PDocId);
         stat.setString(7,Problem);
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
        System.out.println("PRESS 'Y' FOR ADDING MORE PATIENT DETAILS " +
               "AND 'N' FOR MENU"); 
        PrCh=sc.next().charAt(0);
    }
    while(PrCh=='Y'); 
    }
    
     public void viewPatRecord()
    // public static void main(String[]args)
    {
        try
	   {
            String str="SELECT *FROM PATIENT_DETAILS";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_-------PATIENT REGISTRATION RECORD--------");
            System.out.println("Patient_Id  Name\t  Address \t Issue \t Medical_history\t Previous_hospital\t Previous_Doc\t Doctor_Id ");
                       
            while(rs.next())
            {
               PatId=rs.getInt("PatId");
               PDocId=rs.getInt("DocId");
               PName=rs.getString("Name");
               PAddress=rs.getString("Address");
               PHistory=rs.getString("PatHistory");
               PreHosp=rs.getString("PHosp");
               Problem=rs.getString("Issue");
               PreDoc=rs.getString("PDoc");
               System.out.print("  "+PatId+"\t");
               System.out.print("  "+PName+"\t");
               System.out.print("  "+PAddress+"\t");
               System.out.print("   "+Problem+"\t");
               System.out.print("    "+PHistory+"\t");
               System.out.print("    "+PreHosp+"\t");
               System.out.print("    "+PreDoc+"\t");
               System.out.print("    "+PDocId+"\t");
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

        

