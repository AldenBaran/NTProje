import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewPage extends JFrame implements ActionListener
{
    //constructor  
    NewPage()
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Logged in");
        setSize(400, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}