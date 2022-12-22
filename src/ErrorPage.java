import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ErrorPage extends JFrame implements ActionListener
{
    //constructor  
    ErrorPage()
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Logged in");
        setSize(400, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}