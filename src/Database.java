import javax.swing.*;
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
static ErrorPage errorPage = new ErrorPage();
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
        if(rs !=null) {
            int DPassword = rs.getInt("Password");
            if (password == DPassword) {return true;}
            else {return false;}
        }else{
            errorPage.setVisible(true);
            JLabel dbError = new JLabel("This ID is not registered in our systems");
            errorPage.getContentPane().add(dbError);;
            return false;}
    } catch (SQLException e) {
        errorPage.setVisible(true);
        JLabel wel_label = new JLabel("This ID is not registered in our systems");
        errorPage.getContentPane().add(wel_label);;
        System.out.println("Please enter a valid ID");
        throw new RuntimeException(e);
    }
}
public static void CreateSession(int ID) throws SQLException {

    Statement st = con.createStatement();
    ResultSet result = st.executeQuery("SELECT * FROM Data where ID='" + ID + "'");

    while (result.next()) {
        CurrentUser.id = result.getInt("ID");
        CurrentUser.name = (result.getString("Name"));
        CurrentUser.balance = (result.getInt("Balance"));
        CurrentUser.bills = result.getInt("Bills");
        CurrentUser.debt = result.getInt("Debt");
    }

    ResultSet Cards = st.executeQuery("SELECT * FROM Cards where OwnerID ='"+ID+"'");
    while(Cards.next()){
        int CardID = Cards.getInt("CardID");
        int CardBalance = Cards.getInt("CardBalance");
        int OwnerID = Cards.getInt("OwnerID");
        CurrentUser.UserCards.add(new CurrentUserCards(CardID,CardBalance,OwnerID));
    }
}
}
