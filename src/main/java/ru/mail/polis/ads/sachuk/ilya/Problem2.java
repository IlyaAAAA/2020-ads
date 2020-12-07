package ru.mail.polis.ads.sachuk.ilya;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

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

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int k = in.nextInt();

    color = new Color[n];
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

    bfd(list, 0);

    if (isCycled) {
      out.println("Yes");
      out.println(cycledVertexes.stream()
              .min(Comparator.comparing(Integer::valueOf))
              .get() + 1);
    }
    else {
      out.println(-1);
    }
  }


//  private static void bfd(int[][] arr, int index) {
//
//    if (visited[index]) {
//      return;
//    }
//    System.out.println(index);
//
//    visited[index] = true;
//
//    for (int i = 0; i < arr.length; i++) {
//      if (arr[index][i] == 1) {
//        bfd(arr, i);
//      }
//    }
//  }

  private static void bfd(List<List<Integer>> list, int index) {

    color[index] = Color.GREY;

    for (int i : list.get(index)) {

      if (color[i] == Color.WHITE) {
        bfd(list, i );
      }

      if (color[i] == Color.GREY) {
        isCycled = true;
        cycledVertexes.add(i);
        return;
      }
    }
    color[index] = Color.BLACK;
  }


//6 6
//1 2
//2 3
//3 4
//4 5
//5 2
//4 6

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
