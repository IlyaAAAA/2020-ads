package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.StringTokenizer;

public class Problem4 {

  private static void solve(final FastScanner in, final PrintWriter out) {
    int stairsNumber = in.nextInt() + 2;

    int[] stairsCost = new int[stairsNumber];
    for (int i = 1; i < stairsNumber - 1; i++) {
      stairsCost[i] = in.nextInt();
    }

    int maxStep = in.nextInt();
    out.println(getStairsSum(stairsCost, maxStep, stairsNumber));
  }

  private static int getStairsSum(int[] stairsCost, int maxStep, int stairsNumber) {
    int[] d = new int[stairsNumber];

    d[0] = stairsCost[0];
    if (stairsNumber < 2) {
      return d[0];
    }

    for (int i = 1; i < stairsNumber; i++) {

      int cost = d[i - 1] + stairsCost[i];
      for (int step = 2; step <= maxStep; step++) {
        if (i < step) {
          break;
        }
        if (cost < d[i - step] + stairsCost[i]) {
          cost = d[i - step] + stairsCost[i];
        }
      }
      d[i] = cost;
    }


    return d[stairsNumber - 1];
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
