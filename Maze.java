import java.util.*;
import java.io.*;
public class Maze{
  private char[][]maze;
  private boolean animate;//false by default

public Maze(String filename) throws FileNotFoundException{
  animate = false;
  try{
  File text = new File("Maze.txt");
  Scanner maze = new Scanner(text);
  int row = 0;
  int col = 0;
  while (maze.hasNextLine()){
    String line = maze.nextLine();
    System.out.println(line);
    col = 0;
    row += 1;
    col = line.length();
    //System.out.println("row: " + row);
    //System.out.println("col: " + col);
  }
  //maze = new char[row][col];
}
catch(Exception e){}
}
private void wait(int millis){
     try {
         Thread.sleep(millis);
     }
     catch (InterruptedException e) {
     }
 }
public void setAnimate(boolean b){
    animate = b;
}
public void clearTerminal(){ //erase terminal, go to top left of screen.
    System.out.println("\033[2J\033[1;1H");
}
//public int solve(){
        //return solve(???,???)
//}
private int solve(int row, int col){ //you can add more parameters since this is private
    if(animate){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }
    return -1; //so it compiles
}


}
