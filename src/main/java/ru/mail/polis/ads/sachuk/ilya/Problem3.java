package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem3 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int firstLength = in.nextInt();
    int[] firstSequence = new int[firstLength];
    fillMas(firstSequence, in);

    int secondLength = in.nextInt();
    int[] secondSequence = new int[secondLength];
    fillMas(secondSequence, in);

    int[][] mas = new int[firstLength + 1][secondLength + 1];


    out.println(getMaxLengthSubsequence(firstLength, firstSequence, secondLength, secondSequence, mas));
  }

  private static int getMaxLengthSubsequence(int firstLength, int[] firstSequence, int secondLength, int[] secondSequence, int[][] mas) {
    int maxLength = 0;

    for (int i = 1; i < firstLength + 1; i++) {
      for (int j = 1; j < secondLength + 1; j++) {
        if (secondSequence[j - 1] == firstSequence[i - 1]) {
          mas[i][j] = mas[i - 1][j - 1] + 1;
          if (maxLength < mas[i][j]) {
            maxLength = mas[i][j];
          }
        }
        else {
          mas[i][j] = Math.max(mas[i - 1][j], mas[i][j - 1]);
        }
      }
    }
    return maxLength;
  }

  private static void fillMas(int[] mas, final FastScanner in) {
    for (int i = 0; i < mas.length; i++) {
      mas[i] = in.nextInt();
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

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}