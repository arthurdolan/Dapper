import java.io.File; // File class
import java.io.FileNotFoundException; //Handles errors
import java.util.Scanner; // Reads text files
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Analysis
{
    public static void main(String[] args)
    {
        HashMap<String, Person> personMap = new HashMap<>(); // Way to access person instances in a loop  by their names
        Set<String> hashNames = new HashSet<>(); //Stores each name to ensure not added twice
        if(args.length != 1)
        {
            System.out.println("Incorrect number of command line arguments (should be 1)");
        }
        else
        {
            String inputFileName = args[0]; // Takes first file from command line
            File inputFile = new File(inputFileName);
            try
            {
                Scanner readFile = new Scanner(inputFile); 
                while(readFile.hasNextLine())
                {
 
                    String line = readFile.nextLine();
                    String names[]= line.trim().split("\\s+");//seperates and stores each name in names[]
                    String actualUserName = names[0];
                    Person actualUser = new Person(actualUserName);
                    personMap.put(actualUserName, actualUser);
                    hashNames.add(actualUserName);
                    for(int i=1;i<names.length;i++) //iterates through each name in names
                    {
                        String name = names[i];
                        if(!hashNames.contains(name))//will create person instance if not already created
                        {
                            Person person = new Person(name);
                            personMap.put(name, person);
                            hashNames.add(name);
                        }
                        personMap.get(name).addFollower();
                        actualUser.addFollowing(personMap.get(name));
                    }
                    
                }
                readFile.close();
            }   
                catch (FileNotFoundException e)
                {
                    System.out.println("The File was not found");
                }
            
                
        }
        for (Person person : personMap.values()) {
            System.out.println(person);
        }  
    }     
}