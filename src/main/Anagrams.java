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

    //Write results to output file
    //TODO: this
    try {
      File file = new File("./anagram1");
      FileOutputStream out = new FileOutputStream(file);
      if(file.exists() == false){
        file.createNewFile();
      }
      for(List<String> list : B){
        for(String s : list){
          byte[] content = s.getBytes();
          out.write(content);
          out.flush();
        }
      }
      out.close();
    } catch(IOException e) {
      e.printStackTrace(System.out);
    }
  }

  //subroutine for comparing a string to the existing anagram classes
  private static void findClass(String s, ArrayList<List<String>> B){

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
