/**
 * Module 5 Assignment
 *
 * Program that translates Morse code into English and English into Morse code
 * Prompts user for type of translation and outputs translated result
 * Program utilizes two 1-dimensional arrays
 *
 * @author: Daniel Choi
 * Date created: 07 October 2018
 **/
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        char [] English = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

        String [] MCode = { ".-" , "-..." , "-.-." , "-.." , "." , "..-." , "--." , "...." , ".." , ".---" , "-.-" , ".-.." , "--" , "-." , "---" , ".--." , "--.-" ,  ".-." , "..." , "-" , "..-" , "...-" , ".--" , "-..-" , "-.--" , "--.." , "|" };

        System.out.println ("Enter the letter A - if you want to translate Morse Code to English");
        System.out.println ("Enter the letter B - if you want to translate English to Morse Code");

        String ttype = input.nextLine();                                //ttype indicates translation type
            if (ttype.equalsIgnoreCase("A"))
            {
                System.out.println("Please enter desired sentence in Morse Code to be translated to English");
                System.out.println("Individual symbols - letters and/or digits - should be spaced");
                System.out.println("Delimit multiple words with a | symbol");

                String phrase = input.nextLine();
                String morsemulti = "";
                String morsechars = "";
                String morsewords = "";
                String[] morsemultiple=phrase.split("   ");

                for(int m=0; m < morsemultiple.length; m++){
                    morsemulti = morsemultiple[m];
                    String[] morsecharacters = morsemulti.split(" ");
                    for (int n=0; n < morsecharacters.length; n++){
                        morsechars += morsecharacters[n];
                        for (int index=0; index < MCode.length; index++)
                        {
                            if (morsechars.equals(MCode[index]))
                                morsewords += English[index];
                        }
                        morsechars = "";
                    }
                    morsewords += " ";
                    morsemulti = "";
                }
                System.out.println(morsewords);
            }
            else if (ttype.equalsIgnoreCase("B"))     //ttype indicates translation type
            {
                System.out.println("Please enter desired sentence in English to be translated to Morse Code");
                System.out.println("Individual words should be spaced with a blank space.");

                String phrase = input.nextLine();
                for (int count = 0; count < phrase.length(); count++)
                {
                    for (int index = 0; index < English.length; index++)
                    {
                        if (English[index] == phrase.charAt(count))
                            System.out.print(MCode[index] + " ");
                    }
                }
            }

            else                                                        //If entry does not quality for either
            {
                System.out.println("Invalid Entry - Please try again");
            }
    }
}
