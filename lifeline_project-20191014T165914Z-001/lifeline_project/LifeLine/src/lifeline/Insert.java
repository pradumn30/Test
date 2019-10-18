/*
 * Insert.java
 *
 * Created on 22 July, 2015, 1:01 PM
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
public class Insert 
{
public static void main(String[] args)
{
  try{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        PreparedStatement stat=con.prepareStatement("insert into product values(?,?,?,?,?)");
        System.out.println("enter Patient details(id,description,rate,qty,unit):");
        int id=Integer.parseInt(br.readLine());
        String des=br.readLine();
        double rat=Double.parseDouble(br.readLine());
        int qty=Integer.parseInt(br.readLine());
        String unit=br.readLine();
        stat.setInt(1,id);
        stat.setString(2,des);
        stat.setInt(4,qty);
        stat.setDouble(3,rat);
        stat.setString(5,unit);
        stat.executeUpdate();
        con.close();
        System.out.println("RECORD Inserted");
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


