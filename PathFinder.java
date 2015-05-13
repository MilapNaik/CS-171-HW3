/*
      THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
      CODE WRITTEN BY OTHER STUDENTS. Milap Naik
      */

// file is broken make sure to fix. Position should refer to --fixed

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayDeque;


class Position {
 public int i; // row
 public int j; // column
 public char val; // 1, 0, or 'X'
 public Position previous;

 Position(int a, int b, char c) {
  i = a;
  j = b;
  val = c;
 }
}

public class PathFinder {

 public static int counter = 0;

 public static void main(String[] args) throws IOException {
  
  char[][] maze;
  String fileName = args[0];
  

  maze = readMaze(fileName);
  Position[] solutionCoordinates = stackSearch(maze);
  //Create solution 
  char[][] mazeSolution;
  
  System.out.println("STACKSEARCH MAZE SOLUTION\n");
  if(solutionCoordinates != null){
   mazeSolution = readMaze(fileName);
   System.out.println("Solution Coordinates:");
   int lineCount = 1;
   for (int i = counter-1; i >= 0; i--) {
    Position solutionPosition = solutionCoordinates[i];
    System.out.print("[" +solutionPosition.i + "][" + solutionPosition.j + "], ");
    /*if (lineCount % 7 == 0){
     System.out.println("");
    }*/
    lineCount++;
    mazeSolution[solutionPosition.i][solutionPosition.j] = 'X';
   }
   System.out.println("\n");
   printMaze(mazeSolution);
  }
  else System.out.println("Error: Invalid Maze");
 
  
  System.out.println("\n\n\n");
  
  
  System.out.println("QUEUESEARCH MAZE SOLUTION\n");
  maze = readMaze(fileName);
  solutionCoordinates = queueSearch(maze);

  if(solutionCoordinates != null){
   mazeSolution = readMaze(fileName);
   System.out.println("Solution Coordinates:");
   int lineCount = 1;
   for (int i = counter-1; i >= 0; i--) {
    Position solutionPosition = solutionCoordinates[i];
    System.out.print("[" +solutionPosition.i + "][" + solutionPosition.j + "], ");
    if (lineCount % 7 == 0){
     System.out.println("");
    }
    lineCount++;
    mazeSolution[solutionPosition.i][solutionPosition.j] = 'X';
   }
   System.out.println("\n");
   printMaze(mazeSolution);
  }
  else System.out.println("Error: Invalid Maze");

 
 }

 
 public static Position[] stackSearch(char[][] maze) {
  
  int mazeLength = maze.length;
  //System.out.println(mazeLength);
  int row = 0;
  int column = 0;
  char value = '0';
  //Initialize
  int numElements = 0;
  
  //Stack to record Position objects that are along the path
  ArrayDeque<Position> positionStack = new ArrayDeque<Position>(2 * mazeLength * mazeLength);
  Position currentPosition = new Position(row, column, value);
  final Position ENDPOSITION = new Position(mazeLength - 1, mazeLength - 1, 'X');
  Position[] solutionPath = new Position[2 * mazeLength * mazeLength];

  positionStack.push(currentPosition);

  maze[0][0] = 'X';
  numElements++;
  
  boolean continueSearch = true;
    
  while (continueSearch) {
   //Update the current position
   currentPosition = positionStack.peek();
   row = currentPosition.i;
   column = currentPosition.j;
   value = currentPosition.val;
   

   boolean up = false;
   boolean down = false;
   boolean left = false;
   boolean right = false;
   
   //Check if you can move in a certain direction
   if (row - 1 >= 0 && maze[row - 1][column] == '0')
    up = true;
   if (row + 1 < mazeLength && maze[row + 1][column] == '0')
    down = true;
   if (column - 1 >= 0 && maze[row][column - 1] == '0')
    left = true;
   if (column + 1 < mazeLength && maze[row][column + 1] == '0')
    right = true;
   

   if (!up && !down && !left && !right) {
    maze[row][column] = '1';
    positionStack.pop();
    numElements--;
   }

   else {
    if (up) {
     Position moveUp = new Position(row - 1, column, 'X');
     maze[row - 1][column] = 'X';
     positionStack.push(moveUp);
     numElements++;
    }
    else if (down) {
     Position moveDown = new Position(row + 1, column, 'X');
     maze[row + 1][column] = 'X';
     positionStack.push(moveDown);
     numElements++;
    }
    else if (left) {
     Position moveLeft = new Position(row, column - 1, 'X');
     maze[row][column - 1] = 'X';
     positionStack.push(moveLeft);
     numElements++;
    }
    else if (right) {
     Position moveRight = new Position(row, column + 1, 'X');
     maze[row][column + 1] = 'X';
     positionStack.push(moveRight);
     numElements++;
    }
   }

   if(positionStack.isEmpty()){
    solutionPath = null;
    break;
   }
   

   Position pos = positionStack.peek();
   if (pos.i == ENDPOSITION.i && pos.j == ENDPOSITION.j && pos.val == ENDPOSITION.val)
    continueSearch = false;
   else if (positionStack.isEmpty())
    continueSearch = false;
  }//Closing the while loop
  

  if(positionStack.isEmpty()){
   positionStack = null;
  }

  else if (positionStack.peek().i == ENDPOSITION.i && positionStack.peek().j == ENDPOSITION.j) {
   counter = numElements;
   for (int w = 0; w < numElements; w++) {
    solutionPath[w] = positionStack.pop();
   }
  }
  return solutionPath;
 }
 

