import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
//Defines Person type with all features needed for social media
public class Person {
    private String name;
    private List<Person> following;
    private List<Person> followers;
    private HashMap<Integer, ArrayList<Person>> propagationMap;
    public Person(String name)
    {
        this.propagationMap = new HashMap<>();
        this.name = name;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }
    public void addFollower(Person follower)
    {
        this.followers.add(follower);
    }
    public void addFollowing(Person following)
    {
        this.following.add(following);
    }
    public String getName()
    {
        return name;
    }
    public List<Person> getFollowers()
    {
        return followers;
    }
    public List<Person> getFollowing() {
        return following;
    }
    public HashMap<Integer, ArrayList<Person>> getPropMap()
    {
        return propagationMap;
    }
    public void addPropagation(int degree, Person person)
    {
        ArrayList<Person> peopleInThisDegree = propagationMap.getOrDefault(degree, new ArrayList<>());
        peopleInThisDegree.add(person);
        propagationMap.put(degree, peopleInThisDegree); 
    }
}
