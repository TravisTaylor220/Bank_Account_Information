/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tetay
 */
public class TravisTaylorProject12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         try { Account accounts[] = new Account[4];
        accounts[0] = new CheckingAccount(1, 1500);
        accounts[1] = new CheckingAccount(2, 5500);

        accounts[2] = new SavingAccount(3, 1000);
        ((SavingAccount)(accounts[2])).setInterestRate(0.06);
        accounts[3] = new SavingAccount(4, 4800);
        ((SavingAccount)(accounts[3])).setInterestRate(0.092);

        for(Account account: accounts){
            System.out.println("Initial balance");
            System.out.println(account);
            System.out.println("Account's balance after withdrawing $10000");
            account.withdraw(10000);
            System.out.println(account);
            System.out.println();
            System.out.println("Account's balance before deposit of $-600");
            System.out.println(account);
            account.deposit(-600);
            System.out.println("Account's balance after Deposit of $-600");
            System.out.println(account);
            System.out.println();

            if(account instanceof SavingAccount){
                System.out.println("Interest rate included.");
                ((SavingAccount)account).addInterest();
                System.out.println(account);
                System.out.println();
            }
            }
       }catch (Exception e) {

   }
           // 
}
    static class Account {

    private int id;
    double balance;

    public Account() {
        id =0;
        balance =0;
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount){
       try {
           if (amount < 0)
               throw new IllegalAmountException();
           else
               this.balance += amount;
       } catch (IllegalAmountException e) {
           e.printStackTrace();
       }

   }

    public void withdraw(double amount){
        if(amount>0 && amount<=getBalance()){
            setBalance(getBalance()-amount);
    }
    }

    @Override
    public String toString() {
        return "Account ID: "+getId()+", Balance: $"+getBalance();
    }
    }
    static class CheckingAccount  extends Account{

    public CheckingAccount() {
    }

    public CheckingAccount(int id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
         try {
           if ((getBalance() - amount) < 0)
               throw new NoSufficientFundsException();
           else {
               setBalance(getBalance() - amount);
           }
       } catch (NoSufficientFundsException e) {
           e.printStackTrace();
       }

   }
    @Override
    public String toString() {
        return "Checking Account: "+ super.toString();
    }
  }
    static class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount() {
        interestRate = 0;
    }


    public SavingAccount(int id, double balance) {
        super(id, balance);
        interestRate = 0;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void withdraw(double amount)  {
       try {
           if ((getBalance() - amount) < 500)
               throw new NoSufficientFundsException();
           else {
               setBalance(getBalance() - amount);
           }
       } catch (NoSufficientFundsException e) {
           // TODO: handle exception
           e.printStackTrace();
       }

   }
    public void addInterest() {
        setBalance(getBalance() * (1 + getInterestRate()));
    }

    @Override
    public String toString() {
        return "Savings Account: "+super.toString() + ", Interest Rate: %" + String.format("%.2f",getInterestRate()*100);
    }
    }
    static class IllegalAmountException extends Exception {

    public IllegalAmountException() {
       super("Invalid amount");

   }
}

    static class NoSufficientFundsException extends Exception {

    public NoSufficientFundsException() {
       super("insufficient balance ");
        }
    }
  }



/*

run:
Initial balance
Checking Account: Account ID: 1, Balance: $1500.0
Account's balance after withdrawing $750
Checking Account: Account ID: 1, Balance: $750.0

Account's balance before deposit of $600
Checking Account: Account ID: 1, Balance: $750.0
Account's balance after Deposit of $600
Checking Account: Account ID: 1, Balance: $1350.0

Initial balance
Checking Account: Account ID: 2, Balance: $5500.0
Account's balance after withdrawing $750
Checking Account: Account ID: 2, Balance: $4750.0

Account's balance before deposit of $600
Checking Account: Account ID: 2, Balance: $4750.0
Account's balance after Deposit of $600
Checking Account: Account ID: 2, Balance: $5350.0

Initial balance
Savings Account: Account ID: 3, Balance: $1000.0, Interest Rate: %6.00
Account's balance after withdrawing $750
No withdrawal allowed. insufficient funds
Savings Account: Account ID: 3, Balance: $1000.0, Interest Rate: %6.00

Account's balance before deposit of $600
Savings Account: Account ID: 3, Balance: $1000.0, Interest Rate: %6.00
Account's balance after Deposit of $600
Savings Account: Account ID: 3, Balance: $1600.0, Interest Rate: %6.00

Interest rate included.
Savings Account: Account ID: 3, Balance: $1696.0, Interest Rate: %6.00

Initial balance
Savings Account: Account ID: 4, Balance: $4800.0, Interest Rate: %9.20
Account's balance after withdrawing $750
Savings Account: Account ID: 4, Balance: $4050.0, Interest Rate: %9.20

Account's balance before deposit of $600
Savings Account: Account ID: 4, Balance: $4050.0, Interest Rate: %9.20
Account's balance after Deposit of $600
Savings Account: Account ID: 4, Balance: $4650.0, Interest Rate: %9.20

Interest rate included.
Savings Account: Account ID: 4, Balance: $5077.8, Interest Rate: %9.20

BUILD SUCCESSFUL (total time: 0 seconds)


*/