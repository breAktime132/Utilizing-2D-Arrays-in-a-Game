/*
*
* Original Version: 
*
* 3/12/2019
*
* This program will simulate a connect 4 game, with two players dropping chips into any of the 7 columns. 
* 
* If four of the same chip, red or yellow are found in succession diagonally, horizontally, or vertically in any direction plausible, the player, player red or yellow, will win the game. 
*
* If no win is achieved, the program will cease after all columns are filled.
*/
// It appears left to right works, with the exception of the while loop being unable to end unless all content inside the loop has run first, even when its conditions are no longer true, but right to left is weird
import java.util.Scanner;
public class connect4{
   public static void main (String [] args){
      createArray();                                                                // Creates an array and fills it with chips placed by the user.
   }
   public static void printArray(String myArray [][]){                              // Prints the connect 4 grid.
      int column = 0;                                                               // Loop Control Variable for each column of the connect 4 grid.
      int row = 0;                                                                  // Loop Control Variable for each row of the connect 4 grid.
      for(row = 0; row < 6; row++){                                                 // Outer for loop that traverses each row of the grid.
      for(column = 0; column < 7; column++){                                        // Inner for loop that traverses each column of the grid.
         // System.out.print(myArray[LCV2][LCV]);
         System.out.print("|" +myArray[row][column]);                               // Prints out the structure of the grid by adding a vertical line to the left of the element value, thus completing 7 out of 6 cells with one cell on the very end missing a vertical line to the right.
      }
      System.out.print("|");                                                        // Adds a vertical line to the right of the cell at the end of each row.
      System.out.println("");                                                       // Makes a new line.
   }
   }
   public static void createArray(){                                                // Creates an array and fills it with chips placed by the user.
   int row = 0;                                                                     // Loop Control Variable for each row of the grid.
   int column = 0;                                                                  // Loop Control Variable for each column of the grid.
   boolean tempCountVariable = false;                                               // Initializes tempCountVariable to false. This variable will hold the return value of horizontal, vertical, and diagonal checks: when this variable stores a true return value, one of these checks found a solution within the grid.
   boolean tempCountVariable2 = false;                                              // Initializes tempCountVariable2 to false. This variable will hold the return value of horizontal, vertical, and diagonal checks: when this variable stores a true return value, one of these checks found a solution within the grid.
   boolean tempCountVariable3 = false;
   boolean tempCountVariable4 = false;
   boolean tempCountVariable5 = false;
   boolean tempCountVariable6 = false;
   boolean tempCountVariable7 = false;
   boolean tempCountVariable8 = false;
   boolean temp = false;
   String [][] myArray = new String[6][7];                                          // Creates a two-dimensional array with 6 rows and 7 columns.
   for(row = 0; row < 6; row++){                                                    // Loop Control Variable for each row of the two-dimensional array.
      for(column = 0; column < 7; column++){                                        // Loop Control Variable for each column of the two-dimensonal array.
         myArray[row][column] = "_";                                                // Initializes the value of each coordinate within the array as '_'.
      }
   }
   printArray(myArray);                                                             // Prints the created array.
   
   while(tempCountVariable == false && tempCountVariable2 == false && tempCountVariable3 == false && tempCountVariable4 == false && tempCountVariable5 == false && tempCountVariable6 == false && tempCountVariable7 == false && tempCountVariable8 == false){                // While no check method has yet found a solution...
      redPlayerInput(myArray);                                                      // Calls the method redPlayerInput, which receives user input for player red.
      tempCountVariable = winnerCheckHorizontal(myArray);                           // After both players have entered their chips, the method winnerCheckHorizontal checks to see if there is a horizontal solution.
      tempCountVariable2 = winnerCheckVertical(myArray);                            // After both players have entered their chips, the method winnerCheckVertical checks to see if there is a vertical solution.
      tempCountVariable3 = winnerCheckDiagonalLeftToRight(myArray);                 // After both players have entered their chips, the method winnerCheckDiagonal checks to see if there is a diagonal solution from each column of the bottom row.
      tempCountVariable4 = winnerCheckDiagonalRightToLeft(myArray);
      if(tempCountVariable == true || tempCountVariable2 == true || tempCountVariable3 == true || tempCountVariable4 == true){
         break;
      }
      temp = isDraw(myArray);
      if(isDraw(myArray) == true){
         break;
      }
      yellowPlayerInput(myArray);                                                   // Calls the method yellowPlayerInput, which receives user input for player yellow.
      tempCountVariable5 = winnerCheckHorizontal(myArray);                          // After both players have entered their chips, the method winnerCheckHorizontal checks to see if there is a horizontal solution.
      tempCountVariable6 = winnerCheckVertical(myArray);                            // After both players have entered their chips, the method winnerCheckVertical checks to see if there is a vertical solution.
      tempCountVariable7 = winnerCheckDiagonalLeftToRight(myArray);                 // After both players have entered their chips, the method winnerCheckDiagonal checks to see if there is a diagonal solution from each column of the bottom row.
      tempCountVariable8 = winnerCheckDiagonalRightToLeft(myArray);
      temp = isDraw(myArray);
      if(isDraw(myArray) == true){
         break;
      }
   }
   if(tempCountVariable == true){                                                   // If a horizontal solution has been found...
      System.out.println("Player red won by 4 chips horizontally.");                // The console will announce that one of the players has entered a horizontal solution.
   }else if(tempCountVariable5 == true){                                            // If a vertical solution has been found...
      System.out.println("Player yellow won by 4 chips horizontally.");             // The console will announce that one of the players has entered a vertical solution.
   }else if(tempCountVariable2 == true){
      System.out.println("Player red won by 4 chips vertically.");
   }else if(tempCountVariable6 == true){
      System.out.println("Player yellow won by 4 chips vertically.");
   }else if(temp == true){
      System.out.println("No one won.");
   }
   }
   public static void redPlayerInput(String [][] myArray){                          // Receives input for one of the two players, player red, and drops a chip down one of the columns they select (to which row the chip will fall to is determined by a system of checks).
      Scanner input = new Scanner(System.in);                                       // Creates a scanner that allows for user input within this method only.
      int row = 0;                                                                  // Loop Control Variable for each row of the grid.
      int userInputRed = -1;                                                        // Stores which column the user chose to drop a chip into.
      int count = 0;                                                                // Stores the number of times a chip should drop based on whether there is an element other than '_' underneath.
      boolean invalidRow = false;
      while(userInputRed < 0 || userInputRed > 6){
         while(invalidRow == false){
            System.out.println("Please enter player red's chip (R)");               // Asks player red to enter the column they want their chip to drop from. Also notifies the players whose turn it is.
            System.out.println("Please enter a digit 1-7 for columns 1-7");
            userInputRed = input.nextInt();                                         // Gets user input from player red and stores it in userInputRed.
            userInputRed = userInputRed - 1;
            if(myArray[0][userInputRed] == "_"){
               invalidRow = true;
            }else if(myArray[0][userInputRed] == "Y" || myArray[0][userInputRed] == "R"){
               System.out.println("That column is full.");
               invalidRow = false;
            }
         }
      }  
      for(row = 0; row < 6; row++){                                                 // Runs for 6 times. The Loop Control Variable, row, will help traverse the column the user entered through each row.
         if(myArray[row][userInputRed] == "_" && count != 5){                       // If a row contains the standard element, '_', and the variable count, which determines how far a chip can go, is not yet 5 (there are 6 rows and one row is occupied at the start of the check)...
            if(myArray[row + 1][userInputRed] == "_" && count !=5){                 // If the row after it also contains the standard element and not a user-entered one...
               count = count + 1;                                                   // The variable count increments by 1.
            }
         }
      }
      if(myArray[0][userInputRed] == "_"){
         myArray[count][userInputRed] = "R";                                        // The coordinates, the column chosen by the user and the row deemed safe by the program, are set equal to "Y" and the chip is placed in this spot.
      }
      printArray(myArray);                                                          // Prints the newly initialized array for the next player to input.
   }
   public static void yellowPlayerInput(String [][] myArray){                       // Receives input for one of the two players, player red, and drops a chip down one of the columns they select (to which row the chip will fall to is determined by a system of checks).
      Scanner input = new Scanner(System.in);                                       // Creates a scanner that allows for user input within this method only.
      int row = 0;                                                                  // Loop Control Variable for each row of the grid.
      int userInputYellow = -1;                                                     // Stores which column the user chose to drop a chip into.
      int count = 0;                                                                // Stores the number of times a chip should drop based on whether there is an element other than '_' underneath.
      boolean invalidRow = false;
      while(userInputYellow < 0 || userInputYellow > 6){                            // While the user enters an invalid column...
         while(invalidRow == false){
            System.out.println("Please enter a digit 1-7 for columns 1-7");         // Asks the user to enter a digit for a column to drop a chip into.
            System.out.println("Please enter player yellow's chip (Y)");            // Asks player red to enter the column they want their chip to drop from. Also notifies the players whose turn it is.
            userInputYellow = input.nextInt();                                      // Gets user input from player red and stores it in userInputRed.
            userInputYellow = userInputYellow - 1;
            if(myArray[0][userInputYellow] == "_"){
               invalidRow = true;
            }else if(myArray[0][userInputYellow] == "Y" || myArray[0][userInputYellow] == "R"){
               System.out.println("That column is full.");
               invalidRow = false;
            }
         }
      }
      for(row = 0; row < 6; row++){                                                 // Runs for 6 times. The Loop Control Variable, row, will help traverse the column the user entered through each row.
         if(myArray[row][userInputYellow] == "_" && count != 5){                    // If a row contains the standard element, '_', and the variable count, which determines how far a chip can go, is not yet 5 (there are 6 rows and one row is occupied at the start of the check)...
            if(myArray[row + 1][userInputYellow] == "_" && count !=5){              // If the row after it also contains the standard element and not a user-entered one...
               count = count + 1;                                                   // The variable count increments by 1.
            }
         }
      }
 // System.exit(0);
      if(myArray[0][userInputYellow] == "_"){
         myArray[count][userInputYellow] = "Y";                                     // The coordinates, the column chosen by the user and the row deemed safe by the program, are set equal to "Y" and the chip is placed in this spot.
      }
      printArray(myArray);                                                          // Prints the newly initialized array for the next player to input.

   }
   public static boolean winnerCheckHorizontal(String [][] myArray){
      int countR = 0;                                                               // Counter for all 'y' chips in succession.
      int countY = 0;                                                               // Counter for all 'x' chips in succession.
      int row = 0;                                                                  // Variable for each row in the two-dimensional array.
      int column = 0;                                                               // Variable for each column in the two-dimensional array.
      for(row = 0; row < 6; row++){                                                 // Outer For Loop for each row of grid.
         for(column = 0; column < 7; column++){                                     // Inner For Loop for each column of grid.
            if(myArray[row][column] == "R"){                                        // If there is an R within a given coordinate...
                  countR = countR + 1;                                              // The variable countR, which keeps track of how many checkers made by player red are found in a row, increments by one.
                  countY = 0;                                                       // The variable countY, which keeps track of how many checkers made by player yellow are found in a row, is reset to zero (there is no more Y's in a row).
            }else if(myArray[row][column] == "Y"){                                  // If there is a Y within a given coordinate...
                  countY = countY + 1;                                              // The variable countY, which keeps track of how many checkers made by player yellow are found in a row, increments by one.
                  countR = 0;                                                       // The variable countR, which keeps track of how many checkers made by player red are found in a row, is reset to zero (there is no more R's in a row).
            }else if(myArray[row][column] == "_"){
               countY = 0;
               countR = 0;
            }
 if(countR == 4 || countY == 4){                                                    // If countR is equal to 4...
      return true;                                                                  // Returns a boolean value of true to the variable tempCountVariable, found in the method createArray.
 }
      }
         countR = 0;                                                                // Resets red chip counter after every row.
         countY = 0;                                                                // Resets yellow chip counter after every row.
    }
    return false;
   }
   public static boolean winnerCheckVertical(String [][] myArray){
      int row = 0;                                                                  // Loop Control Variable used to traverse each row of the grid.
      int column = 0;                                                               // Loop Control Variable used to traverse each column of the grid.
      int countR = 0;                                                               // Counts the number of "R" chips found in succession.
      int countY = 0;                                                               // Counts the number of "Y" chips found in succession.
      for(row = 0; row < 7; row++){                                                 // Outer For Loop for each row of grid.
         for(column = 0; column < 6; column++){                                     // Inner For Loop for each column of grid.
            if(myArray[column][row] == "R"){                                        // If there is an R within a given coordinate...
                  countR = countR + 1;                                              // The variable countR, which keeps track of how many checkers made by player red are found in a row, increments by one.
                  countY = 0;                                                       // The variable countY, which keeps track of how many checkers made by player yellow are found in a row, is reset to zero (there is no more Y's in a row).
            }else if(myArray[column][row] == "Y"){                                  // If there is a Y within a given coordinate...
                  countY = countY + 1;                                              // The variable countY, which keeps track of how many checkers made by player yellow are found in a row, increments by one.
                  countR = 0;                                                       // The variable countR, which keeps track of how many checkers made by player red are found in a row, is reset to zero (there is no more R's in a row).
            }else if(myArray[column][row] == "_"){
               countY = 0;
               countR = 0;
            }
 if(countR == 4 || countY == 4){                                                    // If countR is equal to 4...
      return true;                                                                  // Returns a boolean value of true to the variable tempCountVariable, found in the method createArray.  
    }
      }
         countR = 0;                                                                // Resets the counter for all "R" chips to zero.
         countY = 0;                                                                // Resets the counter for all "Y" chips to zero.
    }
    return false;                                                                   // If the method was unable to find a solution, it will return a boolean value of false.
   }
   public static boolean winnerCheckDiagonalRightToLeft(String [][] myArray){       // Checks all chips diagonally.
      int countR = 0;                                                               // Stores the number of R chips in succession.
      int countY = 0;                                                               // Stores the number of Y chips in succession.
      int row = 0;                                                                  // Loop Control Variable for each row of the 2D array.
      int column = 0;                                                               // Loop Control Variable for each column of the 2D array.
      for(row = 5; row > 3; row--){                                                 // Outer for Loop to traverse each row of grid.
         for(column = 6; column > 0; column--){                                     // Inner for loop to traverse each column of the grid.
            if(column < 7 && column > 2){                                           // If a columns that diagonally allow for a solution is selected (there are two columns, in this case the rightmost three, that do not allow for a diagonal solution).
               if(myArray[row][column] == "R"){                                     // If player red's chip is found in the specific coordinates given by the double for loop...
                  countY = 0;                                                       // The variable countY is reset, since there cannot be a streak of player yellow's chips if a red chip is in between.
                  countR = countR + 1;                                              // Adds one to the number of times player red's chip is found diagonally.
                  if(myArray[row - 1][column - 1] == "R"){                          // If a red chip is found one diagonal space from the original coordinates...
                  countR = countR + 1;                                              // The counter for red chips increments by one.
                     if(myArray[row - 2][column - 2] == "R"){                       // If a red chip is found two diagonal spaces from the original coordinates...
                     countR = countR + 1;                                           // The counter for red chips increments by one.
                        if(myArray[row - 3][column - 3] == "R"){                    // If a red chip is found three diagonal spaces from the original coordinates...
                        countR = countR + 1;                                        // The counter for red chips increments by one.
                        }
                     }
                  }
               }else if(myArray[row][column] == "Y"){                               // If a red chip is found at the original coordinates...
                  countR = 0;                                                       // The counter for red chips is reset.
                  countY = countY + 1;                                              // The counter for yellow chips increments by one.
                  if(myArray[row - 1][column - 1] == "Y"){                          // If a red chip is found one space diagonally from the original coordinates...
                     countY = countY + 1;                                           // The counter for yellow chips increments by one.
                     if(myArray[row - 2][column - 2] == "Y"){                       // If a red chip is found two spaces diagonally from the original coordinates...
                        countY = countY + 1;                                        // The counter for yellow chips increments by one.
                        if(myArray[row - 3][column - 3] == "Y"){                    // If a red chip is found three spaces diagonally from the original coordinates...
                           countY = countY + 1;                                     // The counter for yellow chips increments by one.
                        }
                     }
                  }
               }
            }
            if(countR == 4){                                                        // If the counter for red chips is equal to four...
               System.out.println("Player red wins diagonally!");                   // The system prints that player red won diagonally (four red chips in a row diagonally).
               return true;                                                         // Returns a true boolean to the method
            }else if(countY == 4){                                                  // If the counter for yellow chips is equal to four...
               System.out.println("Player yellow wins diagonally!");                // The system prints that player yellow won diagonally (four yellow chips in a row diagonally).
               return true;                                                         // Returns a true boolean to the method
            }
            countY = 0;                                                             // Resets yellow chip counter for next diagonal.
            countR = 0;                                                             // Resets red chip counter for next diagonal.
         }
         }
         return false;
   }
   public static boolean winnerCheckDiagonalLeftToRight(String [][] myArray){       // Checks all chips diagonally.
      int countR = 0;                                                               // Stores the number of R chips in succession.
      int countY = 0;                                                               // Stores the number of Y chips in succession.
      int row = 0;                                                                  // Loop Control Variable for each row of the 2D array.
      int column = 0;                                                               // Loop Control Variable for each column of the 2D array.
      for(row = 5; row > 3; row--){                                                 // Outer for loop to traverse each row of the 2D array.
         for(column = 0; column < 6; column++){                                     // Inner for loop to traverse each column of the 2D array.
            if(column >= 0 && column <= 3){                                         // If a columns that diagonally allow for a solution is selected (there are two columns, in this case the leftmost three, that do not allow for a diagonal solution).
               if(myArray[row][column] == "R"){                                     // If a red chip is found in the given coordinates of row x and column y...
                  countY = 0;                                                       // The sequence of yellow chips is reset.
                  countR = countR + 1;                                              // The sequence of red chips is incremented by one.
                  if(myArray[row - 1][column + 1] == "R"){                          // If there is a red chip the next space diagonally from the coordinates given...
                  countR = countR + 1;                                              // The sequence of red chips is incremented by one.
                     if(myArray[row - 2][column + 2] == "R"){                       // If there is a red chip the next space diagonally from the previous diagonal space.
                     countR = countR + 1;                                           // The sequence of red chips is incremented by one.
                        if(myArray[row - 3][column + 3] == "R"){                    // If there is a red chip the next space diagonally from the previous diagonal space (four in a row diagonally)...
                        countR = countR + 1;                                        // The sequence of red chips is incremented by one.
                        }
                     }
                  }
               }else if(myArray[row][column] == "Y"){                               // If there is a yellow chip at the given coordinates of row x and column y...
                  countR = 0;                                                       // The sequence of red chips is reset.
                  countY = countY + 1;                                              // The sequence of yellow chips is incremented by one.
                  if(myArray[row - 1][column + 1] == "Y"){                          // If there is a yellow chip in the next space diagonally from the coordinate given...
                     countY = countY + 1;                                           // The sequence of yellow chips is incremented by one.
                     if(myArray[row - 2][column + 2] == "Y"){                       // If there is a yellow chip in the next space diagonally from the previous diagonal space...
                        countY = countY + 1;                                        // The sequence of yellow chips is incremented by one.
                        if(myArray[row - 3][column + 3] == "Y"){                    // If there is a yellow chip in the next space diagonally from the previous diagonal space...
                           countY = countY + 1;                                     // The sequence of yellow chips is incremented by one.
                        }
                     }
                  }
               }
            }
            if(countR == 4){                                                        // If the counter for red chips is 4 (four diagonal chips in a row)...
               System.out.println("Player red winss diagonally!");                  // Prints to the console that player yellow won diagonally.
               return true;                                                         // Returns the boolean true to stop the while loop in the method
            }else if(countY == 4){                                                  // If the counter for yellow chips is 4 (four diagonal chips in a row)...
               System.out.println("Player yellow winns diagonally!");               // Prints to the console that player yellow won diagonally.
               return true;                                                         // Returns the boolean true to stop the while loop in the method
            }
            countY = 0;                                                             // Resets counter so as to not continue counting the next diagonal set.
            countR = 0;                                                             // Resets counter so as to not continue counting the next diagonal set.
         }
         }
    //  if(countR == 4 || countY == 4){                                             // If countR is equal to 4...
     //    return true;
     // }
     return false;
    }
    public static boolean isDraw(String [][] myArray){
      int row = 0;
      int column = 0;
      int count = 0;
      for(row = 0; row < 6; row++){
         for(column = 0; column < 7; column++){
            if(myArray[row][column] == "R" || myArray[row][column] == "Y"){
               count++;
            }
         }
      }
      if(count == 42){
         return true;
      }
      return false;
   }
}
