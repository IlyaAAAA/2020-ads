package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem1 {

  private enum Color {
    WHITE,
    GREY,
    BLACK
  }

  private static Color[] color;
  private static boolean isCycled = false;
  private static final Deque<Integer> deque = new ArrayDeque<>();

  private static void solve(final FastScanner in, final PrintWriter out) {
    int vertexes = in.nextInt();
    int ribs = in.nextInt();
    color = new Color[vertexes];
    List<List<Integer>> list = new ArrayList<>();

    for (int i = 0; i < vertexes; i++) {
      color[i] = Color.WHITE;
    }

    for (int i = 0; i < vertexes; i++) {
      list.add(new ArrayList<>());
    }

    for (int i = 0; i < ribs; i++) {
      int x = in.nextInt() - 1;
      int y = in.nextInt() - 1;

      list.get(x).add(y);
    }

    topologicalSort(list);

    printRes(out);
  }

  private static void printRes(PrintWriter out) {
    if (isCycled) {
      out.println(-1);
    }
    else {
      while (!deque.isEmpty()) {
        out.print(deque.pollLast() + 1 + " ");
      }
    }
  }

  private static void bfd(List<List<Integer>> list, int index) {

    color[index] = Color.GREY;

    for (int i : list.get(index)) {

        if (color[i] == Color.WHITE) {
          bfd(list, i );
        }

        if (color[i] == Color.GREY) {
          isCycled = true;
          return;
        }
    }
    color[index] = Color.BLACK;
    deque.add(index);
  }


  private static void topologicalSort(List<List<Integer>> list) {
    for (int i = 0; i < list.size(); i++) {
      if (color[i] == Color.WHITE) {
        bfd(list, i);
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

