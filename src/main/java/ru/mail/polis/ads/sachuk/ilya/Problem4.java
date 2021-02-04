package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem4 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    String firstLine = in.next();
    String secondLine = in.next();

    int n = firstLine.length() + 1;
    int m = secondLine.length() + 1;

    int[][] d = new int[m][n];

    d[0][0] = 1;

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (secondLine.charAt(i - 1) == '?' || firstLine.charAt(j - 1) == '?') {
          d[i][j] = d[i - 1][j - 1];
          continue;
        }
        if (secondLine.charAt(i - 1) == firstLine.charAt(j - 1)) {
          d[i][j] = d[i - 1][j - 1];
          continue;
        }

        if (secondLine.charAt(i - 1) == '*' || firstLine.charAt(j - 1) == '*') {
          if (d[i - 1][j - 1] == 1) {
            d[i][j] = 1;
          }
          else if (d[i - 1][j] == 1) {
            d[i][j] = 1;
          }
          else if (d[i][j - 1] == 1) {
            d[i][j] = 1;
          }
          else {
            d[i][j] = 0;
          }
        }
      }
    }

    if (d[m - 1][n - 1] == 1) {
      out.println("YES");
    }
    else {
      out.println("NO");
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