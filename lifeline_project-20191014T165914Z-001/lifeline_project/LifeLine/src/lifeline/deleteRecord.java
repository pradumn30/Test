/*
 * deleteRecord.java
 *
 * Created on 22 July, 2015, 1:13 PM
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
public class deleteRecord 
{    
    public static void main(String[]args)throws IOException
    {
        try
	   {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK3","administrator"," ");
            PreparedStatement stat=con.prepareStatement("DELETE FROM PATIENT_DETAILS where PatId=?");
            System.out.println("ENTER THE PATIENT ID TO BE DELETED: ");
            int Id=Integer.parseInt(br.readLine());
            stat.setInt(1,Id);
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
}
