/**
 * Created by Sydnei on 1/16/2017.
 * Soduku Puzzles from the website 261,262,263,264 & 265 (& 267 (medium)) maybe
 */

import java.io.*;
import java.util.Scanner;

public class SudokuSolver {
    
    public static void main(String args[])throws Exception {
    
    int puzzle [][] = new int [9][9]; //2D array to store the sudoku grid
    int puzzleAnswer [][] = new int [9][9]; //array to store the answer
    int puzzleCopy[][] = new int [9][9]; //a copy of the original array of the puzzle
    
    //initializing some variables I will need
    int num;
    int numAns;
    int squareValue = -1;
    int replaceValue = -1;
    int[] holdPossibleValues = {1,2,3,4,5,6,7,8,9};
    int count = 0;    
        
    //to read the file
    FileReader fr = new FileReader("puzz261.txt");
    Scanner sc = new Scanner(fr);
    
    //to read the answer file
    FileReader frAns = new FileReader("puzzAns261.txt");
    Scanner scAns = new Scanner (frAns);

     //inputs the unsolved puzzle into the 2D array 'puzzle'
     while (sc.hasNext()) {  
         for (int i = 0; i < 9; i++) {
            for (int j= 0; j < 9; j++) {
               num = sc.nextInt();
               puzzle[i][j] = num;
               puzzleCopy[i][j] = num;
            }
            }
     }
          
     //inputs the solved puzzle into the array 'puzzleAnswer'
     while (scAns.hasNext()) {
         for (int k = 0; k < 9; k ++) {
            for (int l = 0; l < 9; l++) {
               numAns = scAns.nextInt();
               puzzleAnswer[k][l] = numAns;
               }
            }
       }
     
    //prints out the unsolved sudoku puzzle for a 'before' comparison
    System.out.println("Here is what the unsolved, original sudoku puzzle looks like:");
    Methods.printSudoku(puzzleCopy);
    
    fr.close();
    
    //algorithm starts right after this
    System.out.println("\nThe program will now attempt to solve the sudkou puzzle....\n\n");
      

  //this while loop will cause everything to continue to iterate until the puzzle is completely solved
  while(Methods.checkCompletion(puzzle) == false) {
    
	 //double for loop to iterate through the 2D array holding the puzzle
     for(int a = 0; a < 9; a++) {
        for(int b = 0; b < 9; b++) {

          //identifies which 3x3 square the puzzle is currently in 
          squareValue = Methods.squareIdentity(a,b);
           
          //returns the an array filled with the possible numbers that could be inserted
          //	in this row/column/square
          int holdRuleOne[] = Methods.ruleOne(puzzle,a);
          int holdRuleTwo[] = Methods.ruleTwo(puzzle,b);
          int holdRuleThree[] = Methods.ruleThree(puzzle,squareValue);
          
          	//only continues if this cell of the puzzle is still unsolved 
            if (puzzle[a][b] == 0) {
                
            	//for loop to search through the array of possible #s that could be inserted
            	//	in each row/column/square
                for (int p = 0; p < 9; p++) {           
       
                   //if it is possible for a certain # to be placed in this cell since it is not in
                	//	each row/column/square. -1 indicates that # has already been placed in that
                	//	row/column/square, so it cannot equal -1 as well.
                   if (holdPossibleValues[p] == holdRuleOne[p] && holdPossibleValues[p] == holdRuleTwo[p] && holdPossibleValues[p] == holdRuleThree[p] && holdRuleOne[p] != -1) {
                  
                	   //keeps track of how many possible numbers can be placed in this cell
                      count++;                      
                     
                       }
                    
                   //if at least one of row/column/square is unable to hold this number that it is
                      //made to equal -1 to indicate that it is no longer possible to place that
                   	  //there.
                   else
                       holdPossibleValues[p] = -1;
                      
                      
                   }
                }
               
                //if the count is equal to 1, meaning that there is only ONE possible number that 
            		//may be placed in this cell.
                if (count == 1) {
                    
                	//places the only possible number of this cell into the puzzle
                    for (int q = 0; q < 9; q++) {
                       if (holdPossibleValues[q] != -1) {
                       
                          puzzle[a][b] = holdPossibleValues[q];
                          
                          }
                      }
                  }
                
//                //if the count is more than 1, meaning there is more than one possible number
//                //	that may be placed here, it figures out if there is a number that may ONLY
//                //	placed here.
                 //DOESN'T WORK, I could think of how I wanted to do it in my head, but found it much
                 //		harder to code it.
//                if (count >= 1) {
//               	 if (a < 3) {
//               		 
//               		 for(int r = 0; r < 9; r++) {
//               			 if(holdPossibleValues[r] != -1) {
//               				 
//               			 }
//               		 }
//               	 }
//                }
     
                  
                 //resets the possible value array and count for the next iteration of the loop.
                 holdPossibleValues = new int[] {1,2,3,4,5,6,7,8,9};                
                 count = 0;
                     }
     		}
  }
    
  	//prints out the solved sudoku puzzle
    System.out.println("This is what the sudoku puzzle looks like after the program attempted to solve it:");
    Methods.printSudoku(puzzle);
    //calls a method to cross-verify with the solved puzzle to see how accurate it was the solver was
    Methods.checkAnswer(puzzleCopy,puzzle,puzzleAnswer);
    
     
    }
    
       }
