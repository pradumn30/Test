/*
 * LoginDet.java
 *
 * Created on 22 July, 2015, 5:29 PM
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
public class LoginDet
{
    public static void main(String[] argsa)
    {
        try
        {
        /*it should be stored in database along with the time of login*/
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(" ENTER LOGIN DETAILS: ");
        System.out.println(" USERNAME : ");
        String userName=br.readLine();
        System.out.println(" PASSWORD: ");
        String pass=br.readLine();
        if(userName.equals("admin") && pass.equals("password@123"))
        {
            System.out.println("\t\t....LOGGING IN.... ");
            MainMenu mm=new MainMenu();
            mm.displayMainMenu();
        }
        else
        {
            System.out.println(" INVALID DETAILS!....PLEASE TRY AGAIN ");
        }
        }
        catch(IOException io)
        {
            System.out.println("ERROR: "+ io);
        }
        catch(Exception e)
        {
            System.out.println("ERROR: "+ e);
        }
    }
    
}
    

