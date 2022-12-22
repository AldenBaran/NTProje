import java.util.ArrayList;
import java.util.List;

public class CurrentUser {
   static String name;
   static int id;
    int balance;
    int owe;
    int gonnaGet;
    List<CurrentUserCards> UserCards;
    private static void GetMoney(){

    }
    private static void GiveMoney(){

    }

    public CurrentUser(String name, int id, int balance, int owe, int gonnaGet, List<CurrentUserCards> UserCards) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.owe = owe;
        this.gonnaGet = gonnaGet;
        this.UserCards = UserCards;
    }
}
