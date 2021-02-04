package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    String line = in.next();
    double c  = Double.parseDouble(line);

    double left = 0;
    double right = c;
    double finalC = 0;

    while (Math.abs(c - finalC) > 0.0000001) {

      double middle = (left + right) / 2;

      finalC = middle * middle + Math.sqrt(middle);

      if (finalC < c) {
        left = middle;
      }
      else {
        right = middle;
      }
    }
    out.printf("%.6f", right);
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
        } catch (IOException e) {
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