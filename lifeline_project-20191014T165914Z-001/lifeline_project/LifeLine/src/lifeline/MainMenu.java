/*
 * MainMenu.java
 *
 * Created on 26 July, 2015, 12:42 PM
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
public class MainMenu 
{
   static public void displayMainMenu()
    {
        try
        {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("  *********MAIN MENU********  ");
        System.out.println("PLEASE CHOOSE AN OPTION : ");
        System.out.println("PRESS 1 FOR Staff Management \n" +
                   "PRESS 2 FOR Patient Registration\n" +
                   "PRESS 3 FOR Drug & Billing\n" +
                   "PRESS 4 FOR Medical Store\n" +
                   "PRESS 5 FOR Eqipment Management\n" +
                   "PRESS 6 FOR Payroll Management\n" +
                   "PRESS 7 FOR Exit");
             int choice;
            choice=Integer.parseInt(br.readLine());
           if(choice==1)
           {
               StaffDetails sd=new StaffDetails();
                sd.displayStaffMenu();          
           }
           else if(choice==2)
           {
               PatReg pr=new PatReg();
               pr.displayPatRegMenu();                              
           }
           else if(choice==3)
           {
                DrugAndTrtmt dt=new DrugAndTrtmt();        
                dt.displayTrtmtMenu(); 
           }
           else if(choice==4)
           {
                MedicalStore ms=new MedicalStore();
                ms.displayMStoreMenu();               
           }
            else if(choice==5)
           {
                EquipMaintainence em=new EquipMaintainence();
                em.displayEqupMenu();               
           }
            else if(choice==6)
           {
                PayrollMang pm=new PayrollMang();
                pm.displayPayMenu(); 
           }
           else if(choice==7)
           {
               System.out.println("______CLOSING THE SOFTWARE_______");
               System.exit(0);              
           }
           else
           {
                System.out.println("INVALID CHOICE! PLEASE TRY AGAIN.... ");
                MainMenu mm=new MainMenu();
                mm.displayMainMenu();
           }
           
       }
       catch(IOException io)
       {
           System.out.println("INVALID INPUT:"+io);
       }
       catch(Exception e)
       {
           System.out.println("ERROR:"+e);
       }
    }
}
