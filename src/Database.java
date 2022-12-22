import java.sql.Connection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
static Connection con = Connect.connect();
static String sql ="";
private static final int NULL = 0;
public static boolean AddUser(String name, int password) throws SQLException {
    try{
    Statement st = con.createStatement();
    sql = "INSERT INTO Data(Name,Balance,Password) values('"
            + name + "',0," + password + ")";
    if(st.executeUpdate(sql) == 1){
        //onay formu
        return true;
    }
    }catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("what");
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public static boolean LoginUser(int ID, int password){
    try{
        sql = "select Password from Data where ID='"+ ID + "'";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        int DPassword = rs.getInt("Password");
        if(password == DPassword){

            return true;
        }
        else {
            return false;
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
public static CurrentUser CreateSession(int ID) throws SQLException {
    String name = "";
    int balance = 0;
    int currentID = 0;
    int bills = 0;
    int debt = 0;
    List<CurrentUserCards> cardList = new ArrayList<>();
    Statement st = con.createStatement();
    ResultSet result = st.executeQuery("SELECT * FROM Data where ID='" + ID + "'");

    while (result.next()) {
        currentID = result.getInt("ID");
        name = (result.getString("Name"));
        balance = (result.getInt("Balance"));
        bills = result.getInt("Bills");
        debt = result.getInt("Debt");
    }
    ResultSet Cards = st.executeQuery("SELECT * FROM Cards where OwnerID ='"+ID+"'");
    while(Cards.next()){
        int CardID = Cards.getInt("CardID");
        int CardBalance = Cards.getInt("CardBalance");
        int OwnerID = Cards.getInt("OwnerID");
        cardList.add(new CurrentUserCards(CardID,CardBalance,OwnerID));
    }
    return new CurrentUser(name, currentID, balance, debt, bills,cardList);
}
}
