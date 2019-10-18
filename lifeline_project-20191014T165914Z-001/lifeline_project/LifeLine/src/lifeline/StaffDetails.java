
package lifeline;

/**
 *
 * @author PRADUMN KUMAR
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class StaffDetails
{
    static char SCh;
    String DName,DAddress,Speciality;
    int DocId;
    
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    Scanner sc=new Scanner(System.in);
    
    //public static void main(String[] args)
    public void displayStaffMenu()
    {  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
        StaffDetails sd=new StaffDetails();
        try
        {            
            System.out.println("----YOU ARE CURRENTLY IN STAFF MANAGEMENT SECTION-----");
            System.out.println("PRESS 1 TO Add a Staff\n" +
                   "PRESS 2 TO View All Staff Members\n" +
                   "PRESS 3 TO Remove a Staff\n" +
                   "PRESS 4 TO Modify Existing Details\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
           int SChoice;
           SChoice=Integer.parseInt(br.readLine());
           if(SChoice==1)
           {
              sd.insertStaffDetails();
           }
           else if(SChoice==2)
           {
               sd.viewStaffDetails();               
           }
           else if(SChoice==3)
           {
               sd.delStaff();               
           }
           else if(SChoice==4)
           {               
                sd.modifyStaffDet();                               
           }
            else if(SChoice==5)
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
            sd.displayStaffMenu();
        }
    }
    
    public void insertStaffDetails()
    //public static void main(String[] args)
    {
        do
        {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        System.out.println("****FOR INSERTING DETAILS*****");
        System.out.println("PLEASE ENTER THE FOLLOWING DETAILS ABOUT DOCTOR: ");
        System.out.println(" ID:");
        DocId=Integer.parseInt(br.readLine());
         System.out.println(" NAME:");
        DName=(br.readLine());
        System.out.println("ADDRESS:");
        DAddress=br.readLine();
        System.out.println("SPECIALITY:");
         Speciality=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO DOCTOR VALUES(?,?,?,?)");
         stat.setInt(1,DocId);
         stat.setString(2,DName);
         stat.setString(3,DAddress);
         stat.setString(4,Speciality);
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
                "OR 'N' FOR MENU");      
        SCh=sc.next().charAt(0);
        }
        while(SCh=='Y');       
    }
    public void viewStaffDetails()
    //public static void main(String[] args)
    {          
        int SCount=0;   
        try
        {
            String str="SELECT *FROM DOCTOR";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_-------STAFF RECORD--------");
            System.out.println("Doctor_Id  Name\t\t Address\t\t Speciality ");
                    
            while(rs.next())
            {
                DocId=rs.getInt("DocId");
                DName=rs.getString("Name");
                DAddress=rs.getString("Address");
                Speciality=rs.getString("Speciality");
                System.out.print(" "+DocId+"\t");
                System.out.print("    "+DName+"\t");
                 System.out.print(DAddress+"\t");
                System.out.print("   "+Speciality);
                 System.out.println("");
                 SCount+=1;
            }
            con.close();
            }
            catch(Exception e2) 
            {      
            System.out.println("ERROR"+e2);  
            } 
        //return SCount;
        System.out.println("CURRENTLY, "+SCount+" STAFFS ARE REGISTERED WITH LIFELINE");
        
    }
    public void delStaff()
    // public static void main(String[] args)
     {
        do
        {
            try
            {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM DOCTOR where DocId=?");
            System.out.println("****FOR DISCARDING DETAILS*****");
            System.out.println("ENTER THE DOCTOR ID TO BE DELETED: ");
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
                System.out.println("can't be deleted:"+e2);
            }
            System.out.println("PRESS 'Y' FOR DELETING MORE RECORDS " +
                "AND 'N' FOR MENU");      
            SCh=sc.next().charAt(0);       
        }
        while(SCh=='Y');
    }
     
    public void modifyStaffDet()
    //public static void main(String[] args)
    {
        do
        {
            try
            {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE DOCTOR SET Name=?,Address=?,Speciality=? where DocId=?");
        System.out.println("****FOR MODIFYING DETAILS*****");
        System.out.println("PLEASE ENTER THE DOCTOR ID: ");
        DocId=Integer.parseInt(br.readLine());
        System.out.println("PLEASE ENTER THE NEW NAME: ");
        DName=br.readLine();
        System.out.println("PLEASE ENTER ADDRESS: ");
        DAddress=br.readLine();
        System.out.println("PLEASE ENTER SPECIALITY: ");
        Speciality=(br.readLine());
        stat.setString(1,DName);
        stat.setString(2,DAddress);
        stat.setString(3,Speciality);        
        stat.setInt(4,DocId);
        stat.executeUpdate();
        con.close();
        System.out.println("Staff Details has been modified");
    }
    catch(IOException e1)
    {
        System.out.println(e1);
    }
    catch(Exception e2)
    {
        System.out.println("Oops! Can't be modified:"+e2);
    }
    System.out.println("PRESS 'Y' FOR DELETING MORE RECORDS " +
                "AND 'N' FOR MENU");      
    SCh=sc.next().charAt(0); 
    }
    while(SCh=='Y');   
    }
}

