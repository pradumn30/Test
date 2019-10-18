/*
 * Billing.java
 *
 * Created on 2 August, 2015, 3:09 PM
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
import java.util.Scanner;

public class Billing 
{
    String TDrugs;   
   
    
   public void MStore()
   // public static void main(String[] args)
    {
       String[] array1;
       try
       {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //System.out.println(PresDrugs);
        Connection con1=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
        Statement stmt1=con1.createStatement();
        ResultSet rs1=stmt1.executeQuery("SELECT *from MStore1");
        double PPu;
        double Total=0;  
        String DgName1;
        array1=PresDrugs.split(",");
        for(int j=0;j<array1.length;j++)
        { 
            System.out.println(array1[j]);
        }
        int j=1;
        while(rs1.next())
        {
            DgName1=rs1.getString("Drug_Name");
           // System.out.print(DgName1+"\t");
            if(DgName1.equals(array1[j]))
            {
                PPu=rs1.getDouble("PricePerUnit");
                System.out.print("\t"+PPu);
                Total=Total+PPu;
                j=j+1;
            }
            
            // System.out.print(array1[j]+"\t");
            //System.out.print(drug);
        }
        con1.close();
        System.out.println("\n TOTAL="+Total);
       }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        } 
    }
    
    /*final String[] drugs;
    public String TrtmtBill()
    //public static void main(String[] args)
    {        
        try
        {
            String str="SELECT *FROM TREATMENT";            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(str);
            System.out.println("*******BILL*****");            
            System.out.println("ENTER THE OPERATIONAL ID WHOSE BILL IS TO BE CREATED:");            
            int InpOpId=Integer.parseInt(br.readLine()); 
            System.out.println("--------LIFELINE----------");
            while(rs.next())                                                                                     
            {
                int OpId=rs.getInt("OpId");
                if(InpOpId==OpId)
                {
                    int TPatId=rs.getInt("Patient_Id");
                    String TDetails=rs.getString("Treatment_Details");
                    String  TDrugs=rs.getString("Recommended_Drugs");
                    System.out.println("Operational_Id ="+InpOpId+"\n" +
                            "Patient_id ="+TPatId+"\n" +
                            "Treatment_details ="+TDetails);
                    System.out.print("Prescribed_Drugs= ");
                    array=TDrugs.split(",");
                    for(int i=0;i<array.length;i++)
                    {
                        System.out.print(array[i]+"\t");
                    }
                    System.out.println();   
                    System.out.println(TDrugs);
                    
                }
                   
            }
            con.close();
        }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        }
       
    }*/
    
    
   
    String PresDrugs;
    int InpOpId;
    public void PrescribedDrugs()
    {
        try
        {
            String str="SELECT *FROM TREATMENT";            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con3=DriverManager.getConnection("jdbc:odbc:PK2","administrator"," ");
            Statement stat3=con3.createStatement();
            ResultSet rs3=stat3.executeQuery(str);
            System.out.println("*******BILL*****");            
            System.out.println("ENTER THE OPERATIONAL ID WHOSE BILL IS TO BE CREATED:");            
            InpOpId=Integer.parseInt(br.readLine()); 
            System.out.println("--------LIFELINE----------");
            
            while(rs3.next())
            {
                int OpId=rs3.getInt("OpId");            
                if(InpOpId==OpId)
                {
                String TDrugs=rs3.getString("Recommended_Drugs");
                PresDrugs=PresDrugs+","+TDrugs;
                /*int TPatId=rs3.getInt("Patient_Id");
                String TDetails=rs3.getString("Treatment_Details");                
                System.out.println("Operational_Id ="+InpOpId+"\n" +
                            "Patient_id ="+TPatId+"\n" +
                            "Treatment_details ="+TDetails);
                System.out.print("Prescribed_Drugs= ");*/
                System.out.println(TDrugs+" ");
                }        
            }
            System.out.println(PresDrugs);
            con3.close();
        }
        catch(Exception e2) 
        {      
            System.out.println("ERROR"+e2);  
        }
    }

    
    public static void main(String[] args)
    {
        Billing bl=new Billing();
        bl.PrescribedDrugs();
        bl.MStore();
       
        
    }
    
}