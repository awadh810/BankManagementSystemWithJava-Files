package bankmanagementsystem;

import java.io.Serializable;
import javax.swing.JOptionPane;

public class EmployeesGroup implements Serializable {  // who can use the system  ,, { users};
    
    private String empName;
    private String empAddress;
    private String empGender;
    private String empJob;
    private String empDepartment;
    private double empSalary;
    private int empID;
    private int empWorkHuor;       // or can change it to String as :  Period of time  ( morning - evening ) instead of int as Work Huor ;
    private int empCode = 0;
    private String empValidities = "r" ;   // default validities is read ;  permission == empValidities
    public static int empTotal = 0;
    

    public EmployeesGroup() {
    }

    public EmployeesGroup(String empName, String empAddress, String empGender, String empJob, String empDepartment ,  double empSalary, int empID, int workHuor , int empCode ,  String empValidities ) {
        this.empName = empName;
        this.empAddress = empAddress;
        this.empGender = empGender;
        this.empJob = empJob;
        this.empDepartment = empDepartment;
        this.empSalary = empSalary;
        this.empID = empID;
        this.empWorkHuor = workHuor;
        this.empCode  = empCode;
        this.empValidities = empValidities;
    }
    
       
    public double incrementSalary(double salary)
    {
          this.empSalary += salary;
          return empSalary;
    }
    
    public double decrementSalary(double Salary)
    {
          this.empSalary -= Salary;
          return empSalary;
    }
    
    public double incrementWorkHour(int hour)
    {
          this.empWorkHuor += hour;
          return empWorkHuor;
    }
    
    public double decrementWorkHour(int hour)
    {
          this.empWorkHuor -= hour;
          return empWorkHuor;
    }
    
    public void setEmpValidities(String empValidities)
    {
        this.empValidities = empValidities;
    }
    
    public void setEmpCode(int empCode)
    {
        this.empCode = empCode;
    } 
    
    public void getDetailsEmplooy()
    {
        final String NEWLIN = "\n";
        final String TAB = "\t\t";
        
        JOptionPane.showMessageDialog(null, "\n" + "   Emplooy name:  " + empName + TAB + "         Emplooy Address:  " +  empAddress + TAB + "         Emplooy Gender:  " + empGender  + TAB + "         Emplooy Job:  " + empJob + TAB + "         Emplooy Departement:  " + empDepartment  + NEWLIN
                +  "         Emplooy Salary:  " +  empSalary+ TAB + "         Emplooy ID:  " + empID + TAB + "         Emplooy Code: " + empCode +  TAB + "         Emplooy Wrok Hour: " + empWorkHuor +  TAB + "         Emplooy Validities : " + empValidities +  NEWLIN );        
    }
    
       
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }
        
    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getEmpWorkHuor() {
        return empWorkHuor;
    }

    public void setEmpWorkHuor(int empWorkHuor) {
        this.empWorkHuor = empWorkHuor;
    }

    public int getEmpCode() {
        return empCode;
    }
   

    public String getEmpValidities() {
        return empValidities;
    }
           

    public static int getEmpTotal() {
        return empTotal;
    }

    public static void setEmpTotal(int empTotal) {
        EmployeesGroup.empTotal = empTotal;
    }
                    
}
