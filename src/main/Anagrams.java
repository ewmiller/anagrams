import java.util.*;
import java.io.*;

public class Anagrams {

  //subroutine for comparing a string to the existing anagram classes
  public static void anagramCompare(String s, List<List<String>> B){

  }

  /* Main method; creates a new array for anagram classes and compares each
  word in the dictionary to the existing array of classes.
  Outputs anagram file when complete. */

  public static void main(String[] args){
    List<String> A = new ArrayList<String>();
    try {
      Scanner fileScanner = new Scanner(new File("./src/main/resources/dict1"));
      System.out.println("Reading file: dict1");
      while(fileScanner.hasNextLine()){
        A.add(fileScanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.out);
    }

    //Declares a list of lists
    List<List<String>> B = new ArrayList<List<String>>();
    for(String s : A){
      anagramCompare(s, B);
    }
  }
}
