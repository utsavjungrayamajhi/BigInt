/**
 * 
 */
package testerPackage;

import bigIntPackage.BigInt;
import exceptionPackage.BigIntException;

import java.util.Scanner;

/**
 * @author Larry Shannon
 *
 */
public class BigIntMathTesterOne
{
  private static Scanner keyboard = new Scanner(System.in);

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    BigInt bigNumberOne = null;
    String yesNo = "no";
    String menuChoice = "q";
    BigInt bigNumberTwo = null;
    BigInt bigNumberAnswer = null;
    BigInt bigZero = null;
    boolean divideByZero = false;
    boolean areEqual = false;
    boolean compairing = false;
    try
    {
      bigZero = new BigInt("0");
    } catch (BigIntException myException)
    {
      System.out.println(myException.getMessage());
    }

    System.out.println("This program will perform basic math on large integer numbers");
    do
    {
      divideByZero = false;
      compairing = false;
      bigNumberOne = inputBigInteger();
      bigNumberTwo = inputBigInteger();
      System.out.println("You input the following integer numbers:\n" + bigNumberOne + "\n" + bigNumberTwo);
      System.out.println("You may perform one of the following opperations:");
      System.out.println("1. Add");
      System.out.println("2. Subtract the first number from the second number");
      System.out.println("3. Subtract the second number from the first number");
//      System.out.println("4. Multiplication");
//      System.out.println("5. Divide the first number by the second number");
//      System.out.println("6. Divide the second number by the first number");
//      System.out.println("7. Modulus the first number by the second number");
//      System.out.println("8. Modulus the second number by the first number");
//      System.out.println("9. Compare numbers for absolute value");
//      System.out.println("A. Compare numbers for equality");
      System.out.println("B. Ignore numbers and start over");
      do
      {
        System.out.println("Please enter a menu choice number.");
        menuChoice = keyboard.nextLine();
      } while (!menuChoice.matches("([1-9])"));
      switch (menuChoice)
      {
        case "1":
          System.out.println("Adding " + bigNumberOne + " to " + bigNumberTwo);
          bigNumberAnswer = bigNumberOne.add(bigNumberTwo);
        break;
        case "2":
          System.out.println("Subtracting " + bigNumberOne + " from " + bigNumberTwo);
          bigNumberAnswer = bigNumberTwo.subtract(bigNumberOne);
        break;
        case "3":
          System.out.println("Subtracting " + bigNumberTwo + " from " + bigNumberOne);
          bigNumberAnswer = bigNumberOne.subtract(bigNumberTwo);
        break;
//        case "4":
//          System.out.println("Multiplying " + bigNumberOne + " by " + bigNumberTwo);
//          bigNumberAnswer = bigNumberTwo.multiply(bigNumberOne);
//        break;
//        case "5":
//          if (bigZero.compareTo(bigNumberTwo) == 0)
//          {
//            System.out.println("Divide by zero");
//            divideByZero = true;
//          } else
//          {
//            System.out.println("Dividing " + bigNumberOne + " by " + bigNumberTwo);
//            bigNumberAnswer = bigNumberOne.divide(bigNumberTwo);
//          }
//        break;
//        case "6":
//          if (bigZero.compareTo(bigNumberOne) == 0)
//          {
//            System.out.println("Divide by zero");
//            divideByZero = true;
//          } else
//          {
//            System.out.println("Dividing " + bigNumberTwo + " by " + bigNumberOne);
//            bigNumberAnswer = bigNumberTwo.divide(bigNumberOne);
//          }
//        break;
//        case "7":
//          if (bigZero.compareTo(bigNumberTwo) == 0)
//          {
//            System.out.println("Divide by zero");
//            divideByZero = true;
//          } else
//          {
//            System.out.println("Modulus " + bigNumberOne + " by " + bigNumberTwo);
//            bigNumberAnswer = bigNumberOne.modulus(bigNumberTwo);
//          }
//        break;
//        case "8":
//          if (bigZero.compareTo(bigNumberOne) == 0)
//          {
//            System.out.println("Divide by zero");
//            divideByZero = true;
//          } else
//          {
//            System.out.println("Modulus " + bigNumberTwo + " by " + bigNumberOne);
//            bigNumberAnswer = bigNumberTwo.modulus(bigNumberOne);
//          }
//        break;
//        case "9":
//          compairing = true;
//          System.out.println("Comparing the absolute value of " + bigNumberTwo + " to the aboslute value of " + bigNumberOne);
//          areEqual = bigNumberTwo.equalsIgnoreSign(bigNumberOne);
//        break;
//        case "A":
//        case "a":
//          compairing = true;
//          System.out.println("Comparing the value of " + bigNumberTwo + " to the value of " + bigNumberOne);
//          areEqual = bigNumberTwo.equals(bigNumberOne);
//        break;
        default:
        break;
      }
//      if (!compairing)
//      {
//        if (!divideByZero)
//        {
          System.out.println("Produces: " + bigNumberAnswer);
//        }
//      } else
//      {
//        System.out.println("Produces that it is: " + areEqual + " that " + bigNumberOne + " and " + bigNumberTwo + " are equal.");
//      }
      System.out.println("Would you like to enter new numbers?");
      do
      {
        System.out.println("Please enter yes or no");
        yesNo = keyboard.nextLine();
      } while (!yesNo.equalsIgnoreCase("yes") && !yesNo.equalsIgnoreCase("no"));
    } while (yesNo.equalsIgnoreCase("yes"));

  }

  private static BigInt inputBigInteger()
  {
    BigInt newBigInt = null;
    String userIntInput = null;
    do
    {
      try
      {
        System.out.println("Please enter an integer of any size.");
        userIntInput = keyboard.nextLine();
        newBigInt = new BigInt(userIntInput);
      } catch (BigIntException myException)
      {
        System.out.println(myException.getMessage());
      }
    } while (newBigInt == null);
    return newBigInt;
  }

}
