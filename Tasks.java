//import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
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
            List<Person> followerList = person.getFollowers();
            int followers = followerList.size();
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
            else if(following == maxNumFollowing)
            {
                if(0>person.getName().compareTo(userFollowingMost))
                {
                    userFollowingMost = person.getName();
                }
            }
        }
        System.out.println("Highest Following = "+userFollowingMost);
    }
    public void degreesOfSeparation(Map<String, Person> personMap, String firstUser)
    {

        Set<Person> secondDegreeHash = new HashSet<>();//hashset avoids duplicates
        Person firstPerson = personMap.get(firstUser); 
        List<Person> followerList = firstPerson.getFollowers();
        for(Person person : followerList)
        {
            Person secDegPerson = personMap.get(person.getName()); 
            List<Person> secDegFollowerList = secDegPerson.getFollowers();
            secondDegreeHash.addAll(secDegFollowerList);
        }
        secondDegreeHash.removeAll(followerList);
        secondDegreeHash.remove(firstPerson);
        
        int hashSize = secondDegreeHash.size();
        System.out.println("No of Second degree links = "+hashSize);

    }
    public void medianFollowers(Map<String, Person> personMap)
    {
        ArrayList<Integer> list = new ArrayList<>();//Sorts during insertion
        for(Person person : personMap.values())
        {
            List<Person> followerList = person.getFollowers();
            list.add(followerList.size());
        }
        int setSize = list.size();
        if(setSize%2==1)
        {
            setSize= ((setSize+1)/2)-1;
        }
        else{
            setSize = (setSize/2)-1;
        }
        Collections.sort(list);
        System.out.println("Median = "+list.get(setSize));
    }
}