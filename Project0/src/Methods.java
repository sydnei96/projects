/**
 * Created by Sydnei on 1/17/2017.
 * Soduku Puzzles from the website 261,262,263,264 & 265, (&267 (medium)) maybe
 * Stores all the methods
 */

import java.io.*;

public class Methods {


 //prints out sudoku puzzle with a grid to make checking the puzzle easier
 public static void printSudoku(int puzzle[][]) {
     
     System.out.println(" -----------");
     
     for (int i = 0; i < 9; i++) {
       if (i % 3 == 0 && i != 0) {
          System.out.println("|---|---|---|");
               }
         for (int j = 0; j < 9; j++) {
            if (j % 3 == 0) {
               System.out.print("|");
               }

            System.out.print(puzzle[i][j]);
            
            }
            System.out.println("|");
            }
         System.out.println(" -----------");     
      }
   
   //checks how correct the program's solved sudoku puzzle is when compared to the actual answer   
   public static void checkAnswer(int puzzleCopy[][], int puzzle[][], int puzzleAnswer[][]) {
   
      int alreadyFilled = 0;
      int totalCorrect = 0;
      int solvedCorrect = 0;
      
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            if (puzzleCopy[i][j] != 0) {
               alreadyFilled++;
               }
            if (puzzle[i][j] == puzzleAnswer[i][j]) {
               totalCorrect++;
               }
            }
         }
         
         solvedCorrect = totalCorrect - alreadyFilled;
         
         System.out.println(alreadyFilled + " spaces in this sudoku puzzle were already filled."); 
         System.out.println("After verifying with the correct answer, this program correctly solved " + solvedCorrect + " spaces, "); 
         System.out.println("for a total of " + totalCorrect + " out of 81 total spaces correct!");
         
         }
     
   //checks if the sudoku puzzle has been solved yet      
   public static boolean checkCompletion (int puzzle[][]) {
      
      boolean completed = false;
      int count = 0;
      
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
            if (puzzle[i][j] !=0) {
               count++;
               }
            }
         }
       if (count == 81) {
           completed = true;
         }
      
      return completed;  
   }         
                     
   
   //rule one is that no two numbers can be in the same row 
 //returns an array of possible values in a designated row
   public static int[] ruleOne(int puzzle[][], int row) {
      
      int doubleCheck[] = {1,2,3,4,5,6,7,8,9};
      int count = 9;
      int holdRowValue = 0;
      
      for (int i = 0; i < 9; i++){
         for (int k = 0; k < 9; k++) {
         
              if(doubleCheck[i] == puzzle[row][k]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
               }
          }
      if (count == 1) {
         for (int j = 0; j < 9; j++) {
            if(doubleCheck[j] != -1) {
               holdRowValue = doubleCheck[j];
               }
             }
         } 
         
      return doubleCheck;
 }  
 
   //rule two is that no two numbers can be in the same column
 //returns an array of possible values in a designated column
   public static int[] ruleTwo(int puzzle[][], int column) {
   
      int doubleCheck[] = {1,2,3,4,5,6,7,8,9};
      int count = 9;
      int holdColumnValue = 0;
      
      for (int i = 0; i < 9; i++){
         for (int k = 0; k < 9; k++) {
               if(doubleCheck[i] == puzzle[k][column]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  }
      if (count == 1) {
         for (int j = 0; j < 9; j++) {
            if(doubleCheck[j] != -1) {
               holdColumnValue = doubleCheck[j];
               }
             }
         }        
      return doubleCheck;
 }     
 
   //rule three is that no two numbers can be in the same 3x3 grid
   //returns an array of possible values in a designated square
   public static int[] ruleThree(int puzzle[][], int square) {
   
      int doubleCheck[] = {1,2,3,4,5,6,7,8,9};
      int count = 9;
      int holdRuleThreeValue = 0;

      for (int i = 0; i < 9; i++){
         for (int j = 0; j < 3; j++) {
               if(square == 0) {
                  if(doubleCheck[i] == puzzle[0][j] || doubleCheck[i] == puzzle[1][j] || doubleCheck[i] == puzzle[2][j]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 1) {
                  if(doubleCheck[i] == puzzle[0][j+3] || doubleCheck[i] == puzzle[1][j+3] || doubleCheck[i] == puzzle[2][j+3]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  } 

               if(square == 2) {
                  if(doubleCheck[i] == puzzle[0][j+6] || doubleCheck[i] == puzzle[1][j+6] || doubleCheck[i] == puzzle[2][j+6]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 3) {
                  if(doubleCheck[i] == puzzle[3][j] || doubleCheck[i] == puzzle[4][j] || doubleCheck[i] == puzzle[5][j]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  } 
                  
               if(square == 4) {
                  if(doubleCheck[i] == puzzle[3][j+3] || doubleCheck[i] == puzzle[4][j+3] || doubleCheck[i] == puzzle[5][j+3]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 5) {
                  if(doubleCheck[i] == puzzle[3][j+6] || doubleCheck[i] == puzzle[4][j+6] || doubleCheck[i] == puzzle[5][j+6]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 6) {
                  if(doubleCheck[i] == puzzle[6][j] || doubleCheck[i] == puzzle[7][j] || doubleCheck[i] == puzzle[8][j]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 7) {
                  if(doubleCheck[i] == puzzle[6][j+3] || doubleCheck[i] == puzzle[7][j+3] || doubleCheck[i] == puzzle[8][j+3]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  }
                  
               if(square == 8) {
                  if(doubleCheck[i] == puzzle[6][j+6] || doubleCheck[i] == puzzle[7][j+6] || doubleCheck[i] == puzzle[8][j+6]) {
                  count--;
                  doubleCheck[i] = -1;
                  }
                  } 
                                                                                                                                                           
                  }
                  }
                  
      if (count == 1) {
         for (int k = 0; k < 9; k++) {
            if(doubleCheck[k] != -1) {
               holdRuleThreeValue = doubleCheck[k];
               }
             }
         }        
      return doubleCheck;
 }
 
   //given the row and column the program is checking, 
   //this method determines which 3x3 grid the number is in.
   public static int squareIdentity(int row, int column) {
   
   int square = -1;
      
        if((row < 3) && (column < 3)) {
             square = 0;
          }
         
           if((row < 3) && ((column > 2) && (column < 6))) {
              square = 1;
           }
         
          if((row < 3) && (column > 5)) {
             square = 2;
          }
         
          if(((row > 2) && (row < 6)) && (column < 3)) {
             square = 3;
          }
          
          if(((row > 2) && (row < 6)) && ((column > 2) && (column < 6))) {
             square = 4;
          }
          
          if(((row > 2) && (row < 6)) && (column > 5)) {
             square = 5;
          }
          
          if((row > 5) && (column < 3)) {
             square = 6;
          }
          
          if((row > 5) && ((column > 2) && (column < 6))) {
             square = 7;
          }
          
          if((row > 5) && (column > 5)) {
             square = 8;
          }
         
         return square;       
    }   
      
}