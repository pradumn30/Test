/*
 * InsertR.java
 *
 * Created on 24 July, 2015, 11:06 AM
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
public class InsertR
{
    public static void main(String[] args)
    {
        try
        {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        String PatName,Address;
        int DocId,PatId;
        System.out.println("PLEASE ENTER THE FOLLOWING DETAILS ABOUT PATIENT ");
        System.out.println(" ID:");
        PatId=Integer.parseInt(br.readLine());
         System.out.println("CONCERNED DOCTOR'S ID:");
        DocId=Integer.parseInt(br.readLine());
        System.out.println("NAME:");
        PatName=br.readLine();
        System.out.println("ADDRESS:");
         Address=br.readLine();
         PreparedStatement stat=con.prepareStatement("INSERT INTO PATIENT_DETAILS VALUES(?,?,?,?)");
         stat.setString(1,PatName);
         stat.setInt(2,PatId);
         stat.setString(3,Address);
         stat.setInt(4,DocId);
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
   
    
}
