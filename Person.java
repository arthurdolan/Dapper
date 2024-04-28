import java.util.List;
import java.util.ArrayList;
public class Person {
    private String name;
    private int numFollowers;
    private List<Person> following;
    
    public Person(String name)
    {
        this.name = name;
        this.numFollowers = 0;
        this.following = new ArrayList<>();
    }
    public void addFollower()
    {
        numFollowers++;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", ");
        sb.append("Followers: ").append(numFollowers).append(", ");
        sb.append("Following: ");
        for (Person person : following) {
            sb.append(person.getName()).append(", ");
        }
        return sb.toString();
    }
}
