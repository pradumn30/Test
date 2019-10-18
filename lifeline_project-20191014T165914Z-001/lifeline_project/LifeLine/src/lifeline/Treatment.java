/*
 * Treatment.java
 *
 * Created on 25 July, 2015, 8:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lifeline;

/**
 *
 * @author PRADUMN KUMAR
 */

/*include DRUG AND BILLING*/
import java.io.*;
import java.sql.*;
public class Treatment
{
    public void insertTrtDet()
    // public static void main(String[] args)
    {
        try
        {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        String TreatmentDet;
        int DocId,PatId,OperId;
        System.out.println("PLEASE ENTER THE FOLLOWING DETAILS : ");
        System.out.println("PATIENT ID:");
        PatId=Integer.parseInt(br.readLine());
         System.out.println("CONCERNED DOCTOR'S ID:");
        DocId=Integer.parseInt(br.readLine());
        System.out.println("OPERATIONAL ID:");
        OperId=Integer.parseInt(br.readLine());
        System.out.println("TREATMENT DETAILS: ");
         TreatmentDet=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO TREATMENT VALUES(?,?,?,?)");
         stat.setInt(1,OperId);
         stat.setInt(2,PatId);
         stat.setInt(3,DocId);
         stat.setString(4,TreatmentDet);
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
   public void DelTetDet()
    
   // public static void main(String[] args)
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
    
    /*public static void main(String[] args)
    {
        try{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE TREATMENT SET ?=? where OpId=?");
        System.out.println("PLEASE ENTER THE ATTRIBUTE TO BE MODIFIED:" +
                " 'PatId' or 'TreatDetails' or 'DocId' \t CAUTION:Case Sensitive");
        String TetChoice=br.readLine();
        stat.setString(1,TetChoice);
        System.out.println("PLEASE ENTER THE NEW "+TetChoice);
        String TetUpdt=br.readLine();
        stat.setString(2,TetUpdt);
        System.out.println("PLEASE ENTER THE OPERATIONAL ID: ");
        int TetOpId=Integer.parseInt(br.readLine());
        stat.setInt(3,TetOpId);
        stat.executeUpdate();
        con.close();
        System.out.println(TetChoice+" has been modified");
    }
    catch(IOException e1)
    {
        System.out.println(e1);
    }
    catch(Exception e2)
    {
        System.out.println("Oops! Can't be modified:"+e2);
    }
    } */
    

public void modifyTetDetails()
//public static void main(String[] args)
{
    try
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE TREATMENT1 SET TreatDetails=?,OpId=?,DocId=? where PatId=?");
        /*System.out.println("PLEASE ENTER THE ATTRIBUTE TO BE MODIFIED:" +
                " 'PatId' or 'TreatDetails' or 'DocId' \t CAUTION:Case Sensitive");
        String TetChoice=br.readLine();
        stat.setString(1,TetChoice);*/
        System.out.println("PLEASE ENTER THE PATIENT ID: ");
        int TetPatId=Integer.parseInt(br.readLine());
        System.out.println("PLEASE ENTER THE NEW TREATMENT DETAILS: ");
        String TetUpdt=br.readLine();
        System.out.println("PLEASE ENTER THE  NEW OPERATIONAL ID: ");
        int TetOpId=Integer.parseInt(br.readLine());
        System.out.println("PLEASE ENTER THE NEW DOCTOR ID: ");
        int TetDocId=Integer.parseInt(br.readLine());
        stat.setString(1,TetUpdt);
        stat.setInt(2,TetOpId);
        stat.setInt(3,TetDocId);        
        stat.setInt(4,TetPatId);
        stat.executeUpdate();
        con.close();
        System.out.println("Treatment Details has been modified");
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
    public void viewTetDetails()
   // public static void main(String[] args)
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
            System.out.println("Operational_Id \t Pat_id \t  Doctor_Id \t Treatment_details ");
                       
            while(rs.next())
            {
                int TetOpId=rs.getInt("OpId");
                int TetPatId=rs.getInt("PatId");
                int TetDocId=rs.getInt("DocId");
                String TetDetls=rs.getString("TreatDetails");
                System.out.print("   "+TetOpId+"\t\t");
                System.out.print("    "+TetPatId+"\t\t");
                 System.out.print("  "+TetDocId+"\t\t");
                System.out.print("   "+TetDetls+"\t");
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
