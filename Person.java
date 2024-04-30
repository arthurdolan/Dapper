import java.util.List;
import java.util.ArrayList;
public class Person {
    private String name;
    //private int numFollowers;
    private List<Person> following;
    private List<Person> followers;

    public Person(String name)
    {
        this.name = name;
        //this.numFollowers = 0;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }
    public void addFollower(Person follower)
    {
        this.followers.add(follower);
        //numFollowers++;
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
}
