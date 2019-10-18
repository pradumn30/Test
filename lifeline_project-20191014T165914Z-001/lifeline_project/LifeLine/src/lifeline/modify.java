/*
 * modify.java
 *
 * Created on 22 July, 2015, 1:08 PM
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
public class modify 
{
    public static void main(String[]args)
    {
    try{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("UPDATE PATIENT_DETAILS SET ?=? where PatId=?");
        System.out.println("PLEASE ENTER THE ATTRIBUTE TO BE MODIFIED:" +
                " 'Name' or 'Address' or 'DocId' \t CAUTION:Case Sensitive");
        String ch10=br.readLine();
        stat.setString(1,ch10);
        System.out.println("PLEASE ENTER THE NEW "+ch10);
        String updt=br.readLine();
        stat.setString(2,updt);
        System.out.println("PLEASE ENTER THE PATIENT'S ID: ");
        int p_id=Integer.parseInt(br.readLine());
        stat.setDouble(3,p_id);
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
        System.out.println("can't be deleted:"+e2);
    }
    }
}
