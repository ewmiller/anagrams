import java.util.*;
import java.io.*;

public class Anagrams {

  /* Main method; creates a new array for anagram classes and compares each
  word in the dictionary to the existing array of classes.
  Outputs anagram file when complete. */

  public static void main(String[] args){
    startTime = System.nanoTime();
    List<String> A = new ArrayList<String>();
    try {
      Scanner fileScanner = new Scanner(new File("./src/main/resources/dict1"));
      System.out.println("Reading file: dict0");
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
    endTime = System.nanoTime();
    System.out.println("Elapsed time: " + (endTime - startTime));
  }

  //subroutine for comparing a string to the existing anagram classes
  private static void findClass(String s, ArrayList<ArrayList<String>> B){
    //given a string and a list of lists to look at
    //for each list in B
    //if string s matches first string in the list, add it
    //if no match found (use mutable bool?), create new list and add it to B
    boolean matched = false;
    for(int i = 0; i < B.size(); i++){
      System.out.println("Comparing string " + s + " to B[" + i +"]");
      if(compare(s, B.get(i).get(0))){
        B.get(i).add(s);
        System.out.println("Found a match for string: " + s);
        matched = true;
      }
      else {
        // System.out.println("String " + s + " did not match B[" + i + "].");
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
  //I've traced the problem to here. Comparison always fails.
  private static boolean compare(String s, String t){
    boolean res = false;
    char[] x = s.toCharArray();
    char[] y = t.toCharArray();
    if(x.length != y.length) {
      // System.out.println(x + " and " + y + " are of different length. Not anagrams.");
      return false;
    }
    else {
      for(int j = 0; j < x.length; j++){
        if(contains(x[j], y)) {
          // System.out.println(s + " and " + t + " both contain character " + x[j]);
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
