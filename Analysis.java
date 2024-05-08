import java.io.File; // File class
import java.io.FileNotFoundException; //Handles errors
import java.util.Scanner; // Reads text files
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*Main class: 
It has error checking
Creates person instances from the file whilst sorting into followers and following.
It calls all the tasks and visual functions
*/
public class Analysis
{
    public static void main(String[] args)
    {
        HashMap<String, Person> personMap = new HashMap<>(); // Way to access person instances in a loop  by their names
        Set<String> hashNames = new HashSet<>(); //Stores each name to ensure not added twice
        int numEdges = 0;//For density calculation later on
        if(args.length != 1)
        {
            System.out.println("Incorrect number of command line arguments (should be 1)");
        }
        else
        {
            boolean firstInput = true;
            String firstUser = "";//Stores initial user as hashMaps do not keep order
            String inputFileName = args[0]; // Takes first file from command line
            File inputFile = new File(inputFileName);
            try
            {
                Scanner readFile = new Scanner(inputFile); 
                Person actualUser = null;// Declared outside the loop so its scope increases out the if else loop
                while(readFile.hasNextLine())
                {
                    String line = readFile.nextLine();
                    String names[]= line.trim().split("\\s+");//seperates and stores each name in names[]
                    String actualUserName = names[0];
                    if(firstInput)
                    {
                        firstUser = actualUserName;
                        firstInput = false;
                    }
                    if(!hashNames.contains(actualUserName))
                    {
                        actualUser = new Person(actualUserName);
                        personMap.put(actualUserName, actualUser);
                        hashNames.add(actualUserName);
                    }
                    else
                    {
                        actualUser = personMap.get(actualUserName);
                    }
                    for(int i=1;i<names.length;i++)
                    {
                        String name = names[i];
                        if(!hashNames.contains(name))//will create person instance if not already created
                        {
                            Person person = new Person(name);
                            personMap.put(name, person);
                            hashNames.add(name);
                        }
                        actualUser.addFollowing(personMap.get(name));
                        numEdges++;
                        personMap.get(name).addFollower(actualUser);
                    }   
                }
                //TASKS INITIALISATION
                int numNodes = hashNames.size();
                Tasks tasks = new Tasks();
                tasks.density(numNodes,numEdges);
                tasks.highestFollowers(personMap);
                tasks.highestFollowing(personMap);
                tasks.degreesOfSeparation(personMap, firstUser);
                tasks.medianFollowers(personMap);
                tasks.infoSpreader(personMap);
                Visual visual = new Visual(personMap, hashNames);
                visual.namesWindow();
                //TASKS 
                readFile.close();
            }   
                catch (FileNotFoundException e)
                {
                    System.out.println("The File was not found");
                }      
        }
    }     
}