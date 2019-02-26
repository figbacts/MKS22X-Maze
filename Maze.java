import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Maze{
public static void main(String args[]){
  try{
  File text = new File("Maze.txt");
  Scanner maze = new Scanner(text);
  int[][] mazeroom;
  int row = 0;
  int col = 0;
  while (maze.hasNextLine()){
    String line = maze.next();
    row += 1;
    col = line.length();
    System.out.println("row: " + row);
    System.out.println("col: " + col);
  }
}
catch(Exception e){

}
}
}
