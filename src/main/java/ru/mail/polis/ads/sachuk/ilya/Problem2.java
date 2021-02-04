package ru.mail.polis.ads.sachuk.ilya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem2 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int m = in.nextInt();
    int n = in.nextInt();

    int[][] mas = new int[m][n];

    fillMas(mas, m, n, in);

    int[][] d = new int[m][n];

    for (int i = m - 1; i >= 0; i--) {
      for (int j = 0; j < n; j++) {
        if (j - 1 >= 0 && i + 1 < m) {
          d[i][j] = Math.max(d[i][j - 1], d[i + 1][j]) + mas[i][j];
        }
        else if (j - 1 >= 0) {
          d[i][j] = mas[i][j] + d[i][j - 1];
        }
        else if (i + 1 < m) {
          d[i][j] = mas[i][j] + d[i + 1][j];
        }
        else {
          d[i][j] += mas[i][j];
        }
      }
    }


    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    int j = n - 1;
    while (i != m - 1 || j != 0) {
      if (i + 1 < m && j - 1 >= 0) {

        if (d[i + 1][j] > d[i][j - 1]) {
          stringBuilder.append("F");
          i++;
        }

        else {
          stringBuilder.append("R");
          j--;
        }
      }
      else if (i + 1 < m) {
        i++;
        stringBuilder.append("F");
      }
      else if (j - 1 >= 0) {
        j--;
        stringBuilder.append("R");
      }
    }
    out.println(stringBuilder.reverse().toString());
  }

  private static void fillMas(int[][] mas, int m, int n, final FastScanner in) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        mas[i][j] = in.nextInt();
      }
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