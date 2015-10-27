import java.util.*;
import java.io.*;

public class Anagrams {
  public static void main(String[] args){
    List<String> A = new ArrayList<String>();
    try {
      Scanner fileScanner = new Scanner(new File("./src/main/resources/dict1"));
      while(fileScanner.hasNextLine()){
        A.add(fileScanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(System.out);
    }
    System.out.println("Success. Line 4 is this: " + A.get(4));
  }
}
