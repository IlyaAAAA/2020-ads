package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem3 {

  private static int[] d;

  private static class Rib {
    int vertexTo;
    int value;

    public Rib(int vertexTo, int value) {
      this.vertexTo = vertexTo;
      this.value = value;
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();

    List<List<Rib>> list = new ArrayList<>();
    d = new int[n];

    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<>());
      d[i] = Integer.MAX_VALUE;
    }

    for (int i = 0; i < m; i++) {
      int from = in.nextInt() - 1;
      int to = in.nextInt() - 1;
      int value = in.nextInt();

      list.get(from).add(new Rib(to, value));
    }

    findMinLength(list, 0);

    for (int i : d) {
      if (i == Integer.MAX_VALUE) {
        out.print(30000 + " ");
        continue;
      }
      out.print(i + " ");
    }
  }

  private static void findMinLength(List<List<Rib>> list, int vertexFrom) {

    d[vertexFrom] = 0;

    for (int i = 0; i < list.size(); i++) {
      for (int currentVertex = 0; currentVertex < list.size(); currentVertex++) {
        List<Rib> ribs = list.get(currentVertex);
        if (ribs != null) {
          for (Rib rib : ribs) {
            if (d[currentVertex] != Integer.MAX_VALUE && (d[currentVertex] + rib.value < d[rib.vertexTo])) {
              d[rib.vertexTo] = d[currentVertex] + rib.value;

            }
          }
        }

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
