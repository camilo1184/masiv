package co.com.masiv.refactor;

public class Printer2 {

  public static void main(String[] args) {
    final int M = 1000;
    final int RR = 50;
    final int CC = 4;
    final int ORDMAX = 30;
    int P[] = new int[M + 1];
    int PAGENUMBER;
    int PAGEOFFSET;
    int ROWOFFSET;
    int C;
    int J;
    int K;
    boolean JPRIME;
    int ORD;
    int SQUARE;
    int N = 0;
    int MULT[] = new int[ORDMAX + 1];
    J = 1;
    K = 1;
    P[1] = 2;
    ORD = 2;
    SQUARE = 9;
    while (K < M) {
      do {
        J += 2;
        if (J == SQUARE) {
          ORD++;
          SQUARE = P[ORD] * P[ORD];
          MULT[ORD - 1] = J;
        }
        N = 2;
        JPRIME = true;
        while (N < ORD && JPRIME) {
          while (MULT[N] < J) MULT[N] += P[N] + P[N];
          if (MULT[N] == J) JPRIME = false;
          N++;
        }
      } while (!JPRIME);
      K++;
      P[K] = J;
    }
    PAGENUMBER = 1;
    PAGEOFFSET = 1;
    printPage(M, RR, CC, P, PAGENUMBER, PAGEOFFSET);
  }

  private static void printPage(int m, int RR, int CC, int[] p, int PAGENUMBER, int PAGEOFFSET) {
    int ROWOFFSET;
    int C;
    while (PAGEOFFSET <= m) {
      System.out.print("The First ");
      System.out.print(Integer.toString(m));
      System.out.print(" Prime Numbers === Page ");
      System.out.print(Integer.toString(PAGENUMBER));
      System.out.println("\n");
      for (ROWOFFSET = PAGEOFFSET; ROWOFFSET <= PAGEOFFSET + RR - 1; ROWOFFSET++) {
        for (C = 0; C <= CC - 1; C++)
          if (ROWOFFSET + C * RR <= m) System.out.printf("%10d", p[ROWOFFSET + C * RR]);
        System.out.println();
      }
      System.out.println("\f");
      PAGENUMBER++;
      PAGEOFFSET += RR * CC;
    }
  }
}
