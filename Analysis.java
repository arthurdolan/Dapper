import java.io.File; // File class
import java.io.FileNotFoundException; //Handles errors
import java.util.Scanner; // Reads text files
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Analysis
{
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.out.println("Incorrect number of command line arguments (should be 1)");
        }
        else
        {

            List<String> arrayOfNames = new ArrayList<>(); //Stores names into a string 
            Set<String> hashNames = new HashSet<>(); //Stores each name to ensure not added twice
            String inputFileName = args[0]; // Takes first file from command line
            File inputFile = new File(inputFileName);
            try
            {
                Scanner readFile = new Scanner(inputFile); 
                while(readFile.hasNextLine())
                {
 
                    String line = readFile.nextLine();
                    String names[]= line.trim().split("\\s+");//seperates and stores each name in names[]
                    for(String name : names) //iterates through each name in names
                    {
                        if(hashNames.contains(name))
                        {
                            //do nothing 
                        }
                        else
                        {

                            Person person = new Person(name, 5);
                            arrayOfNames.add(name);
                            hashNames.add(name);
                            System.out.println("Added name: " + name);
                        }
                    }
                    
                }
                readFile.close();
            }   
                catch (FileNotFoundException e)
                {
                    System.out.println("The File was not found");
                }
            
            

        }
    }
}