
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Visual 
{
    private Map<String, Person> personMap;
    private JFrame frame;
    private List<String> namesList;
    public Visual(Map<String, Person> personMap, Set<String> hashName)
    {
        this.personMap = personMap;
        List<String> namesList =  new ArrayList<>(hashName);
        Collections.sort(namesList);
        this.namesList = namesList;
    }
    
    public JFrame addWindow(String windowName, JPanel buttons)
    {
        frame = new JFrame(windowName);
        //JPanel panel = new JPanel();
        frame.setSize(800,1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(buttons));
        frame.setVisible(true);
        return frame;
    }
    public void namesWindow()
    {
        JPanel buttons = new JPanel(new GridLayout(0,1));
        //List<String> namesList =  new ArrayList<>(hashName);
        
        for(String name : namesList)
        {
            JButton actualButton = new JButton(name);
            buttons.add(actualButton);
            actualButton.addActionListener(new buttonClicked());
        }
        addWindow("Home - List of Dapper Users", buttons);
    }
    class buttonClicked implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton retrievedName = (JButton) e.getSource();
                String textName = retrievedName.getText();
                if(textName == "Return Home")
                {
                    namesWindow();

                }
                else{
                    frame.dispose();
                openUserWindow(textName);
                

                }
            }
        }
    public void openUserWindow(String name)
    {
        Person person = personMap.get(name);

        //follower

        List<String> followerList =  new ArrayList<>();
        for(Person personIndex : person.getFollowers())
        {
            followerList.add(personIndex.getName());
        }
        Collections.sort(followerList);
        followerList.add(0,"Return Home");
        JPanel followersPanel = new JPanel(new GridLayout(0,1));
        followersPanel.setBorder(BorderFactory.createTitledBorder("Followers"));
        for(String nameIndex : followerList)
        {
            JButton actualButton = new JButton(nameIndex);
            followersPanel.add(actualButton);
            actualButton.addActionListener(new buttonClicked());
        }

        //following

        List<String> followingList =  new ArrayList<>();
        for(Person index : person.getFollowing())
        {
            followingList.add(index.getName());
        }
        Collections.sort(followingList);
        JPanel followingPanel = new JPanel(new GridLayout(0,1));
        followingPanel.setBorder(BorderFactory.createTitledBorder("Following"));
        for(String nameIndex : followingList)
        {
            JButton actualButton = new JButton(nameIndex);
            followingPanel.add(actualButton);
            actualButton.addActionListener(new buttonClicked());
        }
        
        //propagation

        Map<Integer ,ArrayList<Person>> propMap =person.getPropMap();
        JPanel propagationPanel = new JPanel(new GridLayout(0,1));
        propagationPanel.setBorder(BorderFactory.createTitledBorder("Propagation Graph"));
        Set <Integer> listOfDegrees = propMap.keySet();
        
        ArrayList<Integer> sortedDegrees = new ArrayList<>(listOfDegrees);
        Collections.sort(sortedDegrees);
        for(int degreesOfProp : sortedDegrees)
        {
            if (degreesOfProp == 0) 
                {
                    continue;// Skip degree
                }
            JPanel degreePanel = new JPanel(new GridLayout(0, 1));
            degreePanel.setBorder(BorderFactory.createTitledBorder("Degree of Propagation: " + degreesOfProp));
            propagationPanel.add(degreePanel);

            ArrayList<Person> peopleInDegree= propMap.get(degreesOfProp);
            for(Person personInDegree: peopleInDegree)
            {
                String personsName = personInDegree.getName();
                JButton actualButton = new JButton(personsName);
                propagationPanel.add(actualButton);
                actualButton.addActionListener(new buttonClicked());
            }
        }
        JPanel parentPanel = new JPanel(new GridLayout(1, 3));
        parentPanel.add(followersPanel);
        parentPanel.add(followingPanel);
        parentPanel.add(propagationPanel);
        addWindow(name+"'s User Record", parentPanel);
    }
    
}
