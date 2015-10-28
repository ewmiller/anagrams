import java.util.*;
import java.io.*;

public class Anagrams {

  /* Main method:
  Reads the dictionary in as a list A of strings. Creates a "list of lists" B
  that will hold the anagram classes. For each word in the dictionary, it compares
  the word to the anagram classes that have been found so far. If it matches a
  class, adds it to that class. If no match found, creates a new class.*/

  public static void main(String[] args){
    long startTime = System.nanoTime(); //to keep track of runtime
    List<String> A = new ArrayList<String>();
    try {
      String filePath = String.format("./src/main/resources/dict%s", args[0]);
      Scanner fileScanner = new Scanner(new File(filePath));
      System.out.println(String.format("Reading file: dict%s", args[0]));
      while(fileScanner.hasNextLine()){
        String str = fileScanner.nextLine();
        A.add(str); //this works
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.out);
    }

    //declares a list of lists of strings
    ArrayList<ArrayList<String>> B = new ArrayList<ArrayList<String>>();
    for(String s : A) {
      findClass(s, B);
    }

    //Write results to output file
    try {
      String writePath = String.format("./anagram%s", args[0]);
      File file = new File(writePath);
      FileWriter fWriter = new FileWriter(file);
      PrintWriter pWriter = new PrintWriter(fWriter);

      for(int i = 0; i < B.size(); i++){ //for every element in B
        for(String s : B.get(i)){ // for every string in the element of B
          pWriter.print(s + " ");
        }
        pWriter.print("\n");
      }
      pWriter.close();
    } catch(IOException e) {
      e.printStackTrace(System.out);
    }
    long endTime = System.nanoTime();
    System.out.println("Elapsed time: " + (endTime - startTime)/1000000000 + " seconds");
    System.out.println("Total anagram classes: " + B.size());
  }

  //subroutine for comparing a string to the existing anagram classes
  private static void findClass(String s, ArrayList<ArrayList<String>> B){
    //given a string and a list of lists to look at
    //for each list in B
    //if string s matches first string in the list, add it
    //if no match found, create new list and add it to B
    boolean matched = false;
    for(int i = 0; i < B.size(); i++){
      if(compare(s, B.get(i).get(0))){
        B.get(i).add(s);
        matched = true;
      }
      else {
      }
    }
    if(!matched){
      ArrayList<String> newClass = new ArrayList<String>();
      newClass.add(s);
      B.add(newClass);
    }
  }

  //subroutine for comparing two strings to see if they are anagrams of each other
  //I've traced the problem to here. Comparison always fails.
  private static boolean compare(String s, String t){
    boolean res = false;
    char[] x = s.toCharArray();
    char[] y = t.toCharArray();
    if(x.length != y.length) {
      return false;
    }
    else {
      for(int j = 0; j < x.length; j++){
        if(contains(x[j], y)) {
          y[indexOf(x[j], y)] = ' ';
          x[j] = ' ';
          res = true;
        }
        else {
          return false;
        }
      } //end for loop
    } //end else
    return res;
  }

  //check if a char array contains a character
  private static boolean contains(char c, char[] array){
    for(int i = 0; i < array.length; i++){
      if(c == array[i]){
        return true;
      }
    }
    return false;
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
