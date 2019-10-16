package viralproteinmotiffinder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
* This class consists exclusively a single static method that operate on string.
* It contains an algorithm that operates on a string input, to print repeats of 
* homologous substrings present inside the input string.
*
* <p>The method of this class throws an <tt>IOException</tt>
* if the file or the file path provided to them are null.
*
*
* @author  Ayushman Kumar Banerjee
*/

public class ViralRepeatFinder {
    
    public static void main(String[] args) {
        // Getting path for input file, containing sequences of proteins in FASTA format
        getViralRepeats(readData());
    }
    
    private static ArrayList<String> readData(){
        String filePath;
        ArrayList<String> sequences = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please enter path of input file: ");
       
        try {
            filePath = bufferedReader.readLine();
            // Reading lines from file
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            String seqFile = "";
            seqFile = allLines.stream().map((line) -> line).reduce(seqFile, String::concat);
            // Splitting the reads at each greater than symbol
            sequences = new ArrayList(Arrays.asList(seqFile.split(">")));
            sequences.remove(0);  
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return sequences;
    }
    
    private static void getViralRepeats(ArrayList<String> sequences){
        Pattern p = Pattern.compile("(\\p{Alpha})\\1{1,}");
        sequences.stream().map((sequence) -> {
            System.out.println("For "+sequence.substring(0, sequence.indexOf(" ")));
            return sequence.substring(sequence.lastIndexOf(" "));
        }).map((sequence) -> p.matcher(sequence)).forEachOrdered((m) -> {
            while (m.find()){
                System.out.println("Found repeats for " + m.group(0) + " from " + m.start() + " to " + m.end());
            }
        });
    }    
}
