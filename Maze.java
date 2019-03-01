import java.util.*;
import java.io.*;
public class Maze{
  private char[][]board;
  private boolean animate;//false by default
  private int startRow;
  private int startCol;
  private int endRow;
  private int endCol;

public Maze(String filename){
  animate = false;
  try{
  File text = new File(filename);
  Scanner maze = new Scanner(text);
  int row = 0;
  int col = 0;
  while (maze.hasNextLine()){
    String line = maze.nextLine();
    col = 0;
    row += 1;
    col = line.length();
    //System.out.println("row: " + row);
    //System.out.println("col: " + col);
  }
  board = new char[row][col];
  maze = new Scanner(text);
  row = 0;
  while(maze.hasNextLine()){
    String line = maze.nextLine();
    for (int c = 0; c <line.length(); c ++){
      if (line.charAt(c) == 'S'){
        startRow = row;
        startCol = c;
      }
      if (line.charAt(c) == 'E'){
        endRow = row;
        endCol = c;
      }
      board[row][c] = line.charAt(c);
    }
    row +=1;
  }
}
catch(Exception e){}
}
public String toString(){
  String ans = "";
    for(int r = 0; r < board.length; r ++){
      for (int c = 0; c < board[r].length; c++){
        ans += board[r][c];
      }
      ans += "\n";
    }
    return ans;
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
public int solve(){
board[startRow][startCol] = '@';
return solve(startRow, startCol, 1);
}

private int solve(int row, int col, int path){ //you can add more parameters since this is private
    if(animate){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }
    for (int d = 0; d < 4; d ++){
      if (board[row-1][col] == 'E'){
        return path;
      }
      if (d == 0 && move(row,col,"up")){
         solve(row -1, col, path + 1);
         remove(row -1,col);
      }
      if (d == 1 && move(row,col,"down")){
        solve(row +1, col, path + 1);
        remove(row +1,col);
        }
      if (d == 2 && move(row,col,"left")){
        solve(row, col -1, path + 1);
        remove(row,col -1);
        }
      if (d == 3 && move(row,col,"right")){
        solve(row, col+1, path + 1);
        remove(row,col + 1);
      }
    }
    return -1; //so it compiles
}
private boolean move(int row, int col, String direction){
  if(direction.equals("up") && board[row -1][col] == ' '){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("down") && board[row +1][col] == ' '){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("left") && board[row][col -1] == ' '){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("right") && board[row][col + 1] == ' '){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("up") && board[row -1][col] == 'E'){
    return true;
  }
  if(direction.equals("down") && board[row +1][col] == 'E'){
    return true;
  }
  if(direction.equals("left") && board[row][col -1] == 'E'){
    return true;
  }
  if(direction.equals("right") && board[row][col + 1] == 'E'){
    return true;
  }
  return false;
}

private boolean remove(int row, int col){
  if (board[row][col] != '#' && board[row][col] != 'E'){
  board[row][col] = '.';
  return true;
}
return false;
}
public static void main(String[] args) {
  Maze maze = new Maze("Maze.txt");
  maze.clearTerminal();
  maze.setAnimate(true);
  System.out.println(maze.solve());
  System.out.println(maze);

}


}
