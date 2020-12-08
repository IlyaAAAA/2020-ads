package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem2 {

  private enum Color {
    WHITE,
    GREY,
    BLACK
  }

  private static Color[] color;
  private static int min = Integer.MAX_VALUE;
  private static boolean isCycled = false;
  private static List<Integer> cycledVertexes = new ArrayList<>();
  private static int cycleStart;
  private static int cycleEnd;
  private static int[] parents;

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int k = in.nextInt();

    color = new Color[n];
    parents = new int[n];
    List<List<Integer>> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      color[i] = Color.WHITE;
    }

    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<>());
    }

    for (int i = 0; i < k; i++) {
      int x = in.nextInt() - 1;
      int y = in.nextInt() - 1;

      list.get(x).add(y);
    }

    findCycles(list);

    if (isCycled) {
      out.println("Yes");
      out.println(min + 1);
    } else {
      out.println(-1);
    }
  }

  private static void bfd(List<List<Integer>> list, int currentVertex) {

    color[currentVertex] = Color.GREY;

    for (int i : list.get(currentVertex)) {

      if (color[i] == Color.WHITE) {
        cycleEnd = i;
        parents[i] = currentVertex;
        bfd(list, i);
      }

      if (color[i] == Color.GREY) {
        cycleStart = i;
        isCycled = true;
//        cycledVertexes.add(i);
        return;
      }
    }
    color[currentVertex] = Color.BLACK;
  }

  private static void findCycles(List<List<Integer>> list) {
    for (int i = 0; i < list.size(); i++) {
      parents[i] = -1;
      bfd(list, i);
      kek();
      try {
        int tmpMin = cycledVertexes.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        min = Math.min(tmpMin, min);
      } catch (Exception e) {
      }
      cycledVertexes.clear();
      cycleStart = 0;
      cycleEnd = 0;
      parents = new int[list.size()];
    }
  }

  private static void kek() {
    int tmpCycleEnd = cycleEnd;
    while (tmpCycleEnd != parents[cycleStart]) {
      cycledVertexes.add(tmpCycleEnd);
      tmpCycleEnd = parents[tmpCycleEnd];
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
