package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3 {
  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] array = new int[n];

    for (int i = 0; i < n; i++) {
      array[i] = in.nextInt();
    }

    out.println(numberOfSort(array));
  }

  private static int numberOfSort(int[] array) {
    int counter = 0;

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - 1 - i; j++)
        if (array[j] > array[j + 1]) {

          int tmp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = tmp;
          counter++;
        }
    }
    return counter;
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