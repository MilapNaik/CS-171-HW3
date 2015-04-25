
/**
 * Starter code for the Maze path finder problem. 
 */

import java.io.*;
import java.util.Scanner;
//import java.util.ArrayDeque;


/*
 * minimal class to represent position [i,j] in the maze
 */
class Position{
 public int i;     //row
 public int j;     //column
 public char val;  //1, 0, or 'X'
 Position (int a, int b, char v){ i = a; j=b; val = v; }

}


public class PathFinder1 {
 
 public static void main(String[] args) throws IOException {
                if(args.length<1){
                        System.err.println("***Usage: java PathFinder maze_file"
);
                        System.exit(-1);
                }

                // use stackSearch 
                char [][] maze;
                maze = readMaze(args[0]);
                printMaze(maze);
                Position [] path = stackSearch(maze);
                System.out.println("stackSearch Solution:");
                printPath(path);
                printMaze(maze);

                // use queueSearch
                char [][] maze2 = readMaze(args[0]);
                path = queueSearch(maze2);
                System.out.println("queueSearch Solution:");
                printPath(path);
                printMaze(maze2);
       }

 
 
 public static Position [] stackSearch(char [] [] maze){
  //todo: your path finding algorithm here using the stack to manage search list
  //your algorithm should modify maze to mark positions on the path, and also
  //return array of Position objects coressponding to path, or null if no path found
  
  return null;
 }
 
 public static Position [] queueSearch(char [] [] maze){
  //todo: your path finding algorithm here using the queue to manage search list
  //your algorithm should modify maze to mark positions on the path, and also
  //return array of Position objects coressponding to path, or null if no path found
  
  return null;
 }
 
        public static void printPath(Position [] path){
                //todo: print a given path
        }
 
 /**
  * Reads maze file in format:
  * N  -- size of maze
  * 0 1 0 1 0 1 -- space-separated 
  * @param filename
  * @return
  * @throws IOException
  */
 public static char [][] readMaze(String filename) throws IOException{
  char [][] maze;
  Scanner scanner;
  try{
   scanner = new Scanner(new FileInputStream(filename));
   scanner.close();
  }
  catch(IOException ex){
   System.err.println("*** Invalid filename: " + filename);
   return null;
  }
  
  int N = scanner.nextInt();
  scanner.nextLine();
  maze = new char[N][N];
  int i=0;
  while(i < N && scanner.hasNext()){
   String line =  scanner.nextLine();
   String [] tokens = line.split("\\s+");
   int j = 0;
   for (; j< tokens.length; j++){
    maze[i][j] = tokens[j].charAt(0);
   }
   if(j!=N){
    System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
    return null;
   }
   i++;
  }
  if(i!=N){
   System.err.println("*** Invalid file: has wrong number of rows: " + i);
   return null;
  }
  return maze;
 }
 
 public static void printMaze(char[][] maze){
  
  if(maze==null || maze[0] == null){
   System.err.println("*** Invalid maze array");
   return;
  }
  
  for(int i=0; i< maze.length; i++){
   for(int j = 0; j< maze[0].length; j++){
    System.out.print(maze[i][j] + " "); 
   }
   System.out.println();
  }
  
  System.out.println();
 }
 
}