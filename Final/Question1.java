import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Question1 {

    public static void main(String[] args) throws FileNotFoundException {
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;
        //if file was not inputted
        if (args.length != 1){
            System.out.println("Invalid");
            System.exit(1);
        }
        //Get file
        Scanner input = new Scanner(new File(args[0]));
        System.out.println("File " + args[0] + " has ");
        //Count in while loop
        while (input.hasNext()){
            lineCount++;
            String nextLine = input.nextLine();
            //Get each word as string with correct delimeter
            StringTokenizer token = new StringTokenizer(nextLine, "\r \n\t,;:.");
            //Count chars and words
            while (token.hasMoreTokens()){
                wordCount++;
                args[0] = token.nextToken();
                charCount += args[0].length();
            }
        }
        //Output
        System.out.println(charCount + " characters ");
        System.out.println(wordCount + " words ");
        System.out.println(lineCount + " lines ");

        input.close();
    }
}