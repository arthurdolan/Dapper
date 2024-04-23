import java.io.File; // File class
import java.io.FileNotFoundException; //Handles errors
import java.util.Scanner; // Reads text files

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
            String inputFileName = args[0]; // Takes first file from command line
            File inputFile = new File(inputFileName);
            try
            {
                Scanner readFile = new Scanner(inputFile);
                while(readFile.hasNextLine())
                {
                    String data = readFile.nextLine();
                    System.out.println(data);
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