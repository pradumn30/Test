/* Drug.java
 * Created on 27 July, 2015, 8:33 PM */

package lifeline;

//@author PRADUMN KUMAR
import java.io.*; 
import java.sql.*;
import java.util.Scanner;
public class Drug 
{
     Scanner sc=new Scanner(System.in);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    
    
    //public static void main(String[] args)
    public void displayPayMenu()
    {
        PayrollMang pm=new PayrollMang();
        try
        {
            System.out.println("----YOU ARE CURRENTLY IN PAYROLL SECTION-----");
            System.out.println("PRESS 1 TO Add a Record\n" +
                   "PRESS 2 TO View a Record\n" +
                   "PRESS 3 TO Remove a Record\n" +
                   "PRESS 4 TO Modify Existing Details\n" +
                   "PRESS 5 FOR Main Menu\n" +
                   "PRESS 6 FOR Exit");
            
            int PChoice;
            PChoice=Integer.parseInt(br.readLine());
           if(PChoice==1)
           {
              pm.insertPayDetails();
           }
           else if(PChoice==2)
           {
               pm.viewPayDetails();               
           }
           else if(PChoice==3)
           {
               pm.deletePayRecord();               
           }
           else if(PChoice==4)
           {               
                pm.modifyPayDetails();                               
           }
            else if(PChoice==5)
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
           pm.displayPayMenu(); 
        }
    }
   
    //public void treatmentDetails()
    //public void drugDetails()
    //public void createBill()   
}
