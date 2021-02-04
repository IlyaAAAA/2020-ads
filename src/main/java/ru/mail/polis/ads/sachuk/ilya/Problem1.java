package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    final int n = in.nextInt();

    int[] mas = new int[n + 1];
    mas[0] = -1;

    for (int i = 1; i < mas.length; i++) {
      mas[i] = in.nextInt();
    }

    String answer = isHeap(mas) ? "YES" : "NO";
    out.println(answer);
  }

  private static boolean isHeap(int[] mas) {

    boolean isHeap = true;

    for (int i = 1; i < mas.length; i++) {
      if (i * 2 < mas.length) {
        if (mas[i] > mas[i * 2]) {
          isHeap = false;
        }
      }
      if (i * 2 + 1 < mas.length) {
        if (mas[i] > mas[i * 2 + 1]) {
          isHeap = false;
        }
      }
    }

    return isHeap;
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
