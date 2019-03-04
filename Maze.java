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
  int ans = 0;
board[startRow][startCol] = '@';
if (solve(startRow, startCol)){
  for(int r = 0; r < board.length; r ++){
    for (int c = 0; c < board[r].length; c++){
      if (board[r][c] == '@'){
        ans += 1;
      }
    }
  }
  return ans;
}
return -1;
}

private boolean solve(int row, int col){ //you can add more parameters since this is private
    if(animate){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }
    if (board[row][col] == 'E'){
      return true;
    }
      if (move(row,col,"up")){
         if (solve(row -1, col)){
           return true;
         }
         remove(row -1,col);
      }
      if (move(row,col,"down")){
        if (solve(row +1, col)){
          return true;
        }
        remove(row +1,col);
        }
      if (move(row,col,"left")){
        if (solve(row, col -1)){
          return true;
        }
        remove(row,col -1);
        }
      if (move(row,col,"right")){
        if (solve(row, col+1)){
          return true;
        }
        remove(row,col + 1);
    }
    return false; //so it compiles
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
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("down") && board[row +1][col] == 'E'){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("left") && board[row][col -1] == 'E'){
    board[row][col] = '@';
    return true;
  }
  if(direction.equals("right") && board[row][col + 1] == 'E'){
    board[row][col] = '@';
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
  maze.setAnimate(false);
  System.out.println(maze.solve());
  System.out.println(maze);

}


}
