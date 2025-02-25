import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//This class is a modular approach to solving all the required tasks in the project
public class Tasks {
    public Tasks()
    {
        //empty class (only used for methods)
    }
    public void density(int nodes, int edges)
    {
        double density = (double) edges/(nodes*(nodes-1));
        density = Math.round(density * 1e8) / 1e8; //Rounds to 8dp
        System.out.println("Task 1: The density of the graph represented by the social network is "+density);
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
        System.out.println("Task 2: The person with the highest number of followers is "+mostFollowedUser);
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
        System.out.println("Task 3: The person who follows highest number of people is "+userFollowingMost);
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
        System.out.println("Task 4: For the first user in the file ("+firstPerson.getName() +"), there are " +hashSize +" users two degrees away.");

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
        System.out.println("Task 5: The median value for the number of followers in the network is "+list.get(setSize));
    }
    public int infoSpreader(Map<String,Person> personMap)
    {
        String topPerson = "";
        int maxDistance =0;
        for(Person mainPerson : personMap.values())
        {
            int distance = calcReachBFS(mainPerson);//calls Bfs search
            if(distance>maxDistance)
            {
                topPerson = mainPerson.getName();
                maxDistance = distance;
            }
            else if(distance == maxDistance)
            {
                if(0>mainPerson.getName().compareTo(topPerson))
                {
                    topPerson = mainPerson.getName();
                }
            }
        }
        System.out.println("Task 6: The person who is most able to spread information in the network is "+topPerson);
        return(maxDistance);
    }
    public int calcReachBFS(Person mainPerson)
    {
        Queue<Person> queue = new LinkedList<>(); //creates queue to allow to iterate through all followers in order 
        Map<Person, Integer> traversal = new HashMap<>(); // allows us to store people and their degrees
        queue.add(mainPerson);
        traversal.put(mainPerson, 0);
        while(!queue.isEmpty())
        {
            Person currentPerson = queue.poll(); // accesses head off queue
            int degrees = traversal.get(currentPerson);
            mainPerson.addPropagation(degrees, currentPerson); // adds head of queue to propagation hashmap (needed for the visualisation)
            for(Person follower: currentPerson.getFollowers())
            {  
               if(!traversal.containsKey(follower))
               {
                    int followerDegrees = degrees + 1;//allows the correct degrees in the while loop whilst incrementing degrees for future loops
                    traversal.put(follower, followerDegrees);
                    queue.add(follower);//adds the the back of the queue to allow for the current loop to complete
               }
            }
        }
        return traversal.size(); // size needed for task 6
    }
}