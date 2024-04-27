import java.util.List;
import java.util.ArrayList;
public class Person {
    private String name;
    private int numFollowers;
    private List<Person> following;
    
    public Person(String name, int numFollowers)
    {
        this.name = name;
        this.numFollowers = numFollowers;
        this.following = new ArrayList<>();
    }
    public int addFollower()
    {
        return numFollowers++;
    }
    public void addFollowing(Person following)
    {
        this.following.add(following);
    }
    public String getName()
    {
        return name;
    }
    public int getFollowers()
    {
        return numFollowers;
    }
    public List<Person> getFollowing() {
        return following;
    }
}
