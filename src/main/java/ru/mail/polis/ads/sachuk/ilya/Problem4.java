package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem4 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int masLength = in.nextInt();
    int requestsNumber = in.nextInt();

    int[] mas = new int[masLength];

    for (int i = 0; i < mas.length; i++) {
      mas[i] = in.nextInt();
    }

    for (int i = 0; i < requestsNumber; i++) {
      int numberToFind = in.nextInt();
      String answer = binarySearch(mas, numberToFind, 0, mas.length - 1) ? "YES" : "NO";
      out.println(answer);
    }
  }

  private static boolean binarySearch(int[] mas, int numberToFind, int left, int right) {

    int mid = (right + left) / 2;


    if (mas[mid] == numberToFind) {
      return true;
    }
    else if (right == left) {
      return false;
    }


    if (mas[mid] > numberToFind) {
      return binarySearch(mas, numberToFind, left, mid);
    }
    else {
      return binarySearch(mas, numberToFind, mid + 1, right);
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