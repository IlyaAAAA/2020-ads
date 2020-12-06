package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.*;

public class Problem5 {

  private static int[] currentLength;
  private static boolean[] visited;
  private static int[] parentsMin;
  private static final Deque<Integer> deque = new ArrayDeque<>();

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int m = in.nextInt();

    List<List<Integer>> list = new ArrayList<>();
    currentLength = new int[n];
    visited = new boolean[n];
    parentsMin = new int[n];

    final int vertexFrom = in.nextInt() - 1;
    final int vertexTo = in.nextInt() - 1;

    for (int i = 0; i < n; i++) {
      list.add(new ArrayList<>());
      currentLength[i] = Integer.MAX_VALUE;
    }

    for (int i = 0; i < m; i++) {
      int from = in.nextInt() - 1;
      int to = in.nextInt() - 1;

      list.get(from).add(to);
      list.get(to).add(from);
    }

    findMinLength(list, vertexFrom);

    if (currentLength[vertexTo] != Integer.MAX_VALUE) {
      out.println(currentLength[vertexTo]);
      findWay(vertexFrom, vertexTo);
      printWay(out);
    } else {
      out.println(-1);
    }
  }

  private static void findMinLength(List<List<Integer>> list, int vertexFrom) {
    currentLength[vertexFrom] = 0;
    deque.addLast(vertexFrom);
    visited[vertexFrom] = true;

    while (!deque.isEmpty()) {
      int current = deque.pollFirst();
      List<Integer> listTo = list.get(current);
      for (Integer i : listTo) {
        if (currentLength[current] + 1 < currentLength[i]) {
          currentLength[i] = currentLength[current] + 1;
          parentsMin[i] = current;
        }
        if (!visited[i]) {
          visited[i] = true;
          deque.addLast(i);
        }
      }
    }
  }

  private static void findWay(int vertexFrom, int vertexTo) {
    deque.clear();
    while (vertexTo != vertexFrom) {
      deque.addFirst(vertexTo + 1);
      vertexTo = parentsMin[vertexTo];
    }
    deque.addFirst(vertexTo + 1);
  }

  private static void printWay(final PrintWriter out) {
    while (!deque.isEmpty()) {
      out.print(deque.pollFirst() + " ");
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
