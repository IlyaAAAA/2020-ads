package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem2 {

  private static class Rib {
    int vertexFrom;
    int vertexTo;
    int value;

    public Rib(int vertexFrom, int vertexTo, int value) {
      this.vertexFrom = vertexFrom;
      this.vertexTo = vertexTo;
      this.value = value;
    }
  }

  private static int resWay;
  private static int[] parents;

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();

    List<Rib> list = new ArrayList<>();
    parents = new int[n];

    for (int i = 0; i < n; i++) {
      parents[i] = i;
    }


    for (int i = 0; i < m; i++) {
      int from = in.nextInt() - 1;
      int to = in.nextInt() - 1;
      int value = in.nextInt();

      list.add(new Rib(from, to, value));
    }

    findMinCarcase(list, n);
    out.println(resWay);
  }

  private static void findMinCarcase(List<Rib> list, int n) {
    int counter = 0;
    list.sort(Comparator.comparingInt(o -> o.value));

    for (Rib rib : list) {
      if (counter == n - 1) {
        return;
      }
      if (rib != null) {
        if (findSet(rib.vertexFrom) != findSet(rib.vertexTo)) {
          unionSets(rib.vertexFrom, rib.vertexTo);
          resWay += rib.value;
          counter++;
        }
      }
    }
  }


  private static int findSet(int v) {
    if (v == parents[v]) {
      return v;
    } else {
      return parents[v] = findSet(parents[v]);
    }
  }

  private static void unionSets(int a, int b) {
    a = findSet(a);
    b = findSet(b);
    if (a != b) {
      parents[a] = b;
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

