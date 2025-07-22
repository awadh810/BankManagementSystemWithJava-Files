package bankmanagementsystem;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class BankInfo extends customerInfo implements Serializable{
    
    private double totalAmount;
    private double lastDeposit;
    private double lastMist;
    private int accountNumber; 
    
    private static int customer = 0;      // to get how maney Client in a Bank (and account number for customer in a bank)
    public boolean openAccount = false;   // After opened account aoutomatic change from false to true;
    //BankInfo c = new BankInfo();
  
    public BankInfo()
    {
        totalAmount = 0.00;
        lastDeposit = 0.00;
        lastMist = 0.00;   
    }
    // this constructor it used when  read object from file , then build the object that we want use it in reccentlly in the program  , that contain all the attributes for object
    public BankInfo (String nam , String addr , int num , int accontNum /*,int code*/ ,  double amount ,double lastD , double lastM  , boolean open)
    {
        this.itsName = nam;
        this.itsAddres = addr;        
        this.itsNumber = num;
        //codeAccount = code;
        this.accountNumber = accontNum;        
        this.totalAmount = amount;
        this.lastMist = lastM;
        this.lastDeposit = lastD;        
        this.openAccount = open;
    }
    
    public void openAcount (String nam , String addr , int num , double amount , int accountNumber)  
    {
        this.itsName= nam; 
        this.itsNumber=num;
        this.itsAddres=addr;
        this.codeAccount+=1;                 // will be developer in the next step; now is defualt by 1 for all customer;
        this.totalAmount = amount;   
        this.openAccount = true;             // this step it means the account is opened  , it will be used to close account when we want close it; 
        customer+=1;  
        this.accountNumber = accountNumber;    // to keep Account number for every customer / Client;      
    }
      
     /* public void closeAcount(boolen check)  // this method will be Develops in the next steps;
    {
        openAcount=false;
    }*/
    
    public double depositMoney (double amount) 
    {  
         totalAmount+=amount;
         lastDeposit = amount;
         return totalAmount;
    }
    
    public double mistsMoney (double amount)   
    {
        if (this.totalAmount >= minMoney)
        {
           totalAmount-=amount;
           lastMist = amount;
           return totalAmount; 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your money is :  " + this.totalAmount 
                    +"\nYou can not mist money from your account it will be less than minimum money to open accont.\n");
            return totalAmount;
        }
         
    }
    
    public double totalAmountQueriying()
    {
         return totalAmount;
    }
     
    public void getDetailsAcount()
    {
        final String NEWLIN = "\n";
        final String TAB = "\t\t";
        
        JOptionPane.showMessageDialog(null, "\n" + "   Account name:  " + itsName + TAB + "         the Adress:  " +  itsAddres + TAB + "         the number:  " + itsNumber + NEWLIN +
                           "         Total amount:  " + totalAmount + TAB +  "         Last deposit:  " + lastDeposit + TAB + "         Last Mist:  " + lastMist + NEWLIN +
                           "         Account number: " + accountNumber + TAB + "         Account code: " +codeAccount + NEWLIN);
        
    }
    
    public void ExplainTheOutput(String text , double num)
    {
        JOptionPane.showMessageDialog(null , text + num + "\n");
    }
         
    public double getLastDeposit() {
        return lastDeposit;
    }

    public double getLastMist() {
        return lastMist;
    }

    public int getAccountNumber() {
        return accountNumber;  
    }
   
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;  
    }

    public boolean isOpenAccount() {
        return openAccount;
    }
    
    
}
