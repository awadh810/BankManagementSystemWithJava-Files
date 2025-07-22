package bankmanagementsystem;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BankManagementSystem {
    
    static BankInfo customer = null;
    static EmployeesGroup emp = null;
    static EmployeesGroup empLogin = null;
    static  Scanner input = null;
    static File file = null;
    static FileWriter fw = null;
    static FileReader fr = null; 
    static BufferedWriter bw = null; 
    static BufferedReader br = null; 
    static PrintWriter pw = null; 
    static DataOutputStream dos = null;
    static DataInputStream dis = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    static RandomAccessFile RAF = null;
 
    static String inputJOp;         //javax.swing.JOptionPane 
    static String loginFolder = null ,  loginFile = null  , validities = "rw";
    static boolean exit1 = true;
    static boolean exit2 = true; 
    
           
    public static void main(String[] args)  throws ClassNotFoundException, IOException
    { //  Name : Awadh mohammed bin wahlan
        try { 

            JOptionPane.showMessageDialog(null, "\t\t  Welcome to The Bank program\n\t\t===========================", "BANK PROGRAM", 1);
            for (;;) { 
                                                                 // enter the system Folder name;
                loginFolder = JOptionPane.showInputDialog(null, "Enter The Login Folder Name :  ", "Login To The System", 1);  // the folder that contain the password and user name        
                file = new File(loginFolder);                
                if (file.isDirectory() && file.exists()) {                    
                    File[] listFiles = file.listFiles();                        
                    for (int x = 0; x < listFiles.length; x++) {                        
                        loginFile = listFiles[x].getName();                        
                        if (loginFile.endsWith(".csv")) // if there are more than one csv file insid the file , the first csv file found
                        {                            
                            String userName = JOptionPane.showInputDialog(null, "Enter User Name:  ", "Login To The System", 1);
                            inputJOp = JOptionPane.showInputDialog(null, "Enter User Password:  ", "Login To The System", 1);                            
                            int password = Integer.parseInt(inputJOp);
                            
                            if (checkLogin(loginFolder, loginFile, userName, password)) {                                
                                do {                                    
                                    inputJOp = JOptionPane.showInputDialog(
                                            ("Emplooy Name :  " + empLogin.getEmpName() + "                          " + "Emplooy ID :  " + empLogin.getEmpID() + "                                " + "Emplooy Validities :  " + empLogin.getEmpValidities())
                                            + "\n____________________________________________________________________________\n"
                                            + "1. Insert New Data Group from CSV File (.csv)\n"
                                            + "2. Export Data Into Object Stream (.obj)\n"
                                            + "3. Export Data Into Data Stream (.dat)\n"
                                            + "4. Open a new account.\n" //Insert new record into Data Group (add into .csv, .obj, .dat)
                                            + "5. Login to account.\n" // Modify record of Data Group (modify .csv, .obj, .dat)
                                            + "6. Delete record of Data Group (delete from .csv, .obj, .dat)\n"
                                            + "7. Search record of Data Group (search at .csv, .obj, .dat)\n"
                                            + "8. Show The Data for all customers.\n"
                                            + "9. Emplooys Department.\n"
                                            + "   Otherwise to finsh the program.\n"
                                            + "   Enter your choice number here :    ");
                                    
                                    int ch = Integer.parseInt(inputJOp);
                                    
                                    if (ch == 1) // Copy file;
                                    {
                                        inputJOp = JOptionPane.showInputDialog("Enter Data Group Name:  ");  // Enter Folder Name That you want to create it:                
                                        if (creatFolder(inputJOp)) {
                                            String saveFolderName = file.getName();                                            
                                            inputJOp = JOptionPane.showInputDialog("Enter File Name that you want to copy it:  ");
                                            file = new File(inputJOp);
                                            if (file.exists() && file.isFile()) {
                                                if (copyFile(inputJOp, saveFolderName)) // source File , Desination ,, where I want to copy it;
                                                {
                                                    JOptionPane.showMessageDialog(null, inputJOp + " File copied Successfully into " + saveFolderName + "  as:  " + inputJOp);
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Opeartion is Falid");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Error , Check The File Name or maybe File not found ");
                                            }                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "There is an error in creat folder");
                                        }                                        
                                    } // end ch == 1 ; 
                                    else if (ch == 2) // Export into .obj file;
                                    {                                        
                                        String Fname;
                                        inputJOp = JOptionPane.showInputDialog("Enter Group Name:  ");  // Enter Folder Name That contain the file csv , you want to export it to obj file:
                                        file = new File(inputJOp);                                        
                                        if (file.isDirectory() && file.exists()) {
                                            File[] listFile = file.listFiles();                                            
                                            for (File listFile1 : listFile) {
                                                Fname = listFile1.getName();                                                
                                                if (Fname.endsWith(".csv")) // if there are more than one csv file insid the file , what csv file will be export
                                                {
                                                    ExportIntoObj(inputJOp, Fname);
                                                    JOptionPane.showMessageDialog(null, "data saved successfully into .obj file");
                                                    break;
                                                }
                                            }                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is not found");
                                        }
                                        
                                    } // end ch == 2
                                    else if (ch == 3) // Export into .dat file;
                                    {
                                        String Fname;                                        
                                        inputJOp = JOptionPane.showInputDialog("Enter Group Name:  ");  // Enter Folder Name That contain the file csv , you want to export it to obj file:
                                        file = new File(inputJOp);
                                        if (file.isDirectory() && file.exists()) {
                                            File[] listFile = file.listFiles();                                            
                                            for (File listFile1 : listFile) {
                                                Fname = listFile1.getName();                                                
                                                if (Fname.endsWith(".csv")) {                                                    
                                                    ExportIntoData(inputJOp, Fname);
                                                    JOptionPane.showMessageDialog(null, "data saved successfully into .dat file");
                                                    break;
                                                }
                                            }                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is not found");
                                        }
                                        
                                    } // end ch == 3
                                    else if (ch == 4) // Open new account;
                                    {                                        
                                        String Fname, folderName;                                        
                                        inputJOp = JOptionPane.showInputDialog("Enter Group Name:  ");    // Enter the folder name that contain the data for customers
                                        folderName = inputJOp;
                                        file = new File(inputJOp);
                                        if (file.isDirectory() && file.exists() && validities.equals(empLogin.getEmpValidities())) {
                                            File[] listFile = file.listFiles();                                            
                                            for (File listFile1 : listFile) {
                                                Fname = listFile1.getName();
                                                if (Fname.endsWith(".csv")) // if .csv file not found insid the folder what will you do ?  that means for loop is fish and file is not found : will be comeback to main screen without any توضيح
                                                {
                                                    openNewAccount(inputJOp, Fname);
                                                    // ExportIntoData(folderName, Fname);                 
                                                    ExportIntoObj(folderName, Fname);                                                    
                                                    break;
                                                }
                                            }                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is not found || empty ,, or you do not have a validities to do these an operation.");
                                        }
                                        
                                    } //end ch == 4
                                    else if (ch == 5) // login to account;
                                    {
                                        loginToAccount();
                                    } // end ch == 5
                                    else if (ch == 6) // delete  account;
                                    {
                                        inputJOp = JOptionPane.showInputDialog("Enter the Group Name: ");  String folderName = inputJOp;
                                        file = new File(inputJOp);
                                        if (file.isDirectory() && file.exists() && validities.equals(empLogin.getEmpValidities())) {
                                            File[] liFiles = file.listFiles();
                                            for (File liFile : liFiles) {
                                                String Fname = liFile.getName();
                                                if (Fname.endsWith(".csv")) // if .csv file do not have data 
                                                {
                                                    //int totalIDS = getID(folderName, Fname);
                                                    inputJOp = JOptionPane.showInputDialog("Enter the ID: ");     String id = inputJOp;                                                 
                                                    if ( DeletFromeFile(folderName, Fname, id)) 
                                                    {                                  
                                                        ExportIntoObj(folderName, Fname);    // stream closed;
                                                        ExportIntoData(folderName, Fname);    //  stream closed;
                                                        JOptionPane.showMessageDialog(null, "The customer is delete from the .csv  && .dat  && .obj");
                                                        break;                                                        
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "There is an error ,, The customer is not delete from the .csv  && .dat  && .obj");
                                                    }                                                    
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is not fonud || empty ,, or you do not have a validities to execute this an operation.");
                                        }
                                    } // end ch == 6
                                    else if (ch == 7) // search in the files
                                    {                                        
                                        int searchID = 0;
                                        inputJOp = JOptionPane.showInputDialog("Enter The Group Data: ");
                                        String folderName = inputJOp;                                        
                                        file = new File(folderName);
                                        
                                        if (file.isDirectory() && file.exists()) {
                                            File[] listFile = file.listFiles();
                                            inputJOp = JOptionPane.showInputDialog("Enter The ID To Search about it: ");
                                            searchID = Integer.parseInt(inputJOp);  // ID for the customer is accountNumber
                                            for (File listFile1 : listFile) {
                                                String Fname = listFile1.getName();                                                
                                                if (Fname.endsWith(".csv")) {
                                                    if ((searchInFiles(searchID, folderName, Fname) == false)) {
                                                        JOptionPane.showMessageDialog(null, "The customer you search about it is not found");
                                                    }
                                                    break;
                                                }
                                            }                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is empty or not found.");
                                        }                                        
                                    } // end ch == 7
                                    else if (ch == 8) // show the data from the file
                                    {
                                        String folderName = JOptionPane.showInputDialog("Enter The Group Name:  ");
                                        file = new File(folderName);
                                        if (file.isDirectory() && file.exists()) {                                            
                                            File[] lisFiles = file.listFiles();
                                            for (File lisFile : lisFiles) {
                                                String Fname = lisFile.getName();
                                                if (Fname.endsWith(".csv")) {
                                                    showTheData(folderName, Fname);
                                                    break;
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The Folder is not found");
                                        }
                                        
                                    } // end ch == 8
                                    else if (ch == 9) {
                                        emplooyDepartment();
                                    } // end ch == 9
                                    else // end the all options [ finsh the program ] ;
                                    {
                                        exit1 = false;  // System.exit(0);
                                        JOptionPane.showMessageDialog(null, "Finsh The Program", "BANK PROGRAM", 0);
                                    }
                                    
                                } while (exit1 != false);   // end do while
                                
                            } // end login to the system method
                            else {
                                JOptionPane.showMessageDialog(null, "There is an error in login data.\n"
                                        + "Cheke entered data ,, Try again.");
                            }                            
                        }                        
                    }  // end for loop to search about the file csv                                      
                } // end if (  file is Directory()  and exists()  )  
                else {                    
                    inputJOp = JOptionPane.showInputDialog(null,
                            "The Folder is not found , Or there is an error in Input Folder Name.\n"
                            + "To try again Enter 1\n"
                            + "Otherwise To Finsh the Program. \n"
                            + "\nEnter your choice here:  ");
                    if (!inputJOp.equals("1")) {
                        break;
                    }                    
                }
                
                if (exit1 == false) {
                    break;
                }
                
            }   // end for loop ;
            
        } catch (HeadlessException headlessException) {
        } catch (NumberFormatException numberFormatException) {
        } catch (IOException iOException) {
        } catch (ClassNotFoundException classNotFoundException) {
        }
                                                                              
    } //end main method;
    
     
//=============================================================================================================================================   
//---------------------------------------------------------  <<<END MAIN METHOD>>>  -----------------------------------------------------------;
//==============================================================================================================================================
 
    
    public static boolean checkLogin(String folderName , String fileName , String userName , int userPasssword) 
    {       
        boolean isFound = false;
        empLogin = new EmployeesGroup();
        try {
            EmployeesGroup read = new EmployeesGroup();            
            fr = new FileReader(folderName + "\\" + fileName);   //file.getAbsoluteFile();
            br = new BufferedReader(fr);
            dis = new DataInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat")));            
            ois = new ObjectInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj")));
             
            String[] data = null;            
            String line = null , nameCSV = null , nameDAT = null;
            int passwordCSV = 0  , passwordDAT = 0 ;
            
            while ((line = br.readLine()) != null) {
                isFound = false;
                data = line.split(", ");
                 
                nameCSV = data[1];
                passwordCSV = Integer.parseInt(data[2]);                                
                dis.readInt();
                nameDAT = dis.readUTF();
                passwordDAT = dis.readInt();
                dis.readUTF();  
                empLogin = (EmployeesGroup) ois.readObject();
                   
                if (nameCSV.equals(userName) && nameDAT.equals(userName) && userName.equals(empLogin.getEmpName())     &&   passwordCSV == userPasssword &&  passwordDAT == userPasssword && empLogin.getEmpCode() == userPasssword)
                {     
                    fr.close();
                    br.close();
                    dis.close();
                    ois.close();
                    isFound = true;
                    break;                    
                }                
            }  // end while
        } catch (IOException iOException) {
        } catch (NumberFormatException numberFormatException) {
        } catch (ClassNotFoundException classNotFoundException) {
        } catch (HeadlessException headlessException) {
        }       
        return isFound;
                                        
    }  // end check login to the system method
    
    
    public static boolean creatFolder(String folderName)
    {      
        file = new File(folderName); 
        if (file.mkdir())        
            return true;       //  return true if the file created  ;                                          
        else
          return false;      
    } // end creat Folder method
    
    
    public static boolean copyFile (String fileName , String folderName) throws IOException
    { 
        if (true)  // will be always true when insid ,, just to do if and  else statement ;
        {           
            try {
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
                
                fw = new FileWriter(folderName + "\\" + fileName);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);                
                
                String line = null;                
                while ((line = br.readLine()) != null) {
                    pw.println(line);
                }                
                pw.flush();         pw.close();           fr.close();          br.close();        bw.close();          fw.close();                                
            } catch (IOException iOException) {
            }            
            return true;
        }
        else
            return false;  // if there is a problom  happen when file copy will be return false;
        
    } // end copy file method ;
    
    public static void ExportIntoObj(String folderName , String fileName) throws FileNotFoundException, IOException
    {
         
        try { 
            fr = new FileReader(folderName + "\\" + fileName);  //file.getAbsoluteFile();
            br = new BufferedReader(fr);   
            oos = new ObjectOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj")));
           
        } catch (IOException iOException) {
        }
        String [] data = null ;
        String line = null , nam = null , addr = null ;
        int  accountNum = 0 , num = 0 ;
        double amount = 0 , lastD = 0 , lastM = 0 ;
        boolean acc;                 
        try { 
            while ( (line = br.readLine()) != null )
            {  
                data = line.split(", ");                
                accountNum = Integer.parseInt(data[0]);      nam = data[1];                        addr = data[2];                       num = Integer.parseInt(data[3]);
                amount = Double.parseDouble(data[4]);        lastD = Double.parseDouble(data[5]);   lastM = Double.parseDouble(data[6]);   acc = Boolean.parseBoolean(data[7]);                
                customer = new BankInfo(nam,  addr,  num,  accountNum,  amount,  lastD,  lastM,  acc);                  
                oos.writeObject(customer);    
            }
           fr.close();            br.close();            oos.close();
        } catch (IOException iOException) {
        } catch (NumberFormatException numberFormatException) {
        }
        
    }  // end Export into obj method;
    
    
   public static void ExportIntoData(String folderName , String fileName) throws IOException
   { 
       try {           
           try {
               fr = new FileReader(folderName + "\\" + fileName);
               br = new BufferedReader(fr);
               dos = new DataOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat")));               
           } catch (IOException iOException) {
           }
           
           String[] data = null;
           String line = null, nam = null, addr = null;
           int accountNum = 0, num = 0;
           double amount = 0, lastD = 0, lastM = 0;
           boolean acc;           
           
           while ((line = br.readLine()) != null) {               
               data = line.split(", ");               
               accountNum = Integer.parseInt(data[0]);
               nam = data[1];
               addr = data[2];
               num = Integer.parseInt(data[3]);
               amount = Double.parseDouble(data[4]);
               lastD = Double.parseDouble(data[5]);
               lastM = Double.parseDouble(data[6]);
               acc = Boolean.parseBoolean(data[7]);
               try {
                   dos.writeInt(accountNum);
                   dos.writeUTF(nam);
                   dos.writeUTF(addr);
                   dos.writeInt(num);
                   dos.writeDouble(amount);
                   dos.writeDouble(lastD);
                   dos.writeDouble(lastM);
                   dos.writeBoolean(acc);
                   dos.writeUTF("\n");                   
               } catch (IOException iOException) {
               }               
           } //end while
           fr.close();
           br.close();
           dos.close();
           
       } catch (IOException iOException) {
       } catch (NumberFormatException numberFormatException) {
       }
   }  // end Export into data method;
        
        
  public static void openNewAccount(String folderName , String fileName) throws IOException
  {  
        //String extensionFiles[] = {".csv" , ".obj" , ".dat" };
        int ID = getID(folderName, fileName);     // get the account number for the last customer in file 
        ID+=1;                                    // and then plus one on return value from the method to give it the next customer that you want to open acconut for it ;
        fw = new FileWriter(folderName + "\\" + fileName , true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
        dos = new DataOutputStream( new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat") , true));
        oos = new ObjectOutputStream( new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj" ) , true));
       
        try {
            inputJOp = JOptionPane.showInputDialog("Enter your money:  ");
            double amount = Double.parseDouble(inputJOp);            
            
            customer = new BankInfo();                      // this class have the account information for Client;
            if (customer.checkAmountToOpenAcount(amount))   //check the Client money befor open account;
            {
                String nam, addr;    
                int num;
                
                nam = JOptionPane.showInputDialog("Enter the name:   ");                
                addr = JOptionPane.showInputDialog("Enter the adress:   ");
                inputJOp = JOptionPane.showInputDialog("Enter the number:   ");
                num = Integer.parseInt(inputJOp);                
                customer.openAcount(nam, addr, num, amount , ID);
                JOptionPane.showMessageDialog(null, "Account is Opened and these your account information: ");
                customer.getDetailsAcount();
                
                inputJOp = JOptionPane.showInputDialog("TO save this customer Information into customer Files ,, Enter number 1");
                int detr = Integer.parseInt(inputJOp);
                if (detr == 1) 
                {
                    pw.println(  customer.getAccountNumber() + ", " + customer.itsName + ", " + customer.itsAddres + ", " + customer.itsNumber + ", " + customer.totalAmountQueriying() + ", " + customer.getLastDeposit() + ", " + customer.getLastMist() + ", " + customer.openAccount);                                                                             
                    dos.writeInt( customer.getAccountNumber());  dos.writeUTF(customer.itsName);   dos.writeUTF(customer.itsAddres);   dos.writeInt(customer.itsNumber);  dos.writeDouble(customer.totalAmountQueriying());    dos.writeDouble(customer.getLastDeposit());   dos.writeDouble(customer.getLastMist());  dos.writeBoolean(customer.openAccount);  dos.writeUTF("\n");
                    oos.writeObject(customer);   
                    pw.flush();            pw.close();
                    bw.close();            fw.close();
                    dos.close();           oos.close();    //customer = null;
                    JOptionPane.showMessageDialog(null, "The data was saved in .csv && .obj && .dat files.");  
                } 
                else                                     
                    JOptionPane.showMessageDialog(null, "The data was not saved in files.");                                                                            
            }
            else 
                JOptionPane.showMessageDialog(null, "Sorry , your money is less than the required money to open account in a bank");
                        
        } catch (HeadlessException headlessException) {
        } catch (NumberFormatException numberFormatException) {
        } catch (IOException iOException) 
        {   System.out.println("Error in writting in file");
            return;
        }
    } // end open account method;
                        
    
  public static void loginToAccount() throws IOException, ClassNotFoundException 
  {                        
        try {
            String Fname = null;
            boolean isThereModify = false;
            inputJOp = JOptionPane.showInputDialog("Enter Account number that you want to login to it:");
            int accountNum = Integer.parseInt(inputJOp);
            
              inputJOp = JOptionPane.showInputDialog("Enter The Group Name:  ");  String folderName = inputJOp;
              file = new File(inputJOp);
              if (file.isDirectory() && file.exists())
              { 
                  File [] lisFiles = file.listFiles();
                  for (int x=0; x<lisFiles.length; x++)
                  {
                      Fname = lisFiles[x].getName();
                      if(Fname.endsWith(".csv"))
                         break;
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "The Folder is not found");
            
            //customer = new BankInfo();
            if ( searchInFiles(accountNum, inputJOp ,Fname ) ) {                
                JOptionPane.showMessageDialog(null, "login into Account is successfuly.");
                
                do {
                    inputJOp = JOptionPane.showInputDialog(
                            "You can do the flowing operation on account:\n"
                            + "\n1. Deposit Money.\n"
                            + "2. Mist Money.\n"
                            + "3. Total Amount Queriying.\n"
                            + "4. Get Details Account.\n\n"
                            + "Otherwise to comeback to main screen.\n"
                            + "Enter your choice number:  ");
                    
                    int choice = Integer.parseInt(inputJOp);
                    
                    switch (choice) {                        
                        case 1:                            
                            inputJOp = JOptionPane.showInputDialog(null, "Enter the deposit money:   ");                            
                            customer.ExplainTheOutput("your amount after deposit is :  ", customer.depositMoney(Double.parseDouble(inputJOp)));                            
                            isThereModify = true;
                            break;
                        
                        case 2:
                            inputJOp = JOptionPane.showInputDialog(null, "Enter the mist money:   ");                                
                            customer.ExplainTheOutput("Your money after transfer is :  ", customer.mistsMoney(Double.parseDouble(inputJOp)));
                            isThereModify = true;
                            break;
                        
                        case 3:
                            //JOptionPane.showMessageDialog( null ,customer[nA-1].ExplainTheOutput("Total money in your account is :  "  , customer[nA-1].totalAmountQueriying()));
                            customer.ExplainTheOutput("Total money in your account is :  ", customer.totalAmountQueriying());
                            exit2 = true;
                            break;
                        
                        case 4:
                            customer.getDetailsAcount(); 
                            exit2 = true;
                            break;
                        
                        default:
                            exit2 = false;       // System.exit(0);                                          
                            break;
                    }                    
                   
                } while (exit2 != false);
                
                if (  isThereModify == true  && validities.equals(empLogin.getEmpValidities()) )
                {
                    int r = Integer.parseInt(JOptionPane.showInputDialog(null, "To save the last modified Enter 1 : "));
                    if (r == 1)
                    {
                       if( saveTheModified(accountNum, folderName, Fname  , "cust") )  // invoke saveTheModified method to save the modify ; 
                       {
                           ExportIntoData(folderName , Fname);
                           ExportIntoObj(folderName, Fname);
                           JOptionPane.showMessageDialog(null, "Saved last modified on account is successfully.");  
                       }
                       else
                           JOptionPane.showMessageDialog(null, "there is an error");
                                              
                    }
                    else
                        JOptionPane.showMessageDialog(null, "last modified on account is faild");                                            
                }
                else
                    exit2 = false;  // don't work any thing , just to do else statement after if statement
                              
            } else {
                JOptionPane.showMessageDialog(null, "\nError, try again.");   // Invalid Input
            }
        } catch (HeadlessException headlessException) {
        } catch (NumberFormatException numberFormatException) {
        } catch (IOException iOException) {
        } catch (ClassNotFoundException classNotFoundException) {
        }
   } // end login to account method
              
    
  public static boolean DeletFromeFile(String folderName , String fileName , String idRecord ) throws FileNotFoundException, IOException
  {
       boolean isDelete = false;        
       try {  
            fr = new FileReader(folderName  + "\\"  + fileName);
            br = new BufferedReader(fr);             
                                  
            String line = null  , id = null;
            String[] data = new String[100];
            String[] record = null;
            int  i = 0 , j = 0;   
            
            while ((line = br.readLine()) != null) 
            {                             
                data[i] = line;   
                i++;                  
            }
             fr.close();           br.close();
           
             file  = new File(folderName  + "\\"  + fileName);
               if (file.delete() && file.exists() == false )
               {                    
                    fw = new FileWriter(folderName  + "\\"  + fileName , true);
                    bw = new BufferedWriter(fw);
                    pw = new PrintWriter(bw); 

                    while (j<i) 
                    {         
                        line = data[j];     
                        record = line.split(", ");
             
                        if (!(record[0].equalsIgnoreCase(idRecord)))
                        { 
                           pw.println(data[j]);  
                        }                 
                       j++;                
                    }            
                    pw.flush();          pw.close();            
                    bw.close();          fw.close();        
                    isDelete = true;
               }
           
            } catch (IOException iOException) {
            }
            return isDelete;
  }  // end delet account method;          
  
    
  public static boolean searchInFiles(int searchID , String folderName , String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
  {  // open the all three files csv , dat , obj ;
      boolean isFound = false;    
      try {          
          fr = new FileReader(folderName + "\\" + fileName);
          br = new BufferedReader(fr);          
          dis = new DataInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat")));          
          ois = new ObjectInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj")));          
          
          String[] dataBR = null;          
          String lineBR = null, nam1 = null, nam2 = null, nam3 = null;
          int lineDIS = 0, accounNum1 = 0, accounNum2 = 0, accounNum3 = 0;          
          
          while ((lineBR = br.readLine()) != null) // read the three files in the same time , to it's equals;
          {
              isFound = false;
              
              dataBR = lineBR.split(", ");
              accounNum1 = Integer.parseInt(dataBR[0]);
              nam1 = dataBR[1];

              // read .dat file to check is the customer written in it or No ;  To access to the customized line >>  you have to read all the line befor it ;
              // ( lineDIS != -1 )
              accounNum2 = dis.readInt();
              nam2 = dis.readUTF();
              dis.readUTF();
              dis.readInt();
              dis.readDouble();
              dis.readDouble();
              dis.readDouble();
              dis.readBoolean();
              dis.readUTF();

              // read .obj file to check is the customer written in it or No ; 
              customer = (BankInfo) ois.readObject();
              accounNum3 = customer.getAccountNumber();
              nam3 = customer.itsName;
                           
              if (searchID == accounNum1 && searchID == accounNum2 && searchID == accounNum3) 
              {                  
                  inputJOp = JOptionPane.showInputDialog(null, "The customer is found in the .csv && .dat && .obj Files , to show it's information Enter 1:  ");                  
                  if (inputJOp.equals("1")) 
                  {
                      showRecord(lineBR);
                  }                  
                  fr.close();               br.close();
                  dis.close();              ois.close();
                  isFound = true;           break;    
              }              
          }   // end while        
                   
      } catch (IOException iOException) {
      } catch (NumberFormatException numberFormatException) {
      } catch (ClassNotFoundException classNotFoundException) {
      } catch (HeadlessException headlessException) {
      }
      
      return isFound;
  }  // end Search in Files method
  
  
  public static void showTheData(String folderName , String fileName) throws FileNotFoundException, IOException
  {
      try {
          fr = new FileReader(folderName + "\\" + fileName);
          br = new BufferedReader(fr);
          String line = null;
          while ((line = br.readLine()) != null) 
          {
              JOptionPane.showMessageDialog(null, line);
          }          
         fr.close();          br.close();
      } catch (IOException iOException) {
      } catch (HeadlessException headlessException) {
      }
      
  }   // end show data method
  
  public static void showRecord(String line)
  {  
      JOptionPane.showMessageDialog(null, line);
  }
  
  public static boolean saveTheModified(int searchID , String folderName , String fileName , String detrmin) throws FileNotFoundException, IOException, ClassNotFoundException
  {
        boolean ismodify =false;
        try {  
            fr = new FileReader(folderName  + "\\"  + fileName);
            br = new BufferedReader(fr);             
                                  
            String line = null;
            String[] data = new String[100];  // [getID()];
            String[] record = null;
            int id = 0 , i = 0 , j = 0;   
            
            while ((line = br.readLine()) != null) {                             
                data[i] = line;  
                i++;                  
            }
             fr.close();           br.close();
            // after read all the data  delete the old file  and then create another one without any data to write the data after modify ;
             file  = new File(folderName  + "\\"  + fileName);  
             if (file.delete() && file.exists() == false)
             {                                  
                fw = new FileWriter(folderName  + "\\"  + fileName );
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw); 

                while (j<i) {                    
                    line = data[j];   
                    record = line.split(", ");
                    id = Integer.parseInt(record[0]);

                    if (detrmin == "cust")     // ( used with customers == cust )
                    {                                                                  
                       if ((searchID == id )) 
                        {               
                          pw.println(customer.getAccountNumber() + ", " + customer.itsName + ", " + customer.itsAddres + ", " + customer.itsNumber + ", " + customer.totalAmountQueriying() + ", " + customer.getLastDeposit() + ", " + customer.getLastMist() + ", " + customer.openAccount);                                                        
                        } 
                       else 
                        {
                           pw.println(data[j]);                                      
                        }                   
                    }

                    else     // ( used with emplooys ==  empl )
                    {
                        if ((searchID == id )) 
                        {              
                            pw.println(emp.getEmpID() + ", " + emp.getEmpName() + ", " + emp.getEmpAddress() + ", " + emp.getEmpGender() + ", " + emp.getEmpJob() + ", " + emp.getEmpDepartment() + ", " +  emp.getEmpSalary()+ ", " + emp.getEmpWorkHuor());      
                        } 
                       else 
                        {
                           pw.println(data[j]);                                      
                        }                     
                    }                                                                 
                  j++;
                }                       
                pw.flush();          pw.close();            
                bw.close();          fw.close();            
                ismodify = true;   

             }
             
         
            
        } catch (IOException iOException) {
        } catch (NumberFormatException numberFormatException) {
        } 
        return ismodify;
      
  }  // end saved last Modify Method ;
 
  
//=======================================================================================================================================  
//----------------------------------------------------- << EMPLOOY GROUP >> -------------------------------------------------------------
//=======================================================================================================================================          
    
  
  public static void emplooyDepartment() throws IOException, FileNotFoundException, ClassNotFoundException
  {
      boolean exit3 = false;  // System.exit(0);
      String folderName = null;
      do
      {
          inputJOp = JOptionPane.showInputDialog(null, 
                "1.Add Emplooy To The System.\n"
              + "2. Export Emplooy Data into .obj File.\n"
              + "3. Export Emplooy Data inot .dat File.\n"
              + "4. Search About Emplooy.\n"
              + "5. Delete Emplooy From System.\n"
              + "6. Login to Emplooy Data.\n"
              + "7. Show The Data For All Emplooys.\n"
              + "Otherwise to comeback to main screen.\n"
              + "\nEnter your choice here:   "
              , "Options Menue", 3);
      
      int c = Integer.parseInt(inputJOp);
      
      switch (c)
      {
          case 1:    // add emplooy 
              inputJOp = JOptionPane.showInputDialog(null, "Enter The Folder Name:  ");
              folderName = inputJOp;
              file = new File(inputJOp);
              if (file.isDirectory() && file.exists()  && validities.equals(empLogin.getEmpValidities()))
              {
                  File [] lisFiles = file.listFiles();
                  for (int x=0; x<lisFiles.length; x++)
                  {
                      String Fname = lisFiles[x].getName();
                      if(Fname.endsWith(".csv"))
                      {  
                          if ( addEmplooy(folderName, Fname) == true )
                          {  
                              ExoprtEMPDataIntoObjFile(folderName, Fname ,  "all");    
                              ExoprtEMPDataIntoObjFile(loginFolder, loginFile , "notall"); 
//                            ExportEmpDataIntodatFile(folderName, Fname);
                          }
                         else
                            JOptionPane.showMessageDialog(null, "The emplooy was not saved in the system.");
                         break;
                      }
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "The Folder is not found || or you do not have an validities to execute this an operation.");              
              exit3 = true;
              break;   // end case 1
              
              
          case 2:       // Export Emplooy Data into .obj File
               folderName = JOptionPane.showInputDialog(null, "Enter The Folder Name:  ");
              file = new File(folderName);
              if (file.isDirectory() && file.exists())
              {
                  File [] lisFiles = file.listFiles();
                  for (int x=0; x<lisFiles.length; x++)
                  {
                      String Fname = lisFiles[x].getName();
                      if(Fname.endsWith(".csv"))
                      {  
                          ExoprtEMPDataIntoObjFile(folderName , Fname , "all");
                          JOptionPane.showMessageDialog(null, "data saved successfully into .obj file");
                          break;
                      }
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "The Folder is not found.");              
              exit3 = true;
              break;   // end case 2
              
              
          case 3:  // Export Emplooy Data Into dat
              folderName = JOptionPane.showInputDialog(null, "Enter The Folder Name:  ");
              file = new File(folderName);
              if (file.isDirectory() && file.exists())
              {
                  File [] lisFiles = file.listFiles();
                  for (int x=0; x<lisFiles.length; x++)
                  {
                      String Fname = lisFiles[x].getName();
                      if(Fname.endsWith(".csv"))
                      {  
                          ExportEmpDataIntodatFile(folderName , Fname , "all");
                          JOptionPane.showMessageDialog(null, "data saved successfully into .dat file");
                          break;
                      }
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "The Folder is not found.");  
              exit3 = true;
              break;  //end case 3
              
              
         case 4:  // Search About Emplooy
              folderName = JOptionPane.showInputDialog(null, "Enter The Folder Name:  ");
              file = new File(folderName);
              if (file.isDirectory() && file.exists())
              {
                  File [] lisFiles = file.listFiles();
                  for (int x=0; x<lisFiles.length; x++)
                  {
                      String Fname = lisFiles[x].getName();
                      if(Fname.endsWith(".csv"))
                      {  
                          int ID = Integer.parseInt( JOptionPane.showInputDialog(null, "Enter The Emplooy ID:  "));
                          if (searchAboutEmplooy(ID, folderName, Fname) == false)
                               JOptionPane.showMessageDialog(null, "The Emplooy you Seaerch about it is not found. ");
                          break;
                      }
                  }
              }
              else
                  JOptionPane.showMessageDialog(null, "The Folder is not found or check the entered name for the folder.");              
             exit3 = true;
             break;  // end case 4
             
             
        case 5:  // delete emplooy
            inputJOp = JOptionPane.showInputDialog("Enter the Folder name: ");   folderName = inputJOp;   // mod
            file = new File(inputJOp);
            if (file.isDirectory() && file.exists() && validities.equals(empLogin.getEmpValidities()))
            {
                File [] listFiles = file.listFiles();
                for (int x = 0; x<listFiles.length; x++)
                {
                    String Fname = listFiles[x].getName();
                    if (Fname.endsWith(".csv"))  
                    {
                        inputJOp = JOptionPane.showInputDialog("Enter the ID: ");    
                        String folder = loginFolder , filee = loginFile;  // there is cuase for create these variable;
                        if (  DeletFromeFile(folderName, Fname, inputJOp)  && DeletFromeFile(folder, filee, inputJOp) )  // systemFolder  , systemFile  , inputJOP;
                        { 
                            ExoprtEMPDataIntoObjFile(folderName, Fname , "all");
                            ExportEmpDataIntodatFile(folderName, Fname , "all"); 
                            ExoprtEMPDataIntoObjFile(folder, filee , "notall");     // mod                         
                            ExportEmpDataIntodatFile(folder, filee , "notall");    // mod
                            JOptionPane.showMessageDialog(null, "The Emplooy is delete from the .csv  && .dat  && .obj");  break;   
                        }
                        else
                            JOptionPane.showMessageDialog(null, "The Emplooy is not delete from the .csv  && .dat  && .obj");                                                
                       }
                   }
               }
               else
                   JOptionPane.showMessageDialog(null, "The File is not fonud ||  empty  ,, or you do not have a validities to execute this an operation.");
            exit3 = true;
            break;  // end case 5
           
            
        case 6:  // modify data
            if (validities.equals(empLogin.getEmpValidities()))                
                loginToEmplooyGroup();
            else
                JOptionPane.showMessageDialog(null, "You do not have a validities to execute this an operation.");
           exit3 = true;
           break;  // end case 6
           
           
        case 7:   // show the data
            folderName = JOptionPane.showInputDialog("Enter The Group Name:  ");
            file = new File(folderName);
            if (file.isDirectory() && file.exists())
            { 
                File [] lisFiles = file.listFiles();
                for (int x=0; x<lisFiles.length; x++)
                {
                    String Fname = lisFiles[x].getName();
                    if(Fname.endsWith(".csv"))
                    {   showTheData(folderName , Fname);
                        //showTheData(systemFolder, systemFile);
                        break;
                    }
                }
            }
            else
              JOptionPane.showMessageDialog(null, "The Folder is not found");
            exit3 = true;
            break;  // end case 7
           
            
           default:
               exit3 =false;
               break;   // end default              
              
      } // end switch
                    
      }while(exit3 != false);  // end while
      
      
  }  // end emplooy Departement method;
  
 //=================================================   >> *** <<   ========================================================================
 //------------------------------------------------    >> *** <<   ------------------------------------------------------------------------
  
  public static boolean addEmplooy(String folderName  , String fileName) throws IOException
  {  
      FileWriter      fws = new FileWriter(loginFolder + "\\" + loginFile, true);
      BufferedWriter  bws = new BufferedWriter(fws);
      PrintWriter pws = new PrintWriter(bws);  
      DataOutputStream doss = new DataOutputStream(new FileOutputStream(loginFolder + "\\" + loginFile.replaceAll(".csv", ".dat"), true));
      
      int ID = getID(folderName, fileName);   ID += 1 ;
      boolean isAdd = false;
      
      emp = new EmployeesGroup();
      try {                                      
              fw = new FileWriter(folderName + "\\" + fileName, true);
              bw = new BufferedWriter(fw);
              pw = new PrintWriter(bw);
              dos = new DataOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat"), true));
              oos = new ObjectOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj"), true));
              
              emp.setEmpID(ID);
              emp.setEmpName(JOptionPane.showInputDialog(null, "Enter Emplooy Name:  "));
              emp.setEmpAddress(JOptionPane.showInputDialog(null, "Enter Emplooy Address:  "));
              emp.setEmpGender(JOptionPane.showInputDialog(null, "Enter Emplooy Gender:  "));
              emp.setEmpJob(JOptionPane.showInputDialog(null, "Enter Emplooy Job:  "));
              emp.setEmpDepartment(JOptionPane.showInputDialog(null, "Enter Emplooy Departement:  "));
              emp.setEmpSalary(Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Emplooy salary:  ")));
              emp.setEmpWorkHuor(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Emplooy Work Hour:  ")));
              emp.setEmpCode(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Emplooy Code:  ")));
              
              inputJOp = JOptionPane.showInputDialog(null, "Determine Emplooy Validities\n"
                                           + "1. read\n"
                                           + "2. read and write\n"
                                           + "\n\nEnter your choice number here:");
              
                                            if (inputJOp.equals("1"))                                                
                                                emp.setEmpValidities("r");
                                            
                                            else if (inputJOp.equals("2"))
                                                emp.setEmpValidities("rw");
                                            
                                            else  // if the user not follow the insturction , give it the defualt Validities  /  premsion;
                                                emp.setEmpValidities("r");
//              emp.setEmpValidities(JOptionPane.showInputDialog(null, "Enter Emplooy Validities:  "));

              pw.println(emp.getEmpID() + ", " + emp.getEmpName() + ", " + emp.getEmpAddress() + ", " + emp.getEmpGender() + ", " + emp.getEmpJob() + ", " + emp.getEmpDepartment() + ", " +  emp.getEmpSalary()+ ", " + emp.getEmpWorkHuor());             
              dos.writeInt(emp.getEmpID());
              dos.writeUTF(emp.getEmpName());
              dos.writeUTF(emp.getEmpAddress());
              dos.writeUTF(emp.getEmpGender());
              dos.writeUTF(emp.getEmpJob());
              dos.writeUTF(emp.getEmpDepartment());  
              dos.writeDouble(emp.getEmpSalary());
              dos.writeInt(emp.getEmpWorkHuor());   
              oos.writeObject(emp);  
              
              pws.println(emp.getEmpID() + ", " + emp.getEmpName() + ", " + emp.getEmpCode()  + ", " + emp.getEmpValidities());
              doss.writeInt(emp.getEmpID());
              doss.writeUTF(emp.getEmpName());
              doss.writeInt(emp.getEmpCode());  
              doss.writeUTF(emp.getEmpValidities());             
              
              pw.flush();  pws.flush();
              pw.close();  pws.close();
              bw.close();  bws.close();
              fw.close();  fws.close();
              dos.close(); doss.close();
              oos.close();                
              JOptionPane.showMessageDialog(null, "Emplooy Data is saved in the system And These it's Data.");
              emp.getDetailsEmplooy();
              isAdd = true;              
          
          
      } catch (IOException iOException) {
      } catch (HeadlessException headlessException) {
      } catch (NumberFormatException numberFormatException) {
      }
      
     return  isAdd;
                 
  }  // end add emplooy method
  
  
  public static void ExoprtEMPDataIntoObjFile(String folderName , String fileName , String determine) throws FileNotFoundException, IOException
  {
      try {           
          fr = new FileReader(folderName + "\\" + fileName);   
          br = new BufferedReader(fr);   
          oos = new ObjectOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj") ));
          
          String[] data = null;
          String line = null;  
          if (determine.equals("all"))
          {  
              while ((line = br.readLine()) != null) {    
              emp = new EmployeesGroup();               
              data = line.split(", ");
              emp.setEmpID(Integer.parseInt(data[0]));              
              emp.setEmpName(data[1]);
              emp.setEmpAddress(data[2]);
              emp.setEmpGender(data[3]);
              emp.setEmpJob(data[4]);
              emp.setEmpDepartment(data[5]);
              emp.setEmpSalary(Double.parseDouble(data[6]));
              emp.setEmpWorkHuor(Integer.parseInt(data[7]));             
              oos.writeObject(emp);    
              }  // end while              
          }  //end if 
          
          else
          {  
              while ((line = br.readLine()) != null) {  
              emp = new EmployeesGroup();              
              data = line.split(", ");
              emp.setEmpID(Integer.parseInt(data[0]));                        
              emp.setEmpName(data[1]); 
              emp.setEmpCode(Integer.parseInt(data[2]));
              emp.setEmpValidities(data[3]);                  
              oos.writeObject(emp);   
            }
         }
          
          fr.close();  
          br.close(); 
          oos.close();  
          //JOptionPane.showMessageDialog(null, "data saved in .obj file");
      } catch (IOException iOException) {
      } catch (NumberFormatException numberFormatException) {
      }
  }  // end Export Emplooy Data Into obj
  
  
  public static void ExportEmpDataIntodatFile(String folderName , String fileName , String determine) throws FileNotFoundException, IOException
  {
      emp = new EmployeesGroup();
      try {          
          fr = new FileReader(folderName + "\\" + fileName);
          br = new BufferedReader(fr);  
          dos = new DataOutputStream(new FileOutputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat") ));          
          String[] data = null;          
          String line = null;  
          if (determine.equals("all"))
          {  
              while ((line = br.readLine()) != null) {  
              data = line.split(", ");  
              emp.setEmpID(Integer.parseInt(data[0]));     
              emp.setEmpName(data[1]);
              emp.setEmpAddress(data[2]);
              emp.setEmpGender(data[3]);
              emp.setEmpJob(data[4]);  
              emp.setEmpDepartment(data[5]);  
              emp.setEmpSalary(Double.parseDouble(data[6]));  
              emp.setEmpWorkHuor(Integer.parseInt(data[7]));    
             
              dos.writeInt(emp.getEmpID());
              dos.writeUTF(emp.getEmpName());
              dos.writeUTF(emp.getEmpAddress());  
              dos.writeUTF(emp.getEmpGender());
              dos.writeUTF(emp.getEmpJob());
              dos.writeUTF(emp.getEmpJob());
              dos.writeDouble(emp.getEmpSalary());
              dos.writeInt(emp.getEmpWorkHuor());         
          }  // end while             
          }  // end if 
          
          else
          {
              while ((line = br.readLine()) != null) {  
              data = line.split(", ");  
              emp.setEmpID(Integer.parseInt(data[0]));     
              emp.setEmpName(data[1]);
              emp.setEmpCode(Integer.parseInt(data[2])); 
              emp.setEmpValidities(data[3]);              
              dos.writeInt(emp.getEmpID());
              dos.writeUTF(emp.getEmpName());
              dos.writeInt(emp.getEmpCode());
              dos.writeUTF(emp.getEmpValidities());                      
            }  // end while
         }
                    
          fr.close();
          br.close();
          dos.close();
          //JOptionPane.showMessageDialog(null, "data saved in .dat file");
      } catch (IOException iOException) {
      } catch (NumberFormatException numberFormatException) {
      }
  }  // Export Emplooy Data Into dat
  
       
    public static boolean searchAboutEmplooy(int searchID , String folderName  , String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        boolean isFound = false;
        emp = new EmployeesGroup();
        try {
            EmployeesGroup read = new EmployeesGroup();            
            fr = new FileReader(folderName + "\\" + fileName);
            br = new BufferedReader(fr);
            dis = new DataInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".dat")));            
            ois = new ObjectInputStream(new FileInputStream(folderName + "\\" + fileName.replaceAll(".csv", ".obj")));
            
            String[] data = null;            
            String line = null;
            int id1 = 0, id2 = 0, id3 = 0;
            
            while ((line = br.readLine()) != null) {
                isFound = false;
                data = line.split(", ");
                id1 = (Integer.parseInt(data[0]));                
                id2 = (dis.readInt());
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readDouble();
                dis.readInt();
                emp = (EmployeesGroup) ois.readObject();
                id3 = emp.getEmpID();
                
                if (searchID == id1 && searchID == id2 && searchID == id3) {
                    inputJOp = JOptionPane.showInputDialog(null, "The emplooy is found in the .csv && .dat && .obj Files , to show it's information Enter 1:  ");                    
                    if (inputJOp.equals("1")) 
                    {
                        showRecord(line);
                    }                    
                    fr.close();
                    br.close();
                    dis.close();
                    ois.close();
                    isFound = true;
                    break;                    
                }                
            }  // end while
        } catch (IOException iOException) {
        } catch (NumberFormatException numberFormatException) {
        } catch (ClassNotFoundException classNotFoundException) {
        } catch (HeadlessException headlessException) {
        }
        return isFound;
    }  // end search about emplooy method;
       
    
     public static void loginToEmplooyGroup() throws IOException, FileNotFoundException, ClassNotFoundException
     {
         String Fname = null;
         boolean isThereModify = false , exit4  = false;         
         int empID = Integer.parseInt(JOptionPane.showInputDialog("Enter Emplooy ID that you want to login to it:"));
         String folderName = JOptionPane.showInputDialog(null, "Enter Emplooy Folder Name: ");  // what will happen if the user entered system folder name instead of emplooys folder;  all the changes will be just in system file 
         file = new File(folderName);
            if (file.isDirectory() && file.exists())
            { 
                File [] lisFiles = file.listFiles();
                for (int x=0; x<lisFiles.length; x++)
                {
                    Fname = lisFiles[x].getName();
                    if(Fname.endsWith(".csv"))
                           break;                    
                }
            }
            else
              JOptionPane.showMessageDialog(null, "The Folder is not found");
            
            
         if ( searchAboutEmplooy(empID, folderName, Fname) )   
         {  
             getInfoEMPFromLoginFile(loginFolder, loginFile, emp.getEmpName());
             JOptionPane.showMessageDialog(null, "login into Emplooy account is successfuly.");
             
             do
             {
                 inputJOp = JOptionPane.showInputDialog(
                            "You can do the flowing operations on account:\n"
                            + "\n1. Increment the salary.\n"
                            + "2. Decrement the salary.\n"
                            + "3. Increment The Work Hours.\n"
                            + "4. Decrement The Work Hours.\n"
                            + "5. Change the validities.\n"
                            + "6. Change the password.\n"
                            + "7. Get Details Emplooy Account.\n\n"
                            + "Otherwise to comeback to main screen.\n"
                            + "Enter your choice number:  ");
                    
                    int cho = Integer.parseInt(inputJOp);
                    
                    switch (cho){
                        case 1: 
                            emp.incrementSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter money that will add with last salary:  ")));
                            isThereModify = true;
                            exit4 = true;                            
                            break;
                            
                        case 2:
                            emp.decrementSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter money that will minus from last salary:  ")));
                            isThereModify = true;
                            exit4 = true;                            
                            break;
                            
                        case 3:
                            emp.incrementWorkHour(Integer.parseInt(JOptionPane.showInputDialog("Enter hour that will add with last hours:  ")));
                            isThereModify = true;
                            exit4 = true;                            
                            break;                                                        
                            
                        case 4:
                            emp.decrementWorkHour(Integer.parseInt(JOptionPane.showInputDialog("Enter hour that will minus from last hours:  ")));
                            isThereModify = true;
                            exit4 = true;                            
                            break;
                            
                        case 5:
                            inputJOp = JOptionPane.showInputDialog(null, "Determine the new validites to the emplooy :\n"
                                    + "1. read.\n"
                                    + "2. read and write.\n"
                                    + "\nEnter your choice here:  ");
                            if (inputJOp.equals("1"))
                                emp.setEmpValidities("r");
                            else if (inputJOp.equals("2"))
                                emp.setEmpValidities("rw");
                            else
                                validities = "rw";  // do not work any thing , just to do else statement ,, Emplooy Validities not change ;
                            isThereModify = true;                                                      
                            exit4 = true;                            
                            break;
                            
                        case 6:
                            emp.setEmpCode(Integer.parseInt(JOptionPane.showInputDialog("Enter the new password :  ")));
                            isThereModify = true;                       
                            exit4 = true;                            
                            break;
                                                        
                        case 7:
                            emp.getDetailsEmplooy();                            
                            exit4 = true;                            
                            break;
                            
                        default:
                            exit4 = false;
                            break;
                    }
                 
             }while(exit4 != false);
             
                if (  isThereModify == true && validities.equals(empLogin.getEmpValidities()) )  // mod
                {
                    int r = Integer.parseInt(JOptionPane.showInputDialog(null, "To save the last modified Enter 1 : "));
                    if ( r == 1)
                    {   
                        String folder = loginFolder , filee = loginFile;
                         if( saveTheModified(emp.getEmpID(), folderName, Fname, "empl")  &&   saveModifyOnSystemFile(emp.getEmpID()  , folder  , filee)  ) {   
                            
                             ExportEmpDataIntodatFile(folderName, Fname , "all");   
                             ExoprtEMPDataIntoObjFile(folderName, Fname, "all");      
                             ExportEmpDataIntodatFile(folder , filee , "notall");  
                             ExoprtEMPDataIntoObjFile(folder, filee , "notall");                             
                                                          
                             JOptionPane.showMessageDialog(null, "Save last modified on account is successfully .");
                        } else JOptionPane.showMessageDialog(null, "last modified on account is faild");                                                 
                    }                                     
                }
                else
                    exit4 = false;                                     
         }
         else
             JOptionPane.showMessageDialog(null, "\t\tErrot , Try again.");                           
         
     }  // end login To Emplooy group method;
     
    public static  boolean saveModifyOnSystemFile (int idSearch , String folderName , String fileName) throws IOException
    {
        boolean ismodify =false;
        try {  
            fr = new FileReader(folderName  + "\\"  + fileName);
            br = new BufferedReader(fr);             
                                  
            String line = null;
            String[] data = new String[100];  // [getID];
            String[] record = null; 
            int id = 0 , i = 0 , j = 0;   
            
             while ((line = br.readLine()) != null) {                             
                data[i] = line;  
                i++;                  
            }
             fr.close();
             br.close();
             // after read all the data ,, delete old last file  and then create another one without any data to write the data after modify ;
             file = new File(folderName  + "\\"  + fileName);
             if (file.delete()  && file.exists() == false)
             {
                fw = new FileWriter(folderName  + "\\"  + fileName );
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw); 

                while (j<i) {                    
                    line = data[j];      
                    record = line.split(", "); 
                    id = Integer.parseInt(record[0]);   

                    if (id == idSearch) {   
                        pw.println(emp.getEmpID() + ", " + emp.getEmpName() + ", " + emp.getEmpCode() + ", " + emp.getEmpValidities());
                        //dos.writeInt(emp.getEmpID());   dos.writeUTF(emp.getEmpName());  dos.writeInt(emp.getEmpCode());  dos.writeUTF(emp.getEmpValidities());
                    } else {                    
                        pw.println(data[j]);
                        //dos.writeInt(emp.getEmpID());   dos.writeUTF(emp.getEmpName());  dos.writeInt(emp.getEmpCode());  dos.writeUTF(emp.getEmpValidities());               
                    }                
                  j++;
                }                       
                pw.flush();          pw.close();            
                bw.close();          fw.close();            
                ismodify = true;            
             }
                        
        } catch (IOException iOException) {
        } catch (NumberFormatException numberFormatException) {
        } 
        return ismodify;
                              
    }  // end save modify on file System method;  
    
    
    public static int getID (String folderName , String fileName) throws FileNotFoundException, IOException
    {
        fr = new FileReader(folderName + "\\" + fileName);
        br = new BufferedReader(fr);
        String line = null;
        String [] data = null;
        int id = 0 ;
        while ( (line  = br.readLine()) != null)
        {
            data = line.split(", ");
            id = Integer.parseInt(data[0]);            
        }
        
        return id;
    }  // end get ID method;
    
    public static void getInfoEMPFromLoginFile(String folderName , String fileName , String userName ) 
    {                   
        try {                       
            fr = new FileReader(folderName + "\\" + fileName);   //file.getAbsoluteFile();
            br = new BufferedReader(fr);
                                    
            String[] data = null;            
            String line = null , nameEMP = null , ValiditieEMP = null; 
            int passwordEMP = 0 ;
            
            while ((line = br.readLine()) != null) {               
                data = line.split(", ");                 
                nameEMP = data[1];
                passwordEMP = Integer.parseInt(data[2]);        
                ValiditieEMP=  data[3];
                                   
                if ( nameEMP.equals(userName) )
                {     
                    emp.setEmpCode(passwordEMP);        emp.setEmpValidities(ValiditieEMP);
                    fr.close();                         br.close();                   
                    break;                    
                }                
            }  // end while
        } catch (IOException | NumberFormatException | HeadlessException iOException) {
        }       
                                     
    }  // end get info from login file for emplooy not found in emplooys file  method ;
    
                           
}  // end class;
