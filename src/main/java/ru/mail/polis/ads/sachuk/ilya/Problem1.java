package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
  private static int[][] d;
  private static int[][] arrK;
  private static void solve(final FastScanner in, final PrintWriter out) {
    String bracketSequence = in.next();

    int length = bracketSequence.length();
    d = new int[length][length];
    arrK = new int[length][length];

    fillMas(d, length);

    for (int j = 0; j < length; j++) {

      for (int i = j - 1; i >= 0; i--) {

        char leftBracket = bracketSequence.charAt(i);
        char rightBracket = bracketSequence.charAt(j);
        int min = Integer.MAX_VALUE;
        arrK[i][j] = i;

        if (checkBrackets(leftBracket, rightBracket)) {
          min = d[i + 1][j - 1];
          arrK[i][j] = Integer.MAX_VALUE;
        }

        for (int k = i; k < j; k++) {
          if (min > d[i][k] + d[k + 1][j]) {
            min = d[i][k] + d[k + 1][j];
            arrK[i][j] = k;
          }
        }
        d[i][j] = min;
      }
    }
    StringBuilder correctSequence = getCorrectSequence(bracketSequence, 0, length - 1);
    out.println(correctSequence);
  }

  private static boolean checkBrackets(char left, char right) {
    return left == '(' && right == ')' || left == '[' && right == ']';
  }

  private static StringBuilder getCorrectSequence(String bracketSequence, int i, int j) {

    if (i == j) {
      if (bracketSequence.charAt(i) == '(' || bracketSequence.charAt(i) == ')') {
        return new StringBuilder("()");
      }
      return new StringBuilder("[]");
    }

    if (d[i][j] == 0) {
      return new StringBuilder(bracketSequence.substring(i, j + 1));
    }

    if (arrK[i][j] == Integer.MAX_VALUE) {
      return new StringBuilder(bracketSequence.charAt(i) + getCorrectSequence(bracketSequence, i + 1, j - 1).toString() + bracketSequence.charAt(j));
    }

    return new StringBuilder(getCorrectSequence(bracketSequence, i, arrK[i][j]).append(getCorrectSequence(bracketSequence, arrK[i][j] + 1, j)));
  }

  private static void fillMas(int[][] d, int length) {
    for (int i = 0; i < length; i++) {
      d[i][i] = 1;
    }
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
      solve(in, out);
    }
    catch (Exception ignored) {

    }
  }
}
