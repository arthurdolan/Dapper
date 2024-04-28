//import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class Tasks {
    public Tasks()
    {
    }
    public void density(int nodes, int edges)
    {
        double density = (double) edges/(nodes*(nodes-1));
        String eightDPdensity = String.format("%.8f", density);
        System.out.println("density = "+eightDPdensity);
    }
    public void highestFollowers(Map<String, Person> personMap){
        int topFollowed =0;
        String mostFollowedUser = "";
        for(Person person : personMap.values())//iterates through person map values but had to make a person type to access followers
        {
            int followers = person.getFollowers();
            if(followers>topFollowed)
            {
                topFollowed = followers;
                mostFollowedUser = person.getName();
            }
            else if(followers == topFollowed)
            {
                if(0>person.getName().compareTo(mostFollowedUser))
                {
                    mostFollowedUser = person.getName();
                }
            }

        }
        System.out.println("Highest Followers = "+mostFollowedUser);
    }
    public void highestFollowing(Map<String, Person> personMap)
    {
        int maxNumFollowing = 0;
        String userFollowingMost = "";
        for(Person person : personMap.values())
        {
            List<Person> followingList = person.getFollowing();
            int following = followingList.size();
            if(following>maxNumFollowing)
            {
                userFollowingMost = person.getName();
                maxNumFollowing = following;
            }
        }
        System.out.println("Highest Following = "+userFollowingMost);
    }
    public void degreesOfSeparation(Map<String, Person> personMap)
    {
        
    }
}