 public static Position[] queueSearch(char[][] maze) {
  
  //Record length 
  int mazeLength = maze.length;
  int row = 0;
  int column = 0;
  char value = '0';
  int numElements = 0;
  
  ArrayDeque<Position> positionStack = new ArrayDeque<Position>(2 * mazeLength * mazeLength);
  Position currentPosition = new Position(row, column, value);
  final Position ENDPOSITION = new Position(mazeLength - 1, mazeLength - 1, 'X');
  Position[] solutionPath = new Position[2 * mazeLength * mazeLength];

  positionStack.push(currentPosition);
  maze[0][0] = 'X';
  numElements++;
  
  //found a solution
  boolean continueSearch = true;
    
  while (continueSearch) {
   currentPosition = positionStack.peek();
   row = currentPosition.i;
   column = currentPosition.j;
   value = currentPosition.val;
   
   boolean up = false;
   boolean down = false;
   boolean left = false;
   boolean right = false;
   
   //Check 
   if (row - 1 >= 0 && maze[row - 1][column] == '0')
    up = true;
   if (row + 1 < mazeLength && maze[row + 1][column] == '0')
    down = true;
   if (column - 1 >= 0 && maze[row][column - 1] == '0')
    left = true;
   if (column + 1 < mazeLength && maze[row][column + 1] == '0')
    right = true;
   
   if (!up && !down && !left && !right) {
    maze[row][column] = '1';
    deleteLastElement(positionStack, maze);
    numElements--;
   }

   else {
    if (up) {
     Position moveUp = new Position(row - 1, column, 'X');
     maze[row - 1][column] = 'X';
     positionStack.push(moveUp);
     numElements++;
    }
    else if (down) {
     Position moveDown = new Position(row + 1, column, 'X');
     maze[row + 1][column] = 'X';
     positionStack.push(moveDown);
     numElements++;
    }
    else if (left) {
     Position moveLeft = new Position(row, column - 1, 'X');
     maze[row][column - 1] = 'X';
     positionStack.push(moveLeft);
     numElements++;
    }
    else if (right) {
     Position moveRight = new Position(row, column + 1, 'X');
     maze[row][column + 1] = 'X';
     positionStack.push(moveRight);
     numElements++;
    }
   }
   
   if(positionStack.isEmpty()){
    solutionPath = null;
    break;
   }
   

   Position pos = positionStack.peek();
   if (pos.i == ENDPOSITION.i && pos.j == ENDPOSITION.j && pos.val == ENDPOSITION.val)
    continueSearch = false;
   else if (positionStack.isEmpty())
    continueSearch = false;
  }//Closing the while loop
  
  //If positionStack is empty
  if(positionStack.isEmpty()){
   positionStack = null;
  }

  else if (positionStack.peek().i == ENDPOSITION.i && positionStack.peek().j == ENDPOSITION.j) {
   counter = numElements;
   for (int w = 0; w < numElements; w++) {
    solutionPath[w] = deleteLastElement(positionStack, maze);
   }
  }
  return solutionPath;
 }
 
 public static Position deleteLastElement(ArrayDeque<Position> queue1, char[][] maze2){
  

  ArrayDeque<Position> reverseQueue = new ArrayDeque<Position>(maze2.length^2);
  Position temp = queue1.peekFirst();
  Position popped;
  
  //reverse 
  while (queue1.peekFirst() != null){
   temp = queue1.removeFirst();
   reverseQueue.addLast(temp);
  }
  
  popped = reverseQueue.poll();

  while (reverseQueue.peekFirst() != null){
   temp = reverseQueue.removeFirst();
   queue1.addLast(temp);
  } 
  
  return popped; 
 }
 

 /**
  * Reads maze file in format: N -- size of maze 0 1 0 1 0 1 --
  * space-separated
  * 
  * @param filename
  * @return
  * @throws IOException
  */
 public static char[][] readMaze(String filename) throws IOException {
  char[][] maze;
  Scanner scanner;
  try {
   scanner = new Scanner(new FileInputStream(filename));
  } catch (IOException ex) {
   System.err.println("*** Invalid filename: " + filename);
   return null;
  }
  
  int N = 0;
  try{
  
  N = scanner.nextInt();
  }catch (InputMismatchException err) {
	    System.out.print(err.getMessage()); //try to find out specific reason.
  }
  scanner.nextLine();
  maze = new char[N][N];
  int i = 0;
  while (i < N && scanner.hasNext()) {
   String line = scanner.nextLine();
   String[] tokens = line.split("\\s+");
   int j = 0;
   for (; j < tokens.length; j++) {
    maze[i][j] = tokens[j].charAt(0);
   }
   if (j != N) {
    System.err.println("*** Invalid line: " + i
      + " has wrong # columns: " + j);
    scanner.close();
    return null;
   }
   i++;
  }
  if (i != N) {
   System.err.println("*** Invalid file: has wrong number of rows: "
     + i);
   scanner.close();
   return null;
  }
  scanner.close();
  return maze;
 }

 public static void printMaze(char[][] maze) {

  if (maze == null || maze[0] == null) {
   System.err.println("*** Invalid maze array");
   return;
  }

  for (int i = 0; i < maze.length; i++) {
   for (int j = 0; j < maze[0].length; j++) {
    System.out.print(maze[i][j] + " ");
   }
   System.out.println();
  }

  System.out.println();
 }

}