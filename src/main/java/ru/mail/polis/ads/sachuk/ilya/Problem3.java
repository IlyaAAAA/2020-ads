package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem3 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int number = in.nextInt();
    int[] arr = new int[number];

    for (int i = 0; i < number; i++) {
      arr[i] = in.nextInt();
    }

    int[] d = new int[number];
    d[0] = 1;

    int maxLength = d[0];

    for (int i = 1; i < number; i++) {
      int max = 0;
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j] == 0) {
          continue;
        }

        if (arr[i] % arr[j] == 0) {
          if (max < d[j]) {
            max = d[j];
          }
        }
      }
      d[i] = max + 1;
      if (maxLength < d[i]) {
        maxLength = d[i];
      }
    }

    out.println(maxLength);
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