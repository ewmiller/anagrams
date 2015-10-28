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
        String str = fileScanner.nextLine();
        A.add(str); //this works
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.out);
    }

    //declares a list of lists of strings
    ArrayList<ArrayList<String>> B = new ArrayList<ArrayList<String>>();
    for(String s : A){ //debug: iterating through A works. Problem is... deeper.
      findClass(s, B);
    }

    //Write results to output file
    try {
      File file = new File("./anagram1"); // this works

      FileWriter fWriter = new FileWriter(file);
      PrintWriter pWriter = new PrintWriter(fWriter);

      for(int i = 0; i < B.size(); i++){ //for every element in B
        System.out.println("processing line " + i + " of B"); //debug
        for(String s : B.get(i)){ // for every string in the element of B
          pWriter.print(s + " ");
          System.out.println("writing content: " + s);
        }
        pWriter.print("\n");
      }
      pWriter.close();
    } catch(IOException e) {
      e.printStackTrace(System.out);
    }
  }

  //subroutine for comparing a string to the existing anagram classes
  private static void findClass(String s, ArrayList<ArrayList<String>> B){
    //given a string and a list of lists to look at
    //for each list in B
    //if string s matches first string in the list, add it
    //if no match found (use mutable bool?), create new list and add it to B
    boolean matched = false;
    for(ArrayList<String> anagramClass : B){
      if(compare(s, anagramClass.get(0))){
        anagramClass.add(s);
        matched = true;
      }
    }
    if(!matched){
      ArrayList<String> newClass = new ArrayList<String>();
      newClass.add(s);
      B.add(newClass);
      System.out.println("made a new class for string: " + s);
    }
  }

  //subroutine for comparing two strings to see if they are anagrams of each other
  private static boolean compare(String s, String t){
    boolean res = false;
    char[] x = s.toCharArray();
    char[] y = t.toCharArray();
    if(x.length != y.length) {
      return false;
    }
    else {
      for(int j = x.length; j < x.length; j++){
        if(contains(x[j], y)) {
          y[indexOf(x[j], y)] = ' ';
          x[j] = ' ';
        }
        else {
          return false;
        }
      } //end for loop
    } //end else
    //if x and y were made equal by iterative process above, then
    //strings s and t are anagrams
    if(x.equals(y))
      return true;
    else
      return false;
  }

  //check if a char array contains a character
  private static boolean contains(char c, char[] array){
    boolean res = false;
    for(char ch : array){
      if(c == ch){
        res = true;
        break;
      }
    }
    return res;
  }

  //return index of character in an array
  private static int indexOf(char c, char[] array){
    int i = -1;
    for(int j = 0; j < array.length; j++){
      if(array[j] == c){
        i = j;
        break;
      }
    }
    return i;
  }
}
