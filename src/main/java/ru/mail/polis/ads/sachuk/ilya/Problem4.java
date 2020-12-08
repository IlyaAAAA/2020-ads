package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem4 {

  private static class Rib {
    int vertexTo;
    int value;

    public Rib(int vertexTo, int value) {
      this.vertexTo = vertexTo;
      this.value = value;
    }
  }

  private static int[] d;
  private static boolean[] visited;
  private static int[] parentsMin;
  private static final Queue<Integer> queue = new ArrayDeque<>();
  private static final Stack<Integer> stack = new Stack<>();

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();

    List<List<Rib>> list = new ArrayList<>();
    d = new int[n];
    visited = new boolean[n];
    parentsMin = new int[n];

    final int vertexFrom = in.nextInt() - 1;
    final int vertexTo = in.nextInt() - 1;

    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<>());
      d[i] = Integer.MAX_VALUE;
    }

    for (int i = 0; i < m; i++) {
      int from = in.nextInt() - 1;
      int to = in.nextInt() - 1;
      int value = in.nextInt();

      list.get(from).add(new Rib(to, value));
      list.get(to).add(new Rib(from, value));
    }

    findMinLength(list, vertexFrom);

    if (d[vertexTo] != Integer.MAX_VALUE) {
      out.println(d[vertexTo]);
      findWay(vertexTo);
      printWay(out);
    } else {
      out.println(-1);
    }
  }

  private static void findMinLength(List<List<Rib>> list, int vertexFrom) {
    d[vertexFrom] = 0;
    parentsMin[vertexFrom] = -1;
    queue.add(vertexFrom);

    while (!queue.isEmpty()) {
      int i = queue.poll();
      visited[i] = true;
      List<Rib> ribs = list.get(i);
      for (Rib rib : ribs) {
        if (d[i] + rib.value < d[rib.vertexTo]) {
          d[rib.vertexTo] = d[i] + rib.value;
          parentsMin[rib.vertexTo] = i;
        }
        if (!visited[rib.vertexTo]) {
          queue.add(rib.vertexTo);
        }
      }
    }
  }


  private static void findWay(int vertexTo) {
    while (vertexTo != -1) {
      stack.add(vertexTo + 1);
      vertexTo = parentsMin[vertexTo];
    }
  }

  private static void printWay(final PrintWriter out) {
    while (!stack.isEmpty()) {
      out.print(stack.pop() + " ");
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
