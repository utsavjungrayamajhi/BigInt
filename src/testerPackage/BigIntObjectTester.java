/**
 * 
 */
package testerPackage;

import bigIntPackage.BigInt;
import exceptionPackage.BigIntException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Larry Shannon
 *
 */
public class BigIntObjectTester
{
  private static Scanner keyboard = new Scanner(System.in);

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int numberOfIntegers = 0;
    ArrayList<BigInt> bigInts = new ArrayList<BigInt>();
    BigInt bigNumberOne = null;
    BigInt bigNumberTwo = null;
    String stringNumberOne = null;
    String yesNo = "";
    int compareValue = 0;
    boolean invalidInput = true;
//    myObject.to
    do
    {
      bigInts.clear();
      do
      {
        do
        {
          invalidInput = true;
          try
          {
            System.out.println("Please enter an integer.");
            System.out.println("The number can be any positive or negative whole number");
            stringNumberOne = keyboard.next();
            keyboard.nextLine();
            bigNumberOne = new BigInt(stringNumberOne);
            bigInts.add(bigNumberOne);
            System.out.println("The number entered is " + bigNumberOne);
            invalidInput = false;
          } catch (BigIntException badValueError)
          {
            System.out.println(badValueError.getMessage() + "\nPlease enter a valid whole number.");

          }
        } while (invalidInput);
        System.out.println("Would you like to enter another big integer?");
        do
        {
          System.out.println("Please enter \"yes\" or \"no\".");
          yesNo = keyboard.next();
          keyboard.nextLine();
        } while (!(yesNo.equalsIgnoreCase("yes") || yesNo.equalsIgnoreCase("no")));
      } while (yesNo.equalsIgnoreCase("yes"));
      bigInts.add(bigInts.get(0).clone());
      numberOfIntegers = bigInts.size();
      bigNumberOne = bigInts.get(0);
      for (int index = 0; index < numberOfIntegers; index++)
      {
//        bigNumberTwo = bigInts.get(index);
//        if (bigNumberOne.equals((Object)bigNumberTwo))
//          System.out.printf("%s and %s are from the same object\n", bigNumberOne, bigNumberTwo);
//        else
//          System.out.printf("%s and %s are not from the same object\n", bigNumberOne, bigNumberTwo);
//        compareValue = bigNumberOne.compareTo(bigNumberTwo);
//        if (bigNumberOne.equals(bigNumberTwo) && compareValue == 0)
//          System.out.printf("%s is equal to %s\n", bigNumberOne, bigNumberTwo);
//        else
//          if (compareValue > 0)
//            System.out.printf("%s is larger than %s\n", bigNumberOne, bigNumberTwo);
//          else
//            System.out.printf("%s is smaller than %s\n", bigNumberOne, bigNumberTwo);
      }

      System.out.println("Would you like to enter a new set of values?");
      do
      {
        System.out.println("Please enter \"yes\" or \"no\".");
        yesNo = keyboard.next();
        keyboard.nextLine();
      } while (!(yesNo.equalsIgnoreCase("yes") || yesNo.equalsIgnoreCase("no")));
  } while (yesNo.equalsIgnoreCase("yes"));
  }

}
    
