package co.com.masiv.refactor;

import java.util.ArrayList;
import java.util.List;

public class Printer {

  public static final int NUMBER_PRIME_LIMIT = 1000;
  public static final int NUMBER_LIMIT_BY_PAGE = 50;
  public static final int NUMBER_LIMIT_BY_LINE = 4;

  public static void main(String[] args) {
    List<Integer> firstNumbersPrime = getFirstNumbersPrime(NUMBER_PRIME_LIMIT);
    printPage(firstNumbersPrime.toArray());
  }

  private static void printPage(Object[] numbersPrime) {
    int pageNumber = 1;
    int pageOffSet = 1;
    while (pageOffSet < NUMBER_PRIME_LIMIT) {
      System.out.println(
          "The First " + NUMBER_PRIME_LIMIT + " Prime Numbers === Page " + pageNumber + "\n");
      for (int rowOffSet = pageOffSet;
          rowOffSet <= pageOffSet + NUMBER_LIMIT_BY_PAGE - 2;
          rowOffSet++) {
        printLinePage(numbersPrime, rowOffSet);
        System.out.println();
      }
      pageNumber++;
      pageOffSet += NUMBER_LIMIT_BY_PAGE * NUMBER_LIMIT_BY_LINE;
    }
  }

  private static void printLinePage(Object[] numbersPrime, int rowOffSet) {
    for (int cont = 0; cont < NUMBER_LIMIT_BY_LINE; cont++)
      if (rowOffSet + cont * NUMBER_LIMIT_BY_PAGE < NUMBER_PRIME_LIMIT)
        System.out.printf("%10d", numbersPrime[rowOffSet + cont * NUMBER_LIMIT_BY_PAGE]);
  }

  private static List<Integer> getFirstNumbersPrime(int limitCountPrime) {
    List<Integer> numbersPrime = new ArrayList<>();
    int number = 1;
    while (numbersPrime.size() < limitCountPrime) {
      if (Printer.isPrime(number)) {
        numbersPrime.add(number);
      }
      number++;
    }
    return numbersPrime;
  }

  public static boolean isPrime(int number) {
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return number != 1;
  }
}
