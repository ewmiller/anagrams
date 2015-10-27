import java.util.*;
import java.io.*;

public class Anagrams {

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

    //Declares an ArrayList of lists
    ArrayList<List<String>> B = new ArrayList<List<String>>(A.size());
    for(String s : A){
      findClass(s, B);
    }
  }

  //subroutine for comparing a string to the existing anagram classes
  public static void findClass(String s, ArrayList<List<String>> B){

    for(int i = 0; i < B.size(); i++){
      if(B.get(i) == null){
        B.get(i).add(s);
        break;
      }
      else {
        List<String> anagramClass = B.get(i);
        boolean match = compare(s, anagramClass.get(0));
        if(match){
          B.get(i).add(s);
          break;
        }
      }
    }
  }

  //subroutine for comparing two strings to see if they are anagrams of each other
  public static boolean compare(String s, String t){
    //TODO: this
    return true;
  }
}
