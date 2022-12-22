import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class FormMaster extends JFrame implements ActionListener {
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;

    //calling constructor
    FormMaster()
    {

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("ID");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("LOGIN"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("PLEASE LOG IN");         //set title to the login form
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
    {
        int ID = Integer.parseInt(textField1.getText());        //get user entered username from the textField1
        int pass = Integer.parseInt(textField2.getText());        //get user entered pasword from the textField2
        //check whether the credentials are authentic or not
        if (Database.LoginUser(ID,pass)) {  //if authentic, navigate user to a new page


              CurrentUser Dude;
            try {
                Dude = Database.CreateSession(ID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            JFrame frame = new JFrame();
            JPanel loggedPanel = new JPanel(new GridLayout(3, 1));

            add(loggedPanel, BorderLayout.CENTER);
            JLabel wel_label = new JLabel("Welcome: "+Dude.name);
            JLabel balance_label = new JLabel("Your current balance: "+Dude.balance);
            loggedPanel.setSize(400,200);

            loggedPanel.add(wel_label);
            loggedPanel.add(balance_label);
            frame.setSize(500,300);
            frame.add(loggedPanel);
            frame.setVisible(true);
            loggedPanel.setVisible(true);
        }
        else{
            NewPage page = new NewPage();
            page.setVisible(true);
            JLabel wel_label = new JLabel("Wrong Password or ID");
            page.getContentPane().add(wel_label);;
        }

    }
}
