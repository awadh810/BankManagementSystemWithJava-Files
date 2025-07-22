package bankmanagementsystem;
import java.io.Serializable;

public class customerInfo implements Serializable{
    protected String itsName;  
    protected String itsAddres;  
    protected int itsNumber;
    protected int codeAccount = 0;
    
    protected boolean isInObjFile = false ;   
    protected static double minMoney = 5000;  // minimum money to open acount in a Bank
   // BankInfo cust =  new BankInfo();
    
    public customerInfo() 
    {
         
    }
   
   public boolean checkAmountToOpenAcount(double amount)
    { 
        if (amount>=minMoney)
            return true;
        else 
            return false;
    }
         
}
