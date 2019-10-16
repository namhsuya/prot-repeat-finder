
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
    * This class consists exclusively a single static method that operate on string.
	* It contains an algorithm that operates on a string input, to print repeats of 
	* homologous substrings present inside the input string.
    *
    * <p>The method of this class throws an <tt>IOException</tt>
    * if the file or the file path provided to them are null.
    *
    *
    * @author  Rajdeep Ghosh
    * @author  Ayushman Kumar Banerjee
 */

public class ViralRepeatFinder 
{
    public static void main(String[] args)throws IOException
    {
		String filePath="";
		 
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter path of the FASTA containing file.");
		filePath = br.readLine();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(filePath));
            allLines.stream().map((line) -> {
				System.out.println("Repeats found for: " + line.substring(line.indexOf(" "), line.indexOf("OS")) + "==>");
				return line;
            }).forEachOrdered((line) -> {
				getViralRepeats(line);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private static void getViralRepeats(String str){
		int count=0,info=0;
		try
        {
            for(char a='A';a<='Z';a++)
            {
                if(a=='B'||a=='J'||a=='O'||a=='U'||a=='X'||a=='Z')
                    continue;
                for(int b=0;b<str.length()-2;b++)
                {
                    if(str.charAt(b)==a)
                    {
                        for(int c=b;c<str.length();c++)
                        {
                            if(str.charAt(c)==a)
                                count++;
                            else
                                break;
                            
                        }
                    }
                    if(count>=3)
                    {
                        System.out.print("A protein repeat:");
                        for(int i=0;i<count;i++)
                            System.out.print("		"+a);
                        System.out.println(" was found from position "+(b+1)+" to "+(b+count));
                        b=b+count;
                        info++;
                    }
                    count=0;
                }   
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Exception caught!");
        }
        if(info==0)
            System.out.println("No protein repeats found in the given sequence.");
	}
}
