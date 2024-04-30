
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.List;;
public class Visual 
{

    public Visual()
    {
    }
    
    public JFrame addWindow(String windowName, JPanel buttons)
    {
        JFrame frame = new JFrame(windowName);
        //JPanel panel = new JPanel();
        frame.setSize(400,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(buttons));
        frame.setVisible(true);
        return frame;
    }
    public void namesWindow(Set<String> hashNames)
    {
        JPanel buttons = new JPanel(new GridLayout(0,1));
        List<String> namesList =  new ArrayList<>(hashNames);
        Collections.sort(namesList);
        for(String name : namesList)
        {
            JButton actualButton = new JButton(name);
            buttons.add(actualButton);
        }
        addWindow("List of Dapper Users", buttons);
    }
}
