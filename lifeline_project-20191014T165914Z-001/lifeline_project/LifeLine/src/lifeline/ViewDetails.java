/*
 * ViewDetails.java
 *
 * Created on 23 July, 2015, 8:06 PM
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
public class ViewDetails
{
    public static void main(String[]args)throws IOException
    {
        try
	   {
            String str="SELECT *FROM PATIENT_DETAILS";
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("\t_-------PATIENT REGISTRATION RECORD--------");
            System.out.println("Patient_Id \t Name \t Address \t Doctor_Id ");
                       
            while(rs.next())
            {
                System.out.println("OK!!");
                //String Pi=Integer.toString(rs.getInt("PatId"));
                String n=rs.getString("Name");
                String Add=rs.getString("Address");
                int Di=rs.getInt("DocId");
                //System.out.print(Pi+"\t");
                if((n.length())<=7)
                {
                    System.out.print(n+"\t\t");
                }
                else
                {
                    System.out.print(n+"\t");
                }
                if((Add.length())<=7)
                {
                    System.out.print(Add+"\t\t");
                }
                else
                {
                    System.out.print(Add+"\t");
                }
                //System.out.print(Pi+"\t");
                con.close();
                stat.close();
                }
            }
            catch(Exception e2) 
            {      
            System.out.println("ERROR"+e2);  
            }
    }
            
}
     