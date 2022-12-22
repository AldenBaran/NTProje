import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class UserPanel extends JFrame implements ActionListener {
    CardLayout cardLayout;

    JButton viewCards_button;
    JButton return_button1;
    JButton return_button2;
    JButton withdraw_button;
    JButton deposit_button;
    JButton takeLoan_button;
    JButton confirmLoan_button;
    final JTextField loanField;
    JFrame frame;

    JPanel loggedPanel;
    JPanel mainPanel;
    JPanel cardsPanel;
    JPanel loanPanel;

    JLabel loanLabel;
    JLabel wel_label;
    JLabel info;
    UserPanel() {
        cardLayout = new CardLayout();
        frame = new JFrame();

        mainPanel = new JPanel(cardLayout);
        loggedPanel = new JPanel(new BorderLayout());
        cardsPanel = new JPanel(new BorderLayout());
        loanPanel = new JPanel(new GridLayout());

        wel_label = new JLabel();
        wel_label.setText("<html>Welcome: " + CurrentUser.name +
                "<br/>Your current balance: " + CurrentUser.balance+
                "<br/>You currently owe: " + CurrentUser.debt+
                "<br/>You will receive: " + CurrentUser.bills+"</html>");
        info= new JLabel("");

        loanLabel = new JLabel("Please enter the amount you want to take.");
        loggedPanel.setSize(400, 200);

        loanField = new JTextField(15);

        return_button1 = new JButton("Go Back");
        return_button1.addActionListener(this);
        return_button2 = new JButton("Go Back");
        return_button2.addActionListener(this);
        viewCards_button = new JButton("View Cards");
        viewCards_button.addActionListener(this);
        withdraw_button = new JButton("Withdraw Money");
        withdraw_button.addActionListener(this);
        deposit_button = new JButton("Deposit Money");
        deposit_button.addActionListener(this);
        takeLoan_button = new JButton("Take a loan");
        takeLoan_button.addActionListener(this);
        confirmLoan_button = new JButton("Confirm amount");
        confirmLoan_button.addActionListener(this);

        cardsPanel.add(info,BorderLayout.WEST);
        cardsPanel.add(return_button1,BorderLayout.EAST);

        loggedPanel.add(wel_label,BorderLayout.CENTER);
        loggedPanel.add(viewCards_button,BorderLayout.EAST);
        loggedPanel.add(takeLoan_button,BorderLayout.SOUTH);

        loanPanel.add(loanLabel);
        loanPanel.add(loanField);
        loanPanel.add(return_button2);
        loanPanel.add(confirmLoan_button);

        frame.setSize(500, 300);

        mainPanel.add(loggedPanel,"Logged");
        mainPanel.add(cardsPanel,"Cards");
        mainPanel.add(loanPanel,"Loan");

        frame.add(mainPanel);
        frame.setTitle("Logged in");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()== viewCards_button){
        info.setText("");
        for(CurrentUserCards a  : CurrentUser.UserCards){
            info.setText("<html>"+info.getText()+"<br/>"+"Card NO: "+a.CardID+"<br/>"+"Card Balance: "+a.CardBalance+"<br/>"+"<br/>"+"<html>");
        }
        cardLayout.show(mainPanel,"Cards");
    }
    if(e.getSource()== return_button1 || e.getSource()==return_button2){
        cardLayout.show(mainPanel,"Logged");
        loggedPanel.revalidate();
    }
    if(e.getSource()== takeLoan_button){
        cardLayout.show(mainPanel,"Loan");
        loggedPanel.revalidate();
    }
    if(e.getSource()== confirmLoan_button){
        int amount = Integer.parseInt(loanField.getText());
        CurrentUser.GetLoan(amount);
        wel_label.setText("<html>Welcome: " + CurrentUser.name +
                "<br/>Your current balance: " + CurrentUser.balance+
                "<br/>You currently owe: " + CurrentUser.debt+
                "<br/>You will receive: " + CurrentUser.bills+"</html>");
        cardLayout.show(mainPanel,"Logged");
        System.out.println(CurrentUser.getBalance());
    }
    }
}
