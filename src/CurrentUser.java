import java.util.ArrayList;
import java.util.List;

public class CurrentUser {
   static String name;
   static int id;
   static int balance;
   static int debt;
   static int bills;
   static List<CurrentUserCards> UserCards = new ArrayList<>();
   public static void GetLoan(int amount){
        CurrentUser.debt += amount;
        CurrentUser.setBalance(CurrentUser.balance+amount);
    }

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        CurrentUser.balance = balance;
    }

    public static int getDebt() {
        return debt;
    }

    public static void setDebt(int debt) {
        CurrentUser.debt = debt;
    }

    public static int getBills() {
        return bills;
    }

    public static void setBills(int bills) {
        CurrentUser.bills = bills;
    }
}
